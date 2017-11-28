package Database;

import java.beans.Statement;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author helmisatria
 */
public class Database {
    private final String server = "jdbc:mysql://localhost:3306/impalv1";
    private final String dbuser = "root";
    private final String dbpasswd = "helmi232";
    private final Statement statement = null;
    private final Connection connection = null;
    
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(server, dbuser, dbpasswd);
//            JOptionPane.showMessageDialog(null, "Connected!");
            return con;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Not connected");
            return null;
        }
    }
}
