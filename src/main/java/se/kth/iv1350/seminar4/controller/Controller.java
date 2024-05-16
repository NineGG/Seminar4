/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.controller;
import se.kth.iv1350.seminar4.model.Sale;
import se.kth.iv1350.seminar4.integration.*;
import java.util.List;
import se.kth.iv1350.seminar4.integration.dto.*;
import se.kth.iv1350.seminar4.model.dto.*;;

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
     * @throws ItemInventoryResultLessThanZeroException thrown if the inventory result in a lower stock than 0.
     */
    public ReceiptDTO payment(int money) throws ItemInventoryResultLessThanZeroException{
        ReceiptDTO printReceipt = sale.payment(money);
        List<ItemDTO> itemList = sale.getItemList();
        ReceiptDTO saleInfo = sale.getSaleInfo();
        accounting.updateAccounting(saleInfo);
        try{
            externInv.updateInventory(itemList);
        } catch (ItemNotFoundInInventoryException e){
            //log and convert to other error type.
        }
        return printReceipt;
    }
    
    /**
     * Adds an item to the sale.
     * 
     * @param itemId The id of the item.
     * 
     * @return A SaleStateDTO containing information about the item and current total of the sale.
     * @throws NoMatchingItemByIdException When no item with the provided id is found.
     */
    public SaleStateDTO addItem(int itemId) throws NoMatchingItemByIdException {
        ItemDTO itemDTO = externInv.retrieveItem(itemId, 1);
        return sale.addItemToReceipt(itemDTO);
    }
    
    /**
     * Adds items to the sale.
     * 
     * @param itemId The id of the item.
     * @param amount The amount of said item.
     * 
     * @return A SaleStateDTO containing information about the item and current total of the sale.
     * @throws NoMatchingItemByIdException When no item with the provided id is found.
     */
    public SaleStateDTO addItem(int itemId, int amount) throws NoMatchingItemByIdException {
        ItemDTO itemDTO = externInv.retrieveItem(itemId, amount);
        return sale.addItemToReceipt(itemDTO);
    }
}
