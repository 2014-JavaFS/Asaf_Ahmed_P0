package com.revature.util;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    public void establishConnection() throws ClassNotFoundException, SQLException, FileNotFoundException;
    public void closeConnection() throws SQLException ;
    void save(T object);
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T object);
    void delete(int id);
}
