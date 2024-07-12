package com.revature.Client;

import com.revature.Account.AccountService;
import com.revature.util.enums.AccountType;

public class ClientController {
    private final ClientService clientService;
    private final AccountService accountService;

    public ClientController(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    public void registerClient(String name, String email, String password){

        String pattern = ("www.*.com");
        if(email.matches(pattern)){

            clientService.create(name,email,password);
            System.out.println("Client was created successfully");
        }else {
            System.out.println("Problem with data input");
        }


    }
    public void createAccount(AccountType type, int clientId, double balance){

        accountService.create(clientId,balance, type);

    }
}
