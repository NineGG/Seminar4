/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;

/**
 * Thrown when the ExternalInventorySystem could not find an item that matches the item identifier provided.
 * @author nilse
 */
public class NoMatchingItemByIdException extends Exception {
    
    private int itemId;
    
    
    /**
     * Creates an No Matching Item By Id Exception object.
     * @param itemId The item identifier that didn't result in an matching item.
     */
    public NoMatchingItemByIdException(int itemId) {
        this.itemId = itemId;
    }
    
    
    /**
     * Gets the item identifier that could not result in an item.
     * 
     * @return The item identifier.
     */
    public int getItemId() {
        return itemId;
    }
    
}
