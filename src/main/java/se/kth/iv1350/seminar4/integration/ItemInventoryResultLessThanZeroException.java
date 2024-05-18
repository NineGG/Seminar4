/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;


/**
 * Thrown when the an update to an item stock in the External Inventory System will result in a stock that is less than zero
 * @author nilse
 */
public class ItemInventoryResultLessThanZeroException extends Exception {
    
    private final int inventoryCount;
    private final int attemptedNumOfItemsToRemove;
    
    /**
     * Creates an ItemInventoryResultLessThanZeroException.
     * @param inventoryCount The state of the inventory before the attempted change.
     * @param attemptedNumOfItemsToRemove The amount of items attempted to be removed from the system.
     */
    public ItemInventoryResultLessThanZeroException(int inventoryCount, int attemptedNumOfItemsToRemove) {
        super("External Inventory is: " + inventoryCount + " and " + attemptedNumOfItemsToRemove + 
                " was attempted to be removed from it");
        this.inventoryCount = inventoryCount;
        this.attemptedNumOfItemsToRemove = attemptedNumOfItemsToRemove;
    }
    
    /**
     * Gets the inventory count of the item in the inventory system.
     * @return The inventory count.
     */
    public int getInventoryCount() {
        return inventoryCount;
    }
    
    /**
     * Gets the attempted amount of items to remove from the inventory system.
     * @return The amount of attempted removed items.
     */
    public int getattemptedNumOfItemsToRemove() {
        return attemptedNumOfItemsToRemove;
    }
    
}
