package application.student;

import application.common.DataBaseConnection;
import application.course.CourseData;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

/*
 * Title : StudentData.java
 * Purpose : Handling all the data related to student
 */
public class StudentData {
    static Connection con = DataBaseConnection.getConnection();
    public static void closeConnection() throws SQLException {
        con.close();
    }

    public int addStudent(Student s) {
        int result = 0;
        String query = "insert into students values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, s.getDeptCode());
            pr.setInt(2, s.getSemorYear());
            pr.setLong(3, s.getRollNumber());
            pr.setString(4, s.getOptionalCourse());
            pr.setString(5, s.getFirstName());
            pr.setString(6, s.getLastName());
            pr.setString(7, s.getEmailId());
            pr.setString(8, s.getContactNumber());
            pr.setString(9, s.getBirthDate());
            pr.setString(10, s.getGender());
            pr.setString(11, s.getAddress());
            pr.setString(12, s.getFatherName());
            pr.setString(13, s.getFatherOccupation());
            pr.setString(14, s.getMotherName());
            pr.setString(15, s.getMotherOccupation());
            pr.setBytes(16, s.getProfilePicInBytes());
            pr.setInt(17, 0);//sr no
            pr.setString(18, "");//lastlogin
            pr.setString(19, s.generateUserId());//userid
            pr.setString(20, s.getBirthDate());//password
            pr.setString(21, s.generateAdmissionDate());
            result = pr.executeUpdate();

            pr.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int deleteMarksData(Student s) {
        int result = 0;
        String query = "delete from marks where Departmentcode='" + s.getDeptCode() + "' and semoryear=" + s.getSemorYear() + " and rollnumber=" + s.getRollNumber();
        try {
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public int deleteAttandanceData(Student s) {
        int result = 0;
        String query = "delete from attandance where Departmentcode='" + s.getDeptCode() + "' and semoryear=" + s.getSemorYear() + " and rollnumber=" + s.getRollNumber();
        try {
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public int deleteUsersHistory(Student s) {
        int result = 0;
        String query = "delete from users where userid='" + s.getUserId() + "'";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;

    }

    public int deleteOldOptionalCourseMarks(Student s) {
        int result = 0;
        try {
            String query = "delete from marks where Departmentcode='" + s.getDeptCode() + "' and semoryear=" + s.getSemorYear() + " and rollnumber=" + s.getRollNumber() + " and coursename='" + s.getOptionalCourse() + "'";
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public int updateStudentData(Student sold, Student s) {
        int result = 0;
        String query = "update students set Departmentcode=?,semoryear=?,rollnumber=?,optionalcourse=?,firstname=?,lastname=?,emailid=?,contactnumber=?,dateofbirth=?,gender=?,address=?,fathername=?,fatheroccupation=?,mothername=?,motheroccupation=?,profilepic=?,lastlogin=?,userid=? where Departmentcode='" + sold.getDeptCode() + "' and semoryear=" + sold.getSemorYear() + " and rollnumber=" + sold.getRollNumber();

        //if dept or sem or rollnumber is changed
        if (!s.getDeptCode().equals(sold.getDeptCode()) || s.getSemorYear() != sold.getSemorYear() || s.getRollNumber() != sold.getRollNumber()) {
            //deleting all the data of student from this dept
            this.deleteMarksData(sold);
            this.deleteAttandanceData(sold);
            this.deleteUsersHistory(sold);
        }
        if (!s.getOptionalCourse().equals(sold.getOptionalCourse())) {
            this.deleteOldOptionalCourseMarks(sold);
        }
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, s.getDeptCode());
            pr.setInt(2, s.getSemorYear());
            pr.setLong(3, s.getRollNumber());
            pr.setString(4, s.getOptionalCourse());
            pr.setString(5, s.getFirstName());
            pr.setString(6, s.getLastName());
            pr.setString(7, s.getEmailId());
            pr.setString(8, s.getContactNumber());
            pr.setString(9, s.getBirthDate());
            pr.setString(10, s.getGender());
            pr.setString(11, s.getAddress());
            pr.setString(12, s.getFatherName());
            pr.setString(13, s.getFatherOccupation());
            pr.setString(14, s.getMotherName());
            pr.setString(15, s.getMotherOccupation());
            pr.setBytes(16, s.getProfilePicInBytes());
            pr.setString(17, s.getLastLogin());
            pr.setString(18, s.generateUserId());
            result = pr.executeUpdate();
            pr.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int getTotalStudentInDept(String Deptcode, int sem) {
        int rollnumber = 0;
        String query = "select rollnumber from students where Departmentcode='" + Deptcode + "' and semoryear=" + sem;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rollnumber++;
            }
            st.close();
            rs.close();
            return rollnumber;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rollnumber;
    }

    public String[] getRollNumber(String Deptcode, int sem) {
        String[] rollnumber = null;
        int i = 0;
        String query = "select rollnumber from students where Departmentcode='" + Deptcode + "' and semoryear=" + sem + " order by rollnumber asc";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            long num;
            rollnumber = new String[this.getTotalStudentInDept(Deptcode, sem) + 1];
            rollnumber[i++] = "---Select Rollnumber---";
            while (rs.next()) {
                num = rs.getLong(1);
                rollnumber[i++] = num + "";
            }
            return rollnumber;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rollnumber;
    }

    public ResultSet getStudentinfo(String condition) {
        ResultSet rs = null;
        String query = "select s.Departmentcode as 'Class' ,s.rollnumber as 'Roll Number',concat(s.firstname,' ',s.lastname) as 'Student Name',d.DepartmentName as 'Department Name',concat(d.semoryear,'-',s.semoryear) as 'Sem/Year' from students s, departments d where s.Departmentcode=d.Departmentcode " + condition + " order by s.Departmentcode asc,s.semoryear asc,s.rollnumber asc";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet searchStudent(String query) {
        query += " order by s.Departmentcode asc,s.semoryear asc,s.rollnumber asc";
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Student getStudentDetails(String Deptcode, int sem, long rollnumber) {
        Student s = new Student();
        String query = " select * from students where Departmentcode='" + Deptcode + "' and semoryear=" + sem + " and rollnumber=" + rollnumber;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            s.setDeptCode(rs.getString(1));
            s.setSemorYear(rs.getInt(2));
            s.setRollNumber(rs.getLong(3));
            s.setOptionalCourse(rs.getString(4));
            s.setFirstName(rs.getString(5));
            s.setLastName(rs.getString(6));
            s.setEmailId(rs.getString(7));
            s.setContactNumber(rs.getString(8));
            s.setBirthDate(rs.getString(9));
            s.setGender(rs.getString(10));
            s.setAddress(rs.getString(11));
            s.setFatherName(rs.getString(12));
            s.setFatherOccupation(rs.getString(13));
            s.setMotherName(rs.getString(14));
            s.setMotherOccupation(rs.getString(15));
            s.setProfilePic(rs.getBytes(16));
            s.setSrNo(rs.getInt(17));
            s.setLastLogin(rs.getString(18));
            s.setUserId(rs.getString(19));
            s.setPassword(rs.getString(20));
            s.setAdmissionDate(rs.getString(21));
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public int addStudentTheoryMarks(Marks m) {
        int result = 0;
        try {
            String query = "update marks set marks=" + m.getTheoryMarks() + " where coursecode='" + m.getCourseCode() + "' and rollnumber=" + m.getRollNumber();
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();
            if (result == 0) {
                query = "insert into marks values(?,?,?,?,?,?)";
                pr = con.prepareStatement(query);
                pr.setString(1, m.getDeptCode());
                pr.setInt(2, m.getSemorYear());
                pr.setString(3, m.getCourseCode());
                pr.setString(4, m.getCourseName());
                pr.setLong(5, m.getRollNumber());
                pr.setInt(6, m.getTheoryMarks());
                result = pr.executeUpdate();
            }
        } catch (Exception exp) {
        }
        return result;
    }

/*    public int addStudentPracticalMarks(Marks m) {
        int result = 0;
        try {
            String query = "update marks set practicalmarks=" + m.getPracticalMarks() + " where coursecode='" + m.getCourseCode() + "' and rollnumber=" + m.getRollNumber();
            PreparedStatement pr = con.prepareStatement(query);
            result = pr.executeUpdate();
            if (result == 0) {
                query = "insert into marks values(?,?,?,?,?,?,?)";
                pr = con.prepareStatement(query);
                pr.setString(1, m.getDeptCode());
                pr.setInt(2, m.getSemorYear());
                pr.setString(3, m.getCourseCode());
                pr.setString(4, m.getCourseName());
                pr.setLong(5, m.getRollNumber());
                pr.setInt(6, Types.NULL);
                pr.setInt(7, m.getPracticalMarks());
                result = pr.executeUpdate();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }*/

    public ArrayList<Marks> getStudentTheoryMarksDetails(String Deptcode, int sem, String coursename) {
        ResultSet rs = null;
        ArrayList<Marks> marks = new ArrayList<Marks>();
        String coursecode = new CourseData().getCourseCode(Deptcode, sem, coursename);
        String query = "select distinct s.firstname,s.rollnumber,courses.coursename,courses.marks,m.marks from students s left join marks m on s.rollnumber=m.rollnumber and m.coursecode='" + coursecode + "',courses where s.Departmentcode='" + Deptcode + "' and s.semoryear=" + sem + " and courses.coursecode='" + coursecode + "'  order by s.rollnumber asc";
        String coursetype = new CourseData().checkCoreorOptional(coursecode);
        if (!coursetype.equals("core")) {
            query = "select distinct s.firstname,s.rollnumber,courses.coursename,courses.marks,m.marks from students s left join marks m on s.rollnumber=m.rollnumber and m.coursecode='" + coursecode + "',courses where s.optionalcourse=courses.coursename  and s.Departmentcode='" + Deptcode + "' and s.semoryear=" + sem + " and courses.coursecode='" + coursecode + "'  order by s.rollnumber asc";
        }
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Marks m = new Marks();
                m.setRollNumber(rs.getLong(2));
                m.setStudentName(rs.getString(1));
                m.setCourseName(rs.getString(3));
                m.setMaxTheoryMarks(rs.getInt(4));
                m.setTheoryMarks(rs.getInt(5));
                marks.add(m);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marks;
    }

   /* public ArrayList<Marks> getStudentPracticalMarksDetails(String Deptcode, int sem, String coursename) {
        ResultSet rs = null;
        ArrayList<Marks> marks = new ArrayList<Marks>();
        String coursecode = new CourseData().getCourseCode(Deptcode, sem, coursename);
        String query = "select distinct s.firstname,s.rollnumber,courses.coursename,courses.practicalmarks,m.practicalmarks from students s left join marks m on s.rollnumber=m.rollnumber and m.coursecode='" + coursecode + "',courses where s.Departmentcode='" + Deptcode + "' and s.semoryear=" + sem + " and courses.coursecode='" + coursecode + "' order by s.rollnumber asc";
        String coursetype = new CourseData().checkCoreorOptional(coursecode);
        if (!coursetype.equals("core")) {
            query = "select distinct s.firstname,s.rollnumber,courses.coursename,courses.practicalmarks,m.practicalmarks from students s left join marks m on s.rollnumber=m.rollnumber and m.coursecode='" + coursecode + "',courses where s.optionalcourse=courses.coursename  and s.Departmentcode='" + Deptcode + "' and s.semoryear=" + sem + " and courses.coursecode='" + coursecode + "'  order by s.rollnumber asc";
        }
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Marks m = new Marks();
                m.setRollNumber(rs.getLong(2));
                m.setStudentName(rs.getString(1));
                m.setCourseName(rs.getString(3));
                m.setMaxPracticalMarks(rs.getInt(4));
                m.setPracticalMarks(rs.getInt(5));
                marks.add(m);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marks;
    }*/

    public Student getStudentDetails(int row) {
        Student s = new Student();
        String query = "select * from students where sr_no=" + row;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            s.setDeptCode(rs.getString(1));
            s.setSemorYear(rs.getInt(2));
            s.setRollNumber(rs.getLong(3));
            s.setOptionalCourse(rs.getString(4));
            s.setFirstName(rs.getString(5));
            s.setLastName(rs.getString(6));
            s.setEmailId(rs.getString(7));
            s.setContactNumber(rs.getString(8));
            s.setBirthDate(rs.getString(9));
            s.setGender(rs.getString(10));
            s.setAddress(rs.getString(11));
            s.setFatherName(rs.getString(12));
            s.setFatherOccupation(rs.getString(13));
            s.setMotherName(rs.getString(14));
            s.setMotherOccupation(rs.getString(15));
            s.setProfilePic(rs.getBytes(16));
            s.setSrNo(rs.getInt(17));
            s.setLastLogin(rs.getString(18));
            s.setUserId(rs.getString(19));
            s.setPassword(rs.getString(20));
            s.setAdmissionDate(rs.getString(21));
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public Student getStudentDetailsByUserId(String userid) {
        Student s = new Student();
        String query = "select * from students where userid='" + userid + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            s.setDeptCode(rs.getString(1));
            s.setSemorYear(rs.getInt(2));
            s.setRollNumber(rs.getLong(3));
            s.setOptionalCourse(rs.getString(4));
            s.setFirstName(rs.getString(5));
            s.setLastName(rs.getString(6));
            s.setEmailId(rs.getString(7));
            s.setContactNumber(rs.getString(8));
            s.setBirthDate(rs.getString(9));
            s.setGender(rs.getString(10));
            s.setAddress(rs.getString(11));
            s.setFatherName(rs.getString(12));
            s.setFatherOccupation(rs.getString(13));
            s.setMotherName(rs.getString(14));
            s.setMotherOccupation(rs.getString(15));
            s.setProfilePic(rs.getBytes(16));
            s.setSrNo(rs.getInt(17));
            s.setLastLogin(rs.getString(18));
            s.setUserId(rs.getString(19));
            s.setPassword(rs.getString(20));
            s.setAdmissionDate(rs.getString(21));
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public int getTotalStudents() {
        int totalstudent = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from students");
            while (rs.next()) {
                totalstudent++;
            }
            rs.close();
            st.close();
            return totalstudent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalstudent;
    }

    public Marks getOptionalCoursesMarks(String deptcode, int semoryear, long rollnumber) {
        try {
            Marks m = new Marks();
            m.setDeptCode(deptcode);
            m.setSemorYear(semoryear);
            m.setRollNumber(rollnumber);
            String ccode = this.getOptionalCoursesCode(deptcode, semoryear, rollnumber);
            if (ccode == null) {
                return null;
            }
            String query = "select cu.coursecode,cu.coursename,cu.marks,m.marks from courses cu left join marks m on m.coursecode='" + ccode + "' and m.rollnumber=" + rollnumber + " where su.coursecode='" + ccode + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            m.setCourseCode(rs.getString(1));
            m.setCourseName(rs.getString(2));
            m.setMaxTheoryMarks(rs.getInt(3));
            m.setTheoryMarks(rs.getInt(4));
            m.setRollNumber(rollnumber);
            m.setStudentName(getStudentName(deptcode + "-" + semoryear + "-" + rollnumber));
            return m;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

    public String getOptionalCoursesCode(String deptcode, int semoryear, long rollnumber) {
        String osub = null;

        try {
            String query = "select optionalcourse from students where userid='" + deptcode + "-" + semoryear + "-" + rollnumber + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            osub = rs.getString(1);
            osub = new CourseData().getCourseCode(deptcode, semoryear, osub);

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return osub;

    }

    public ArrayList<Marks> getMarkssheetOfStudent(String deptcode, int sem, long rollnumber) {
        ArrayList<Marks> list = new ArrayList<Marks>();
        String query = "select su.coursecode,su.coursename,su.marks,m.marks from courses su left join marks m on su.coursename=m.coursename and m.rollnumber=" + rollnumber + " and m.semoryear=" + sem + " and m.Departmentcode='" + deptcode + "' where su.Departmentcode='" + deptcode + "' and su.semoryear=" + sem + " and su.coursetype='core' order by su.coursetype asc";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int index = 1;
            while (rs.next()) {
                Marks m = new Marks();
                m.setSrNo(index);
                m.setCourseCode(rs.getString(1));
                m.setCourseName(rs.getString(2));
                m.setMaxTheoryMarks(rs.getInt(3));
                m.setTheoryMarks(rs.getInt(4));
                m.setRollNumber(rollnumber);
                m.setStudentName(getStudentName(deptcode + "-" + sem + "-" + rollnumber));
                index++;
                list.add(m);
            }
            {
                Marks m = getOptionalCoursesMarks(deptcode, sem, rollnumber);
                if (m != null) {
                    m.setSrNo(index);
                    list.add(m);
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    public int addStudentAttandance(Attandance a) {
        int result = 0;
        try {
            String query = "insert into attandance values(?,?,?,?,?,?)";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, a.getCourseCode());
            pr.setString(2, a.getAttandanceDate());
            pr.setLong(3, a.getRollNumber());
            pr.setBoolean(4, a.getPresentStatus());
            pr.setString(5, a.getDeptCode());
            pr.setInt(6, a.getSemorYear());
            result = pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Attandance> getStudentsForAttandance(Attandance a) {
        ArrayList<Attandance> list = new ArrayList<Attandance>();
        String query = "select s.rollnumber,concat(s.firstname,' ',s.lastname),s.semoryear,a.present from students s left join attandance a on"
                + " s.rollnumber=a.rollnumber"
                + " and a.date='" + a.getAttandanceDate() + "'"
                + " and a.coursecode='" + a.getCourseCode() + "'"
                + " where s.Departmentcode='" + a.getDeptCode() + "'"
                + " and s.semoryear=" + a.getSemorYear();
        String coursetype = new CourseData().checkCoreorOptional(a.getCourseCode());
        if (!coursetype.equals("core")) {
            query += " and s.optionalcourse='" + a.getCourseName() + "'";
        }
        query += " order by s.rollnumber asc";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Attandance at = new Attandance();
                at.setRollNumber(rs.getLong(1));
                at.setStudentName(rs.getString(2));
                at.setSemorYear(rs.getInt(3));
                at.setPresentStatus(rs.getBoolean(4));
                at.setDeptCode(a.getDeptCode());
                at.setCourseName(a.getCourseName());
                list.add(at);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    public boolean isSubmitted(String coursecode, String date) {
        try {
            String query = " select count(*) from attandance where "
                    + "coursecode='" + coursecode
                    + "'"
                    + " and date='" + date
                    + "'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getInt(1) > 0;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public ArrayList<Attandance> getAttandanceReportByCourse(Attandance a) {
        ArrayList<Attandance> list = new ArrayList<Attandance>();
        try {
            String query = "select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name',(select count(*) from attandance where "
                    + "coursecode='" + a.getCourseCode()
                    + "' and rollnumber=s.rollnumber and present=1),(select count(*) from attandance where coursecode='" + a.getCourseCode() + "' and rollnumber=s.rollnumber)"
                    + "from students s left join attandance a on s.rollnumber=a.rollnumber where "
                    + "s.Departmentcode='" + a.getDeptCode()
                    + "' and s.semoryear=" + a.getSemorYear() + " ";
            String coursetype = new CourseData().checkCoreorOptional(a.getCourseCode());
            if (!coursetype.equals("core")) {
                query += " and s.optionalcourse='" + a.getCourseName() + "'";
            }
            query += " order by s.rollnumber asc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Attandance at = new Attandance();
                at.setRollNumber(rs.getLong(1));
                at.setStudentName(rs.getString(2));
                at.setDeptCode(a.getDeptCode());
                at.setCourseName(a.getCourseName());
                at.setAttandance(rs.getInt(3));
                at.setTotalAttandance(rs.getInt(4));
                list.add(at);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;

    }

    public ArrayList<Marks> getMarksheetReportByCourse(Marks marks) {
        ArrayList<Marks> list = new ArrayList<Marks>();
        try {
            String query = "select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name'"
                    + ",(select marks from marks where "
                    + "coursecode='" + marks.getCourseCode()
                    + "' and rollnumber=s.rollnumber) as 'Theory'"
                    + ",(select marks from courses where coursecode='" + marks.getCourseCode()
                    + "') as 'Total theory' from students s left join marks m on s.rollnumber=m.rollnumber where s.Departmentcode='" + marks.getDeptCode()
                    + "' and s.semoryear=" + marks.getSemorYear();
            String coursetype = new CourseData().checkCoreorOptional(marks.getCourseCode());
            if (!coursetype.equals("core")) {
                query += " and s.optionalcourse='" + marks.getCourseName() + "'";
            }
            query += " order by s.rollnumber asc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Marks m = new Marks();
                m.setRollNumber(rs.getLong(1));
                m.setStudentName(rs.getString(2));
                m.setCourseName(marks.getCourseName());
                m.setTheoryMarks(rs.getInt(3));
//                m.setMaxTheoryMarks(rs.getInt(5));

                list.add(m);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;

    }

    public ArrayList<Marks> getMarksheetReportByClass(Marks marks) {
        ArrayList<Marks> list = new ArrayList<Marks>();
        try {
            String query = "select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name',(select sum(marks) from marks where Departmentcode=s.Departmentcode and semoryear=s.semoryear and rollnumber=s.rollnumber) as 'Theory',(select sum(marks) from courses where Departmentcode=s.Departmentcode and semoryear=s.semoryear and coursetype='core') as 'Total theory' from students s left join marks m on s.rollnumber=m.rollnumber where s.Departmentcode='" + marks.getDeptCode() + "' and s.semoryear=" + marks.getSemorYear();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Marks m = new Marks();
                //String coursecode = this.getOptionalCoursesCode(marks.getDeptCode(), marks.getSemorYear(), rs.getLong(1));
                //int maxtheorymarks = new CourseData().getMaxTheoryMarksOfCourse(coursecode);

                m.setRollNumber(rs.getLong(1));
                m.setStudentName(rs.getString(2));
                m.setCourseName(marks.getCourseName());
                m.setTheoryMarks(rs.getInt(3));
                //m.setMaxTheoryMarks(rs.getInt(4) + maxtheorymarks);

                list.add(m);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;

    }

    public ArrayList<Attandance> getAttandanceReportByClass(Attandance a) {
        ArrayList<Attandance> list = new ArrayList<Attandance>();
        try {
            String query = "select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name',(select count(*) from attandance where "
                    + "Departmentcode='" + a.getDeptCode()
                    + "' and semoryear=" + a.getSemorYear()
                    + " and rollnumber=s.rollnumber and present=1),(select count(*) from attandance where Departmentcode='" + a.getDeptCode() + "' and semoryear=" + a.getSemorYear() + " and  rollnumber=s.rollnumber)"
                    + " from students s left join attandance a on s.rollnumber=a.rollnumber where "
                    + "s.Departmentcode='" + a.getDeptCode()
                    + "' and s.semoryear=" + a.getSemorYear() + " order by s.rollnumber";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Attandance at = new Attandance();
                at.setRollNumber(rs.getLong(1));
                at.setStudentName(rs.getString(2));
                at.setCourseName("All");
                at.setAttandance(rs.getInt(3));
                at.setTotalAttandance(rs.getInt(4));
                list.add(at);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    public ArrayList<Attandance> getTotalAttandanceReportOfStudent(Attandance a) {
        ArrayList<Attandance> list = new ArrayList<Attandance>();
        try {
            String query = "select distinct s.rollnumber,concat(s.firstname,' ',s.lastname) as 'Student Name',(select count(*) from attandance where "
                    + "Departmentcode='" + a.getDeptCode()
                    + "' and semoryear=" + a.getSemorYear()
                    + " and rollnumber=s.rollnumber and present=1),(select count(*) from attandance where Departmentcode='" + a.getDeptCode() + "' and semoryear=" + a.getSemorYear() + " and  rollnumber=s.rollnumber)"
                    + " from students s left join attandance a on s.rollnumber=a.rollnumber where "
                    + "s.Departmentcode='" + a.getDeptCode()
                    + "' and s.semoryear=" + a.getSemorYear()
                    + " and s.rollnumber=" + a.getRollNumber()
                    + " order by s.rollnumber";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Attandance at = new Attandance();
                at.setRollNumber(rs.getLong(1));
                at.setStudentName(rs.getString(2));
                at.setCourseName("All");
                at.setAttandance(rs.getInt(3));
                at.setTotalAttandance(rs.getInt(4));
                list.add(at);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;

    }

    public ArrayList<Attandance> getAttandanceReportByStudent(Attandance a) {
        ArrayList<Attandance> list = new ArrayList<Attandance>();
        try {
            String query = "select distinct su.coursecode,su.coursename,(select count(*) from attandance where coursecode=su.coursecode "
                    + "and rollnumber=" + a.getRollNumber() + " and present=1) as 'Attandance',(select count(*) from attandance where"
                    + " coursecode=su.coursecode and rollnumber=" + a.getRollNumber() + ") as 'Total Attandance' from courses su "
                    + "left join attandance a on su.coursecode=a.coursecode where su.Departmentcode='" + a.getDeptCode()
                    + "' and su.semoryear=" + a.getSemorYear() + "  order by su.coursecode asc;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Attandance at = new Attandance();
                at.setStudentName(this.getStudentName(a.getDeptCode() + "-" + a.getSemorYear() + "-" + a.getRollNumber()));
                at.setRollNumber(a.getRollNumber());
                at.setCourseCode(rs.getString(1));
                at.setCourseName(rs.getString(2));
                at.setAttandance(rs.getInt(3));
                at.setTotalAttandance(rs.getInt(4));
                String depttype = new CourseData().checkCoreorOptional(at.getCourseCode());
                if (!depttype.equals("core")) {
                    if (at.getCourseCode().equals(this.getOptionalCoursesCode(a.getDeptCode(), a.getSemorYear(), a.getRollNumber()))) {
                        list.add(at);
                    }
                } else {
                    list.add(at);
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    public boolean checkPassword(String userid, String password) {
        boolean result = false;
        try {
            String query = "select count(*) from students where userid='" + userid + "' and password='" + password + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            if (rs.getInt(1) > 0) {
                result = true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public ArrayList<Student> getStudentsDetails(String condition) {
        ArrayList<Student> list = new ArrayList<Student>();
        String query = "select * from students s " + condition + " order by s.Departmentcode asc,s.semoryear asc,s.rollnumber asc";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Student s = new Student();
                s.setDeptCode(rs.getString(1));
                s.setSemorYear(rs.getInt(2));
                s.setRollNumber(rs.getLong(3));
                s.setOptionalCourse(rs.getString(4));
                s.setFirstName(rs.getString(5));
                s.setLastName(rs.getString(6));
                s.setEmailId(rs.getString(7));
                s.setContactNumber(rs.getString(8));
                s.setBirthDate(rs.getString(9));
                s.setGender(rs.getString(10));
                s.setAddress(rs.getString(11));
                s.setFatherName(rs.getString(12));
                s.setFatherOccupation(rs.getString(1));
                s.setMotherName(rs.getString(14));
                s.setMotherOccupation(rs.getString(15));
                s.setProfilePic(rs.getBytes(16));
                s.setSrNo(rs.getInt(17));
                s.setLastLogin(rs.getString(18));
                s.setUserId(rs.getString(19));
                s.setPassword(rs.getString(20));
                s.setAdmissionDate(rs.getString(21));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getStudentName(String userid) {
        String name = "";
        try {
            String query = "select concat(firstname,' ',lastname) from students where userid='" + userid + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            name = rs.getString(1);

            rs.close();
            st.close();

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return name;
    }

    public String getLastLogin(String userid) {
        try {
            String query = "select lastlogin from students where userid='" + userid + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getString(1);
        } catch (Exception exp) {
            exp.printStackTrace();
            return "deleted";
        }
    }

    public Image getProfilePic(String userid) {
        Image image = null;
        try {
            String query = "select profilepic from students where userid='" + userid + "'";
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
            String query = "update students set password='" + password + "' where userid='" + userid + "'";
            PreparedStatement pr = con.prepareStatement(query);
            return pr.executeUpdate();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return 0;
    }
}
