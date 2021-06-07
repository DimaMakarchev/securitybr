package com.security.br.security.services;

import lombok.SneakyThrows;

import java.sql.*;

public class Main {

    static final String url = "jdbc:mysql://localhost:3306/bro?serverTimezone=UTC";
    static final String name = "root";
    static final String password = "1234";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, name, password);
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        statement.execute("drop table if exists trbr");
//        statement.execute("create table trbr(id int not null  primary key ,name varchar (200))");
//        statement.execute("update trbr set name ='new bro' where id=1");
        ResultSet resultSet=  statement.executeQuery("select count(*) from trbr");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }

        new NewTread().start();
        Thread.sleep(2500);

        ResultSet resultSetNext = statement.executeQuery("select count(*) from trbr");
        while (resultSetNext.next()) {
            System.out.println(resultSetNext.getInt(1));
        }
//        connection.commit();
    }

    static class NewTread extends Thread {
        @SneakyThrows
        @Override
        public void run() {

            Connection connection = DriverManager.getConnection(url, name, password);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.);
            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from trbr");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("name"));
//            }
            statement.execute("insert into trbr (id ,name) values (5,'new bro1')");
            statement.execute("insert into trbr (id ,name) values (6,'new bro2')");
//            statement.execute("insert into trbr (id ,name) values (4,'new bro3')");
//            statement.execute("update trbr set name ='new bro' where id=1");
            connection.commit();
        }
    }
}
