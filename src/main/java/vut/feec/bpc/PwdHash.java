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
    public static int validCredentials(Connection conn, String username, String pwd) {
        String sql = "SELECT u.user_id, u.password, ura.role_id " +
                "FROM bds.users u " +
                "JOIN bds.user_role_assignments ura ON u.user_id = ura.user_id " +
                "WHERE u.username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String pwdHash = rs.getString("password");
                int roleId = rs.getInt("role_id"); // Get the role ID

                Argon2 argon2 = Argon2Factory.create();
                if (argon2.verify(pwdHash, pwd.toCharArray())) {
                    return roleId; // Return role ID on successful login
                } else {
                    return -1; // Return -1 on password mismatch
                }
            } else {
                return -1; // Return -1 if username not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Return -1 on exception
        }
    }
}
