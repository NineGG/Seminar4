/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.model.dto;

import se.kth.iv1350.seminar4.integration.dto.ItemDTO;

/**
 * A DTO to transfer basic sale information.
 * @author nilse
 */
public class SaleStateDTO {
    private ItemDTO itemDTO;
    private double runningTotal;
    
    /**
     * Creates an SaleStateDTO
     * @param itemDTO The itemDTO to record in the SaleStateDTO.
     * @param runningTotal The running total of the sale.
     */
    public SaleStateDTO(ItemDTO itemDTO, double runningTotal) {
        this.itemDTO = itemDTO;
        this.runningTotal = runningTotal;
    }
    
    /**
     * Gets the itemDTO from the SaleStateDTO.
     * @return The itemDTO.
     */
    public ItemDTO getItemDTO() {
        return this.itemDTO;
    }
    
    /**
     * Gets the running total of the sale stored in the SaleStateDTO.
     * @return The running total.
     */
    public double getRunningTotal() {
        return runningTotal;
    }
    
}
