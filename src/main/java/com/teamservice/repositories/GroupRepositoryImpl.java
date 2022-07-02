package com.teamservice.repositories;

import com.teamservice.models.Group;
import com.teamservice.models.User;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class GroupRepositoryImpl implements GroupRepository {

    private SessionFactory sessionFactory;

    @Override
    public void delete(Group entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            return;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int updated = session.createQuery("delete Group g where g.id = :id")
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
            return session.get(Group.class, id) != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Group> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select g from Group g", Group.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Group> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(Group.class, id));
        }
    }

    @Override
    public Group getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.load(Group.class, id);
        }
    }

    @Override
    public Group save(Group entity) {
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
    public void removeByTeamLeadId(Long teamLeadId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int rowsUpdated =
                    session.createQuery("delete from Group g where g.teamLead.telegramId = :teamLeadId")
                            .setParameter("teamLeadId", teamLeadId)
                            .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void addUser(Long groupId, User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (!existsById(groupId))
                return;
            Group group = session.get(Group.class, groupId).addUser(user);
            session.saveOrUpdate(group);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
