package com.revature;

import com.revature.Account.AccountController;
import com.revature.Account.AccountRepository;
import com.revature.Account.AccountService;
import com.revature.Client.Client;
import com.revature.Client.ClientController;
import com.revature.Client.ClientRepository;
import com.revature.Client.ClientService;
import com.revature.util.auth.AuthController;
import com.revature.util.auth.AuthService;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Airport Management System is up and running.....");

        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson());
        });

        ClientRepository clientRepository = new ClientRepository();
        ClientService clientService = new ClientService(clientRepository);
        ClientController clientController = new ClientController(clientService);
        clientController.registerRoutes(app);

        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(accountService);
        accountController.registerRoutes(app);

        AuthService authService = new AuthService(clientService);
        AuthController authController = new AuthController(authService);
        authController.registerRoutes(app);
        app.start();
    }
}