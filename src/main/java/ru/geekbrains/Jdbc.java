package ru.geekbrains;

import java.sql.*;

public class Jdbc {
    private static int counter = 0;
    public Jdbc() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db");
        prepareTable(connection);
        insertDataToTable(connection);
        findInTable(connection);

        connection.close();
    }

    private static void findInTable(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select id, name, author from book where author = 'Стивен Кинг'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");

                printResult(id, name, author);


            }
        } catch (SQLException ignore) {

        }
    }

    private static void printResult(int id, String name, String author) {
        System.out.println("[" + counter++ + "] id = " + id + ", book name = " + name + ", author = " + author);
    }

    private static void insertDataToTable(Connection connection) {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                                    insert into book(id, name, author)
                                    values (1, 'Зерцалия', 'Евегний Гаглоев'),
                                    (2, 'Темная Башня', 'Стивен Кинг'),
                                    (3, 'Кладбище домашних животных', 'Стивен Кинг'),
                                    (4, 'Властелин колец', 'Джон Рональд Роуэл Толкиен'),
                                    (5, 'Гниль и руины', 'Джонатан Мэйберра'),
                                    (6, 'Пехотная баллада', 'Терри Пратчетт'),
                                    (7, 'Серафина', 'Рэйчел Хартман'),
                                    (8, 'Когда мы были дельфинами', 'Терри Пратчетт'),
                                    (9, 'Благие знамения', 'Терри Пратчетт'),
                                    (10, 'Американские боги', 'Нил Гейман')
                    """);
        } catch (SQLException ignore) {
            System.out.println("didnt insert");
        }
    }

    private static void prepareTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table book (
                        id bigint,
                        name varchar(255),
                        author varchar(255)
                    )
                    """);
        } catch (SQLException ignored) {
            System.out.println("didnt create table");
        }
    }


}
