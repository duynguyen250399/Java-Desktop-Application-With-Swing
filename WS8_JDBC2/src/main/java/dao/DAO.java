package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {

    private static String url = "jdbc:mysql://localhost:3306/bookdb";
    private static String user = "root";
    private static String pass = "250349";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Can not find driver!");
        }
    }

    public void openConnection() {

        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("open connection successfully!");
        } catch (SQLException ex) {
            System.out.println("Can not connection to MySQL");
        }

    }

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            System.out.println("connection closed");
        } catch (SQLException e) {
            System.out.println("Error while closing connection!");
        }
    }
}
