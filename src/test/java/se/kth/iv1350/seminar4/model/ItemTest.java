/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminar4.model;

import se.kth.iv1350.seminar4.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @author nilse
 */
public class ItemTest {

    private Item itemToTest;
    
    private String itemName;
    private String itemDesc;
    private double valueAddedTax;
    private int amount;
    private double price;
    private int itemId;

    
    @BeforeEach
    public void setUp() {
        amount = 5;
        price = 5.99;
        itemId = 1;
        valueAddedTax = 0.30;
        itemDesc = "An Test Item.";
        itemName = "Item";
        
        
        itemToTest = new Item(amount, itemId, itemName, itemDesc, valueAddedTax, price);
    }
    
    @AfterEach
    public void tearDown() {
        itemToTest = null;
        amount = 0;
        price = 0;
        itemId = 0;
        valueAddedTax = 0;
        itemDesc = null;
        itemName = null;
    }

    @Test
    public void testGetItemAmount() {
        System.out.println("getItemAmount");
        int expResult = amount;
        int result = itemToTest.getItemAmount();
        assertEquals(expResult, result, "doesn't match the expected amount");
    }

    @Test
    public void testGetItemId() {
        System.out.println("getItemId");
        int expResult = itemId;
        int result = itemToTest.getItemId();
        assertEquals(expResult, result, "doesn't match the expected Id");
    }

    @Test
    public void testGetVATEqualsExpectedVAT() {
        System.out.println("getVAT");
        double expResult = valueAddedTax;
        double result = itemToTest.getVAT();
        assertEquals(expResult, result, "doesn't equal the expected VAT");
    }

    @Test
    public void testGetItemNameMatchesExpectedItemName() {
        System.out.println("getItemName");
        String expResult = itemName;
        String result = itemToTest.getItemName();
        assertEquals(expResult, result, "doesn't match the expected item name");
    }

    @Test
    public void testGetItemDescriptionMatchesExpectedDescription() {
        System.out.println("getItemDescription");
        String expResult = itemDesc;
        String result = itemToTest.getItemDescription();
        assertEquals(expResult, result, "doesn't match the expected item description");
    }

    @Test
    public void testGetPriceEqualsExpectedPrice() {
        System.out.println("getPrice");
        double expResult = price;
        double result = itemToTest.getPrice();
        assertEquals(expResult, result, "doesn't equal the expected price");
    }

    @Test
    public void testIncreaseAmountEqualsExpectedIncreasedAmount() {
        System.out.println("increaseAmount");
        int increase = 3;
        int expResult = amount + increase;
        itemToTest.increaseAmount(increase);
        int result = itemToTest.getItemAmount();
        assertEquals(expResult, result, "result doesn't equal the expected increase in price");
    }
    
}
