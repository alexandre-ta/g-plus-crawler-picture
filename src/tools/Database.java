/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre
 */
public class Database {
    
    /**
     * Log users id
     */
    private PrintWriter out_database = null;

    /**
     * Private constructor
     */
    private Database() {
        try {
            out_database = new PrintWriter(new FileWriter(config.Config.FILENAME_DATABASE, true));
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
        private final static Database instance = new Database();
    }

    /**
     * Access point to the single instance of the singleton
     */
    public static Database getInstance() {
        return Database.SingletonHolder.instance;
    }

    /**
     * Write a line into corresponding log file
     * @param type
     * @param value
     * @param information
     * @param newLine 
     */
    public synchronized void write(String value) {
        out_database.print(value);
        out_database.flush();
    }
}
