package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDao userDaoJDBC = new UserDaoJdbcImpl();
       userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Azamat", "Baitashov", (byte) 35);
        userDaoJDBC.saveUser("Kuban", "Nurdinov", (byte) 35);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.removeUserById(2);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
