/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.seminar4.model;

/**
 * An exception thrown when the payment doesn't equal or exceed the price of the sale.
 * @author nilse
 */
public class InsufficientPaymentException extends Exception {
    
    private double cost;
    private double payment;
    
    /**
     * creates an Insufficient Payment Exception
     * @param payment The payment made that caused the exception
     * @param cost The cost of the sale that caused the exception
     */
    public InsufficientPaymentException(double payment, double cost) {
        this.cost = cost;
        this.payment = payment;
    }
    
    
    /**
     * A getter method for the cost of the sale
     * 
     * @return 
     */
    public double getCost(){
        return this.cost;
    }
    
    
    /**
     * A getter method for the payment total.
     * 
     * @return The payment made. 
     */
    public double getPayment() {
        return this.payment;
    }
    
}
