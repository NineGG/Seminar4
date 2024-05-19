/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.view;

import se.kth.iv1350.seminar4.model.Observer;

/**
 * Prints total revenue from sales to System.out.
 * @author Nils Ekenberg
 */
public class TotalRevenueView implements Observer {

    private double totalRevenueFromAllSales;
    
    public TotalRevenueView() {
        totalRevenueFromAllSales = 0;
    }
    
    @Override
    public void updateTotalRevenue(double totalRevenueFromSale) {
        totalRevenueFromAllSales += totalRevenueFromSale;
        System.out.println("-----------------TotalRevenueView Start-----------------");
        System.out.println("Revenue from sale: " + totalRevenueFromSale + " SEK");
        System.out.println("Revenue from all sales since program start: " + totalRevenueFromAllSales + " SEK");
        System.out.println("------------------TotalRevenueView END------------------");
    }
    
}
