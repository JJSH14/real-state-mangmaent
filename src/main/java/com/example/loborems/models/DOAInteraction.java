package com.example.loborems.models;

import com.example.loborems.interfaces.DOA;
import com.example.loborems.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DOAInteraction implements DOA<Interaction> {

    private final SessionFactory sessionFactory;

    public DOAInteraction() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Interaction interaction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(interaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Interaction interaction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(interaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Interaction interaction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(interaction);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Interaction findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Interaction.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Interaction> findAll() {
        return null;
    }


}
