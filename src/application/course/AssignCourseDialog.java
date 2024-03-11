package application.course;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import application.admin.AdminMain;
import application.department.DepartmentData;
import application.teacher.Teacher;
import application.teacher.TeacherData;
import application.teacher.ViewTeacherPanel;

/*
 * Title : AssignCourseDialog.java
 * Purpose : For assigning course to teacher
 */
@SuppressWarnings("serial")
public class AssignCourseDialog extends JDialog implements ActionListener {

    private final JPanel contentPanel = new JPanel();
    Teacher t = null;
    static AssignCourseDialog dialog;
    private JComboBox<String> deptnamecombo, semoryearcombo, coursenamecombo, positioncombo;
    private JButton assigncoursebutton;
    private AdminMain am;
    JLabel Errorlabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            dialog = new AssignCourseDialog(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     *
     * @param t
     * @wbp.parser.constructor
     */
    private AssignCourseDialog(Teacher t) {

        super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);
        this.setLocation(450, 100);

        getContentPane().setBackground(Color.WHITE);
        this.t = t;
        setSize(521, 580);
        getContentPane().setLayout(null);

        JLabel headerlabel = new JLabel("Assign Course");
        headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerlabel.setBackground(new Color(48, 11, 103, 105));
        headerlabel.setOpaque(true);
        headerlabel.setForeground(new Color(255, 255, 255));
        headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
        headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
        headerlabel.setBounds(0, 0, 510, 39);
        getContentPane().add(headerlabel);

        JLabel lblImage = new JLabel();
        lblImage.setBounds(34, 50, 98, 111);

        lblImage.setIcon(new ImageIcon(t.getProfilePic(lblImage.getWidth(), lblImage.getHeight())));
        lblImage.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        lblImage.setOpaque(true);
        lblImage.setBackground(new Color(240, 255, 255));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblImage);

        JLabel lblTeacherName = new JLabel("Teacher Name  :  " + t.getTeacherName());
        lblTeacherName.setHorizontalAlignment(SwingConstants.LEFT);
        lblTeacherName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblTeacherName.setBounds(156, 77, 293, 29);
        getContentPane().add(lblTeacherName);

        JLabel lblDegree = new JLabel("Qualification :  " + t.getQualification());
        lblDegree.setHorizontalAlignment(SwingConstants.LEFT);
        lblDegree.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblDegree.setBounds(156, 108, 293, 26);
        getContentPane().add(lblDegree);

        JLabel lblExperience = new JLabel("Experience  :  " + t.getExperience());
        lblExperience.setHorizontalAlignment(SwingConstants.LEFT);
        lblExperience.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblExperience.setBounds(156, 138, 293, 26);
        getContentPane().add(lblExperience);

        JLabel lblTeacherId = new JLabel("Teacher ID  : " + t.getTeacherId());
        lblTeacherId.setHorizontalAlignment(SwingConstants.LEFT);
        lblTeacherId.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblTeacherId.setBounds(156, 50, 323, 22);
        getContentPane().add(lblTeacherId);

        deptnamecombo = new JComboBox<String>(new DepartmentData().getDeptName());
        deptnamecombo.setFocusable(false);
        deptnamecombo.addActionListener(this);
        deptnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        deptnamecombo.setBackground(new Color(255, 255, 255));
        deptnamecombo.setBounds(156, 199, 338, 39);
        getContentPane().add(deptnamecombo);

        JLabel lblDeptName = new JLabel("Department Name:");
        lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDeptName.setFont(new Font("Candara", Font.PLAIN, 18));
        lblDeptName.setBounds(10, 199, 138, 39);
        getContentPane().add(lblDeptName);

        JLabel lblSelectSemyear = new JLabel("Semster/Year  :");
        lblSelectSemyear.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSelectSemyear.setFont(new Font("Candara", Font.PLAIN, 18));
        lblSelectSemyear.setBounds(10, 265, 138, 37);
        getContentPane().add(lblSelectSemyear);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setFocusable(false);
        semoryearcombo.addActionListener(this);
        semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        semoryearcombo.setBackground(Color.WHITE);
        semoryearcombo.setBounds(156, 265, 338, 39);
        getContentPane().add(semoryearcombo);

        JLabel lblCourse = new JLabel("Course  :");
        lblCourse.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCourse.setFont(new Font("Candara", Font.PLAIN, 18));
        lblCourse.setBounds(10, 332, 138, 37);
        getContentPane().add(lblCourse);

        coursenamecombo = new JComboBox<String>();
        coursenamecombo.addActionListener(this);
        coursenamecombo.setFocusable(false);
        coursenamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        coursenamecombo.setBackground(Color.WHITE);
        coursenamecombo.setBounds(156, 332, 338, 39);
        getContentPane().add(coursenamecombo);

        JLabel lblPosition = new JLabel("Position  :");
        lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPosition.setFont(new Font("Candara", Font.PLAIN, 18));
        lblPosition.setBounds(10, 397, 138, 37);
        getContentPane().add(lblPosition);

