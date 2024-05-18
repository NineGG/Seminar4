/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.model;
import java.util.List;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;
import se.kth.iv1350.seminar4.model.dto.*;

/**
 * The class used to manage a new Sale.
 * @author nilse
 */
public class Sale {
    private Receipt receipt;

    /**
     * Creates a Sale instance and a Receipt instance to go with it.
     */
    public Sale(){        
        receipt = new Receipt();
    }
    /**
     * Adds an item to the receipt, 
     * if the item already is on the receipt, increase that items amount instead.
     * 
     * @param itemDTO The item to add.
     * @return A DTO containing information about the sale and the item.
     */
    public SaleStateDTO addItemToReceipt(ItemDTO itemDTO){
        if (receipt.itemExists(itemDTO)){
            return receipt.increaseItemAmount(itemDTO);
        } else{
            return receipt.addItemToReceipt(itemDTO);
        }
    }
    
    /**
     * Gets the items stored in the current sale.
     * 
     * @return A list of ItemDTO objects from the current sale.
     */
    public List<ItemDTO> getItemList(){
        return receipt.getItemList();
    }
    
    
    /**
     * Gets the running total of the current sale.
     * 
     * @return An int representing the running total of the current Sale.
     */
    public double getRunningTotal(){
        return receipt.getCurrentCost();
    }
    
    
    /**
     * Some payment logic.
     * 
     * @param payment The paid amount in SEK.
     * @return A string representing the receipt and the change.
     * @throws InsufficientPaymentException When payment is insufficient.
     */
    public ReceiptDTO payment(int payment) throws InsufficientPaymentException{
        receipt.payment(payment);
        return receipt.getReceiptDTO();
    }
    
    /**
     * Get sale ingo.
     * @return An ReceiptDTO object that contains all relevant information about the sale.
     */
    public ReceiptDTO getSaleInfo(){
        return new ReceiptDTO(receipt);
    }
    
    
    
    
    
    
}
