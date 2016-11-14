/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.google.common.io.Files;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import model.UserModel;

/**
 * IO Tools
 *
 * @author Alexta
 */
public class Tools {

    /**
     * Add list of users into file
     *
     * @param users
     * @param fileName
     */
    public void writeUsersIdIntoFile(List<UserModel> users, String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(fileName, true));
            for (int i = 0; i < users.size(); i++) {
                out.println(users.get(i).getId());
            }
            out.close();
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.USER, fileName, ex.getMessage());
        } finally {
            out.close();
        }
    }

    /**
     * Save photo into server
     *
     * @param urlPhoto
     * @param urlServer
     */
    public boolean savePhotoIntoServer(String urlPhoto, String urlServer, String information) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        FileOutputStream fos = null;
        try {
            // read photo
            URL url = new URL(urlPhoto);
            in = new BufferedInputStream(url.openStream());
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            // save photo into folder
            createFile(urlServer);
            fos = new FileOutputStream(urlServer);
            fos.write(response);
            fos.close();
            // Save photo contents into log file
            Database.getInstance().write(information);
            return true;
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, urlPhoto, ex.getMessage());
            return false;
        }
    }

    /**
     * Substrat set of id between two files
     *
     * @param fileName1
     * @param fileName2
     */
    public List<String> subtractFiles(String fileUsersId, String fileLog) {
        List<String> listUsersId = getListFromFile(fileUsersId);
        List<String> listProcessedUsers = getListFromFile(fileLog);
        listUsersId.removeAll(listProcessedUsers);
        return listUsersId;
    }

    /**
     * Serialize a file into a list of string
     *
     * @param fileName
     * @return
     */
    public List<String> getListFromFile(String fileName) {
        BufferedReader in = null;
        List<String> list = new ArrayList<String>();
        String line;
        try {
            in = new BufferedReader(new FileReader(fileName));
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, fileName, ex.getMessage());
        } finally {
            try {
                in.close();
            } catch (Exception ex) {
                LogFile.getInstance().write(LogFile.TypeLog.ERROR, fileName, ex.getMessage());
            }
        }
        return list;
    }
    
    public static String createMD5byFilename(String md5) {
        StringBuffer sb = null;
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
             LogFile.getInstance().write(LogFile.TypeLog.ERROR, md5, e.getMessage());
         }
         return null;
     }

    /**
     * Save a list as a file
     *
     * @param list
     * @param fileName
     */
    public void saveFileFromList(List<String> list, String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(fileName));
            for (int i = 0; i < list.size(); i++) {
                out.println(list.get(i));
            }
            out.close();
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, fileName, ex.getMessage());
        } finally {
            out.close();
        }
    }

    /**
     * Create a file if it doesn't exist
     *
     * @param file
     */
    public void createFile(String file) {
        try {
            File f = new File(file);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, file, ex.getMessage());
        }
    }

    /**
     * Check if a file exists
     *
     * @param file
     * @return
     */
    public boolean fileExists(String file) {
        File f = new File(file);
        return f.exists();
    }

    /**
     * Check if the file is empty
     *
     * @param file
     * @return
     */
    public boolean fileEmpty(String file) {
        File f = new File(file);
        return f.length() == 0;
    }

    /**
     * Create a directory
     *
     * @param dir
     */
    public void createDirectory(String dir) {
        File theDir = new File(dir);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("Create directory : " + dir);
            theDir.mkdir();
        }
    }

    /**
     * Clear a file
     *
     * @param fileName
     */
    public void clearFile(String fileName) {
        deleteFile(fileName);
        createFile(fileName);
    }

    /**
     * Delete a file
     *
     * @param fileName
     */
    public void deleteFile(String fileName) {
        File myFile = new File(fileName);
        myFile.delete();
    }

    /**
     * Count line
     *
     * @param fileName
     * @return
     */
    public int countLines(String fileName) {
        FileInputStream fis = null;
        int count = 0;
        try {
            fis = new FileInputStream(fileName);
            LineNumberReader l = new LineNumberReader(new BufferedReader(new InputStreamReader(fis)));
            while (l.readLine() != null) {
                count = l.getLineNumber();
            }
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, fileName, ex.getMessage());
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
                LogFile.getInstance().write(LogFile.TypeLog.ERROR, fileName, ex.getMessage());
            }
        }
        return count;
    }

    /**
     * Get extension file
     *
     * @param fileName
     * @return
     */
    public String getExtensionFromFilename(String fileName) {
        String ext = Files.getFileExtension(fileName);
        return "." + ext;
    }
}
