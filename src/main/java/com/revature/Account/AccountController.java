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
    }

    private void withdrawAccount(Context context) {
    }

    private void depositAccount(Context context) {
    }

    private void updateAccount(Context context) {
    }

    private void createAccount(Context context) {
    }

    private void getAccountById(Context context) {
    }

    private void getAllAccounts(Context context) {
    }
}
