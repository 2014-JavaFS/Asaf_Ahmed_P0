package com.revature.Account;

import com.revature.Client.Client;
import com.revature.util.Serviceable;
import com.revature.util.enums.AccountType;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountService implements Serviceable<Account> {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try {
            accountRepository.establishConnection();
            accountList= accountRepository.findAll();
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                accountRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return accountList;
    }

    @Override
    public Optional<Account> findById(int id) {
        Optional<Account> account ;
        try {
            accountRepository.establishConnection();
            account= accountRepository.findById(id);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                accountRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return account;
    }


    public Account create( Account account ) {
        try {
            accountRepository.establishConnection();
             accountRepository.save(account);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                accountRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return account;

    }
    public void deposit(int clientId, double deposit){
        if(!accountRepository.findByClientId(clientId).isEmpty()&&deposit>0){

            Account accountToChange = accountRepository.findByClientId(clientId).get();
            accountToChange.setAccountBalance(accountToChange.getAccountBalance()+deposit);
            try {
                accountRepository.establishConnection();
                accountRepository.update(accountToChange);
            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    accountRepository.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }else {
            System.out.println("Deposit Unsuccessful");
        }



    }
    public void withDraw(int clientId, double withDraw){
        if(!accountRepository.findByClientId(clientId).isEmpty()&&withDraw>0&&accountRepository.findByClientId(clientId).get().getAccountBalance()>withDraw){

            Account accountToChange = accountRepository.findByClientId(clientId).get();
            accountToChange.setAccountBalance(accountToChange.getAccountBalance()-withDraw);
            try {
                accountRepository.establishConnection();
                accountRepository.update(accountToChange);
                System.out.println("WithDraw Successful");
            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    accountRepository.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }else{
            System.out.println("WithDraw Unsuccessful");
        }
    }
    @Override
    public void delete(int id) {
        try {
            accountRepository.establishConnection();
            accountRepository.delete(id);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                accountRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(int accountId, int primaryUserId, double accountBalance, AccountType accountType) {
        Account toUpdate = new Account(accountId,accountType,primaryUserId,accountBalance);
        try {
            accountRepository.establishConnection();
            accountRepository.update(toUpdate);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                accountRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
