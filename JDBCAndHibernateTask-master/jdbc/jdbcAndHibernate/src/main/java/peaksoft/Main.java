package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDaoJDBC = new UserDaoHibernateImpl();
//       userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Azamat", "Baitashov", (byte) 35);
        userDaoJDBC.saveUser("Kuban", "Nurdinov", (byte) 35);
         List<User> users= userDaoJDBC.getAllUsers();
//        System.out.println(users.size());
//        System.out.println(users.get(1));
//        userDaoJDBC.removeUserById(2);
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.saveUser("Beksultan", "Mamatkadyr uulu" , (byte) 17);
//        userDaoJDBC.removeUserById(1);



//        Util.getSessionFactory();

    }
}
