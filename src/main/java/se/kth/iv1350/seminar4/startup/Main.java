/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package se.kth.iv1350.seminar4.startup;

import java.io.IOException;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.view.View;

/**
 * The main class, contains the code for the startup of the application.
 * @author nilse
 */
public class Main {

    /**
     * Starts the application.
     * @param args Arguments, not used in this application.
     */
    public static void main(String[] args) {
        
        try {
            Controller contr = new Controller();
            View view = new View(contr);
        } catch (IOException e) {
            System.out.println("Application failed to start");
        }
    }
}
