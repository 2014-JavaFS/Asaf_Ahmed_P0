package com.revature.Client;

import com.revature.Account.AccountService;
import com.revature.util.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Optional;

public class ClientController implements Controller {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;

    }
    @Override
    public void registerRoutes(Javalin app) {
        app.get("/clients", this::getAllClients);
        app.get("/clients/{id}", this::getClientById);
        app.post("/clients", this::createClient);
        app.patch("/clients/", this::updateClient);
        app.delete("/clients/", this::deleteClient);

    }


    public void getAllClients(Context ctx){
        List<Client> clients = clientService.findAll();
        ctx.json(clients);
    }
    public void getClientById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Client> client = clientService.findById(id);
        if(client.isPresent()){
            ctx.json(client.get());
        }else {
            ctx.status(404).result("Client not found");
        }

    }
    public void createClient(Context ctx){
        Client client = ctx.bodyAsClass(Client.class);
        Client createdCLient = clientService.create(client);
        ctx.status(201).json(createdCLient).result("client created");

    }

    public void updateClient(Context ctx){
        int id = Integer.parseInt(ctx.header("primary_client_id"));
        Client client = ctx.bodyAsClass(Client.class);
        client.setClientId(id);
        clientService.update(client);
        ctx.status(200).result("Client updated");
    }
    public void deleteClient(Context ctx){
        int id = Integer.parseInt(ctx.header("primary_client_id"));
        clientService.delete(id);
        ctx.status(200).result("Client deleted");
    }


}
