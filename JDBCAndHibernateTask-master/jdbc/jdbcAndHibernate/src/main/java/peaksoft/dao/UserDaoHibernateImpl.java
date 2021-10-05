package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age INTEGER)").executeUpdate();

            session.getTransaction().commit();
            session.close();
            System.out.println("Created successfully ");
        } catch (HibernateException e) {
            System.out.println("hsohs");
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();

            session.createSQLQuery("drop table users").executeUpdate();
//            session.createQuery("delete ")
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted ");
        } catch (
                Exception e) {
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
        System.out.println("Added successfully " + session);

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User e = session.get(User.class, id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            users = session.createQuery("from users").getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + users.size() + " users");
        } catch (
                Exception e) {
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("delete from users").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all data in users");
    }
}

