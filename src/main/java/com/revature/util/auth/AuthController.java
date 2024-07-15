package com.revature.util.auth;

import com.revature.Client.Client;
import com.revature.util.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import javax.security.sasl.AuthenticationException;

public class AuthController implements Controller {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.post("/login", this::postLogin);
        app.get("/user-info", this::getRedirect);
    }

    private void getRedirect(Context ctx){
        ctx.redirect("https://i.pinimg.com/736x/6a/6d/11/6a6d1124cf69e5588588bc7e397598f6.jpg");
    }

    private void postLogin(Context ctx){
        String email = ctx.queryParam("email");
        String password = ctx.queryParam("password");

        try {
            Client client = authService.login(email, password);
            ctx.header("clientId", String.valueOf(client.getClientId()));
            ctx.status(200);
        } catch (AuthenticationException e) {
            ctx.status(HttpStatus.UNAUTHORIZED);
        }
    }
}
