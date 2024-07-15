package com.revature.util.auth;

import com.revature.Client.Client;
import com.revature.Client.ClientService;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

/**
 * Authentication Service to check our member database for matching information based on users input. Separated for
 * security concerns. REQUIRES MemberService to be injected.
 *
 *
 */
public class AuthService {
    private final ClientService clientService; // Assuming a MemberService instance

    public AuthService(ClientService clientService) {
        this.clientService = clientService;
    }

    public Client login(String email, String password) throws AuthenticationException {
        Optional<Client>client =clientService.findByEmailPassword(email, password);
        if(client.isEmpty()) throw new AuthenticationException("Invalid member credentials, please try again");
        return client.get();
    }


}
