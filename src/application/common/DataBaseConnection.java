package application.common;

import java.sql.*;

/*
 * Title : DataBaseConnection.java
 * Purpose : For database connection
 */
public class DataBaseConnection {
    static Connection con = null;
    //local database
    static final String url = "jdbc:mysql://localhost:3308/collegedata";
    static final String uname = "root";
    static final String password = "";

    //online database
//    static final String url = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6688694";
//    static final String uname = "sql6688694";
//    static final String password = "2NGtt4N8cx";

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
