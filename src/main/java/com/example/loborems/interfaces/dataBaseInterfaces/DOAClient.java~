package com.example.loborems.modules;

import com.example.loborems.interfaces.DOA;
import com.example.loborems.utile.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DOAClient implements DOA<Client> {

    private final SessionFactory sessionFactory;

    public DOAClient() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Client entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Client entity) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Client entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
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
        return null;
    }
}
