package com.example.Mrs_Potato_Updated;

import java.util.GregorianCalendar;

/**
 * @author Katherine Pham
 * 
 */
public class Transaction {
    
    /**
     * private instance variable.
     */
    private String category;
    
    /**
     * private instance variable.
     */
    private double amount;
    
    /**
     * private instance variable.
     */
    private long id;
    
    /**
     * private instance variable.
     */
    private GregorianCalendar userTimeStamp;
    
    /**
     * private instance variable.
     */
    private boolean deposit;
    
    
    /**
     * Constructor.
     * @param anAmount amount
     * @param isdeposit true if transaction is a deposit
     * @param aCategory category of transaction
     * @param userTime2 time
     */
    public Transaction(double anAmount, boolean isdeposit, String aCategory,
            GregorianCalendar userTime2) {
        this.amount = anAmount;
        this.deposit = isdeposit;
        this.category = aCategory;
        this.userTimeStamp = userTime2;
    }

    /**
     * Returns the category of the transaction.
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the amount of the transaction..
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the id of the transaction.
     * @param anId id
     */
    public void setID(long anId) {
        this.id = anId;
    }

    /**
     * Returns the id of the transaction.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the userTimeStamp.
     * @return time
     */
    public GregorianCalendar getUserTimeStamp() {
        return userTimeStamp;
    }

    /**
     *Returns if the transaction is a deposit.
     * @return if transaction is deposit
     */
    public boolean isDeposit() {
        return deposit;
    }
}