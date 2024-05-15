/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.model;
import java.util.List;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;
import se.kth.iv1350.seminar4.model.dto.ReceiptDTO;

/**
 * The class used to manage a new Sale.
 * @author nilse
 */
public class Sale {
    private Receipt receipt;
    private int customerId;

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
     * @param item The item to add.
     * @param amount The number of items to add.
     * @return A string containing information about the sale and the item.
     */
    public String addItemToReceipt(Item item, int amount){
        String displayInfo = "";
        if (receipt.itemExists(item)){
            receipt.increaseItemAmount(item.getItemId(), amount);
        } else{
            item.increaseAmount(amount);
            receipt.addItemToReceipt(item);
        }
        if (amount > 1){
            displayInfo = "Add " + amount + " items with item id " + item.getItemId() + ": \n";
        } else{
            displayInfo = "Add " + amount + " item with item id " + item.getItemId() + ": \n";
        }
                
        displayInfo += "Item Id: " + item.getItemId() + "\n" + "Item Name: " + item.getItemName() + "\n" + 
                "VAT: " + (int)(item.getVAT()*100) + "% \n" + "Item Cost: " + item.getPrice() + " SEK \n" + 
                "Item description: " + item.getItemDescription() + "\n\n" + "Running Total (incl VAT): " + 
                receipt.getCurrentCost() + " SEK \n" + "Total VAT: " + receipt.getTotalVAT() + " SEK\n";
        
        return displayInfo;
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
     */
    public ReceiptDTO payment(int payment){
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
