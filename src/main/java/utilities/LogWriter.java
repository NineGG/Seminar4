/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Nils Ekenberg
 */
public class LogWriter {
    private static final String LOG_FILE_NAME = "log.txt";
    private PrintWriter logFile;
    
    
    
    public LogWriter() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
    }
}
