package com.revature.util.exceptions;

public class OverdraftException extends Exception {

    private static final long serialVersionUID = 9182879691346971845L;

    public OverdraftException(double amount, double balance) {
        super("Can't withdraw $" + amount + ", account only has $" + balance);
    }

}