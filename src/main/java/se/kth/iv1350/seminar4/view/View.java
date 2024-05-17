/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.model.dto.ReceiptDTO;
import se.kth.iv1350.seminar4.integration.NoMatchingItemByIdException;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;
import se.kth.iv1350.seminar4.model.dto.SaleStateDTO;

/**
 * A simple View Class. PULL TEST CHANGE
 * @author nilse
 */
public class View{
    private Controller contr;
    
    private ReceiptDTO receipt;
    
    private SaleStateDTO saleState;
    
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
            saleState = contr.addItem(423);
            System.out.println("Added " + saleState.getItemDTO().getItemAmount() + 
                    " " + saleState.getItemDTO().getItemName() + " item To sale" );
            System.out.println("Current running total: " + saleState.getRunningTotal());
            
            System.out.println();
            
            saleState = contr.addItem(231,3);
            System.out.println("Added " + saleState.getItemAmountChange() + 
                    " " + saleState.getItemDTO().getItemName() + " item's To sale" );
            System.out.println("Current running total: " + saleState.getRunningTotal());
            
            System.out.println();
            
        } catch (NoMatchingItemByIdException e) {
            
            System.out.println("System could not find an item with the id: " + e.getItemId());
            //reminder to add functionality to log to file
        } catch (Exception e) {
            
        }
        
        double endOfSaleCost = contr.endSale();
        
        System.out.println("Ending sale, to complete sale please pay " + endOfSaleCost + " SEK");
        
        String dateAndTimeString;
        
        LocalDateTime dateAndTime;
        
        
        
        try {
            receipt = contr.payment(500);
            
            dateAndTime = receipt.getDateAndTime();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
            dateAndTime.format(formatter);
            dateAndTimeString = dateAndTime.toString();
            
            System.out.println(dateAndTimeString);
            
            String[] dateAndTimeSplit1 = dateAndTimeString.split("T");
            String[] dateAndTimeSplit2 = dateAndTimeSplit1[1].split("\\.");
        
            dateAndTimeString = dateAndTimeSplit1[0] + " " + dateAndTimeSplit2[0];
            
            System.out.println("\n------------------ Begin receipt -------------------\n"
                + "Time of Sale: " + dateAndTimeString);
            System.out.println();
        
            for (ItemDTO item : receipt.getItemList()){
                System.out.println(item.getItemName() + "\t " + item.getItemAmount() + " x " + item.getPrice()
                        + " SEK");
            }
        
            System.out.println("\nTotal: \t" + receipt.getCostAfterDiscount() + " SEK\nVAT: \t" + receipt.getTotalVAT() + " SEK" + 
                    "\n------------------ End receipt -------------------\n\n" +
                    "Change to give to the customer: " + receipt.getChange() + " SEK");
            
        } catch (Exception e) {
            
        }
    }
    
}
