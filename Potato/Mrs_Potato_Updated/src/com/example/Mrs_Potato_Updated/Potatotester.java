package com.example.Mrs_Potato_Updated;

import com.robotium.solo.Solo;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

/**
 * 
 * @author Ruby
 *
 */
public class Potatotester extends
        ActivityInstrumentationTestCase2<MainActivity> {
    
    /**
     * private instance variable name.
     */
    private Solo solo;

    /**
     * Constructor.
     */        
    public Potatotester() {
        super(MainActivity.class);
    }

    /**
     * @throws Exception exception
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.solo = new Solo(getInstrumentation());
        getActivity();
    }

    /**
     * Will Cely
     * Tests the mainActivity.
     */
    public void testMain() {
        // testing login
        solo.clickOnButton("Login");
        solo.waitForDialogToClose(10000);
        solo.waitForActivity("Loginactivity", 200);
        solo.assertCurrentActivity("Failure to start Login Activity",
                Loginactivity.class);
        solo.sleep(200);
        solo.goBack();
        solo.sleep(200);
        // testing register
        solo.clickOnButton("Register");
        solo.sleep(200);
        solo.typeText((EditText) solo.getView(R.id.editText1), "NewUser12");
        solo.typeText((EditText) solo.getView(R.id.editText2), "password");
        solo.clickOnButton("Register");
        solo.waitForActivity("Profileactivity");
        solo.assertCurrentActivity("Failure to start Profile Activity",
                Profileactivity.class);
        solo.sleep(300);
        solo.finishOpenedActivities();
    }

    /**
     * Shehmeer Jiwani
     * Tests the loginActivity.
     */
    public void testLogin() {
        Intent intent = new Intent(getInstrumentation().getContext(),
                Loginactivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getInstrumentation().startActivitySync(intent);
        solo.sleep(1000);
        solo.assertCurrentActivity("Failure to start Login Activity",
                Loginactivity.class);
        solo.enterText((EditText) solo.getView(R.id.editText1), "poopie");
        solo.enterText((EditText) solo.getView(R.id.editText2), "poopie");
        solo.clickOnButton("Login");
        solo.waitForDialogToClose(10000);
        solo.waitForActivity("Profileactivity", 200);
        solo.assertCurrentActivity("Failure to start Profile Activity",
                Profileactivity.class);
        solo.sleep(200);
        solo.clickOnButton("X");
        // testing cancel login
        solo.clickOnButton("Login");
        solo.enterText((EditText) solo.getView(R.id.editText1), "Incorrect");
        solo.enterText((EditText) solo.getView(R.id.editText2), "pass");
        solo.clickOnButton("Cancel");
        solo.sleep(300);
        solo.finishOpenedActivities();
    }

    /**
     * 
     * Local method to login.
     */
    private void login() {
        Intent intent = new Intent(getInstrumentation().getContext(),
                Loginactivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getInstrumentation().startActivitySync(intent);
        solo.sleep(1000);
        solo.assertCurrentActivity("Failure to start Login Activity",
                Loginactivity.class);
        solo.enterText((EditText) solo.getView(R.id.editText1), "poopie");
        solo.enterText((EditText) solo.getView(R.id.editText2), "poopie");
        solo.clickOnButton("Login");
        solo.waitForDialogToClose(1000);
        solo.waitForActivity("Profileactivity", 200);
        solo.assertCurrentActivity("Failure to start Profile Activity",
                Profileactivity.class);
        solo.sleep(70);
    }

    /**
     * Ruby Smith
     * Tests AddAccount method.
     */
    public void testAddAccount() {
        login();
        solo.clickOnButton("Add Account");
        solo.waitForDialogToClose(10000);
        // testing Add Account
        solo.waitForActivity("Registeraccountactivity", 200);
        solo.assertCurrentActivity("Failure to start Register Activity",
                Registeraccountactivity.class);
        solo.enterText((EditText) solo.getView(R.id.editText1), "Account7");
        solo.enterText((EditText) solo.getView(R.id.editText2), "20");
        solo.clickOnButton("Add Account");
        solo.waitForActivity("Profileactivity", 200);
        solo.assertCurrentActivity("Failure to start Profile_Activity",
                Profileactivity.class);
        solo.sleep(200);
        // testing cancel Add Account
        solo.clickOnButton("Add Account");
        solo.waitForDialogToClose(10000);
        solo.waitForActivity("Registeraccountactivity", 200);
        solo.assertCurrentActivity("Failure to start Register_Activity",
                Registeraccountactivity.class);
        solo.clickOnButton("Cancel");
        solo.waitForActivity("Profileactivity", 200);
        solo.assertCurrentActivity("Failure to start Profile_Activity",
                Profileactivity.class);
        solo.sleep(200);
        solo.finishOpenedActivities();
    }

    /**
     * Michael Glover
     * Tests profileActivity.
     */
    public void testProfile() {
        login();
        ListView lv = (ListView) solo.getView(R.id.listView1);
        for (int i = 0; i < lv.getCount(); i++) {
            Log.d("COUNT", lv.getCount() + "");
            View v = lv.getChildAt(i);
            Log.d("ListItem", v.toString());
            solo.clickOnView(v);
            solo.goBack();
        }
        solo.clickOnButton("Add Account");
        ;
        solo.sleep(200);
        solo.clickOnButton("Cancel");
        solo.clickOnButton("Generate Spending Report");
        solo.sleep(200);
        solo.goBack();
        solo.sleep(300);
        solo.finishOpenedActivities();
    }

    /**
     * Katherine Pham
     * Tests addTransaction method.
     */
    public void testAddTransactions() {
        login();
        ListView lv = (ListView) solo.getView(R.id.listView1);
        if (lv.getCount() != 0) {
            View v = lv.getChildAt(0);
            Log.d("ListItem", v.toString());
            solo.clickOnView(v); // clicking on account
            // testing deposit
            solo.clickOnButton("Add Transaction");
            solo.waitForActivity("Transactionactivity");
            solo.typeText((EditText) solo.getView(R.id.editText1), "Paycheck");
            solo.typeText((EditText) solo.getView(R.id.editText2), "20");
            solo.clickOnButton("Deposit");
            solo.waitForActivity("Accountactivity");
            solo.sleep(150);
            // testing withdraw
            solo.clickOnButton("Add Transaction");
            solo.waitForActivity("Transactionactivity");
            solo.typeText((EditText) solo.getView(R.id.editText1), "Food");
            solo.typeText((EditText) solo.getView(R.id.editText2), "20");
            solo.clickOnButton("Withdraw");
            solo.waitForActivity("Accountactivity");
            solo.sleep(150);
            // testing cancel
            solo.clickOnButton("Add Transaction");
            solo.waitForActivity("Transactionactivity");
            solo.typeText((EditText) solo.getView(R.id.editText1),
                    "Shananigans");
            solo.typeText((EditText) solo.getView(R.id.editText2), "100");
            solo.clickOnButton("Cancel");
            solo.waitForActivity("Accountactivity");
            solo.sleep(200);
            solo.finishOpenedActivities();
        }
    }
    
    /**
     * @throws Exception exception
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}