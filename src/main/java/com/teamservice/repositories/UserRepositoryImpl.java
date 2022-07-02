package com.teamservice.repositories;

import com.teamservice.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Override
    public void delete(User entity) {
        deleteById(entity.getTelegramId());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            return;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int updated = session.createQuery("delete User u where u.telegramId = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            if (updated != 1) {
                throw new IllegalArgumentException();
            }
            transaction.commit();
        } catch (IllegalArgumentException e) {
            System.err.println("Entity is not present in the table");
            throw new RuntimeException();
        }
    }

    @Override
    public boolean existsById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id) != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select u from User u", User.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(User.class, id));
        }
    }

    @Override
    public User getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.load(User.class, id);
        }
    }


    @Override
    public User save(User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getUsersNotInGroup(Long groupId) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select u from User u where u.group.id <> :groupId", User.class)
                    .setParameter("groupId", groupId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> dayOverdue(Long userId) {
        try(Session session = sessionFactory.openSession()){
            return session
                    .createQuery("select u from User u where current_date - lastModified >= 1", User.class)
                    .getResultList();
            }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
    }
}
