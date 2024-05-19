/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.controller;
import java.io.IOException;
import se.kth.iv1350.seminar4.model.Sale;
import se.kth.iv1350.seminar4.integration.*;
import java.util.List;
import se.kth.iv1350.seminar4.integration.dto.*;
import se.kth.iv1350.seminar4.model.InsufficientPaymentException;
import se.kth.iv1350.seminar4.model.ItemAmountOverInventoryLimitException;
import se.kth.iv1350.seminar4.model.Observer;
import se.kth.iv1350.seminar4.model.dto.*;
import se.kth.iv1350.seminar4.utilities.LogWriter;
;

/**
 * A controller class.
 * @author nilse
 */
public class Controller {
    private Sale sale;
    private final ExternalInventorySystemAccessPoint externInv;
    
    private final ExternalAccountingSystemAccessPoint accounting;
    
    private final LogWriter logger;
    
    /**
     * Adds an observer to the sale.
     * @param obs The observer.
     */
    public void addObserver(Observer obs) {
        sale.addObserver(obs);
    }
    
    /**
     * Creates a new Controller instance
     * @throws IOException if LogWriter is unable to start.
     */
    public Controller() throws IOException {
        this.externInv = ExternalInventorySystemAccessPoint.getInstance();
        this.accounting = ExternalAccountingSystemAccessPoint.getInstance();
        this.logger = new LogWriter();
    }
    
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
     * @param payment The amount paid in SEK.
     * 
     * @return A string representing the receipt and the change
     * @throws ItemInventoryResultLessThanZeroException thrown if the inventory result in a lower stock than 0.
     * @throws InsufficientPaymentException When payment is insufficient.
     */
    public ReceiptDTO payment(int payment) throws ItemInventoryResultLessThanZeroException, InsufficientPaymentException{
        ReceiptDTO printReceipt = sale.payment(payment);
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
     * @return A SaleStateDTO containing information about the item and current total of the sale.
     * @throws ActionFailedException When no item with the provided id is found.
     * @throws ItemAmountOverInventoryLimitException If item amount attempts to go above allowed Inventory limit.
     */
    public SaleStateDTO addItem(int itemId) throws ActionFailedException, ItemAmountOverInventoryLimitException {
        ItemDTO itemDTO;
        
        try {
            itemDTO = externInv.retrieveItem(itemId, 1);
            return sale.addItemToReceipt(itemDTO);
            
        } catch (NoMatchingItemByIdException e) {
            logger.log(e);
            throw new ActionFailedException("Could not find item", e);
        } catch (DatabaseUnresponsiveException e) {
            logger.log(e);
            throw new ActionFailedException("Could not connect to database", e);
        }
        
    }
    
    /**
     * Adds items to the sale.
     * 
     * @param itemId The id of the item.
     * @param amount The amount of said item.
     * 
     * @return A SaleStateDTO containing information about the item and current total of the sale.
     * @throws ActionFailedException When no item with the provided id is found.
     * @throws ItemAmountOverInventoryLimitException If item amount attempts to go above allowed Inventory limit.
     */
    public SaleStateDTO addItem(int itemId, int amount) throws ActionFailedException, ItemAmountOverInventoryLimitException {
        ItemDTO itemDTO;
        
        try {
            itemDTO = externInv.retrieveItem(itemId, amount);
            return sale.addItemToReceipt(itemDTO);
            
        } catch (NoMatchingItemByIdException e) {
            logger.log(e);
            throw new ActionFailedException("Could not find item", e);
        } catch (DatabaseUnresponsiveException e) {
            logger.log(e);
            throw new ActionFailedException("Could not connect to database", e);
        }
    }
}
