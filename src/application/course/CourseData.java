package application.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.common.DataBaseConnection;
/*
 * Title : CourseData.java
 * Purpose : Handling all the data related to course
 */
public class CourseData {
    static Connection con = DataBaseConnection.getConnection();
    public static void closeConnection() throws SQLException {
        con.close();
    }

    public String checkCoreorOptional(String coursecode) {
        String type = "core";
        try {
            String query = "select coursetype from courses where coursecode='" + coursecode + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            type = rs.getString(1);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return type;
    }

    public boolean isExist(String deptcode, int semoryear, String coursename) {
        try {
            String query = "select coursename from courses where Departmentcode='" + deptcode + "' and semoryear=" + semoryear + " and coursename='" + coursename + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.first()) {
                return true;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public int getTotalCourse() {
        int totalcourse = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from courses");
            while (rs.next()) {
                totalcourse++;
            }
            rs.close();
            st.close();
            return totalcourse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalcourse;
    }

    public String createCoursecode(String deptcode, int sem) {
        int code = 101;
        String coursecode = deptcode + sem + code;
        try {
            String query = "select Departmentcode,semoryear from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                code++;
            }
            coursecode = deptcode + sem + code;
            rs.close();
            st.close();
            return coursecode;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return coursecode;
    }

    public int addCourse(Course co) {
        String query = "insert into courses values(?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, co.getCourseCode());
            pr.setString(2, co.getCourseName());
            pr.setString(3, co.getDeptCode());
            pr.setInt(4, co.getSemorYear());
            pr.setString(5, co.getCourseType());
            pr.setInt(6, co.getMaxTheoryMarks());
            result = pr.executeUpdate();

            pr.close();
            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public String getCourseName(String coursecode) {
        String coursename = null;
        try {
            String query = "select coursename from courses where coursecode='" + coursecode + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            coursename = rs.getString(1);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return coursename;
    }

    public ResultSet getCourseinfo(String deptcode, int sem) {

        ResultSet st = null;
        try {
            String query = "select coursecode as 'Course Code',coursename as 'Course Name',semoryear as 'Sem/Year',coursetype as 'Course Type',marks as 'Marks' from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem;
            PreparedStatement pr = con.prepareStatement(query);
            st = pr.executeQuery();
            return st;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return st;
    }

    public String[] getOptionalCourse(String deptcode, int sem) {
        int totaloptionalcourse = this.gettotalOptionalCourse(deptcode, sem);
        if (totaloptionalcourse > 0) {
            String[] course = new String[totaloptionalcourse + 1];
            course[0] = "---Select Optional Course---";
            String query = "select coursename from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem + " and coursetype='optional'";
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                int i = 1;
                while (rs.next()) {
                    course[i++] = rs.getString(1);
                }
                rs.close();
                st.close();
                return course;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        return null;
    }

    public int gettotalOptionalCourse(String deptcode, int sem) {
        int totalopcourse = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select coursename from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem + " and coursetype='optional'");
            while (rs.next()) {
                totalopcourse++;
            }
            rs.close();
            st.close();
            return totalopcourse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalopcourse;
    }

    public String[] getCourseDept(String deptcode, int sem) {
        int totalcourseindept = this.getTotalCourseinDept(deptcode, sem);
        if (totalcourseindept > 0) {
            String[] course = new String[totalcourseindept + 1];
            course[0] = "---Select Course---";
            String query = "select coursename from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem;
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                int i = 1;
                while (rs.next()) {
                    course[i++] = rs.getString(1);
                }
                rs.close();
                st.close();
                return course;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        return null;
    }

    public int getTotalCourseinDept(String deptcode, int sem) {
        int totalcourseindept = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select coursename from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem);
            while (rs.next()) {
                totalcourseindept++;
            }
            rs.close();
            st.close();
            return totalcourseindept;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalcourseindept;
    }

    public String getCourseCode(String deptcode, int sem, String cousename) {
        String coursecode = null;
        String query = "select coursecode from courses where Departmentcode='" + deptcode + "' and semoryear=" + sem + " and coursename='" + cousename + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            coursecode = rs.getString(1);

        } catch (Exception exp) {
            return null;
        }
        return coursecode;
    }
}
