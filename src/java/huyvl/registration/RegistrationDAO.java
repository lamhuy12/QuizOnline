/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.registration;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class RegistrationDAO implements Serializable {

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte hash[] = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String encodedPassword = Base64.getEncoder().encodeToString(hash);
        return encodedPassword;
    }

    public String checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String role = "failed";

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String hashPassword = hashPassword(password);
                String sql = "select Role \n"
                        + "from Registration \n"
                        + "where Email = ? and Password = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, hashPassword);
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getString("Role");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return role;
    }

    public String getFullname(String email) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Fullname "
                        + "from Registration "
                        + "where Email =?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String fullname = rs.getString("Fullname");
                    return fullname;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createAccount(String email, String password,
            String fullname, String role, String status) throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String hashPassword = hashPassword(password);
                String sql = "Insert into Registration(Email, Password, Fullname, Role, Status) "
                        + "Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, hashPassword);
                stm.setString(3, fullname);
                stm.setString(4, role);
                stm.setString(5, status);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }
}
