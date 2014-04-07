package com.example.Mrs_Potato_Updated;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Ruby
 *
 */

public class Account {
	
	/**
	 * private instance variable name.
	 */
    private String name;
    
    /**
	 * private instance variable balance.
	 */
    private double balance;
    
    /**
	 * private instance variable trans.
	 */
    private ArrayList<Transaction> trans = new ArrayList<Transaction>();
    
    /**
     * 
     * 
     * @param aBalance initial balance for account
     * @param aName name of account
     */
    public Account(double aBalance, String aName) {
        this.balance = aBalance;
        this.name = aName;
    }

    /**
     * 
     * 
     * 
     * @return name of the account
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * 
     * @return account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 
     * @param aBalance balance of account
     */
    public void setBalance(double aBalance) {
        this.balance = aBalance;
    }
    
    /**
     * 
     * 
     * @param aTrans ArrayList which stores all account transactions
     */
    public void setTrans(ArrayList<Transaction> aTrans) {
        this.trans = aTrans;
    }

    /**
     * 
     * 
     * @return arraylist of all account transactions
     */
    public ArrayList<Transaction> getTrans() {
        return trans;
    }
}
