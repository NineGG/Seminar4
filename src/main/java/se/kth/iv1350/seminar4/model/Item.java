/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.model;

import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 * An item object, containing information about the item.
 *
 * @author nilse
 */
public class Item {
    private int itemAmount = 0;
    private int itemId;
    private double itemPrice;
    private String itemName;
    private String itemDescription;
    private double valueAddedTax;
    
    /**
     * Creates an item object
     * @param amount The amount of said item;
     * @param itemId The item Id
     * @param itemName The name of the item
     * @param itemDescription A simple description of the item
     * @param valueAddedTax The VAT
     * @param itemPrice The cost of the item
     */
    
    public Item(int amount, int itemId, String itemName, String itemDescription, double valueAddedTax, double itemPrice){
        this.itemAmount = amount;
        this.itemPrice = itemPrice;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.valueAddedTax = valueAddedTax;
    }
    /**
     * Creates an Item object from an ItemDTO
     * @param itemDTO The ItemDTO to create an Item from.
     */
    public Item(ItemDTO itemDTO){
        this.itemAmount = itemDTO.getItemAmount();
        this.itemPrice = itemDTO.getPrice();
        this.itemId = itemDTO.getItemId();
        this.itemName = itemDTO.getItemName();
        this.itemDescription = itemDTO.getItemDescription();
        this.valueAddedTax = itemDTO.getVAT();
    }
    
    /**
     * Gets current item amount.
     * @return The item amount.
     */
    public int getItemAmount(){
        return this.itemAmount;
    }
    
    /**
     * Gets the item identifier.
     * @return The item identifier.
     */
    public int getItemId(){
        return this.itemId;
    }
    
    /**
     * Gets the Value Added Tax (VAT).
     * @return The VAT.
     */
    public double getVAT(){
        return this.valueAddedTax;
    }
    
    /**
     * Gets the items name.
     * @return The item name
     */
    public String getItemName(){
        return this.itemName;
    }
    
    /**
     * Gets a string with descriptive tags of the item.
     * @return The item description.
     */
    public String getItemDescription(){
        return this.itemDescription;
    }
    
    /**
     * Gets the price of the item.
     * @return The price.
     */
    public double getPrice(){
        return this.itemPrice;
    }
    
    /**
     * Increases the item count.
     * @param increase The amount to increase the item count by.
     */
    public void increaseAmount(int increase) {
        this.itemAmount += increase;
    }
    
    
}
