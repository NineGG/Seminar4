/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.kth.iv1350.seminar4.controller.ActionFailedException;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.integration.DatabaseUnresponsiveException;
import se.kth.iv1350.seminar4.integration.ItemInventoryResultLessThanZeroException;
import se.kth.iv1350.seminar4.model.dto.ReceiptDTO;
import se.kth.iv1350.seminar4.integration.NoMatchingItemByIdException;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;
import se.kth.iv1350.seminar4.model.dto.SaleStateDTO;

/**
 * A simple View Class.
 * @author nilse
 */
public class View{
    private Controller contr;
    
    private ReceiptDTO receipt;
    
    private SaleStateDTO saleState;
    
    /**
     * Creates a View object.
     * @param contr A controller.
     */
    public View(Controller contr){
        this.contr = contr;
        fakeCustomer();
    }
    
    private void saleStatePrinter(SaleStateDTO saleState) {
        
        if (saleState.getItemDTO().getItemAmount() > 1){
            System.out.println("Added " + saleState.getItemDTO().getItemAmount() + 
                    " " + saleState.getItemDTO().getItemName() + " items to sale" );
            System.out.println("Current running total: " + saleState.getRunningTotal() + " SEK");

            System.out.println();
        } else {
            System.out.println("Added " + saleState.getItemDTO().getItemAmount() + 
                        " " + saleState.getItemDTO().getItemName() + " item to sale" );
            System.out.println("Current running total: " + saleState.getRunningTotal() + " SEK");

            System.out.println();
        }
    }
    
    private void receiptPrinter(ReceiptDTO receipt) {
        String dateAndTimeString;
        LocalDateTime dateAndTime;
        
        dateAndTime = receipt.getDateAndTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        dateAndTimeString = dateAndTime.format(formatter);
        

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
        
    }
    
    
    /**
     * Fake customer interaction.
     */
    private void fakeCustomer(){
        
        contr.startSale();
        
        try {
            saleState = contr.addItem(423);
            saleStatePrinter(saleState);
            
            saleState = contr.addItem(231,3);
            saleStatePrinter(saleState);
            
            saleState = contr.addItem(123,3);
            saleStatePrinter(saleState);
            
        } catch (ActionFailedException e) {
            System.out.println("Action could not be performed");
        }
        
        System.out.println();
        
        double endOfSaleCost = contr.endSale();
        
        System.out.println("Ending sale, to complete sale please pay " + endOfSaleCost + " SEK");
        
        
        
        try {
            receipt = contr.payment(500);
            receiptPrinter(receipt);
        } catch (ItemInventoryResultLessThanZeroException e) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
