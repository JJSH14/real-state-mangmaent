package com.example.loborems.interfaces;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.loborems.models.Client;
import com.example.loborems.util.HibernateUtil;

public class DOAClient implements DOA<Client> {

    private final SessionFactory sessionFactory;

    public DOAClient() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public Client findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        }
    }

    @Override
    public List<Client> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }
}
