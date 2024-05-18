/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package se.kth.iv1350.seminar4.model;

import se.kth.iv1350.seminar4.model.Item;
import se.kth.iv1350.seminar4.model.Receipt;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 *
 * @author nilse
 */
public class ReceiptTest {
    
    private Receipt testReceipt;
    private ItemDTO testItemDTO;
    private String itemName;
    private String itemDesc;
    private double valueAddedTax;
    private int amount;
    private double price;
    private int itemId;
    
    @BeforeEach
    public void setUp() {
        testReceipt = new Receipt();
        amount = 5;
        price = 5.99;
        itemId = 1;
        valueAddedTax = 0.30;
        itemDesc = "An Test Item.";
        itemName = "Item";
        testItemDTO = new ItemDTO(amount, itemId, itemName, itemDesc, valueAddedTax, price);
        
    }
    
    @AfterEach
    public void tearDown() {
        testReceipt = null;
        testItemDTO = null;
        amount = 0;
        price = 0;
        itemId = 0;
        valueAddedTax = 0;
        itemDesc = null;
        itemName = null;
    }

    @Test
    public void testAddItemToReceiptAddsItemToMatchExpextedList() {
        //tests both the addItem and getItemList methods.
        System.out.println("addItemToReceipt");
        List<ItemDTO> expectedList = new ArrayList<>();
        expectedList.add(new ItemDTO(
            testItemDTO.getItemAmount(), 
            testItemDTO.getItemId(), 
            testItemDTO.getItemDescription(), 
            testItemDTO.getItemName(), 
            testItemDTO.getVAT(), 
            testItemDTO.getPrice()
        ));
        testReceipt.addItemToReceipt(testItemDTO);
        List<ItemDTO> resultList = testReceipt.getItemList();
        boolean isEqual = true;
        if (resultList.size() != expectedList.size())
            isEqual = false;
        
        int listSize = resultList.size();
        for (int i = 0; i < listSize; i++){
            if (resultList.get(i).getVAT() == expectedList.get(i).getVAT())
                isEqual = false;
            
            if (resultList.get(i).getItemAmount() == expectedList.get(i).getItemAmount())
                isEqual = false;
            
            if (resultList.get(i).getItemId() == expectedList.get(i).getItemId())
                isEqual = false;
            
            if (resultList.get(i).getItemName().equals(expectedList.get(i).getItemName()))
                isEqual = false;
            
            if (resultList.get(i).getItemDescription().equals(expectedList.get(i).getItemDescription()))
                isEqual = false;
            
            if (resultList.get(i).getPrice() == expectedList.get(i).getPrice())
                isEqual = false;
            
        }
        
        assertFalse(isEqual, "List in receipt isn't equal to the expected list");
    }

    @Test
    public void testIncreaseItemAmountEqualsExpectedIncreasedValue() {
        System.out.println("increaseItemAmount");
        testReceipt.addItemToReceipt(testItemDTO);
        int increase = 2;
        ItemDTO increaseByItemDTO = new ItemDTO(
            increase,
            testItemDTO.getItemId(),
            testItemDTO.getItemDescription(),
            testItemDTO.getItemName(),
            testItemDTO.getVAT(),
            testItemDTO.getPrice()
        );
        testReceipt.increaseItemAmount(increaseByItemDTO);
        int expectedResult = amount + increase;
        
        List<ItemDTO> testList = testReceipt.getItemList();
        int result = testList.get(0).getItemAmount();
        assertEquals(expectedResult, result, "result doesn't equal the expected increase in price");
    }

    @Test
    public void testPaymentEqualsPaidAmount() {
        System.out.println("payment");
        testReceipt.addItemToReceipt(testItemDTO);
        
        double payment = 100.0;
        try {
            testReceipt.payment(payment);
        } catch (Exception e) {
            fail("Threw an exception");
        }
        double result = testReceipt.getPayment();
        assertEquals(payment, result, "Payment on receipt doesn't equal the paid amount");
    }
    
    @Test public void testPaymentThrowsCorrectlyWhenPaidTooLittle() {
        System.out.println("payment exception");
        
        testReceipt.addItemToReceipt(testItemDTO);
        double payment = 0;
        try {
            testReceipt.payment(payment);
            fail("Failed, did not throw");
        } catch (InsufficientPaymentException e) {
        } catch (Exception e) {
            fail("Threw but threw an unexpected exception");
        }
    }
    
    @Test
    public void testItemExistsGivesTheExpectedValueWhenItDoesExist() {
        System.out.println("itemExists");
        testReceipt.addItemToReceipt(testItemDTO);
        boolean expValue = true;
        boolean result = testReceipt.itemExists(testItemDTO);
        assertEquals(expValue, result, "item not found in receipt even though it was added");
    }
    
    @Test
    public void testDiscountOnlyFlatEqualsExpected(){
        System.out.println("Discount Flat (getDiscount + addDiscountFlat)");
        testReceipt.addItemToReceipt(testItemDTO);
        double expResult = 3.0;
        testReceipt.addDiscountFlat(expResult);
        double result = testReceipt.getDiscount();
        assertEquals(expResult, result, "Discount doesnt equal the expected value");
    }
    
    @Test
    public void testDiscountOnlyPercentageEqualsExpected() {
        System.out.println("Discount Percentage (getDiscount + addDiscountPercentage)");
        testReceipt.addItemToReceipt(testItemDTO);
        double discountPercentage = 0.4;
        testReceipt.addDiscountPercentage(discountPercentage);
        double expResult = testReceipt.getCostBeforeDiscount() - (price * amount * (1-discountPercentage));
        double result = testReceipt.getDiscount();
        assertEquals(expResult, result, "Discount doesn't equal the expected value");
    }
    
    @Test
    public void testDiscountPrecentageAndFlatEqualsExpected(){
        System.out.println("Discount (getDiscount + addDiscountPercentage + addDiscountFlat)");
        testReceipt.addItemToReceipt(testItemDTO);
        double discountPercentage = 0.4;
        double discountFlat = 3.0;
        double totalCost = (price * amount);
        double expResult =  totalCost - (totalCost - discountFlat) * (1 - discountPercentage);
        testReceipt.addDiscountFlat(discountFlat);
        testReceipt.addDiscountPercentage(discountPercentage);
        double result = testReceipt.getDiscount();
        assertEquals(expResult, result, "Discount doesn't equal the expected value");
    }
}
