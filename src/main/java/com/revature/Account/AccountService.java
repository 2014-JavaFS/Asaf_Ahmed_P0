package com.revature.Account;

import com.revature.util.Serviceable;
import com.revature.util.enums.AccountType;
import com.revature.util.exceptions.InvalidInputException;
import com.revature.util.exceptions.NegativeAmountException;
import com.revature.util.exceptions.OverdraftException;

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


    public List<Account> findAll(int client_id) {
        List<Account> accountList = new ArrayList<>();
        try {
            accountRepository.establishConnection();
            accountList= accountRepository.findAll(client_id);
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


    public Account create( AccountType accountType, double balance, int primary_client_id ) {
        Account account = new Account(primary_client_id,balance,accountType);
        Account created_account;
        try {
            accountRepository.establishConnection();
            created_account = accountRepository.save(account);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException | InvalidInputException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                accountRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return created_account;

    }
    public void deposit(int clientId,int account_id, double deposit) throws NegativeAmountException {
        Account accountToChange = new Account();
        if(deposit>0){
            try {


            accountRepository.establishConnection();
            List<Account> accounts = accountRepository.findAll(clientId);

            for(Account account:accounts){
                if(account.getAccountId()==account_id){
                    accountToChange = account;
                }
            }
            accountToChange.setAccountBalance(accountToChange.getAccountBalance()+deposit);
            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    accountRepository.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


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
            throw new NegativeAmountException(deposit);
        }



    }
    public void withDraw(int clientId,int account_id, double withDraw) throws OverdraftException, NegativeAmountException {
        Account accountToChange = new Account();
        if(withDraw>0){
            try {


                accountRepository.establishConnection();
                List<Account> accounts = accountRepository.findAll(clientId);

                for(Account account:accounts){
                    if(account.getAccountId()==account_id){
                        accountToChange = account;
                    }
                }
                if (withDraw<accountToChange.getAccountBalance()){
                    accountToChange.setAccountBalance(accountToChange.getAccountBalance()-withDraw);
                }else {
                    throw new OverdraftException(withDraw,accountToChange.getAccountBalance());
                }
            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    accountRepository.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

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
            throw new NegativeAmountException(withDraw);
        }
    }

    public void delete(int account_id, int primary_client_id) {
        try {
            accountRepository.establishConnection();
            accountRepository.delete(account_id,primary_client_id);
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

    public Account update(Account toUpdate, int client_id, int account_id) {
        toUpdate.setClientUserId(client_id);
        toUpdate.setAccountId(account_id);
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
        return toUpdate;
    }
}
