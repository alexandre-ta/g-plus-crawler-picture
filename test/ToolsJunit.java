/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.NameTools;
import tools.Tools;

/**
 *
 * @author Alexta
 */
public class ToolsJunit {
    
    /**
     * IO Tools
     */
    public static Tools ioTool = new Tools();

    /**
     * Folder contains test files
     */
    private static final String urlDir = "UNIT_TEST/";

    private static final String file_log = urlDir + "test_user_log.txt";
    private static final String file_ids = urlDir + "test_user_ids.txt";
    
    /**
     * Test substract method
     */
    @Test
    public void testSubstract() {
        List<String> test = ioTool.subtractFiles(file_ids, file_log);
        assertEquals(test.size(), 6);
        //
        assertEquals(test.get(0), "116786475217922291839");
        assertEquals(test.get(1), "107929953801290113446");
        assertEquals(test.get(2), "103987063927856226370");
        assertEquals(test.get(3), "112275133317653212316");
        assertEquals(test.get(4), "113356637750577112246");
        assertEquals(test.get(5), "107133727723755872340");
    }
    
    /**
     * Test NameTools class
     */
    @Test
    public void testNameTools()
    {
        NameTools nameTools = new NameTools(file_log);
        try {
            assertEquals(nameTools.getNext(), "116810324410520928492");
            assertEquals(nameTools.getNext(), "102383438524250873399");
            assertEquals(nameTools.getNext(), "104778163660256216181");
            assertEquals(nameTools.getNext(), "109933675709798946009");
            assertEquals(nameTools.getNext(), "111908559430723967744");
        } catch (IOException ex) {
            Logger.getLogger(ToolsJunit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test CopyFileToList method
     */
    @Test
    public void testCopyFileToList()
    {
        try {
            String fileTmp = "UNIT_TEST/tmp.txt";
            List<String> test = new ArrayList<>();
            test.add("1");
            test.add("2");
            test.add("3");
            test.add("4");
            test.add("5");
            ioTool.createFile(fileTmp);
            assertEquals(ioTool.fileExists(fileTmp), true);
            ioTool.saveFileFromList(test, fileTmp);
            //
            NameTools nameTools = new NameTools(fileTmp);
            assertEquals(nameTools.getNext(), "1");
            assertEquals(nameTools.getNext(), "2");
            assertEquals(nameTools.getNext(), "3");
            assertEquals(nameTools.getNext(), "4");
            assertEquals(nameTools.getNext(), "5");
            //
            ioTool.deleteFile(fileTmp);
            //
            assertEquals(ioTool.fileExists(fileTmp), false);
        } catch (IOException ex) {
            Logger.getLogger(ToolsJunit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test count lines in file
     */
    @Test
    public void testCountLines()
    {
        assertEquals(ioTool.countLines(file_log),5);
        assertEquals(ioTool.countLines(file_ids),11);  
    }

}