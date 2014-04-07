package com.example.Mrs_Potato_Updated;

import java.util.ArrayList;

/**
 * 
 * @author Ruby
 *
 */
public class Profile {
    
    /**
     * private instance variable name.
     */
    String username;
    
    /**
     * private instance variable name.
     */
    String password;
    
    /**
     * private instance variable name.
     */
    ArrayList<Account> accountsList;

    /**
     * Constructor.
     * @param aUsername username
     * @param aPassword password
     */
    public Profile(String aUsername, String aPassword) {
        this.username = aUsername;
        this.password = aPassword;
    }

    /**
     *Returns the profile's username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the profile's password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the profile's accounts.
     * @param accounts arraylist of accounts
     */
    public void setAccountsList(ArrayList<Account> accounts) {
        accountsList = accounts;
    }
}