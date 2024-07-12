package com.revature.Account;

import com.revature.Client.Client;
import com.revature.util.ConnectionManager;
import com.revature.util.Repository;
import com.revature.util.enums.AccountType;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements Repository<Account> {
    Connection connection =null;
    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException, FileNotFoundException {
        if(connection==null){
            connection = ConnectionManager.getConnection();
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void save(Account account) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into account(account_type,account_balance, client_id) values(?,?,?)");
            stmt.setString(1,account.getAccountType().toString());
            stmt.setDouble(2,account.getAccountBalance());
            stmt.setInt(3,account.getClientUserId());
            int rs = stmt.executeUpdate();
            if(rs>0){
                System.out.println("Insertion succeeded");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Account> findById(int id) {
        Account account = new Account();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from account where account_id =?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt(1);
                AccountType accountType = AccountType.valueOf(rs.getString(2));
                Double balance = rs.getDouble(3);
                int client_id = rs.getInt(4);
                account.setAccountId(account_id);
                account.setAccountType(accountType);
                account.setAccountBalance(balance);
                account.setClientUserId(client_id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(account);
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from account");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Account account = new Account();
                int account_id = rs.getInt(1);
                AccountType accountType = AccountType.valueOf(rs.getString(2));
                Double balance = rs.getDouble(3);
                int client_id = rs.getInt(4);
                account.setAccountId(account_id);
                account.setAccountType(accountType);
                account.setAccountBalance(balance);
                account.setClientUserId(client_id);
                accountList.add(account);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    @Override
    public void update(Account account) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update account set account_type = ?, account_balance =?, client_id =? where account_id=?");
            stmt.setString(1,account.getAccountType().toString());
            stmt.setDouble(2,account.getAccountBalance());
            stmt.setInt(3,account.getClientUserId());
            stmt.setInt(4,account.getAccountId());
            int rs = stmt.executeUpdate();
            if(rs>0){
                System.out.println("Update Successful");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void delete(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from account where account_id =?");
            int rs = stmt.executeUpdate();
            while (rs>0){
                System.out.println("Delete Successful");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Optional<Account> findByClientId(int clientId){
        Account account = new Account();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from account where client_id =?");
            stmt.setInt(1,clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int account_id = rs.getInt(1);
                AccountType accountType = AccountType.valueOf(rs.getString(2));
                Double balance = rs.getDouble(3);
                int client_id = rs.getInt(4);
                account.setAccountId(account_id);
                account.setAccountType(accountType);
                account.setAccountBalance(balance);
                account.setClientUserId(client_id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(account);

    }

}
