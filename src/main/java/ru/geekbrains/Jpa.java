package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Jpa {

    public Jpa() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book b1 = new Book();
            Book b2 = new Book();
            Book b3 = new Book();
            Book b4 = new Book();
            Book b5 = new Book();
            Book b6 = new Book();
            Book b7 = new Book();
            Book b8 = new Book();
            Book b9 = new Book();
            Book b10 = new Book();

            b1.setName("Темная башня");
            b1.setAuthor("Стивен Кинг");

            b2.setName("Благие знамения");
            b2.setAuthor("Терри Пратчетт");

            b3.setName("Американские боги");
            b3.setAuthor("Нил Гейман");

            b4.setName("Когда мы были дельфинами");
            b4.setAuthor("Терри Пратчетт");

            b5.setName("Серафина");
            b5.setAuthor("Рэйчел Хартман");

            b6.setName("Пехотная баллада");
            b6.setAuthor("Терри Пратчетт");

            b7.setName("Гниль и руины");
            b7.setAuthor("Джонатан Мэйберра");

            b8.setName("Властелин колец");
            b8.setAuthor("Джон Рональд Роуэл Толкиен");

            b9.setName("Кладбище домашних животных");
            b9.setAuthor("Стивен Кинг");

            b10.setName("Зерцалия");
            b10.setAuthor("Евегний Гаглоев");

            session.persist(b1);
            session.persist(b2);
            session.persist(b3);
            session.persist(b4);
            session.persist(b5);
            session.persist(b6);
            session.persist(b7);
            session.persist(b8);
            session.persist(b9);
            session.persist(b10);
            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery("select b from Book b where b.author = 'Стивен Кинг'", Book.class)
                    .getResultList();

            System.out.println(books);
        }
        sessionFactory.close();
    }
}
