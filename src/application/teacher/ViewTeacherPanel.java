package application.teacher;

import application.admin.AdminMain;
import application.common.ChangePasswordDialog;
import application.course.AssignCourseDialog;
import application.department.DepartmentData;
import application.student.StudentMain;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Title : ViewTeacherPanel.java
 * Purpose : Displaying all the details of  teacher
 */
@SuppressWarnings("serial")
public class ViewTeacherPanel extends JPanel {
    /**
     * Create the panel.
     */
    public JComponent lastpanel;
    private final JButton assignCoursebutton;
    private final JButton backbutton;
    private final JButton editdetailsbutton;

    /**
     * @wbp.parser.constructor
     */
    private ViewTeacherPanel(Teacher t) {
        setBackground(new Color(255, 255, 255));
        this.setSize(1116, 705);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(48, 11, 103, 105));
        panel.setBounds(10, 0, 1096, 188);
        add(panel);
        panel.setLayout(null);
        JLabel lblDisplayingStudentDetails = new JLabel(t.getTeacherName());

        lblDisplayingStudentDetails.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDisplayingStudentDetails.setForeground(new Color(255, 255, 255));
        lblDisplayingStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblDisplayingStudentDetails.setBounds(661, 11, 415, 44);
        panel.add(lblDisplayingStudentDetails);

        editdetailsbutton = new JButton("Edit Details");
        editdetailsbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        editdetailsbutton.setFocusable(false);
        editdetailsbutton.setForeground(new Color(61, 0, 169));
        editdetailsbutton.setBackground(new Color(255, 255, 255));
        editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editdetailsbutton.setBounds(919, 141, 153, 35);
        panel.add(editdetailsbutton);

        backbutton = new JButton("Back");
        //backbutton.setContentAreaFilled(false);
        backbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
        backbutton.setFocusable(false);
        backbutton.setForeground(new Color(61, 0, 169));
        backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backbutton.setBackground(new Color(255, 255, 255));
        backbutton.setBounds(10, 141, 88, 36);
        backbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(backbutton);

        JLabel lblLastLogin = new JLabel("Last Login : ");
        if (t.getLastLogin() == null || t.getLastLogin().isEmpty()) {
            lblLastLogin.setText("Last Login : No Login");
        } else {
            lblLastLogin.setText("Last Login : " + t.getLastLogin());
        }
        lblLastLogin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLastLogin.setForeground(Color.WHITE);
        lblLastLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblLastLogin.setBounds(719, 57, 357, 19);
        panel.add(lblLastLogin);

        JLabel lblStudentDetails = new JLabel("Teacher Details");
        lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
        lblStudentDetails.setForeground(Color.WHITE);
        lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblStudentDetails.setBounds(10, 65, 415, 44);
        panel.add(lblStudentDetails);

        assignCoursebutton = new JButton("Assign Course");
        assignCoursebutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        assignCoursebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        assignCoursebutton.setFocusable(false);
        assignCoursebutton.setForeground(new Color(61, 0, 169));
        assignCoursebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        assignCoursebutton.setBackground(Color.WHITE);
        assignCoursebutton.setBounds(743, 141, 153, 35);
        panel.add(assignCoursebutton);

        JLabel teacheridlbl = new JLabel("Teacher ID   ");
        teacheridlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
        teacheridlbl.setBackground(new Color(255, 255, 255));
        teacheridlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        teacheridlbl.setHorizontalAlignment(SwingConstants.RIGHT);
        teacheridlbl.setOpaque(true);
        teacheridlbl.setBounds(309, 66 + 150, 274, 48);
        add(teacheridlbl);

        JLabel teachernamelbl = new JLabel("Teacher Name   ");
        teachernamelbl.setOpaque(true);
        teachernamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
        teachernamelbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        teachernamelbl.setBorder(new LineBorder(new Color(192, 192, 192)));
        teachernamelbl.setBackground(Color.WHITE);
        teachernamelbl.setBounds(309, 113 + 150, 274, 48);
        add(teachernamelbl);

        JLabel lblAddress = new JLabel("Address   ");
        lblAddress.setOpaque(true);
        lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAddress.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblAddress.setBackground(Color.WHITE);
        lblAddress.setBounds(309, 160 + 150, 274, 48);
        add(lblAddress);

        JLabel lblEmailId = new JLabel("Email ID  ");
        lblEmailId.setOpaque(true);
        lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmailId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblEmailId.setBackground(Color.WHITE);
        lblEmailId.setBounds(309, 207 + 150, 274, 48);
        add(lblEmailId);

