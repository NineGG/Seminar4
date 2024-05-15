/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;
import java.util.ArrayList;
import se.kth.iv1350.seminar4.model.Item;
import java.util.List;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 * This class is a handler class that represents a connection 
 * to the External inventory System.
 *
 * @author nilse
 */
public class ExternalInventorySystemAccessPoint {
    
    List<Item> inventory = new ArrayList<>();
    String itemDescription;
    String itemName;
    double price;
    double valueAddedTax;
    int itemId;
    
    /**
     * initialize database
     */
    public ExternalInventorySystemAccessPoint(){
        itemDescription = "Whole Milk, 500ml, 3% Fat Content";
        itemName = "Milk\t";
        price = 25.50;
        valueAddedTax = 0.12;
        itemId = 423;
        inventory.add(new Item(100, itemId, itemName, itemDescription, valueAddedTax, price));
        
        itemDescription = "500g, Gluten Free";
        itemName = "Bread\t";
        price = 54.50;
        valueAddedTax = 0.12;
        itemId = 231;
        inventory.add(new Item(100, itemId, itemName, itemDescription, valueAddedTax, price));
    }
    
    /**
     * requests an item from the ExternalInventorySystem(fake) based on the itemId provided.
     * 
     * @param itemId The id of the item requested.
     * @return The item object.
     * @throws NoMatchingItemByIdException When no item with the provided id is found.
     */
    public Item retrieveItem(int itemId) throws NoMatchingItemByIdException {
        for (Item item : inventory){
            if (itemId == item.getItemId()){
                itemDescription = "Whole Milk, 500ml, 3% Fat Content";
                itemDescription = item.getItemDescription();
                itemName = "Milk\t";
                itemName = item.getItemName();
                price = 25.50;
                price = item.getPrice();
                valueAddedTax = 0.12;
                valueAddedTax = item.getVAT();
            } 
        }
        
        return new Item(0, itemId, itemName, itemDescription, valueAddedTax, price);
    }
    
    /**
     * Updates the ExternalInventorySystem based on the items provided.
     * 
     * @param itemList List of Item objects.
     */
    public void updateInventory(List<ItemDTO> itemList){
        for(ItemDTO item : itemList){
            for(Item inventoryItem : inventory){
                if (inventoryItem.getItemId() == item.getItemId()){
                    inventoryItem.increaseAmount(-item.getItemAmount());
                }
            }
            System.out.println("Informed external inventory system to decrease inventory "
                    + "quantity of item " + item.getItemId() + " by " + item.getItemAmount() + " units.");
        }
    }

    
}
