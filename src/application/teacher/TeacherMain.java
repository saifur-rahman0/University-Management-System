package application.teacher;

import application.admin.AdminData;
import application.admin.AdminProfilePanel;
import application.common.DataBaseConnection;
import application.common.HomePanel;
import application.common.SearchPanel;
import application.common.TimeUtil;
import application.course.AssignCoursePanel;
import application.course.CoursePanel;
import application.login.LoginPageFrame;
import application.student.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class TeacherMain extends JFrame implements ActionListener {
    public JPanel contentPane;
    private final JLabel teachernamelabel;
    private final JLabel teacherprofilepiclabel;
    private final JPanel profilepanel;
    private final JButton homebutton;
    private final JButton studentsbutton;
    private final JButton coursebutton;
    private final JButton teachersbutton;
    private final JButton entermarksbutton;
    private final JButton assignedcoursebutton;
    private final JButton markattandancebutton;
    private final JButton marksheetreportbutton;
    private final JButton attandancereportbutton;
    private final JButton searchbutton;
    private final JButton logoutbutton;
    private final JButton exitbutton;
    private final Color buttonbcolor = new Color(155, 72, 169, 255);
    private final Color buttonfcolor = Color.DARK_GRAY;
    private final Font buttonfont = new Font("Tw Cen MT", Font.PLAIN, 20);
    public CoursePanel coursepanel;
    public HomePanel homepanel;
    public StudentPanel studentpanel;
    public ViewStudentPanel viewstudentpanel;
    public MarkSheetPanel marksheetpanel;
    public JScrollPane marksheetpanelscroll;
    public ViewTeacherPanel viewteacherpanel;
    public AssignCoursePanel assigncoursepanel;
    public EnterMarksPanel entermarkspanel;
    public JScrollPane entermarkspanelscroll;
    private MarkAttandancePanel markattandancepanel;
    private JScrollPane markattandancepanelscroll;
    public AttandanceReportPanel attandancereportpanel;
    public JScrollPane attandancereportpanelscroll;
    public TeacherPanel teacherpanel;
    public AdminProfilePanel adminprofilepanel;
    public SearchPanel searchpanel;
    public int panely = 0, panelx = 250;
    private JButton btn;
    private final JButton myprofilebutton;
    private final String lastlogin;
    public Teacher t;
    private int row = 63;
    private final Timer timer;
    public MarkSheetReportPanel marksheetreportpanel;
    public JScrollPane marksheetreportpanelscroll;
    private final JButton contactusbutton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    if (DataBaseConnection.checkconnection()) {
                        Teacher t = new TeacherData().getTeacherInfo(1);
                        TeacherMain frame = new TeacherMain(t);
                        frame.setVisible(true);
                    } else {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        JOptionPane.showMessageDialog(null, "You Are Not Connected To DataBase", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public TeacherMain(Teacher t) {
        this.t = t;
        timer = new Timer(1000, this);
        timer.start();
        Color bgColor = new Color(125, 104, 196);
        Color frColor = Color.white;
        UIManager.put("ComboBoxUI", "com.sun.java.swing.plaf.windows.WindowsComboBoxUI");
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(bgColor));
        UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
        UIManager.put("ComboBox.foreground", new ColorUIResource(Color.DARK_GRAY));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(frColor));
        UIManager.put("ScrollBarUI", "com.sun.java.swing.plaf.windows.WindowsScrollBarUI");

        this.setResizable(false);
        setTitle("University Data Management");
        setIconImage(new AdminData().getProfilePic());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setForeground(Color.DARK_GRAY);
        contentPane.setBackground(new Color(108, 67, 164, 255));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.setBounds(-2, 0, 1370, 733);
        createpanel();
        JPanel sidebarpanel = new JPanel();
        sidebarpanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(50, 0, 70, 31)));
        sidebarpanel.setBackground(new Color(183, 183, 183, 255));
        sidebarpanel.setBounds(5, 11, 240, 706);
        contentPane.add(sidebarpanel);
        sidebarpanel.setLayout(null);

        profilepanel = new JPanel();
        profilepanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        profilepanel.setBackground(new Color(108, 67, 164));
        profilepanel.setBounds(0, 0, 240, 61);
        sidebarpanel.add(profilepanel);
        profilepanel.setLayout(null);

        teachernamelabel = new JLabel();
        teachernamelabel.setForeground(Color.WHITE);
        teachernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
        teachernamelabel.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
        teachernamelabel.setBackground(new Color(5,5,5, 0));
        teachernamelabel.setOpaque(true);
        teachernamelabel.setBounds(65, 5, 171, 36);
        profilepanel.add(teachernamelabel);

        teacherprofilepiclabel = new JLabel();
        teacherprofilepiclabel.setBounds(5, 0, 50, 50);
        profilepanel.add(teacherprofilepiclabel);
        teacherprofilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
        teacherprofilepiclabel.setBackground(new Color(5,5,5, 0));

        teacherprofilepiclabel.setBorder(new LineBorder(Color.black, 0));
        teacherprofilepiclabel.setOpaque(true);

        homebutton = createButton("Home");
        sidebarpanel.add(homebutton);
        btn = homebutton;

        studentsbutton = createButton("Students");
        sidebarpanel.add(studentsbutton);

        coursebutton = createButton("Courses");
        sidebarpanel.add(coursebutton);

        teachersbutton = createButton("Co-Teachers", "Teachers");
        sidebarpanel.add(teachersbutton);

        assignedcoursebutton = createButton("Assigned Course", "Assign Course");
        sidebarpanel.add(assignedcoursebutton);

        entermarksbutton = createButton("Enter Marks");
        sidebarpanel.add(entermarksbutton);

        marksheetreportbutton = createButton("Marksheet Report");
        sidebarpanel.add(marksheetreportbutton);

        markattandancebutton = createButton("Mark Attandance");
        sidebarpanel.add(markattandancebutton);

        attandancereportbutton = createButton("Attandance Report");
        sidebarpanel.add(attandancereportbutton);

        searchbutton = createButton("Search");
        sidebarpanel.add(searchbutton);

        myprofilebutton = createButton("My Profile", "Profile");
        sidebarpanel.add(myprofilebutton);

        contactusbutton = createButton("Contact Us");
        sidebarpanel.add(contactusbutton);

        logoutbutton = createButton("logout");
        sidebarpanel.add(logoutbutton);

        exitbutton = createButton("Exit");
        sidebarpanel.add(exitbutton);

        activeButton(homebutton);
        homepanel.setVisible(true);

        this.setTeacherDetails();
        lastlogin = t.getLastLogin();
        homepanel.setLastLogin(lastlogin);
        t.setLastLogin(TimeUtil.getCurrentTime());
        t.setActiveStatus(true);
        new TeacherData().updateTeacherData(t, t);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowenent) {
                openPanel(exitbutton);
            }
        });
    }

    public void createpanel() {
        homepanel = new HomePanel(t);
        homepanel.setLocation(panelx, panely);
        homepanel.setVisible(true);
        homepanel.setFocusable(true);
        contentPane.add(homepanel);
    }

    public void activeButton(JButton button) {
        btn.setContentAreaFilled(false);
        btn.setBackground(buttonbcolor);
        btn.setForeground(buttonfcolor);
        btn.setFont(buttonfont);
        btn.setDisabledIcon(new ImageIcon(""));
        btn.setIcon(new ImageIcon("./assets/sidebar/" + btn.getName() + "dac.png"));
        btn = button;
        btn.setForeground(Color.white);
        btn.setContentAreaFilled(true);
        btn.setBackground(buttonbcolor);
        btn.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        btn.setIcon(new ImageIcon("./assets/sidebar/" + btn.getName() + "ac.png"));
        disablepanel();
    }

    public JButton createButton(String text, String name) {
        JButton button = createButton(text);
        button.setName(name);
        button.setIcon(new ImageIcon("./assets/sidebar/" + button.getName() + "dac.png"));
        return button;
    }

    public JButton createButton(String text) {
        JButton button = new JButton();
        button.setForeground(buttonfcolor);
        button.setFont(buttonfont);
        button.setBackground(buttonbcolor);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setText(text);
        button.setName(text);
        button.setIcon(new ImageIcon("./assets/sidebar/" + button.getName() + "dac.png"));
        button.addActionListener(this);
        button.setIconTextGap(10);
        button.setLocation(0, row);
        button.setSize(234, 40);
        row += 39;
        return button;
    }

    public void disablepanel() {
        if (homepanel != null && homepanel.isVisible()) {
            homepanel.setVisible(false);
        } else if (coursepanel != null && coursepanel.isVisible()) {
            coursepanel.setVisible(false);
        } else if (studentpanel != null && studentpanel.isVisible()) {
            studentpanel.setVisible(false);
        } else if (viewstudentpanel != null && viewstudentpanel.isVisible()) {

            viewstudentpanel.setVisible(false);
        } else if (teacherpanel != null && teacherpanel.isVisible()) {
            teacherpanel.setVisible(false);
        } else if (viewteacherpanel != null && viewteacherpanel.isVisible()) {
            viewteacherpanel.setVisible(false);
        } else if (assigncoursepanel != null && assigncoursepanel.isVisible()) {
            assigncoursepanel.setVisible(false);
        } else if (entermarkspanelscroll != null && entermarkspanelscroll.isVisible()) {
            entermarkspanelscroll.setVisible(false);
        } else if (marksheetpanelscroll != null && marksheetpanelscroll.isVisible()) {
            marksheetpanelscroll.setVisible(false);
        } else if (markattandancepanelscroll != null && markattandancepanelscroll.isVisible()) {
            markattandancepanelscroll.setVisible(false);
        } else if (marksheetreportpanelscroll != null && marksheetreportpanelscroll.isVisible()) {
            marksheetreportpanelscroll.setVisible(false);
        } else if (attandancereportpanelscroll != null && attandancereportpanelscroll.isVisible()) {
            attandancereportpanelscroll.setVisible(false);
        } else if (adminprofilepanel != null && adminprofilepanel.isVisible()) {
            adminprofilepanel.setVisible(false);
        } else if (searchpanel != null && searchpanel.isVisible()) {
            searchpanel.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.openPanel(e.getSource());
    }

    public void openPanel(Object source) {
        if (source == homebutton) {
            activeButton(homebutton);
            homepanel = new HomePanel(t);
            homepanel.setLocation(panelx, panely);
            homepanel.setFocusable(true);
            contentPane.add(homepanel);
            homepanel.setLastLogin(lastlogin);
            homepanel.setVisible(true);

        } else if (source == coursebutton) {
            activeButton(coursebutton);
            coursepanel = new CoursePanel(this);
            coursepanel.setLocation(panelx, panely);
            coursepanel.setFocusable(true);
            contentPane.add(coursepanel);
            coursepanel.setVisible(true);
        } else if (source == studentsbutton) {
            activeButton(studentsbutton);
            studentpanel = new StudentPanel(this);
            studentpanel.setLocation(panelx, panely);
            studentpanel.setVisible(true);
            studentpanel.setFocusable(true);
            contentPane.add(studentpanel);
        } else if (source == teachersbutton) {
            activeButton(teachersbutton);
            teacherpanel = new TeacherPanel(this);
            teacherpanel.setLocation(panelx, panely);
            teacherpanel.setVisible(true);
            teacherpanel.setFocusable(true);
            contentPane.add(teacherpanel);

        } else if (source == assignedcoursebutton) {
            activeButton(assignedcoursebutton);
            assigncoursepanel = new AssignCoursePanel(this);
            assigncoursepanel.setLocation(panelx, panely);
            assigncoursepanel.setVisible(true);
            assigncoursepanel.setFocusable(true);
            contentPane.add(assigncoursepanel);

        } else if (source == entermarksbutton) {

            activeButton(entermarksbutton);
            entermarkspanel = new EnterMarksPanel(this);
            entermarkspanel.setVisible(true);
            entermarkspanelscroll = new JScrollPane(entermarkspanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            entermarkspanelscroll.setBounds(panelx, panely, 1116, 705);
            entermarkspanelscroll.setVisible(true);
            entermarkspanelscroll.getVerticalScrollBar().setUnitIncrement(16);
            contentPane.add(entermarkspanelscroll);
            for (Component c : entermarkspanelscroll.getComponents()) {
                c.setBackground(Color.white);
            }

        } else if (source == marksheetreportbutton) {
            activeButton(marksheetreportbutton);
            marksheetreportpanel = new MarkSheetReportPanel(this);
            marksheetreportpanel.setVisible(true);
            marksheetreportpanelscroll = new JScrollPane(marksheetreportpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            marksheetreportpanelscroll.setBounds(panelx, panely, 1116, 705);
            marksheetreportpanelscroll.setVisible(true);
            marksheetreportpanelscroll.setName("Marksheet Report Panel Scroll");
            marksheetreportpanelscroll.getVerticalScrollBar().setUnitIncrement(80);
            contentPane.add(marksheetreportpanelscroll);
            for (Component c : marksheetreportpanelscroll.getComponents()) {
                c.setBackground(Color.white);
            }
        } else if (source == markattandancebutton) {
            activeButton(markattandancebutton);
            markattandancepanel = new MarkAttandancePanel(this);
            markattandancepanel.setVisible(true);
            markattandancepanelscroll = new JScrollPane(markattandancepanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            markattandancepanelscroll.setBounds(panelx, panely, 1116, 705);
            markattandancepanelscroll.setVisible(true);
            markattandancepanelscroll.getVerticalScrollBar().setUnitIncrement(16);
            contentPane.add(markattandancepanelscroll);
            for (Component c : markattandancepanelscroll.getComponents()) {
                c.setBackground(Color.white);
            }
        } else if (source == attandancereportbutton) {
            activeButton(attandancereportbutton);
            attandancereportpanel = new AttandanceReportPanel(this);
            attandancereportpanel.setVisible(true);
            attandancereportpanelscroll = new JScrollPane(attandancereportpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            attandancereportpanelscroll.setBounds(panelx, panely, 1116, 705);
            attandancereportpanelscroll.setVisible(true);
            attandancereportpanelscroll.setName("Attadance Report Panel Scroll");
            attandancereportpanelscroll.getVerticalScrollBar().setUnitIncrement(16);
            contentPane.add(attandancereportpanelscroll);
            for (Component c : attandancereportpanelscroll.getComponents()) {
                c.setBackground(Color.white);
            }
        }
        else if (source == searchbutton) {
            activeButton(searchbutton);
            searchpanel = new SearchPanel(this);
            searchpanel.setLocation(this.panelx, this.panely);
            searchpanel.setVisible(true);
            contentPane.add(searchpanel);

        } else if (source == contactusbutton) {
            activeButton(contactusbutton);
            adminprofilepanel = new AdminProfilePanel();
            adminprofilepanel.setLocation(panelx, panely);
            adminprofilepanel.setVisible(true);
            adminprofilepanel.setFocusable(true);
            contentPane.add(adminprofilepanel);
        } else if (source == myprofilebutton) {
            activeButton(myprofilebutton);
            viewteacherpanel = new ViewTeacherPanel(t, this);
            viewteacherpanel.setLocation(panelx, panely);
            viewteacherpanel.setVisible(true);
            viewteacherpanel.setFocusable(true);
            contentPane.add(viewteacherpanel);
        } else if (source == exitbutton) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to exit this application ?", "Exit", JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                t.setActiveStatus(false);
                timer.stop();
                disablepanel();
                System.exit(0);
            }
        } else if (source == logoutbutton) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to logout this application ?", "Logout", JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                t.setActiveStatus(false);
                timer.stop();
                LoginPageFrame loginpageframe = new LoginPageFrame();
                loginpageframe.setVisible(true);
                loginpageframe.setLocationRelativeTo(null);
                disablepanel();
                this.dispose();
            }
        }
    }

    public void setTeacherDetails() {
        teacherprofilepiclabel.setIcon(new ImageIcon(t.getRoundedProfilePic(50, 50, 50)));
        teachernamelabel.setText(t.getTeacherName());
    }
}
