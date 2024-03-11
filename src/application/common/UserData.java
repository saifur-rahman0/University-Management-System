package application.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.teacher.Teacher;
import application.student.Student;


/*
 * Title : UserData.java
 * Purpose : For getting all the data related to user login activity
 */
public class UserData {

    Connection con = DataBaseConnection.getConnection();

    public int addStudentLoginTime(Student s) {
        int result = 0;
        try {
            String query = "insert into users values(?,?,?,?,?,?)";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, 0);
            pr.setString(2, s.getDeptCode());
            pr.setInt(3, s.getSemorYear());
            pr.setString(4, s.getUserId());
            pr.setString(5, TimeUtil.getCurrentTime());
            pr.setString(6, "Student");
            result = pr.executeUpdate();

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public int addTeacherLoginTime(Teacher s) {
        int result = 0;
        try {
            String query = "insert into users values(?,?,?,?,?,?)";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, 0);
            pr.setString(2, s.getDeptCode());
            pr.setInt(3, s.getSemorYear());
            pr.setString(4, s.getTeacherId() + "");
            pr.setString(5, TimeUtil.getCurrentTime());
            pr.setString(6, "Teacher");
            result = pr.executeUpdate();

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public ArrayList<User> getUserInfo(String condition) {
        ArrayList<User> list = new ArrayList<User>();
        try {
            String query = "select Departmentcode as 'Cource',semoryear as 'Sem/Year',userid as 'Userid',logintime as 'Login Time',userprofile as 'User Profile' from users " + condition + " order by sr_no desc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setDeptCode(rs.getString(1));
                user.setSemorYear(rs.getInt(2));
                user.setUserId(rs.getString(3));
                user.setLoginTime(rs.getString(4));
                user.setUserProfile(rs.getString(5));
                list.add(user);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

}
