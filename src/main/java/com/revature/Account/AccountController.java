package com.revature.Account;

import com.revature.Client.Client;
import com.revature.util.Controller;
import com.revature.util.enums.AccountType;
import com.revature.util.exceptions.InvalidInputException;
import com.revature.util.exceptions.NegativeAmountException;
import com.revature.util.exceptions.OverdraftException;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Optional;

public class AccountController implements Controller {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.get("/accounts", this::getAllAccounts);
        app.get("/accounts/{id}", this::findById);
        app.post("/accounts", this::createAccount);
        app.patch("/accounts/{id}", this::updateAccount);
        app.patch("/accounts/id/deposit", this::depositAccount);
        app.patch("/accounts/id/withdrawal", this::withdrawAccount);
        app.delete("/accounts/{id}", this::deleteAccount);
    }

    private void findById(Context context) {
        int id = Integer.parseInt(context.pathParam("id"));
        Optional<Account> account =accountService.findById(id);
        if(account.isPresent()) {
            context.status(200).json(account.get());
        }else{
            context.status(400).result("bad request");
        }
    }

    private void deleteAccount(Context context) {
        int account_id = Integer.parseInt(context.pathParam("id"));
        int primary_client_id = Integer.parseInt(context.header("primary_client_id"));
        accountService.delete(account_id,primary_client_id);
        context.status(200).result("account deleted");
    }

    private void withdrawAccount(Context context) {
        int client_id = Integer.parseInt(context.header("primary_client_id"));
        double amount = Double.parseDouble(context.queryParam("withdrawal"));
        int accountId = Integer.parseInt(context.queryParam("account_id"));
        try{accountService.withDraw(client_id,accountId,amount);
            context.status(202).result("withdrawal succeeded");
        } catch (OverdraftException | NegativeAmountException e){
            context.status(400).result(e.getMessage());
        }

    }

    private void depositAccount(Context context) {
        int client_id = Integer.parseInt(context.header("primary_client_id"));
        double amount = Double.parseDouble(context.queryParam("deposit"));
        int accountId = Integer.parseInt(context.queryParam("account_id"));
        try {
            accountService.deposit(client_id,accountId,amount);
            context.status(202).result("deposit succeeded");
        }catch (NegativeAmountException e){
            context.status(400).result(e.getMessage());
        }

    }

    private void updateAccount(Context context) {
        int client_id = Integer.parseInt(context.header("primary_client_id"));
        int account_id = Integer.parseInt(context.pathParam("id"));
        Account toUpdate = context.bodyAsClass(Account.class);
        Account updated = accountService.update(toUpdate,client_id,account_id);

        context.status(202).json(updated);
    }

    private void createAccount(Context context) {
        int client_id = Integer.parseInt(context.header("primary_client_id"));

        AccountType accountType = AccountType.valueOf(context.queryParam("account_type"));
        double amount = Double.parseDouble(context.queryParam("balance"));
        try {
            Account createdAccount = accountService.create(accountType,amount,client_id);
            context.status(201).json(createdAccount);
        }catch (InvalidInputException e){
            context.status(400).result(e.getMessage());
        }

    }


    private void getAllAccounts(Context context) {
        int client_id =  Integer.parseInt(context.header("primary_client_id"));
        List<Account> accountList = accountService.findAll(client_id);
        if(accountList.isEmpty()){
            context.status(400).result("No accounts associated");
        }
        context.status(200).json(accountList);
    }
}
