package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private String sql;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "  id BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(64) NOT NULL,\n" +
                    "  last_name VARCHAR(64) NOT NULL,\n" +
                    "  age TINYINT NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  UNIQUE INDEX id_UNIQUE (id ASC));";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            sql = "DROP TABLE IF EXISTS users;";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String last_name, byte age) {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            User user = new User(name, last_name, age);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            User user = (User) session.get("users", id);
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> users = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            users = (List<User>)session.createQuery("FROM User").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            String hql = "DELETE FROM User";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
