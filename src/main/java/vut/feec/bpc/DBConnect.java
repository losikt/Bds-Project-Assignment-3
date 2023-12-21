package vut.feec.bpc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection dbConnect(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void dbDisconnect (Connection conn){
        try {
            conn.close();
            System.out.println("Connection to the PostgreSQL server successfully closed.");
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }
}
