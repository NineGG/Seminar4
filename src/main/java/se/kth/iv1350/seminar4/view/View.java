/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.view;

import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.model.dto.ReceiptDTO;
import se.kth.iv1350.seminar4.integration.NoMatchingItemByIdException;

/**
 * A simple View Class.
 * @author nilse
 */
public class View{
    private Controller contr;
    
    private ReceiptDTO receipt;
    
    public View(Controller contr){
        this.contr = contr;
        fakeCustomer();
    }
    
    
    /**
     * Fake customer interaction.
     */
    private void fakeCustomer(){
        
        contr.startSale();
        
        try {
            System.out.println(contr.addItem(423));
            System.out.println(contr.addItem(231,3));
            System.out.println(contr.endSale());
            receipt = contr.payment(500);
        } catch (NoMatchingItemByIdException e) {
            System.out.println("System could not find an item with the id: " + e.getItemId());
            //reminder to add functionality to log to file
        } catch (Exception e) {
            
        }
    }
    
}
