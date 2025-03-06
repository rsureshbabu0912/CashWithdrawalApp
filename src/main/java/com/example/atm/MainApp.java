package com.example.atm;

import com.example.atm.serviceimpl.CashWithdrawServiceImpl;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Main app - Reads the input from user and invoke Service class to perform cashWithdraw function.
 * Also maintain initial denomination values in Denomination object and adjust the total after each
 * cash withdrawal. CashWithdrawal will be denied if the Max withdrawal amount exceed 25000 and if the total
 * available denominations are less than the withdrawal amount.
 */
public class MainApp {
    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

    public static void main(String[] args) {

        CashWithdrawServiceImpl cashWithdrawServiceImpl = new CashWithdrawServiceImpl();


        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter amount to withdraw. Amount Max limit is 25000 and denomination should be multiple of 10:");
                int amount = in.nextInt();

                if (cashWithdrawServiceImpl.doCashWithdraw(amount)) {
                    System.out.println("Withdrawal Success");

                } else {
                    System.out.println("Withdrawal failed. Please Enter valid input amount");
                }

            }
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, "UnExpected error occurred. The exception message:" + exception.getMessage());
        }
    }
}

