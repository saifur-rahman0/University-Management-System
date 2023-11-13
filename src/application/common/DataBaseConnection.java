package application.common;

import java.sql.*;

/*
 * Title : DataBaseConnection.java
 * Purpose : For database connection
 */
public class DataBaseConnection {
    static Connection con = null;
    static final String url = "jdbc:mysql://localhost:3308/Collegedata";
    static final String uname = "root";
    static final String password = "";

    public static Connection getConnection() {
        if (con != null) {
            return con;
        }
        else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, uname, password);
                return con;
            }
            catch (Exception exp) {
                exp.printStackTrace();
                return con;
            }
        }
    }

    public static boolean checkconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, password);
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
