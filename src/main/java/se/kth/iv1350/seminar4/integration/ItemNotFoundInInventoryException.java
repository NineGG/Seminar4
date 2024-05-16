/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;

import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 * Thrown when an item type is not found in the inventory during an inventory update;
 * @author nilse
 */
public class ItemNotFoundInInventoryException extends Exception {
    
    ItemDTO itemThatWasNotFound;
    
    
    /**
     * Creates an ItemNotFoundInInventoryException with the itemDTO that caused the error.
     * @param itemThatWasNotFound itemDTO that was not found in the inventory system
     */
    public ItemNotFoundInInventoryException(ItemDTO itemThatWasNotFound) {
        super("ExternalInventorySystemAccessPoint was unable to record the sale of item with itemId " + itemThatWasNotFound.getItemId());
        this.itemThatWasNotFound = itemThatWasNotFound;
    }
    
    
}
