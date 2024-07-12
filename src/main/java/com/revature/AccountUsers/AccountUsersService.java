package com.revature.AccountUsers;

import com.revature.util.Serviceable;

import java.util.List;
import java.util.Optional;

public class AccountUsersService implements Serviceable<AccountUsers> {
    @Override
    public List<AccountUsers> findAll() {
        return List.of();
    }

    @Override
    public Optional<AccountUsers> findById(int id) {
        return Optional.empty();
    }


    public AccountUsers create(AccountUsers object) {
        return null;
    }

    @Override
    public void delete(int id) {

    }


    public void update(AccountUsers object) {

    }
}
