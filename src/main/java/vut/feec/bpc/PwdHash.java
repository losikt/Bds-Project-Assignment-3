package vut.feec.bpc;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PwdHash {
    public static String hashPassword (String pwd){
        Argon2 argon2 = Argon2Factory.create();
        return argon2.hash(10, 65536, 1, pwd.toCharArray());
    }
    public static Boolean validCredentials (Connection conn, String username, String pwd){
        String sql = "select password from bds.users where username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username );

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String pwdHash = rs.getString("password");
                Argon2 argon2 = Argon2Factory.create();
                return argon2.verify(pwdHash, pwd.toCharArray());
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
