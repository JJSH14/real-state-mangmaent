package com.example.loborems.models.services;

import com.example.loborems.models.Interfaces.PropertyDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.loborems.models.Property;
import com.example.loborems.util.HibernateUtil;

public class PropertyDAOImpl implements PropertyDAO {

    @Override
    public void save(Property property) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Property getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Property property = null;
        try {
            property = session.get(Property.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return property;
    }

    @Override
    public void update(Property property) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Property property) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
