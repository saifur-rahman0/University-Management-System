package application.department;

import application.common.DataBaseConnection;

import java.sql.*;

/*
 * Title : RollNumberData.java
 * Purpose : Handling all the data related to rollnumber
 */
public class RollNumberData {
    static Connection con = DataBaseConnection.getConnection();

    public static void closeConnection() throws SQLException {
        con.close();
    }

    public void adddata(String deptcode, int sem, long rollnumber) {
        String query = "insert into Rollgenerator values(?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, deptcode);
            pr.setInt(2, sem);
            pr.setLong(3, rollnumber);
            pr.executeUpdate();
            pr.close();

        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    public long getMasterRollNumber(String deptcode, int sem) {
        long rollnumber = -1;
        String query = "select rollnumber from rollgenerator where Departmentcode='" + deptcode + "' and semoryear=+" + sem;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            try {
                rollnumber = rs.getLong(1);
            } catch (Exception exp) {
                rollnumber = -1;
            }
            return rollnumber;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rollnumber;
    }

    public long getRollNumber(String deptcode, int sem) {

        long rollnumber = this.getMasterRollNumber(deptcode, sem) + 1;
        if (rollnumber == 0) {
            return 0;
        }

        String query = "select rollnumber from students where Departmentcode='" + deptcode + "' and semoryear=+" + sem;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (isExist(deptcode, sem, rollnumber) == 0) {
                    break;
                }
                rollnumber++;

            }

            return rollnumber;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rollnumber;
    }

    public int isExist(String deptcode, int sem, long rollnumber) {
        int result = 0;
        String query = "select rollnumber from students where Departmentcode='" + deptcode + "' and semoryear=+" + sem + " and rollnumber=" + rollnumber;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                result++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
