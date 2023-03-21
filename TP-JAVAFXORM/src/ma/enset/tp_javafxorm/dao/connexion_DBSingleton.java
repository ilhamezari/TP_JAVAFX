package ma.enset.tp_javafxorm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion_DBSingleton {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stock","root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        return connection;
    }
}
