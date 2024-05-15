/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.controller;
import se.kth.iv1350.seminar4.model.Sale;
import se.kth.iv1350.seminar4.model.Item;
import se.kth.iv1350.seminar4.integration.ExternalInventorySystemAccessPoint;
import java.util.List;
import se.kth.iv1350.seminar4.integration.ExternalAccountingSystemAccessPoint;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;
import se.kth.iv1350.seminar4.model.dto.ReceiptDTO;
import se.kth.iv1350.seminar4.integration.NoMatchingItemByIdException;

/**
 * A controller class.
 * @author nilse
 */
public class Controller {
    private Sale sale; //might be better for it to be an list of sale objects.
    private ExternalInventorySystemAccessPoint externInv = new ExternalInventorySystemAccessPoint();
    private ExternalAccountingSystemAccessPoint accounting = new ExternalAccountingSystemAccessPoint();
    
    /**
     * Starts a new sale.
     */
    public void startSale(){
        this.sale = new Sale();
    }
    
    /**
     * Ends the sale.
     * 
     * @return gets running total.
     */
    public double endSale(){
        return sale.getRunningTotal();
    }
    
    
    /**
     * A simple payment processing method.
     * 
     * @param money The amount paid in SEK.
     * 
     * @return A string representing the receipt and the change
     */
    public ReceiptDTO payment(int money){
        ReceiptDTO printReceipt = sale.payment(money);
        List<ItemDTO> itemList = sale.getItemList();
        ReceiptDTO saleInfo = sale.getSaleInfo();
        accounting.updateAccounting(saleInfo);
        externInv.updateInventory(itemList);
        return printReceipt;
    }
    
    /**
     * Adds an item to the sale.
     * 
     * @param itemId The id of the item.
     * 
     * @return A string containing information about the item and current total of the sale.
     * @throws NoMatchingItemByIdException When no item with the provided id is found.
     */
    public String addItem(int itemId) throws NoMatchingItemByIdException {
        Item item = externInv.retrieveItem(itemId);
        return sale.addItemToReceipt(item, 1);
    }
    
    /**
     * Adds items to the sale.
     * 
     * @param itemId The id of the item.
     * @param amount The amount of said item.
     * 
     * @return A string containing information about the item and current total of the sale.
     * @throws NoMatchingItemByIdException When no item with the provided id is found.
     */
    public String addItem(int itemId, int amount) throws NoMatchingItemByIdException {
        Item item = externInv.retrieveItem(itemId);
        return sale.addItemToReceipt(item, amount);
    }
}
