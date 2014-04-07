/**
 * 
 */
package com.example.Mrs_Potato_Updated;

import java.util.GregorianCalendar;

/**
 * @author katpham
 *
 */
public class Transaction {
    private String category;
    private double amount;
    private long  id;
    private GregorianCalendar userTime;
    private boolean isDeposit;
    public Transaction( double amount, boolean isDeposit, String category, GregorianCalendar userTime2){
	    this.amount = amount;
	    this.isDeposit = isDeposit;
	    this.category = category;
	    this.userTime = userTime2;
	}
    public String getCategory() {
        return category;
	}
    public double getAmount() {
        return amount;
	}
    public void setID(long id) {
        this.id=id;
	}
    public long getId() {
	    return id;
	}
    public GregorianCalendar getUserTimeStamp() {
        return userTime;
	}
    public boolean isDeposit() {
	    return isDeposit;
	}
}