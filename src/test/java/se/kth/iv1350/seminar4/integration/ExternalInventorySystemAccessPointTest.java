/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 *
 * @author Nils Ekenberg
 */
public class ExternalInventorySystemAccessPointTest {
    
    ExternalInventorySystemAccessPoint invInstance = ExternalInventorySystemAccessPoint.getInstance();
    
    private final ItemDTO itemDTO;
    private final ItemDTO itemTwoDTO;
    private String itemDescription = "500g, Gluten Free";
    private String itemName = "Bread";
    private double price = 54.50;
    private double valueAddedTax = 0.12;
    private int itemId = 231;
    
    
    public ExternalInventorySystemAccessPointTest() {
        itemDescription = "500g, Gluten Free";
        itemName = "Bread";
        price = 54.50;
        valueAddedTax = 0.12;
        itemId = 231;
        itemDTO = new ItemDTO(110, itemId, itemName, itemDescription, valueAddedTax, price);
        itemTwoDTO = new ItemDTO(90, itemId, itemName, itemDescription, valueAddedTax, price);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    


    @Test
    public void testUpdateInventoryUpdateEqualsExpectedValue() {
        System.out.println("updateInventory");
        List<ItemDTO> itemList = new ArrayList<>();
        itemList.add(itemTwoDTO);
        try {
            int expected = invInstance.retrieveItemStatus(itemTwoDTO.getItemId()).getItemAmount() - itemTwoDTO.getItemAmount();
            invInstance.updateInventory(itemList);
            int result = invInstance.retrieveItemStatus(itemTwoDTO.getItemId()).getItemAmount();
            assertEquals(expected, result, "Did not update correctly, expected " + expected + " got " + result);
        } catch (Exception e) {
            fail("Threw an unexpected exception.");
        }
    }

    
    @Test
    public void testUpdateInventoryThrowsItemInventoryResultLessThanZeroExceptionCorrectly() {
        System.out.println("updateInventory Exception");
        
        List<ItemDTO> itemList = new ArrayList<>();
        itemList.add(itemDTO);
        
        try {
            invInstance.updateInventory(itemList);
            fail("updateInvntory fails to throw when inventory is about to go into the negatives.");
        } catch (ItemInventoryResultLessThanZeroException e) {
        } catch (Exception e) {
            fail("Threw an unexpected exception.");
        }
    
    }
}
