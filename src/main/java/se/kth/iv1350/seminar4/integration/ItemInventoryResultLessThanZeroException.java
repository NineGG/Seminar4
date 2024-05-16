/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;

import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 * Thrown when the an update to an item stock in the External Inventory System will result in a stock that is less than zero
 * @author nilse
 */
public class ItemInventoryResultLessThanZeroException extends Exception {
    
    private int inventory;
    private int attemptedNumOfItemsToRemove;
    /**
     * Creates an ItemInventoryResultLessThanZeroException
     * @param itemDTO the DTO that is pushed to the inventory system to update the stock
     * @param inventoryItem 
     */
    public ItemInventoryResultLessThanZeroException(ItemDTO itemDTO, ExternalInventorySystemItem inventoryItem) {
        this.inventory = inventoryItem.getNumOfItemInInventory();
        this.attemptedNumOfItemsToRemove = itemDTO.getItemAmount();
    }
    
    /**
     * Creates an ItemInventoryResultLessThanZeroException.
     * @param inventory The state of the inventory before the attempted change.
     * @param attemptedNumOfItemsToRemove The amount of items attempted to be removed from the system.
     */
    public ItemInventoryResultLessThanZeroException(int inventory, int attemptedNumOfItemsToRemove) {
        this.inventory = inventory;
        this.attemptedNumOfItemsToRemove = attemptedNumOfItemsToRemove;
    }
    
}
