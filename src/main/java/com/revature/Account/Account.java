package com.revature.Account;

import com.revature.util.enums.AccountType;

public class Account {
    private int accountId;
    private AccountType accountType;
    private int clientUserId;
    private double accountBalance;

    public Account(int clientUserId, double accountBalance, AccountType accountType) {
        this.clientUserId = clientUserId;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(int clientUserId) {
        this.clientUserId = clientUserId;
    }


    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Account(int clientUserId) {
        this.accountType = AccountType.CHECKING;
        this.accountBalance = 100;
        this.clientUserId = clientUserId;
    }

    public Account() {
    }

    public Account(int accountId, AccountType accountType, int clientUserId, double accountBalance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.clientUserId = clientUserId;

        if(accountBalance<0){
            this.accountBalance = 0;
        }else {
        this.accountBalance = accountBalance;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType=" + accountType +
                ", userId=" + clientUserId +

                ", accountBalance=" + accountBalance +
                '}';
    }
}
