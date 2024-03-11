package application.admin;

import application.common.*;
import application.course.AssignCoursePanel;
import application.course.CoursePanel;
import application.department.DepartmentPanel;
import application.login.LoginPageFrame;
import application.student.*;
import application.teacher.TeacherPanel;
import application.teacher.ViewTeacherPanel;

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

public class AdminMain extends JFrame implements ActionListener {
    public JPanel contentPane;
    private final JLabel collagenamelabel;
    private final JLabel profilepiclabel;
    private final JPanel profilepanel;
    private final JButton homebutton;
    private final JButton courcebutton;
    private final JButton studentsbutton;
    private final JButton coursebutton;
    private final JButton deptbutton;
    private final JButton usersbutton;
    private final JButton entermarksbutton;
    private final JButton assigncoursebutton;
    private final JButton markattandancebutton;
    private final JButton attandancereportbutton;
    private final JButton searchbutton;
    private final JButton exitbutton;
    private JButton btn;
    private final JButton adminprofilebutton;

    private final Color buttonbcolor = new Color(155, 72, 169, 255);
    private final Color buttonfcolor = Color.DARK_GRAY;
    private final Font buttonfont = new Font("Tw Cen MT", Font.PLAIN, 20);
    private DepartmentPanel courcepanel;
    private CoursePanel coursepanel;
    private HomePanel homepanel;

    public StudentPanel studentpanel;
    public ViewStudentPanel viewstudentpanel;
    public MarkSheetPanel marksheetpanel;
    public JScrollPane marksheetpanelscroll;
    public ViewTeacherPanel viewteacherpanel;
    public AssignCoursePanel assignCoursepanel;
    public EnterMarksPanel entermarkspanel;
    public JScrollPane entermarkspanelscroll;
    private MarkAttandancePanel markattandancepanel;
    private JScrollPane markattandancepanelscroll;
    public AttandanceReportPanel attandancereportpanel;
    public JScrollPane attandancereportpanelscroll;
    public MarkSheetReportPanel marksheetreportpanel;
    public JScrollPane marksheetreportpanelscroll;
    public TeacherPanel teacherpanel;
    public AdminProfilePanel adminprofilepanel;
    public SearchPanel searchpanel;
    public UsersPanel userspanel;

    public int panely = 0, panelx = 250;

    private Admin a;
    private final String lastlogin;
    private int row = 0;
    private final JButton logoutbutton;

