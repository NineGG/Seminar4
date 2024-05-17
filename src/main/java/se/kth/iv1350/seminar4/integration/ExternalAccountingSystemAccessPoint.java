/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar4.model.dto.ReceiptDTO;

/**
 * This class is a handler class that represents a connection 
 * to the External Accounting System.
 * 
 * 
 * @author nilse
 */
public class ExternalAccountingSystemAccessPoint {
    private static final ExternalAccountingSystemAccessPoint INSTANCE = 
            new ExternalAccountingSystemAccessPoint();
    private final List<ReceiptDTO> database = new ArrayList<>();
    
    public static ExternalAccountingSystemAccessPoint getInstance(){
        return INSTANCE;
    }
    
    private ExternalAccountingSystemAccessPoint() {
    
    }
    
    
    /**
     * Sends the receiptDTO to the accounting system.
     * 
     * @param receiptDTO The ReceiptDTO to be sent.
     */
    public void updateAccounting(ReceiptDTO receiptDTO){
        database.add(receiptDTO);
    }
}