        positioncombo = new JComboBox<String>();
        positioncombo.setFocusable(false);
        positioncombo.addActionListener(this);
        positioncombo.setModel(new DefaultComboBoxModel<String>(new String[]{"---Select Position---", "Full Professor", "Associate Professor", "Assistant Professor", "Lecturer", "lab Assitant"}));
        positioncombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        positioncombo.setBackground(Color.WHITE);
        positioncombo.setBounds(156, 397, 338, 39);
        getContentPane().add(positioncombo);

        assigncoursebutton = new JButton("Assign Course");
        assigncoursebutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        assigncoursebutton.setFocusable(false);
        assigncoursebutton.addActionListener(this);
        assigncoursebutton.setBackground(new Color(255, 255, 255));
        assigncoursebutton.setForeground(new Color(61, 0, 169));
        assigncoursebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        assigncoursebutton.setBounds(356, 485, 139, 37);
        assigncoursebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getContentPane().add(assigncoursebutton);

        JLabel label = new JLabel("");
        label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
        label.setBounds(0, 462, 505, 8);
        getContentPane().add(label);

        JLabel label_1 = new JLabel("");
        label_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
        label_1.setBounds(0, 172, 505, 8);
        getContentPane().add(label_1);

        Errorlabel = new JLabel("This is required question  !");
        Errorlabel.setVisible(false);
        Errorlabel.setForeground(Color.RED);
        Errorlabel.setFont(new Font("Arial", Font.PLAIN, 14));
        Errorlabel.setBounds(156, 236, 215, 22);
        getContentPane().add(Errorlabel);

        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        if (!t.getDeptCode().equals("Not Assigned")) {
            this.setDataInComboBox();
        }
    }

    public AssignCourseDialog(Teacher t, AdminMain am) {
        // TODO Auto-generated constructor stub
        this(t);
        this.am = am;
    }

    public void setDataInComboBox() {
        deptnamecombo.setSelectedItem(new DepartmentData().getdeptname(t.getDeptCode()));
        semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(deptnamecombo.getSelectedItem() + "")));
        String[] totalsub = new CourseData().getCourseDept(t.getDeptCode(), t.getSemorYear());
        coursenamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
        semoryearcombo.setSelectedIndex(t.getSemorYear());
        coursenamecombo.setSelectedItem(t.getCourse());
        positioncombo.setSelectedItem(t.getPosition());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Errorlabel.setVisible(false);
        if (e.getSource() == deptnamecombo) {
            deptnamecombo.setFocusable(false);
            coursenamecombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            if (deptnamecombo.getSelectedIndex() == 0) {
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            } else {
                String dept = (String) deptnamecombo.getSelectedItem();
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(dept)));
            }
        }
        if (e.getSource() == semoryearcombo && semoryearcombo.getSelectedIndex() > 0) {
            String dept = (String) deptnamecombo.getSelectedItem();
            String[] totalsub = new CourseData().getCourseDept(new DepartmentData().getDeptcode(dept), semoryearcombo.getSelectedIndex());
            if (totalsub != null) {
                coursenamecombo.setModel(new DefaultComboBoxModel<String>(totalsub));
            } else {
                coursenamecombo.setModel(new DefaultComboBoxModel<String>(new String[]{"No Course"}));
            }
        }
        if (e.getSource() == assigncoursebutton) {
            if (deptnamecombo.getSelectedIndex() == 0) {
                showerror(deptnamecombo);
            } else if (semoryearcombo.getSelectedIndex() == 0) {
                showerror(semoryearcombo);
            } else if (coursenamecombo.getSelectedIndex() == 0) {
                showerror(coursenamecombo);
            } else if (positioncombo.getSelectedIndex() == 0) {
                showerror(positioncombo);
            } else {
                Teacher tnew = new Teacher();
                tnew.setDeptCode(new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + ""));
                tnew.setPosition(positioncombo.getSelectedItem() + "");
                tnew.setSemorYear(semoryearcombo.getSelectedIndex());
                tnew.setCourse(coursenamecombo.getSelectedItem() + "");
                tnew.setTeacherId(t.getTeacherId());
                tnew.setTeacherName(t.getTeacherName());
                int result = new TeacherData().assignCourse(t, tnew);
                if (result > 0) {
                    if (am != null) {
                        if (am.assignCoursepanel != null && am.assignCoursepanel.isVisible()) {
                            am.assignCoursepanel.createtablemodel();
                        } else if (am.viewteacherpanel != null && am.viewteacherpanel.isVisible()) {
                            System.out.println("updateing ");
                            am.viewteacherpanel.setVisible(false);
                            am.viewteacherpanel = new ViewTeacherPanel(new TeacherData().getTeacherInfobyId(t.getTeacherId()), am, am.viewteacherpanel.lastpanel);
                            am.viewteacherpanel.setVisible(true);
                            am.viewteacherpanel.setLocation(am.panelx, am.panely);
                            am.getContentPane().add(am.viewteacherpanel);
                        }
                    }
                    this.dispose();
                }
            }
        }

    }

    public void showerror(JComponent tf) {
        Errorlabel.setVisible(true);
        Errorlabel.setBounds(tf.getX(), tf.getY() + tf.getHeight() - 5, 400, 26);
    }

}
