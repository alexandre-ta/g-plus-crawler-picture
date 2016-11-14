/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googlepluscrawler;

import tools.Tools;
import java.util.List;
import model.ActivityModel;
import model.AttachmentsModel;
import model.UserModel;
import process.GoogleCrawlerAPI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import tools.LogFile;
import tools.NameTools;

/**
 * Process class containing all main methods
 *
 * @author Alexta
 */
public class Process {

    /**
     * Google tools
     */
    private GoogleCrawlerAPI gCrawlerApi;
    /**
     * IO tools
     */
    private Tools iotool;
    /**
     * Name tools
     */
    private NameTools nameTools;

    /**
     * Constructor
     */
    public Process() {
        gCrawlerApi = new GoogleCrawlerAPI();
        iotool = new Tools();
        nameTools = new NameTools(config.Config.FILENAME_NAMES);
    }

    /**
     * Add users id to file
     */
    public void addUsersIdToFile() {
        String line = "";
        List<UserModel> users;
        try {
            while ((line = nameTools.getNext()) != null) {
                users = gCrawlerApi.getUserIdByName(line);
                iotool.writeUsersIdIntoFile(users, config.Config.FILENAME_USERS_ID);
                Thread.sleep(10);
            }
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, line, ex.getMessage());
        } finally {
            nameTools.close();
        }
    }

    /**
     * Process photo by user id save photo in server save photo log with file
     * name, content and like count
     *
     * @param fileName
     */
    public void processPhotoByUsersId(String userId) {
        int countPhoto = 0, countLikes = 0;
        String urlPhoto, ext, idPhoto, urlServer, fileName, value;
        boolean isEnded = false;
        try {
            System.out.println("Process userID : " + userId);
            // Add user id in log file
            LogFile.getInstance().write(LogFile.TypeLog.USER, "", userId + "\n");
            
            List<ActivityModel> activities = gCrawlerApi.getActivityIdByUserId(userId);
            
            for (int i = 0; i < activities.size() && !isEnded; i++) {
                ActivityModel activity = activities.get(i);
                if (activity.getObject()!= null && activity.getObject().getAttachments() != null) {
                    List<AttachmentsModel> attachments = activity.getObject().getAttachments();
                    
                    for (int j = 0; j < attachments.size() && !isEnded; j++) {
                        AttachmentsModel attachment = attachments.get(j);
                        if (attachment.getObjectType().equals("photo")) {
                       
                            // Process photo
                            urlPhoto = attachment.getFullImage().getUrl();
                            ext = iotool.getExtensionFromFilename(urlPhoto);
                            idPhoto = attachment.getId().toString();
                            
                            if(!idPhoto.contains(".com")){
                                idPhoto = idPhoto.substring(22, idPhoto.length());
                                
                                // check if the extension doesn't contain .com
                                if(ext.contains(".com"))
                                {
                                    ext = ".jpg";
                                }
       
                                fileName = tools.Tools.createMD5byFilename(idPhoto);
                                urlServer = config.Config.URI_DIR_IMGS + fileName + ext;
                                // get number of likes for this photo
                                countLikes = activity.getObject().getPlusoners().getTotalItems();
                                // create the line in the database
                                value = urlPhoto + "\t" + fileName + ext + "\t" + countLikes + "\n";
                                // save photo into local server
                                if(iotool.savePhotoIntoServer(urlPhoto, urlServer, value))
                                {
                                   countPhoto++; 
                                   // check if the number of photos is under the limit
                                   if(countPhoto >= config.Config.LIMIT_PHOTO)
                                   {
                                       isEnded = true;
                                   }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error : " + userId + " " + ex.toString());
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, userId, ex.getMessage());
        }
    }

    /**
     * Start processing
     *
     * @throws InterruptedException
     */
    public void startProcess(List<String> usersId) throws InterruptedException {
        // Get the number of line in the file
        int nbLines = usersId.size();
        int frame = nbLines / config.Config.NB_THREADS;
        int start = 0;
        int end = start + frame;

        ExecutorService es;
        es = Executors.newFixedThreadPool(config.Config.NB_THREADS);

        // Split into different threads
        if (config.Config.NB_THREADS == 1) {
            System.out.println("Thread count : 1");
            ProcessThread myThread = new ProcessThread(usersId, 0, nbLines, 1);
            es.execute(myThread);
            es.shutdown();
        } else {
            System.out.println("Threads count : " + config.Config.NB_THREADS);
            // Error if the number of threads greater or equal 2 and number of user ID is 1
            if(nbLines == 1)
            {
                LogFile.getInstance().write(LogFile.TypeLog.ERROR, "-", "Number of user ID need to be greater than 1 if the number of threads is greater than 1 too.");
            } else {
                for (int i = 0; i < config.Config.NB_THREADS; i++) {

                    ProcessThread myThread = new ProcessThread(usersId, start, end, i);
                    es.execute(myThread);
                    if ((i + 2) == config.Config.NB_THREADS) {
                        start = end;
                        end = nbLines;
                    } else {
                        start = end;
                        end = end + frame;
                    }
                }
                es.shutdown();                
            }
        }
    }

    /**
     * Thread
     */
    public class ProcessThread extends Thread {

        private List<String> list;
        private int startLine, endLine;

        public ProcessThread(List<String> list, int startLine, int endLine, int idThread) {
            this.list = list;
            this.startLine = startLine;
            this.endLine = endLine;
        }

        @Override
        public void run() {
            for (int i = startLine; i < endLine; i++) {
                processPhotoByUsersId(list.get(i));
            }
        }
    }
}
