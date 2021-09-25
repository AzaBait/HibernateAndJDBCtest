package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
