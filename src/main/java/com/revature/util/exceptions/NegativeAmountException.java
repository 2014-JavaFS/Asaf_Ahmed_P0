package com.revature.util.exceptions;

public class NegativeAmountException extends Exception {

    private static final long serialVersionUID = 2182879691346971845L;

    public NegativeAmountException(double amount) {
        super("Can't deposit $" + amount + ", because its negative amount" );
    }

}