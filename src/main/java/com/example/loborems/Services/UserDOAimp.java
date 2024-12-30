package com.example.loborems.Services;

import com.example.loborems.Interfaces.UserDOA;
import com.example.loborems.models.Permission;
import com.example.loborems.models.User;
import com.example.loborems.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDOAimp implements UserDOA {
    private static final Logger logger = Logger.getLogger(UserDOAimp.class.getName());
    private final SessionFactory sessionFactory;

    public UserDOAimp() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Save the user
            session.save(user);

            // Save or update associated role if provided
            if (user.getRole() != null) {
                session.saveOrUpdate(user.getRole());
            }

            // Save or update associated permissions if provided
            if (user.getPermissions() != null && !user.getPermissions().isEmpty()) {
                for (Permission permission : user.getPermissions()) {
                    session.saveOrUpdate(permission);
                }
            }

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.severe("Error during save: " + e.getMessage());
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Update the user
            session.update(user);

            // Update associated role if provided
            if (user.getRole() != null) {
                session.saveOrUpdate(user.getRole());
            }

            // Update associated permissions if provided
            if (user.getPermissions() != null && !user.getPermissions().isEmpty()) {
                for (Permission permission : user.getPermissions()) {
                    session.saveOrUpdate(permission);
                }
            }

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.severe("Error during update: " + e.getMessage());
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Reload the user to ensure we have a managed entity
            User managedUser = session.get(User.class, user.getId());

            if (managedUser != null) {
                // Clear permissions
                if (managedUser.getPermissions() != null) {
                    managedUser.getPermissions().clear();
                    session.flush();
                }

                // Clear role reference
                managedUser.setRole(null);
                session.flush();

                // Delete the user
                session.delete(managedUser);
            }

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.severe("Error during delete: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            logger.severe("Error getting all users: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public User findClient(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                Hibernate.initialize(user.getRole());
                Hibernate.initialize(user.getPermissions());
            }
            return user;
        } catch (Exception e) {
            logger.severe("Error finding client: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public User getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            if (user != null) {
                Hibernate.initialize(user.getRole());
                Hibernate.initialize(user.getPermissions());
            }
            return user;
        } catch (Exception e) {
            logger.severe("Error getting user by email: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public User getUserWithDetails(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                Hibernate.initialize(user.getRole());
                Hibernate.initialize(user.getPermissions());
            }
            return user;
        } catch (Exception e) {
            logger.severe("Error getting user with details: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}