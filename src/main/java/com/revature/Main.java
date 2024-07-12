package com.revature;

import com.revature.Account.AccountRepository;
import com.revature.Account.AccountService;
import com.revature.Client.ClientController;
import com.revature.Client.ClientRepository;
import com.revature.Client.ClientService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepository();
        AccountRepository accountRepository = new AccountRepository();
        ClientService clientService = new ClientService(clientRepository);
        AccountService accountService = new AccountService(accountRepository);
        ClientController clientController = new ClientController(clientService,accountService);

        clientController.registerClient("asaf","www.asafbishoy@gmail.com","5691");
    }
}