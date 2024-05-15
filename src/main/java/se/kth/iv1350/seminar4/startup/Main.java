/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package se.kth.iv1350.seminar4.startup;

import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.view.View;

/**
 *
 * @author nilse
 */
public class Main {

    public static void main(String[] args) {
        
        Controller contr = new Controller();
        
        View view = new View(contr);
    }
}
