/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Name Tools
 * @author alexandre
 */
public class NameTools {

    /**
     * Name file
     */
    private String nameFile;
    
    /**
     * Buffered reader
     */
    private BufferedReader in = null;

    /**
     * Constructor
     * @param nameFile 
     */
    public NameTools(String nameFile) {
        this.nameFile = nameFile;
        try {
            in = new BufferedReader(new FileReader(this.nameFile));
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, nameFile, ex.getMessage());
        }
    }

    /**
     * Get next name
     * @return
     * @throws IOException 
     */
    public String getNext() throws IOException {
        return in.readLine();
    }

    /**
     * Close the file
     */
    public void close() {
        try {
            in.close();
        } catch (Exception ex) {
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, nameFile, ex.getMessage());
        }
    }
}
