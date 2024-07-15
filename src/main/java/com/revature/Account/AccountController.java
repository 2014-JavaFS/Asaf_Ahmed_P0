package com.revature.Account;

import com.revature.util.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AccountController implements Controller {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.get("/accounts", this::getAllAccounts);
        app.get("/accounts/{id}", this::getAccountById);
        app.post("/accounts", this::createAccount);
        app.put("/accounts/{id}", this::updateAccount);
        app.patch("/accounts/{deposit}", this::depositAccount);
        app.patch("/accounts/{withdrawal}", this::withdrawAccount);
        app.delete("/accounts/{id}", this::deleteAccount);
    }

    private void deleteAccount(Context context) {
        int id = Integer.parseInt(context.pathParam("id"));
        accountService.delete(id);
        context.status(204).result("account deleted");
    }

    private void withdrawAccount(Context context) {
        int client_id = Integer.parseInt(context.header("client_id"));
        double amount = Double.parseDouble(context.pathParam("withdrawal"));
        accountService.withDraw(client_id,amount);
        context.status(202).result("withdrawal succeeded");
    }

    private void depositAccount(Context context) {
        int client_id = Integer.parseInt(context.header("client_id"));
        double amount = Double.parseDouble(context.pathParam("deposit"));
        accountService.deposit(client_id,amount);
        context.status(202).result("deposit succeeded");
    }

    private void updateAccount(Context context) {
    }

    private void createAccount(Context context) {
        Account account = context.bodyAsClass(Account.class);
        Account createdAccount = accountService.create(account);
        context.status(201).json(createdAccount);

    }

    private void getAccountById(Context context) {

    }

    private void getAllAccounts(Context context) {

    }
}
