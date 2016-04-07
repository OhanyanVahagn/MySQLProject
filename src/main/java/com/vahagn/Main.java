package com.vahagn;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Vahagn on 06.04.2016.
 */
public class Main {
    public static final String USERNAME="root";
    public static final String PASSWORD="root";
    public static final String URL="jdbc:mysql://localhost:3306/mydbtest";

    public static void main(String[] args) {


        try(Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD); Statement statement=connection.createStatement()) {
            Driver driver=new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            statement.execute("insert into users (name, age, email) VALUES ('Steve',45,'steve@mail.ru')");
           ResultSet resultSet= statement.executeQuery("select * from users");
           while (resultSet.next()){
               User user=new User();
               int id=resultSet.getInt(1);
               String name=resultSet.getString(2);
               int age=resultSet.getInt(3);
               String email=resultSet.getString(4);
               user.setId(id);
               user.setName(name);
               user.setAge(age);
               user.setEmail(email);
               System.out.println(user);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
