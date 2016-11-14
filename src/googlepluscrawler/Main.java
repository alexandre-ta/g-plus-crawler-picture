/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googlepluscrawler;

import java.util.List;
import tools.Tools;

/**
 * Main
 *
 * @author Alexta
 */
public class Main {

    /**
     * IO Tools
     */
    private static Tools iotool;
    /**
     * Process
     */
    private static Process process;

    /**
     * Initialize all files and folders and retrieve all user ids by names
     */
    public static void initialize() {
        // check if users file exists
        iotool.createDirectory(config.Config.URI_DIR_IMGS);
        
        iotool.createDirectory(config.Config.URI_DIR_LOGS);

        // Create files for logs users and errors   
        iotool.createFile(config.Config.URI_DIR_LOGS + config.Config.FILENAME_LOG_USERS);
        iotool.createFile(config.Config.URI_DIR_LOGS + config.Config.FILENAME_LOG_ERRORS);

        // Create database file
        iotool.createFile(config.Config.FILENAME_DATABASE);
        
        if (!iotool.fileExists(config.Config.FILENAME_USERS_ID)) {
            // Create file
            iotool.createFile(config.Config.FILENAME_USERS_ID);
            // Load all users id into file
            process.addUsersIdToFile();
        }
    }

    /**
     * Run this method after a crash
     */
    public static List<String> backup() {
        List<String> userIds = iotool.subtractFiles(config.Config.FILENAME_USERS_ID, config.Config.URI_DIR_LOGS + config.Config.FILENAME_LOG_USERS);
        return userIds;
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        iotool = new Tools();
        if (!iotool.fileExists(config.Config.FILENAME_NAMES)) {
            System.out.println("Error : users name file doesn't exist. Please check if the file is located in the folder containing the program.");
        } else {
            System.out.println(" ----- Start ---- ");
            process = new Process();
            try {
                initialize();
                process.startProcess(backup());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