        JLabel lblDateOfBirth = new JLabel("Date Of Birth ");
        lblDateOfBirth.setOpaque(true);
        lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblDateOfBirth.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblDateOfBirth.setBackground(Color.WHITE);
        lblDateOfBirth.setBounds(309, 254 + 150, 274, 48);
        add(lblDateOfBirth);

        JLabel lblContactNumber = new JLabel("Contact Number ");
        lblContactNumber.setOpaque(true);
        lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblContactNumber.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblContactNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblContactNumber.setBackground(Color.WHITE);
        lblContactNumber.setBounds(309, 300 + 150, 274, 48);
        add(lblContactNumber);

        JLabel qualificationlbl = new JLabel("Qualification   ");
        qualificationlbl.setOpaque(true);
        qualificationlbl.setHorizontalAlignment(SwingConstants.RIGHT);
        qualificationlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        qualificationlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
        qualificationlbl.setBackground(Color.WHITE);
        qualificationlbl.setBounds(20, 359 + 150, 291, 48);
        add(qualificationlbl);

        JLabel deptlbl = new JLabel("Department ");
        deptlbl.setOpaque(true);
        deptlbl.setHorizontalAlignment(SwingConstants.RIGHT);
        deptlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        deptlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
        deptlbl.setBackground(Color.WHITE);
        deptlbl.setBounds(20, 405 + 150, 291, 48);
        add(deptlbl);

        JLabel semoryearlbl = new JLabel("Semester/Year    ");
        semoryearlbl.setOpaque(true);
        semoryearlbl.setHorizontalAlignment(SwingConstants.RIGHT);
        semoryearlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        semoryearlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
        semoryearlbl.setBackground(Color.WHITE);
        semoryearlbl.setBounds(582, 405 + 150, 239, 48);
        add(semoryearlbl);

        JLabel lblcourse = new JLabel("Course    ");
        lblcourse.setOpaque(true);
        lblcourse.setHorizontalAlignment(SwingConstants.RIGHT);
        lblcourse.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblcourse.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblcourse.setBackground(Color.WHITE);
        lblcourse.setBounds(20, 452 + 150, 291, 48);
        add(lblcourse);

        JLabel positionlbl = new JLabel("Position    ");
        positionlbl.setOpaque(true);
        positionlbl.setHorizontalAlignment(SwingConstants.RIGHT);
        positionlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        positionlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
        positionlbl.setBackground(Color.WHITE);
        positionlbl.setBounds(582, 452 + 150, 239, 48);
        add(positionlbl);

        JLabel teacheridlabel = new JLabel("  " + t.getTeacherId());
        teacheridlabel.setOpaque(true);
        teacheridlabel.setHorizontalAlignment(SwingConstants.LEFT);
        teacheridlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        teacheridlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        teacheridlabel.setBackground(Color.WHITE);
        teacheridlabel.setBounds(582, 66 + 150, 523, 48);
        add(teacheridlabel);

        JLabel teachernamelabel = new JLabel("  " + t.getTeacherName());
        teachernamelabel.setOpaque(true);
        teachernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
        teachernamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        teachernamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        teachernamelabel.setBackground(Color.WHITE);
        teachernamelabel.setBounds(582, 113 + 150, 523, 48);
        add(teachernamelabel);

        JLabel addresslabel = new JLabel("  " + t.getAddress());
        addresslabel.setOpaque(true);
        addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
        addresslabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        addresslabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        addresslabel.setBackground(Color.WHITE);
        addresslabel.setBounds(582, 160 + 150, 523, 48);
        add(addresslabel);

        JLabel emailidlabel = new JLabel("  " + t.getEmailId());
        emailidlabel.setOpaque(true);
        emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailidlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        emailidlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        emailidlabel.setBackground(Color.WHITE);
        emailidlabel.setBounds(582, 207 + 150, 523, 48);
        add(emailidlabel);

        JLabel dateofbirthlabel = new JLabel("  " + t.getBirthDate());
        dateofbirthlabel.setOpaque(true);
        dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateofbirthlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        dateofbirthlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        dateofbirthlabel.setBackground(Color.WHITE);
        dateofbirthlabel.setBounds(582, 254 + 150, 523, 48);
        add(dateofbirthlabel);

