package config;

import java.io.File;

/**
 * Configuration file
 * @author Alexta
 */
public class Config {
    
    // Separator character
    public static String SEPARATE = File.separator;

    // File containing list of users
    public static String FILENAME_USERS_ID = "list_users.txt";
    // File containing list of names
    public static String FILENAME_NAMES = "names.txt";
    // File containing list of names
    public static String FILENAME_DATABASE = "database.txt";
    
    // Directory containing photos
    public static String URI_DIR_IMGS = "."+SEPARATE+"IMGS"+SEPARATE;
    // Directory containing logs
    public static String URI_DIR_LOGS = "."+SEPARATE+"LOGS"+SEPARATE;
    // Log containing users id and photos id
    public static String FILENAME_LOG_USERS = "log_users.txt";
    // Log containing errors occurs during execution
    public static String FILENAME_LOG_ERRORS = "log_errors.txt";
    // Google plus url
    public static String URL_G_PEOPLE = "https://www.googleapis.com/plus/v1/people";
    // API Key
    public static String API_KEY = "";
    // Limit photos count
    public static int LIMIT_PHOTO = 5000;
    // Number of threads
    public static int NB_THREADS = 2;

}
