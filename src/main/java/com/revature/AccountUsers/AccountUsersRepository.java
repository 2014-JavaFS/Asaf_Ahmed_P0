package com.revature.AccountUsers;

import com.revature.util.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountUsersRepository implements Repository<AccountUsers> {
    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {

    }

    @Override
    public void save(AccountUsers object) {

    }

    @Override
    public Optional<AccountUsers> findById(int id) {
        return Optional.empty();
    }


    @Override
    public List<AccountUsers> findAll() {
        return List.of();
    }

    @Override
    public void update(AccountUsers object) {

    }

    @Override
    public void delete(int id) {

    }
}
