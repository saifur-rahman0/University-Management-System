package application.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import application.admin.Admin;
import application.admin.AdminMain;
import application.department.DepartmentData;
import application.teacher.Teacher;
import application.teacher.TeacherPanel;
import application.teacher.TeacherData;
import application.student.Student;
import application.student.StudentData;
import application.course.CourseData;
import application.teacher.TeacherPanel;

/*
 * Title : HomePanel.java
 * Purpose : Home Page
 */
@SuppressWarnings("serial")
public class HomePanel extends JPanel  {

    /**
     *
     */
    private JPanel homeheaderpanel;
    private JLabel totalstudentlabel, totalfaculitieslabel, totaldeptlabel, totallectureslabel;
    public JLabel lastloginlabel;
    private JLabel timedifflabel;
    private JLabel welcomelabel;
    private JPanel deptspanel;
    private JPanel faculitiespanel;
    private JPanel studentspanel;
    int pos[] = {20, 294, 568, 842};
    private JPanel coursepanel;
    /**
     * Create the panel.
     */
    private HomePanel() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        this.setSize(1116, 705);
        setLayout(null);

        deptspanel = new JPanel();
        deptspanel.setBorder(new LineBorder(new Color(73, 10, 115, 218), 3));
        deptspanel.setBounds(20, 244, 253, 247);
        add(deptspanel);
        deptspanel.setBackground(new Color(245, 245, 245, 255));
        deptspanel.setLayout(null);

        totaldeptlabel = new JLabel("0");
        totaldeptlabel.setForeground(new Color(10, 10, 10));
        totaldeptlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        totaldeptlabel.setHorizontalAlignment(SwingConstants.CENTER);
        totaldeptlabel.setBounds(10, 174, 233, 35);
        deptspanel.add(totaldeptlabel);

        JLabel lblDepts = new JLabel("Departments");
        lblDepts.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblDepts.setForeground(new Color(0, 0, 0));
        lblDepts.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepts.setHorizontalTextPosition(JLabel.CENTER);
        lblDepts.setVerticalTextPosition(JLabel.BOTTOM);
        lblDepts.setBounds(10, 31, 233, 142);
        deptspanel.add(lblDepts);
        lblDepts.setIcon(new ImageIcon(".//assets//homepannel//depthomepage.png"));

        studentspanel = new JPanel();
        studentspanel.setBorder(new LineBorder(new Color(73, 10, 115, 218), 3));
        studentspanel.setLayout(null);
        studentspanel.setBackground(new Color(245, 245, 245, 255));
        studentspanel.setBounds(294, 244, 253, 247);
        add(studentspanel);

        totalstudentlabel = new JLabel("0");
        totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
        totalstudentlabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalstudentlabel.setForeground(Color.BLACK);
        totalstudentlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        totalstudentlabel.setBounds(10, 174, 233, 35);
        studentspanel.add(totalstudentlabel);

        JLabel lblStudents = new JLabel("Students");
        lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
        lblStudents.setForeground(Color.BLACK);
        lblStudents.setIcon(null);
        lblStudents.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblStudents.setBounds(10, 32, 233, 144);
        lblStudents.setHorizontalTextPosition(JLabel.CENTER);
        lblStudents.setVerticalTextPosition(JLabel.BOTTOM);
        studentspanel.add(lblStudents);
        lblStudents.setIcon(new ImageIcon(".//assets//homepannel//studenthomepage.png"));

        faculitiespanel = new JPanel();
        faculitiespanel.setBorder(new LineBorder(new Color(73, 10, 115, 218), 3));
        faculitiespanel.setLayout(null);
        faculitiespanel.setBackground(new Color(245, 245, 245, 255));
        faculitiespanel.setBounds(568, 244, 253, 247);
        add(faculitiespanel);

        totalfaculitieslabel = new JLabel("0");
        totalfaculitieslabel.setBackground(Color.WHITE);
        totalfaculitieslabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalfaculitieslabel.setForeground(Color.BLACK);
        totalfaculitieslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        totalfaculitieslabel.setBounds(10, 174, 233, 35);
        faculitiespanel.add(totalfaculitieslabel);

        JLabel lblFaculities = new JLabel("Teachers");
        lblFaculities.setHorizontalAlignment(SwingConstants.CENTER);
        lblFaculities.setForeground(Color.BLACK);
        lblFaculities.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblFaculities.setBounds(10, 34, 233, 140);
        lblFaculities.setHorizontalTextPosition(JLabel.CENTER);
        lblFaculities.setVerticalTextPosition(JLabel.BOTTOM);
        faculitiespanel.add(lblFaculities);
        lblFaculities.setIcon(new ImageIcon(".//assets//homepannel//teacherhomepage.png"));

        coursepanel = new JPanel();
        coursepanel.setBorder(new LineBorder(new Color(73, 10, 115, 218), 3));
        coursepanel.setLayout(null);
        coursepanel.setBackground(new Color(245, 245, 245, 255));
        coursepanel.setBounds(842, 244, 253, 247);
        add(coursepanel);