        JLabel contactnumberlabel = new JLabel("  " + t.getContactNumber());
        contactnumberlabel.setOpaque(true);
        contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
        contactnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        contactnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        contactnumberlabel.setBackground(Color.WHITE);
        contactnumberlabel.setBounds(582, 300 + 150, 523, 48);
        add(contactnumberlabel);

        JLabel qualificationlabel = new JLabel("  " + t.getQualification());
        qualificationlabel.setOpaque(true);
        qualificationlabel.setHorizontalAlignment(SwingConstants.LEFT);
        qualificationlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        qualificationlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        qualificationlabel.setBackground(Color.WHITE);
        qualificationlabel.setBounds(309, 359 + 150, 274, 48);
        add(qualificationlabel);

        JLabel deptnamelabel = new JLabel();
        if (t.getDeptCode() == null || t.getDeptCode().equals("Not Assigned")) {
            deptnamelabel.setText("  " + t.getDeptCode());
        } else {
            deptnamelabel.setText("  " + t.getDeptName());
        }

        deptnamelabel.setOpaque(true);
        deptnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
        deptnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        deptnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        deptnamelabel.setBackground(Color.WHITE);
        deptnamelabel.setBounds(309, 405 + 150, 274, 48);
        add(deptnamelabel);

        JLabel semoryearlabel = new JLabel();
        if (t.getDeptCode() == null || t.getDeptCode().equals("Not Assigned")) {
            semoryearlabel.setText("  Not Assigned");
        } else {
            semoryearlabel.setText("  " + new DepartmentData().getsemoryear(t.getDeptCode()) + "-" + t.getSemorYear() + " " + " (" + t.getDeptCode() + ")");
        }
        semoryearlabel.setOpaque(true);
        semoryearlabel.setHorizontalAlignment(SwingConstants.LEFT);
        semoryearlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        semoryearlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        semoryearlabel.setBackground(Color.WHITE);
        semoryearlabel.setBounds(820, 405 + 150, 285, 48);
        add(semoryearlabel);

        JLabel courselabel = new JLabel("  " + t.getCourse());
        courselabel.setOpaque(true);
        courselabel.setHorizontalAlignment(SwingConstants.LEFT);
        courselabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        courselabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        courselabel.setBackground(Color.WHITE);
        courselabel.setBounds(309, 452 + 150, 274, 48);
        add(courselabel);

        JLabel postionlabel = new JLabel("  " + t.getPosition());
        postionlabel.setOpaque(true);
        postionlabel.setHorizontalAlignment(SwingConstants.LEFT);
        postionlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        postionlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        postionlabel.setBackground(Color.WHITE);
        postionlabel.setBounds(820, 452 + 150, 285, 48);
        add(postionlabel);

        JLabel profilepiclabel = new JLabel();
        profilepiclabel.setBounds(20, 66 + 150, 250, 270);
        add(profilepiclabel);
        profilepiclabel.setIcon(new ImageIcon(t.getProfilePic(250, 270)));
        profilepiclabel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        profilepiclabel.setOpaque(true);
        profilepiclabel.setBackground(new Color(240, 248, 255));
        profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblsemoryear = new JLabel("Experience   ");
        lblsemoryear.setOpaque(true);
        lblsemoryear.setHorizontalAlignment(SwingConstants.RIGHT);
        lblsemoryear.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblsemoryear.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblsemoryear.setBackground(Color.WHITE);
        lblsemoryear.setBounds(582, 359 + 150, 239, 48);
        add(lblsemoryear);

