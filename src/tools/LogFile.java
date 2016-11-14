/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Log File
 * @author alexandre
 */
public class LogFile {

    /**
     * Log users id
     */
    private PrintWriter out_users = null;
    
    /**
     * Log errors
     */
    private PrintWriter out_errors = null;

    /**
     * Log type
     */
    public enum TypeLog {
        USER, ERROR
    };

    /**
     * Private constructor
     */
    private LogFile() {
        try {
            out_users = new PrintWriter(new FileWriter(config.Config.URI_DIR_LOGS + config.Config.FILENAME_LOG_USERS, true));
            out_errors = new PrintWriter(new FileWriter(config.Config.URI_DIR_LOGS + config.Config.FILENAME_LOG_ERRORS, true));
        } catch (IOException ex) {
            Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Holder
     */
    private static class SingletonHolder {

        /**
         * Single instance not reset
         */
        private final static LogFile instance = new LogFile();

    }

    /**
     * Access point to the single instance of the singleton
     */
    public static LogFile getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Write a line into corresponding log file
     * @param type
     * @param value
     * @param information
     * @param newLine 
     */
    public synchronized void write(TypeLog type, String value, String information) {
        String timeStamp = new SimpleDateFormat("[HH:mm:ss yyyy/MM/dd]").format(Calendar.getInstance().getTime());
        String str = timeStamp + "," + value + "," + information;
        str += "\n";
        switch (type) {
            case USER:
                out_users.print(information);
                break;
            case ERROR:
                out_errors.print(str);
                break;
        }
        out_users.flush();
        out_errors.flush();
    }
}