        totallectureslabel = new JLabel("0");

        totallectureslabel.setHorizontalAlignment(SwingConstants.CENTER);
        totallectureslabel.setForeground(Color.BLACK);
        totallectureslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        totallectureslabel.setBounds(10, 174, 233, 35);
        coursepanel.add(totallectureslabel);

        JLabel lblLectures = new JLabel("Courses");
        lblLectures.setHorizontalAlignment(SwingConstants.CENTER);
        lblLectures.setForeground(Color.BLACK);
        lblLectures.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblLectures.setBounds(10, 40, 233, 141);
        lblLectures.setIconTextGap(10);
        lblLectures.setHorizontalTextPosition(JLabel.CENTER);
        lblLectures.setVerticalTextPosition(JLabel.BOTTOM);
        coursepanel.add(lblLectures);
        try {
            Image image = ImageIO.read(new File(".//assets//homepannel//courceshomepage.png"));
            lblLectures.setIcon(new ImageIcon(image.getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        homeheaderpanel = new JPanel();
        homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        homeheaderpanel.setBackground(new Color(48, 11, 103, 105));
        homeheaderpanel.setLayout(null);
        homeheaderpanel.setBounds(10, 0, 1096, 279);
        add(homeheaderpanel);

        welcomelabel = new JLabel("Welcome");
        welcomelabel.setHorizontalAlignment(SwingConstants.RIGHT);
        welcomelabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        welcomelabel.setForeground(Color.WHITE);
        welcomelabel.setBounds(10, 11, 1076, 45);
        homeheaderpanel.add(welcomelabel);

        JLabel lblHome = new JLabel("Home Page");
        lblHome.setIcon(null);
        lblHome.setForeground(Color.WHITE);
        lblHome.setFont(new Font("Segoe UI", Font.BOLD, 65));
        lblHome.setBounds(10, 97, 400, 85);
        homeheaderpanel.add(lblHome);

        lastloginlabel = new JLabel("Last Login : First Login");
        lastloginlabel.setBackground(Color.WHITE);
        lastloginlabel.setForeground(Color.WHITE);
        lastloginlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lastloginlabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastloginlabel.setBounds(20, 47, 1066, 30);
        homeheaderpanel.add(lastloginlabel);

        timedifflabel = new JLabel("");
        timedifflabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timedifflabel.setForeground(Color.WHITE);
        timedifflabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        timedifflabel.setBackground(Color.WHITE);
        timedifflabel.setBounds(599, 75, 486, 19);
        homeheaderpanel.add(timedifflabel);

    }

    public HomePanel(Admin a) {
        this();
        totalfaculitieslabel.setText(new TeacherData().getTotalTeacher() + "");
        totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
        totaldeptlabel.setText(new DepartmentData().getTotalDepartment() + "");
        welcomelabel.setText("Welcome Adminstrator");
        totallectureslabel.setText(new CourseData().getTotalCourse() + "");
    }

    public HomePanel(Teacher f) {
        this();
        totalfaculitieslabel.setText(new TeacherData().getTeacher(f.getDeptCode(), f.getSemorYear()) + "");
        totalstudentlabel.setText(new StudentData().getTotalStudentInDept(f.getDeptCode(), f.getSemorYear()) + "");
        deptspanel.setVisible(false);
        welcomelabel.setText("Welcome " + f.getTeacherName());
        totallectureslabel.setText(new CourseData().getTotalCourseinDept(f.getDeptCode(), f.getSemorYear()) + "");

        studentspanel.setLocation(pos[0], studentspanel.getY());
        faculitiespanel.setLocation(pos[1], faculitiespanel.getY());
        coursepanel.setLocation(pos[2], coursepanel.getY());
    }

    public HomePanel(Student s) {
        this();
        totalfaculitieslabel.setText(new TeacherData().getTeacher(s.getDeptCode(), s.getSemorYear()) + "");
        totalstudentlabel.setText(new StudentData().getTotalStudentInDept(s.getDeptCode(), s.getSemorYear()) + "");

        deptspanel.setVisible(false);
        welcomelabel.setText("Welcome " + s.getFullName());
        totallectureslabel.setText(new CourseData().getTotalCourseinDept(s.getDeptCode(), s.getSemorYear()) + "");
        studentspanel.setLocation(pos[0], studentspanel.getY());
        faculitiespanel.setLocation(pos[1], faculitiespanel.getY());
        coursepanel.setLocation(pos[2], coursepanel.getY());
    }

    public void setLastLogin(String lastlogin) {
        if (lastlogin == null || lastlogin.isEmpty()) {
            this.lastloginlabel.setText("last login : First Time");
        } else {
            this.lastloginlabel.setText("last login : " + lastlogin);
        }
    }


}
