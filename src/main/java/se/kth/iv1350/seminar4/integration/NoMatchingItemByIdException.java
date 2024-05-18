/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;

/**
 * Thrown if item with matching id doesn't exist.
 * @author Nils Ekenberg
 */
public class NoMatchingItemByIdException extends RuntimeException{
    
    private final int itemId;
    
    /**
     * creates an NoMatchingItemByIdException instance.
     * @param itemId 
     */
    public NoMatchingItemByIdException(int itemId) {
        this.itemId = itemId;
    }
    
    /**
     * Gets the itemId that is responsible for the exception.
     * @return The itemId
     */
    public int getItemId(){
        return itemId;
    }
}
