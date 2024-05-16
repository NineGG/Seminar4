/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 * This class is a handler class that represents a connection 
 * to the External inventory System.
 *
 * @author nilse
 */
public class ExternalInventorySystemAccessPoint {
    
    private List<ExternalInventorySystemItem> inventory = new ArrayList<>();
    private String itemDescription;
    private String itemName;
    private double price;
    private double valueAddedTax;
    private int itemId;
    
    /**
     * initialize database
     */
    public ExternalInventorySystemAccessPoint(){
        itemDescription = "Whole Milk, 500ml, 3% Fat Content";
        itemName = "Milk";
        price = 25.50;
        valueAddedTax = 0.12;
        itemId = 423;
        inventory.add(new ExternalInventorySystemItem(100, itemId, itemName, itemDescription, valueAddedTax, price));
        
        itemDescription = "500g, Gluten Free";
        itemName = "Bread";
        price = 54.50;
        valueAddedTax = 0.12;
        itemId = 231;
        inventory.add(new ExternalInventorySystemItem(100, itemId, itemName, itemDescription, valueAddedTax, price));
    }
    
    /**
     * requests an item from the ExternalInventorySystem(fake) based on the itemId provided.
     * 
     * @param itemId The id of the item requested.
     * @param itemAmount The number of said item to retrieve.
     * @return The item object.
     * @throws NoMatchingItemByIdException When no item with the provided id is found.
     */
    public ItemDTO retrieveItem(int itemId, int itemAmount) throws NoMatchingItemByIdException {
        for (ExternalInventorySystemItem item : inventory){
            if (itemId == item.getItemId()){
                itemDescription = item.getItemDescription();
                itemName = item.getItemName();
                price = item.getPrice();
                valueAddedTax = item.getVAT();
            } 
        }
        
        return new ItemDTO(itemAmount, itemId, itemName, itemDescription, valueAddedTax, price);
    }
    
    private class UpdateQueue{
        private class UpdateQueueSet{
        
            private ItemDTO itemDTO;
            private ExternalInventorySystemItem inventoryItem;

            public UpdateQueueSet(ItemDTO itemDTO, ExternalInventorySystemItem inventoryItem) {
                this.itemDTO = itemDTO;
                this.inventoryItem = inventoryItem;
            }

            public void update(){
                inventoryItem.updateInventory(itemDTO);
            }
        }
        
        private List<UpdateQueueSet> setList = new ArrayList<>();
        
        public void addSet(ItemDTO itemDTO, ExternalInventorySystemItem inventoryItem){
            setList.add(new UpdateQueueSet(itemDTO, inventoryItem));
        }
    }
    
    /**
     * Updates the ExternalInventorySystem based on the items provided.
     * 
     * @param itemList List of Item objects.
     * @throws ItemInventoryResultLessThanZeroException thrown if the inventory update would result in an inventory less than 0
     * @throws ItemNotFoundInInventoryException Thrown if there was no Inventory Item with equating itemId to one of the items.
     */
    public void updateInventory(List<ItemDTO> itemList) throws ItemInventoryResultLessThanZeroException, ItemNotFoundInInventoryException{
        UpdateQueue queue = new UpdateQueue();
        boolean itemFoundInInventory;
        
        
        for(ItemDTO item : itemList){
            itemFoundInInventory = false;
            for(ExternalInventorySystemItem inventoryItem : inventory){
                if (inventoryItem.getItemId() == item.getItemId()){
                    itemFoundInInventory = true;
                    queue.addSet(item, inventoryItem);
                    
                    if (inventoryItem.getNumOfItemInInventory() < item.getItemAmount()){
                        throw new ItemInventoryResultLessThanZeroException(
                                inventoryItem.getNumOfItemInInventory(), 
                                item.getItemAmount()
                        );
                    }
                }
            }
            
            if (!itemFoundInInventory) {
                //throw error.
            }
            
            //System.out.println("Informed external inventory system to decrease inventory "
            //        + "quantity of item " + item.getItemId() + " by " + item.getItemAmount() + " units.");
        }
    }

    
}
