package ru.geekbrains;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("-----------------------Whith JDBC-----------------------");
        new Jdbc();
        System.out.println("\n-----------------------Whith JPA-----------------------");
        new Jpa();
    }

    }