    private final Timer timer = new Timer(2000, this);
    private final JButton marksheetreportbutton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    if (DataBaseConnection.checkconnection()) {
                        AdminMain frame = new AdminMain();
                        frame.setVisible(true);
                    } else {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        JOptionPane.showMessageDialog(null, "You Are Not Connected To DataBase", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    new Thread().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminMain() {

        a = new AdminData().getAdminData();
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

        profilepanel = new JPanel();
        profilepanel.setBounds(5, 7, 240, 63);
        contentPane.add(profilepanel);
        profilepanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        profilepanel.setBackground(new Color(108, 67, 164));
        profilepanel.setLayout(null);

        collagenamelabel = new JLabel();
        collagenamelabel.setForeground(Color.WHITE);
        collagenamelabel.setHorizontalAlignment(SwingConstants.LEFT);
        collagenamelabel.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
        collagenamelabel.setBackground(new Color(108, 67, 164));
        collagenamelabel.setText("Adminstrator");
        collagenamelabel.setOpaque(true);
        collagenamelabel.setBounds(65, 5, 171, 36);
        profilepanel.add(collagenamelabel);

        profilepiclabel = new JLabel();
        profilepiclabel.setBounds(5, 0, 50, 50);
        profilepanel.add(profilepiclabel);
        profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilepiclabel.setBackground(new Color(108, 67, 164));
        profilepiclabel.setBorder(new LineBorder(Color.black, 0));
        profilepiclabel.setOpaque(true);

        createHomepanel();

        //creating side bar panel
        JPanel sidebarpanel = new JPanel();
        sidebarpanel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(255, 0, 0, 31)));
        sidebarpanel.setBackground(new Color(183, 183, 183, 255));
        sidebarpanel.setBounds(5, 75, 240, 654);
        contentPane.add(sidebarpanel);
        sidebarpanel.setLayout(null);

        //Adding buttons to sidebar
        homebutton = createButton("Home");
        sidebarpanel.add(homebutton);
        btn = homebutton;

        courcebutton = createButton("Departments");
        sidebarpanel.add(courcebutton);

        studentsbutton = createButton("Students");
        sidebarpanel.add(studentsbutton);

        coursebutton = createButton("Courses");
        sidebarpanel.add(coursebutton);

        deptbutton = createButton("Teachers");
        sidebarpanel.add(deptbutton);

        assigncoursebutton = createButton("Assign Course");
        sidebarpanel.add(assigncoursebutton);

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

        usersbutton = createButton("Users");
        sidebarpanel.add(usersbutton);

        adminprofilebutton = createButton("Admin Profile", "Profile");
        sidebarpanel.add(adminprofilebutton);

        logoutbutton = createButton("Logout");
        sidebarpanel.add(logoutbutton);

        exitbutton = createButton("Exit");
        sidebarpanel.add(exitbutton);

        activeButton(homebutton);
        homepanel.setVisible(true);

        this.setCollageDetails();
        lastlogin = a.getLastLogin();
        homepanel.setLastLogin(lastlogin);
        a.setLastLogin(TimeUtil.getCurrentTime());
        new AdminData().updateAdminDetails(a);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowenent) {
                openPanel(exitbutton);
            }
        });

    }

    public void createHomepanel() {
        homepanel = new HomePanel(a);
        homepanel.setLocation(panelx, panely);
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
        btn.setFont(new Font("Tw Cen MT", Font.BOLD, 24));
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
        button.setBorder(new EmptyBorder(0, 4, 0, 0));
        button.setText(text);
        button.setName(text);
        button.setIcon(new ImageIcon("./assets/sidebar/" + button.getName() + "dac.png"));
        button.addActionListener(this);
        button.setIconTextGap(13);
        button.setLocation(0, row);
        button.setSize(240, 40);
        row += 39;
        return button;
    }

    public void disablepanel() {
        if (homepanel != null && homepanel.isVisible()) {
            homepanel.setVisible(false);
        } else if (courcepanel != null && courcepanel.isVisible()) {
            courcepanel.setVisible(false);
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
        } else if (assignCoursepanel != null && assignCoursepanel.isVisible()) {
            assignCoursepanel.setVisible(false);
        } else if (entermarkspanelscroll != null && entermarkspanelscroll.isVisible()) {
            entermarkspanelscroll.setVisible(false);
        } else if (marksheetpanelscroll != null && marksheetpanelscroll.isVisible()) {
            marksheetpanelscroll.setVisible(false);
        } else if (markattandancepanelscroll != null && markattandancepanelscroll.isVisible()) {
            markattandancepanelscroll.setVisible(false);
        } else if (attandancereportpanelscroll != null && attandancereportpanelscroll.isVisible()) {
            attandancereportpanelscroll.setVisible(false);
        } else if (marksheetreportpanelscroll != null && marksheetreportpanelscroll.isVisible()) {
            marksheetreportpanelscroll.setVisible(false);
        } else if (adminprofilepanel != null && adminprofilepanel.isVisible()) {
            adminprofilepanel.setVisible(false);
        } else if (searchpanel != null && searchpanel.isVisible()) {
            searchpanel.setVisible(false);
        } else if (userspanel != null && userspanel.isVisible()) {
            userspanel.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openPanel(e.getSource());
    }

    public void openPanel(Object source) {
        if (source == homebutton) {
            activeButton(homebutton);
            homepanel = new HomePanel(a);
            homepanel.setLocation(panelx, panely);
            homepanel.setFocusable(true);
            contentPane.add(homepanel);
            homepanel.setVisible(true);
            homepanel.setLastLogin(lastlogin);
        } else if (source == courcebutton) {
            activeButton(courcebutton);
            courcepanel = new DepartmentPanel();
            courcepanel.setLocation(panelx, panely);
            courcepanel.setFocusable(true);
            contentPane.add(courcepanel);
            courcepanel.setVisible(true);

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

        } else if (source == deptbutton) {
            activeButton(deptbutton);
            teacherpanel = new TeacherPanel(this);
            teacherpanel.setLocation(panelx, panely);
            teacherpanel.setVisible(true);
            teacherpanel.setFocusable(true);
            contentPane.add(teacherpanel);
        } else if (source == assigncoursebutton) {
            activeButton(assigncoursebutton);
            assignCoursepanel = new AssignCoursePanel(this);
            assignCoursepanel.setLocation(panelx, panely);
            assignCoursepanel.setVisible(true);
            assignCoursepanel.setFocusable(true);
            contentPane.add(assignCoursepanel);

        } else if (source == entermarksbutton) {
            activeButton(entermarksbutton);
            entermarkspanel = new EnterMarksPanel();
            entermarkspanel.setVisible(true);
            entermarkspanelscroll = new JScrollPane(entermarkspanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            entermarkspanelscroll.setBounds(panelx, panely, 1116, 705);
            entermarkspanelscroll.setVisible(true);
            entermarkspanelscroll.getVerticalScrollBar().setUnitIncrement(80);
            contentPane.add(entermarkspanelscroll);
            for (Component c : entermarkspanelscroll.getComponents()) {
                c.setBackground(Color.white);
            }
        } else if (source == markattandancebutton) {
            activeButton(markattandancebutton);
            markattandancepanel = new MarkAttandancePanel(this);
            markattandancepanel.setVisible(true);
            markattandancepanelscroll = new JScrollPane(markattandancepanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            markattandancepanelscroll.setBounds(panelx, panely, 1116, 705);
            markattandancepanelscroll.setVisible(true);
            markattandancepanelscroll.getVerticalScrollBar().setUnitIncrement(80);
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
            attandancereportpanelscroll.getVerticalScrollBar().setUnitIncrement(80);
            contentPane.add(attandancereportpanelscroll);
            for (Component c : attandancereportpanelscroll.getComponents()) {
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
        } else if (source == usersbutton) {
            activeButton(usersbutton);
            userspanel = new UsersPanel(this);
            userspanel.setVisible(true);
            userspanel.setLocation(this.panelx, this.panely);
            contentPane.add(userspanel);
        } else if (source == searchbutton) {
            activeButton(searchbutton);
            searchpanel = new SearchPanel(this);
            searchpanel.setLocation(this.panelx, this.panely);
            searchpanel.setVisible(true);
            contentPane.add(searchpanel);

        } else if (source == adminprofilebutton) {
            activeButton(adminprofilebutton);
            adminprofilepanel = new AdminProfilePanel(this);
            adminprofilepanel.setLocation(panelx, panely);
            adminprofilepanel.setVisible(true);
            adminprofilepanel.setFocusable(true);
            contentPane.add(adminprofilepanel);
        } else if (source == exitbutton) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to exit this application ?", "Exit", JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                timer.stop();
                this.disablepanel();
                DataBaseConnection.closeConnection();
                System.exit(0);
            }
        } else if (source == logoutbutton) {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to logout this application ?", "Logout", JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                timer.stop();
                LoginPageFrame loginpageframe = new LoginPageFrame();
                loginpageframe.setVisible(true);
                loginpageframe.setLocationRelativeTo(null);
                this.disablepanel();
                this.dispose();
            }
        }

    }

    public void setCollageDetails() {
        a = new AdminData().getAdminData();
        profilepiclabel.setIcon(new ImageIcon(a.getRoundedProfilePic(50, 50, 50)));
    }
}
