/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    
    public void log(Exception e){
        String message = e.getMessage() + " Exception Thrown: " + time();
        logFile.println(message);
        e.printStackTrace(logFile);
    }
    
    private String time(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        return dateTime.format(formatter);
    }
    
    
}
