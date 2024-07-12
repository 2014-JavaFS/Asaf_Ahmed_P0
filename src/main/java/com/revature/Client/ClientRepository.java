package com.revature.Client;

import com.revature.util.ConnectionManager;
import com.revature.util.Repository;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements Repository<Client> {
    private Connection connection =null;
    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException, FileNotFoundException {
        if(connection==null){
            connection = ConnectionManager.getConnection();
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void save(Client client) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into client(client_name, client_email, client_password) values(?,?,?)");
            stmt.setString(1,client.getName());
            stmt.setString(2,client.getEmail());
            stmt.setString(3,client.getPassword());
            int rs = stmt.executeUpdate();
            if(rs>0){
                System.out.println("Client Created Successfully");
            }else {
                System.out.println("Cant insert this client");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Client> findById(int id) {
        Client client = new Client();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from client where client_id =?");
            stmt.setInt(1,id);
            ResultSet rs =stmt.executeQuery();
            while (rs.next()){
                int client_id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                client.setClientId(client_id);
                client.setName(name);
                client.setEmail(email);
                client.setPassword(password);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(client);

    }

    @Override
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from client");
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                clientList.add(new Client(id,name,email,password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }

    @Override
    public void update(Client client) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update client set client_name =?, client_email =?, client_password =? where client_id = ?");
            stmt.setString(1,client.getName());
            stmt.setString(2,client.getEmail());
            stmt.setString(3,client.getPassword());
            stmt.setInt(4,client.getClientId());
            int rs = stmt.executeUpdate();
            if(rs>0){
                System.out.println("Update Succeeded");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from client where client_id = ?");
            stmt.setInt(1,id);
            int rs = stmt.executeUpdate();
            if(rs>0){
                System.out.println("Deletion Success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