        JLabel experiencelabel = new JLabel("  " + t.getExperience());
        experiencelabel.setOpaque(true);
        experiencelabel.setHorizontalAlignment(SwingConstants.LEFT);
        experiencelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        experiencelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        experiencelabel.setBackground(Color.WHITE);
        experiencelabel.setBounds(820, 359 + 150, 285, 48);
        add(experiencelabel);

    }

    public ViewTeacherPanel(Teacher f, AdminMain am, JComponent lastpanel) {
        // TODO Auto-generated constructor stub
        this(f);
        this.lastpanel = lastpanel;
        JLabel lblJoinedDate = new JLabel("Joined Date    ");
        lblJoinedDate.setOpaque(true);
        lblJoinedDate.setHorizontalAlignment(SwingConstants.RIGHT);
        lblJoinedDate.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblJoinedDate.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblJoinedDate.setBackground(Color.WHITE);
        lblJoinedDate.setBounds(20, 649, 291, 48);
        add(lblJoinedDate);

        JLabel joineddatelabel = new JLabel("  " + f.getJoinedDate());
        joineddatelabel.setOpaque(true);
        joineddatelabel.setHorizontalAlignment(SwingConstants.LEFT);
        joineddatelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        joineddatelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        joineddatelabel.setBackground(Color.WHITE);
        joineddatelabel.setBounds(309, 649, 274, 48);
        add(joineddatelabel);

        JLabel lblPassword = new JLabel("Password    ");
        lblPassword.setOpaque(true);
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
        lblPassword.setBackground(Color.WHITE);
        lblPassword.setBounds(582, 649, 239, 48);
        add(lblPassword);

        JLabel passwordlabel = new JLabel("  " + f.getPassword());
        passwordlabel.setOpaque(true);
        passwordlabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        passwordlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
        passwordlabel.setBackground(Color.WHITE);
        passwordlabel.setBounds(820, 649, 285, 48);
        add(passwordlabel);

        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                am.viewteacherpanel.setVisible(false);
                if (lastpanel.getName().equals("Teacher Panel")) {
                    if (am.teacherpanel.viewbutton.getText().equals("Photo View")) {
                        am.teacherpanel.createtablemodel();
                    } else {
                        am.teacherpanel.createphotoviewpanel();
                    }
                    am.teacherpanel.setVisible(true);
                } else if (lastpanel.getName().equals("Search Panel")) {
                    am.searchpanel.createtablemodel();
                    am.searchpanel.setVisible(true);
                } else if (lastpanel.getName().equals("Users Panel")) {
                    am.userspanel.createtablemodel();
                    am.userspanel.setVisible(true);
                } else {
                    lastpanel.setVisible(true);
                }
            }
        });
        editdetailsbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                AddTeacherDialog ad = new AddTeacherDialog(am, f);
                ad.setLocationRelativeTo(null);
                ad.setVisible(true);
            }
        }
        );
        assignCoursebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AssignCourseDialog as = new AssignCourseDialog(f, am);
                as.setLocation(450, 100);
                as.setVisible(true);
            }
        });

    }

    public ViewTeacherPanel(Teacher f, TeacherMain fm, JComponent lastpanel) {
        // TODO Auto-generated constructor stub
        this(f);
        this.lastpanel = lastpanel;
        assignCoursebutton.setVisible(false);
        editdetailsbutton.setVisible(false);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                fm.viewteacherpanel.setVisible(false);
                if (lastpanel.getName().equals("Teacher Panel")) {
                    if (fm.teacherpanel.viewbutton.getText().equals("Photo View")) {
                        fm.teacherpanel.createtablemodel();
                    } else {
                        fm.teacherpanel.createphotoviewpanel();
                    }
                    fm.teacherpanel.setVisible(true);
                } else if (lastpanel.getName().equals("Search Panel")) {
                    fm.searchpanel.createtablemodel();
                    fm.searchpanel.setVisible(true);
                } else {
                    lastpanel.setVisible(true);
                }
            }
        });
    }

    public ViewTeacherPanel(Teacher f, TeacherMain fm) {
        // TODO Auto-generated constructor stub
        this(f);
        assignCoursebutton.setVisible(false);
        editdetailsbutton.setText("Change Password");
        backbutton.setVisible(false);
        editdetailsbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ChangePasswordDialog cp = new ChangePasswordDialog(f);
                cp.setLocationRelativeTo(null);
                cp.setVisible(true);
            }
        }
        );

    }

    public ViewTeacherPanel(Teacher f, StudentMain sm, JComponent lastpanel) {
        // TODO Auto-generated constructor stub
        this(f);
        this.lastpanel = lastpanel;
        assignCoursebutton.setVisible(false);
        editdetailsbutton.setVisible(false);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sm.viewteacherpanel.setVisible(false);
                if (lastpanel.getName().equals("Teacher Panel")) {
                    if (sm.teacherpanel.viewbutton.getText().equals("Photo View")) {
                        sm.teacherpanel.createtablemodel();
                    } else {
                        sm.teacherpanel.createphotoviewpanel();
                    }
                    sm.teacherpanel.setVisible(true);
                } else if (lastpanel.getName().equals("Search Panel")) {
                    sm.searchpanel.createtablemodel();
                    sm.searchpanel.setVisible(true);
                } else {
                    lastpanel.setVisible(true);
                }

            }
        });
    }
}
