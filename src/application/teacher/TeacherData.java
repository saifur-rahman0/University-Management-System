package application.teacher;

import application.common.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

/*
 * Title : TeacherData.java
 * Purpose : Handling all the data related to Teacher
 */
public class TeacherData {
    static Connection con = DataBaseConnection.getConnection();
    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalTeacher() {
        int totalt = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from teachers");
            rs.next();
            totalt = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalt;
    }

    public int getTeacher(String deptcode, int sem) {
        int t = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from teachers where Departmentcode='" + deptcode + "' and semoryear=" + sem);
            rs.next();
            t = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public int createTeacherID() {
        int id = 101;
        try {
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from teachers");
            rs.next();
            id = id + rs.getInt(1);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return id;
    }

    public ResultSet getTeacherInfo(String condition) {
        ResultSet rs = null;
        try {
            String query = "select teacher_id as 'Teacher ID',teacherName as 'Teacher Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from teachers  " + condition + " order by teacher_id";
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchTeacher(String query) {
        ResultSet rs = null;
        query += " order by teacher_id";
        try {
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return rs;
    }

    public String getTeacherName(String teacherId) {
        try {
            String query = "select teacherName from teachers where teacher_id=" + teacherId;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getString(1);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return "";
    }

    public int addTeacherData(Teacher t) {
        int result = 0;
        String query = "insert into teachers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, t.getTeacherId());
            pr.setString(2, t.getTeacherName());
            pr.setString(3, t.getAddress());
            pr.setString(4, t.getEmailId());
            pr.setString(5, t.getContactNumber());
            pr.setString(6, t.getQualification());
            pr.setString(7, t.getExperience());
            pr.setString(8, t.getBirthDate());
            pr.setString(9, t.getGender());
            pr.setBytes(10, t.getProfilePicInBytes());
            pr.setString(11, "Not Assigned");
            pr.setInt(12, 0);
            pr.setString(13, "Not Assigned");
            pr.setString(14, "Not Assigned");
            pr.setInt(15, 0);
            pr.setString(16, null);
            pr.setString(17, t.getBirthDate());
            pr.setString(18, t.generateJoinedDate());
            result = pr.executeUpdate();
            pr.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public int updateTeacherData(Teacher fold, Teacher f) {
        int result = 0;
        String query = "update teachers set teacher_id=? , teacherName=? ,address=? , emailid=? , contactnumber=? , qualification=? , experience=? , birthdate=? , gender=? , profilepic=?,lastlogin=? where teacher_id=" + fold.getTeacherId();
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, f.getTeacherId());
            pr.setString(2, f.getTeacherName());
            pr.setString(3, f.getAddress());
            pr.setString(4, f.getEmailId());
            pr.setString(5, f.getContactNumber());
            pr.setString(6, f.getQualification());
            pr.setString(7, f.getExperience());
            pr.setString(8, f.getBirthDate());
            pr.setString(9, f.getGender());
            pr.setBytes(10, f.getProfilePicInBytes());
            pr.setString(11, f.getLastLogin());
            result = pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Teacher getTeacherInfo(int row) {
        Teacher t = new Teacher();
        String query = "select * from teachers where sr_no=" + row;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            t.setTeacherId(rs.getInt(1));
            t.setTeacherName(rs.getString(2));
            t.setAddress(rs.getString(3));
            t.setEmailId(rs.getString(4));
            t.setContactNumber(rs.getString(5));
            t.setQualification(rs.getString(6));
            t.setExperience(rs.getString(7));
            t.setBirthDate(rs.getString(8));
            t.setGender(rs.getString(9));
            t.setProfilePic(rs.getBytes(10));
            t.setDeptCode(rs.getString(11));
            t.setSemorYear(rs.getInt(12));
            t.setCourse(rs.getString(13));
            t.setPosition(rs.getString(14));
            t.setLastLogin(rs.getString(16));
            t.setPassword(rs.getString(17));
            t.setJoinedDate(rs.getString(18));
            st.close();
            return t;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return t;
    }

    public Teacher getTeacherInfobyId(int teacherId) {
        Teacher f = new Teacher();
        String query = "select * from teachers where teacher_id=" + teacherId;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            f.setTeacherId(rs.getInt(1));
            f.setTeacherName(rs.getString(2));
            f.setAddress(rs.getString(3));
            f.setEmailId(rs.getString(4));
            f.setContactNumber(rs.getString(5));
            f.setQualification(rs.getString(6));
            f.setExperience(rs.getString(7));
            f.setBirthDate(rs.getString(8));
            f.setGender(rs.getString(9));
            f.setProfilePic(rs.getBytes(10));
            f.setDeptCode(rs.getString(11));
            f.setSemorYear(rs.getInt(12));
            f.setCourse(rs.getString(13));
            f.setPosition(rs.getString(14));
            f.setLastLogin(rs.getString(16));
            f.setPassword(rs.getString(17));
            f.setJoinedDate(rs.getString(18));
            st.close();
            return f;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return f;
    }

    public Teacher getTacherInfobyUserId(String teacherId) {
        Teacher f = new Teacher();
        teacherId = teacherId.replaceAll("\\s", "");
        String query = "select * from teachers where teacher_id=" + teacherId;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            f.setTeacherId(rs.getInt(1));
            f.setTeacherName(rs.getString(2));
            f.setAddress(rs.getString(3));
            f.setEmailId(rs.getString(4));
            f.setContactNumber(rs.getString(5));
            f.setQualification(rs.getString(6));
            f.setExperience(rs.getString(7));
            f.setBirthDate(rs.getString(8));
            f.setGender(rs.getString(9));
            f.setProfilePic(rs.getBytes(10));
            f.setDeptCode(rs.getString(11));
            f.setSemorYear(rs.getInt(12));
            f.setCourse(rs.getString(13));
            f.setPosition(rs.getString(14));
            f.setLastLogin(rs.getString(16));
            f.setPassword(rs.getString(17));
            f.setJoinedDate(rs.getString(18));
            st.close();
            return f;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return f;
    }

    public int assignCourse(Teacher fold, Teacher f) {
        int result = 0;
        try {
            if (!fold.getCourse().equals(f.getCourse()) || !fold.getDeptCode().equals(f.getDeptCode()) || fold.getSemorYear() != f.getSemorYear() || !fold.getPosition().equals(f.getPosition())) {
                String query = "update teachers set Departmentcode='" + f.getDeptCode() + "',semoryear=" + f.getSemorYear() + ",course='" + f.getCourse() + "',position='" + f.getPosition() + "' where teacher_id=" + f.getTeacherId();
                PreparedStatement pr = con.prepareStatement(query);
                result = pr.executeUpdate();
                pr.close();
            }
        }catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public ResultSet getTeacherCourseInfo(String condition) {
        ResultSet rs = null;
        try {
            String query = "select teacher_id as 'Teacher ID',teacherName as 'Teacher Name',Departmentcode as 'Class',semoryear as 'Sem/Year',course as 'Course',position as 'Position' from teachers " + condition + " order by teacher_id asc";
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return rs;
    }

    public boolean checkPassword(String teacherId, String password) {
        boolean result = false;
        try {
            if (teacherId.isEmpty() || teacherId.equalsIgnoreCase(" Enter Teacher user-id")) {
                JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password", "Error", JOptionPane.ERROR_MESSAGE);
                result = false;
            } else {
                String query = "select count(*) from teachers where teacher_id=" + teacherId + " and password='" + password + "'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                rs.next();
                if (rs.getInt(1) > 0) {
                    result = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public String getLastLogin(String userid) {
        try {
            String query = "select lastlogin from teachers where teacher_id=" + userid;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getString(1);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

    public Image getProfilePic(String userid) {
        Image image = null;
        try {
            String query = "select profilepic from teachers where teacher_id=" + userid;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            byte[] imagedata = rs.getBytes(1);
            image = Toolkit.getDefaultToolkit().createImage(imagedata);
            rs.close();
            st.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return image;
    }

    public int changePassword(String userid, String password) {
        try {
            String query = "update teachers set password='" + password + "' where teacher_id=" + userid;
            PreparedStatement pr = con.prepareStatement(query);
            return pr.executeUpdate();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return 0;
    }
}
