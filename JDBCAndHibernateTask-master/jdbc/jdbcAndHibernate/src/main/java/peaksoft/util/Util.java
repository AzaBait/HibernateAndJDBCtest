package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.model.User;

import javax.security.auth.login.AppConfigurationEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres"
            );
            System.out.println("CONNECTED!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return connection;
    }


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .setProperty(Environment.DRIVER, "org.postgresql.Driver")
                    .setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect")
                    .setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/postgres")
                    .setProperty(Environment.USER, "postgres")
                    .setProperty(Environment.PASS, "postgres")
                    .setProperty(Environment.HBM2DDL_AUTO, "create")
                    .setProperty(Environment.SHOW_SQL, "true")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
