package com.revature.Account;
enum AccountType{Checking, Saving}
public class Account {
    private int accountId;
    private AccountType accountType;
    private int userId;
    private String password;
    private double accountBalance;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Account(int accountId, AccountType accountType, int userId, String password, double accountBalance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.userId = userId;
        this.password = password;
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
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
