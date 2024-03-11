package application.student;

import application.admin.AdminMain;
import application.common.HintTextField;
import application.course.CourseData;
import application.department.DepartmentData;
import application.department.RollNumberData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Title : AddStudentDialog.java
 * Purpose : For adding new student or edit student details
 */
@SuppressWarnings("serial")
public class AddStudentDialog extends JDialog implements ActionListener {

    private final JPanel contentPanel = new JPanel();
    private final JTextField rollnumberfield;
    private final JTextField firstnamefield;
    private final JTextField lastnamefield;
    private final JTextField emailidfield;
    private final JTextField contactnumberfield;
    private final JTextField addressfield;
    private final JTextField fathernamefield;
    private final JTextField fatheroccupationfield;
    private final JTextField mothernamefield;
    private final JTextField motheroccupationfield;
    private final JLabel lblPhoto;
    private final JLabel filename;
    private final JComboBox<String> deptnamecombo;
    private final JComboBox<String> semoryearcombo;
    private final JComboBox<String> optionalcoursecombo;
    private final JComboBox<String> gendercombo;
    private final JSpinner birthdatespinner;
    private final JButton choosefilebutton;
    private final JButton addstudentbutton;
    private File file;
    private String imagepath = null;
    private final JLabel filesize;
    private AdminMain am;
    private final JLabel profilepiclabel;
    private final JLabel filesizenote;
    private final JLabel Errorlabel;
    private static AddStudentDialog dialog;
    private StudentPanel sp;
    private final JLabel headerlabel;
    private Student student;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // If translucent windows aren't supported, exit.
        JFrame.setDefaultLookAndFeelDecorated(true);

        try {
            dialog = new AddStudentDialog();

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     *
     */
    public AddStudentDialog() {
        super(new JFrame(), true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 700);
        getContentPane().setLayout(null);
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        headerlabel = new JLabel("Add New Student");
        headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerlabel.setBounds(0, 0, 834, 40);
        getContentPane().add(headerlabel);

        headerlabel.setBackground(new Color(125, 104, 196));
        headerlabel.setOpaque(true);
        headerlabel.setForeground(new Color(255, 255, 255));
        headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
        headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        deptnamecombo = new JComboBox<String>(new DepartmentData().getDeptName());
        deptnamecombo.setForeground(Color.DARK_GRAY);
        deptnamecombo.setToolTipText("Department");
        deptnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        deptnamecombo.addActionListener(this);
        deptnamecombo.setBackground(new Color(255, 255, 255));
        deptnamecombo.setBounds(10, 51, 400, 40);
        deptnamecombo.setFocusable(false);
        getContentPane().add(deptnamecombo);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setPrototypeDisplayValue("--select prototype--");
        semoryearcombo.setToolTipText("Semester/Year");
        semoryearcombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        semoryearcombo.setBackground(Color.WHITE);
        semoryearcombo.setFocusable(false);
        semoryearcombo.setBounds(424, 51, 400, 40);
        semoryearcombo.addActionListener(this);
        semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
        getContentPane().add(semoryearcombo);

        rollnumberfield = new HintTextField("");
        rollnumberfield.setToolTipText("Roll Number");
        rollnumberfield.setFocusable(false);
        rollnumberfield.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
        rollnumberfield.setBounds(134, 116, 276, 40);
        getContentPane().add(rollnumberfield);
        rollnumberfield.setColumns(10);

        JLabel lblRollNo = new JLabel("Roll Number");
        lblRollNo.setForeground(Color.DARK_GRAY);
        lblRollNo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 19));
        lblRollNo.setFocusable(true);
        lblRollNo.setBounds(20, 116, 106, 40);
        getContentPane().add(lblRollNo);

        optionalcoursecombo = new JComboBox<String>();
        optionalcoursecombo.setToolTipText("Optional Course");
        optionalcoursecombo.setFocusable(false);
        optionalcoursecombo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    optionalcoursecombo.setFocusable(false);
                    firstnamefield.setFocusable(true);
                }
            }
        });
        optionalcoursecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
        optionalcoursecombo.setBackground(Color.WHITE);
        optionalcoursecombo.setBounds(424, 116, 400, 40);
        optionalcoursecombo.addActionListener(this);
        getContentPane().add(optionalcoursecombo);

        firstnamefield = new HintTextField("First Name");
        firstnamefield.setToolTipText("First Name");
        firstnamefield.setForeground(Color.DARK_GRAY);
        firstnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        firstnamefield.addActionListener(this);
        firstnamefield.setColumns(10);
        firstnamefield.setBounds(10, 177, 400, 40);
        getContentPane().add(firstnamefield);

        lastnamefield = new HintTextField("Last Name");
        lastnamefield.setToolTipText("Last Name");
        lastnamefield.setForeground(Color.DARK_GRAY);
        lastnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lastnamefield.setColumns(10);
        lastnamefield.setBounds(424, 177, 400, 40);
        getContentPane().add(lastnamefield);

        emailidfield = new HintTextField(" Email ID");
        emailidfield.setToolTipText("Email ID");
        emailidfield.setForeground(Color.DARK_GRAY);
        emailidfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        emailidfield.setColumns(10);
        emailidfield.setBounds(10, 240, 400, 40);
        getContentPane().add(emailidfield);

        contactnumberfield = new HintTextField(" Contact Number");
        contactnumberfield.setToolTipText("Contact Number");
        contactnumberfield.setForeground(Color.DARK_GRAY);
        contactnumberfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        contactnumberfield.setColumns(10);
        contactnumberfield.setBounds(424, 240, 400, 40);
        getContentPane().add(contactnumberfield);

        JLabel lblDateOfBirth = new JLabel("Date of Birth");
        lblDateOfBirth.setForeground(Color.DARK_GRAY);
        lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 19));
        lblDateOfBirth.setBounds(10, 302, 114, 40);
        getContentPane().add(lblDateOfBirth);

        birthdatespinner = new JSpinner();
        birthdatespinner.setToolTipText("Date Of Birth");
        birthdatespinner.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    birthdatespinner.setFocusable(false);
                }
            }
        });
        birthdatespinner.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        SimpleDateFormat model = new SimpleDateFormat("dd-MM-yyyy");
        birthdatespinner.setModel(new SpinnerDateModel());
        birthdatespinner.setEditor(new JSpinner.DateEditor(birthdatespinner, model.toPattern()));
        birthdatespinner.setBounds(134, 302, 276, 42);
        getContentPane().add(birthdatespinner);

        gendercombo = new JComboBox<String>();
        gendercombo.setToolTipText("Gender");
        gendercombo.setModel(new DefaultComboBoxModel<String>(new String[]{"---Select Gender---", "Male", "Female"}));
        gendercombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
        gendercombo.setBackground(Color.WHITE);
        gendercombo.addActionListener(this);
        gendercombo.setBounds(424, 303, 400, 40);
        gendercombo.setFocusable(false);
        getContentPane().add(gendercombo);

        addressfield = new HintTextField("Address");
        addressfield.setToolTipText("Address");
        addressfield.setForeground(Color.DARK_GRAY);
        addressfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        addressfield.setColumns(10);
        addressfield.setBounds(10, 363, 815, 40);
        getContentPane().add(addressfield);

        fathernamefield = new HintTextField(" Father Name");
        fathernamefield.setToolTipText("Father Name");
        fathernamefield.setForeground(Color.DARK_GRAY);
        fathernamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        fathernamefield.setColumns(10);
        fathernamefield.setBounds(10, 424, 400, 40);
        getContentPane().add(fathernamefield);

        fatheroccupationfield = new HintTextField(" Father Occupation");
        fatheroccupationfield.setToolTipText("Father Occupation");
        fatheroccupationfield.setForeground(Color.DARK_GRAY);
        fatheroccupationfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        fatheroccupationfield.setColumns(10);
        fatheroccupationfield.setBounds(424, 424, 400, 40);
        getContentPane().add(fatheroccupationfield);

        mothernamefield = new HintTextField(" Mother Name");
        mothernamefield.setToolTipText("Mother Name");
        mothernamefield.setForeground(Color.DARK_GRAY);
        mothernamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        mothernamefield.setColumns(10);
        mothernamefield.setBounds(10, 485, 400, 40);
        getContentPane().add(mothernamefield);

        motheroccupationfield = new HintTextField(" Mother Occupation");
        motheroccupationfield.setToolTipText("Mother Occupation");
        motheroccupationfield.setForeground(Color.DARK_GRAY);
        motheroccupationfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        motheroccupationfield.setColumns(10);
        motheroccupationfield.setBounds(424, 485, 400, 40);
        getContentPane().add(motheroccupationfield);

        filesizenote = new JLabel("Image size  <  1024 KB");
        filesizenote.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filesizenote.setBounds(134, 624, 545, 32);
        getContentPane().add(filesizenote);

        filesize = new JLabel("");
        filesize.setToolTipText("Image Size");
        filesize.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filesize.setBounds(200, 544, 566, 32);
        getContentPane().add(filesize);

        profilepiclabel = new JLabel();
        profilepiclabel.setToolTipText("Profile Picture");
        profilepiclabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        profilepiclabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
        profilepiclabel.setBounds(10, 536, 100, 120);
        getContentPane().add(profilepiclabel);
        profilepiclabel.setIcon(new ImageIcon("./assets/profilepicicon.jpg"));

        choosefilebutton = new JButton("Choose File");
        choosefilebutton.addActionListener(this);
        choosefilebutton.setFocusable(false);
        choosefilebutton.setBackground(new Color(245, 245, 245));
        choosefilebutton.setFont(new Font("Tahoma", Font.ITALIC, 14));
        choosefilebutton.setForeground(new Color(61, 0, 169));
        choosefilebutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        choosefilebutton.setBounds(134, 582, 114, 32);
        choosefilebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getContentPane().add(choosefilebutton);

        lblPhoto = new JLabel("Photo");
        lblPhoto.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        lblPhoto.setBounds(136, 548, 73, 21);
        getContentPane().add(lblPhoto);

        filename = new JLabel("No file choosen");
        filename.setToolTipText("File Name");
        filename.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filename.setBounds(258, 582, 566, 32);
        getContentPane().add(filename);

        addstudentbutton = new JButton("Add Student");
        addstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
        addstudentbutton.setForeground(new Color(255, 255, 255));
        addstudentbutton.setBackground(new Color(155, 72, 169, 255));
        addstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addstudentbutton.addActionListener(this);
        addstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addstudentbutton.setBounds(685, 613, 139, 37);
        addstudentbutton.setFocusable(false);
        getContentPane().add(addstudentbutton);

        Errorlabel = new JLabel("This is required question !");
        Errorlabel.setBorder(new MatteBorder(0, 0, 0, 0, new Color(255, 0, 0)));
        Errorlabel.setHorizontalAlignment(SwingConstants.LEFT);
        Errorlabel.setForeground(new Color(255, 69, 0));
        Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        Errorlabel.setVisible(false);
        Errorlabel.setBounds(10, 90, 400, 26);
        getContentPane().add(Errorlabel);

    }

    public AddStudentDialog(AdminMain am, Student s) {
        this();
        this.am = am;
        this.student = s;
        deptnamecombo.setSelectedItem(s.getDeptName());
        semoryearcombo.setSelectedIndex(s.getSemorYear());
        rollnumberfield.setText(s.getRollNumber() + "");
        rollnumberfield.setEditable(false);
        optionalcoursecombo.setSelectedItem(s.getOptionalCourse());
        firstnamefield.setText(s.getFirstName());
        lastnamefield.setText(s.getLastName());
        emailidfield.setText(s.getEmailId());
        contactnumberfield.setText(s.getContactNumber());
        birthdatespinner.setValue(s.getBirthDateInDateFormat());
        gendercombo.setSelectedItem(s.getGender());
        addressfield.setText(s.getAddress());
        fathernamefield.setText(s.getFatherName());
        fatheroccupationfield.setText(s.getFatherOccupation());
        mothernamefield.setText(s.getMotherName());
        motheroccupationfield.setText(s.getMotherOccupation());
        profilepiclabel.setIcon(new ImageIcon(s.getProfilePic(100, 120)));
        headerlabel.setText("Edit Student Details");
        addstudentbutton.setText("Update Student");
        deptnamecombo.setEnabled(false);
        semoryearcombo.setEnabled(false);
        rollnumberfield.setEditable(false);
        deptnamecombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                super.paint(g);
            }
        });
        semoryearcombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                super.paint(g);
            }
        });
    }

    public AddStudentDialog(JTable table, StudentPanel studentpanel) {
        this();
        this.sp = studentpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Errorlabel.setVisible(false);
        Errorlabel.setText("This is required question  !");
        if (e.getSource() == choosefilebutton) {

            FileDialog fd = new FileDialog(this, "Choose a Profile pic", FileDialog.LOAD);
            fd.setDirectory(".\\Students Profile pic");
            fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif;");
            fd.setLocationRelativeTo(null);
            fd.setVisible(true);
            String strfilename = fd.getFile();
            imagepath = fd.getDirectory() + strfilename;
            if (fd.getFile() != null) {
                file = new File(imagepath);
                long bytes = file.length();
                if (bytes < 1048576) {
                    try {
                        filesize.setText(bytes / 1024 + " KB");
                        filesizenote.setForeground(new Color(46, 139, 27));
                        filesizenote.setText("Image size < 1024 KB");
                        Image image = ImageIO.read(file).getScaledInstance(100, 120, Image.SCALE_SMOOTH);
                        profilepiclabel.setIcon(new ImageIcon(image));
                        filename.setText(file.getName());
                    } catch (IOException ex) {
                        file = null;
                        filename.setText("No file Choosen");
                        filesize.setText("");
                        filesizenote.setForeground(Color.red);
                        filesizenote.setText("Image Not supported");
                        ex.printStackTrace();
                    }
                } else {
                    file = null;
                    filename.setText("No File Choosen");
                    filesize.setText("");
                    filesizenote.setForeground(Color.red);
                    filesizenote.setText("Image size is greater than 1 MB");
                }
            }
        }

        if (e.getSource() == deptnamecombo) {
            deptnamecombo.setFocusable(false);
            rollnumberfield.setText("");
            rollnumberfield.setEditable(true);
            optionalcoursecombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            rollnumberfield.setText("");
            if (deptnamecombo.getSelectedIndex() == 0) {
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));

            } else {
                String dept = (String) deptnamecombo.getSelectedItem();
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(dept)));
            }
        }
        if (e.getSource() == semoryearcombo && semoryearcombo.getSelectedIndex() > 0) {
            String deptcode = new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + "");
            int sem = semoryearcombo.getSelectedIndex();
            long rollnumber = 0;
            if (student != null && deptcode.equals(student.getDeptCode()) && sem == student.getSemorYear()) {
                rollnumber = student.getRollNumber();

            } else {
                rollnumber = new RollNumberData().getRollNumber(deptcode, sem);
            }
            if (rollnumber == 0) {
                rollnumberfield.setText("");
                rollnumberfield.setEditable(true);

            } else {
                rollnumberfield.setText(rollnumber + "");
                rollnumberfield.setEditable(false);

            }
            String[] totalopsub = new CourseData().getOptionalCourse(deptcode, sem);
            if (totalopsub != null) {
                optionalcoursecombo.setModel(new DefaultComboBoxModel<String>(totalopsub));
            } else {
                optionalcoursecombo.setModel(new DefaultComboBoxModel<String>(new String[]{"No Optional Course"}));
            }
        }

        if (e.getSource() == addstudentbutton) {

            if (deptnamecombo.getSelectedIndex() == 0) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(deptnamecombo.getX(), deptnamecombo.getY() + deptnamecombo.getHeight(), 400,
                        26);
            } else if (semoryearcombo.getSelectedIndex() == 0) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(semoryearcombo.getX(), semoryearcombo.getY() + semoryearcombo.getHeight(), 400,
                        26);
            } else if (rollnumberfield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(), 400,
                        26);
            } else if (optionalcoursecombo.getSelectedIndex() == 0
                    && !optionalcoursecombo.getSelectedItem().toString().equals("No Optional Course")) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(optionalcoursecombo.getX(),
                        optionalcoursecombo.getY() + optionalcoursecombo.getHeight(), 400, 26);
            } else if (firstnamefield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(firstnamefield.getX(), firstnamefield.getY() + firstnamefield.getHeight(), 400,
                        26);
            } else if (lastnamefield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(lastnamefield.getX(), lastnamefield.getY() + lastnamefield.getHeight(), 400, 26);
            } else if (emailidfield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(emailidfield.getX(), emailidfield.getY() + emailidfield.getHeight(), 400, 26);
            } else if (contactnumberfield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(contactnumberfield.getX(), contactnumberfield.getY() + contactnumberfield.getHeight(), 400, 26);
            } else if (gendercombo.getSelectedIndex() == 0) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(gendercombo.getX(), gendercombo.getY() + gendercombo.getHeight(), 400, 26);
            } else if (addressfield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(addressfield.getX(), addressfield.getY() + addressfield.getHeight(), 400, 26);
            }  else if (fathernamefield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(fathernamefield.getX(), fathernamefield.getY() + fathernamefield.getHeight(), 400, 26);
            } else if (fatheroccupationfield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(fatheroccupationfield.getX(), fatheroccupationfield.getY() + fatheroccupationfield.getHeight(), 400, 26);
            } else if (mothernamefield.getText().isEmpty()) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(mothernamefield.getX() + 120, mothernamefield.getY() + mothernamefield.getHeight(), 400, 26);
            } else if (motheroccupationfield.getText().isEmpty() || motheroccupationfield.getText().equals(" Mother Occupation")) {
                Errorlabel.setVisible(true);
                Errorlabel.setBounds(motheroccupationfield.getX(), motheroccupationfield.getY() + motheroccupationfield.getHeight(), 400, 26);
            } else {
                try {
                    Student s = new Student();

                    s.setDeptCode(new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + ""));
                    s.setSemorYear(semoryearcombo.getSelectedIndex());
                    s.setRollNumber(Long.parseLong(rollnumberfield.getText()));
                    int rollnumberexist = new RollNumberData().isExist(s.getDeptCode(), s.getSemorYear(), s.getRollNumber());
                    if (rollnumberexist > 0) {
                        if (!(student != null && student.getRollNumber() == s.getRollNumber())) {
                            throw new RollNumberAvailableException();
                        }

                    }
                    s.setOptionalCourse(optionalcoursecombo.getSelectedItem().toString());
                    s.setFirstName(firstnamefield.getText());
                    s.setLastName(lastnamefield.getText());
                    s.setEmailId(emailidfield.getText());
                    s.setContactNumber(contactnumberfield.getText());

                    s.setGender(gendercombo.getSelectedItem() + "");
                    Date date = (Date) birthdatespinner.getValue();
                    s.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
                    s.setAddress(addressfield.getText());
                    s.setFatherName(fathernamefield.getText());
                    s.setMotherName(mothernamefield.getText());
                    s.setFatherOccupation(fatheroccupationfield.getText());
                    s.setMotherOccupation(motheroccupationfield.getText());
                    s.generateAdmissionDate();
                    s.generateUserId();
                    if (student != null) {
                        s.setPassword(student.getPassword());
                        s.setAdmissionDate(student.getAdmissionDate());
                        s.setLastLogin(student.getLastLogin());
                    }
                    if (file != null) {
                        s.setProfilePic(ImageIO.read(file));
                    } else if (student != null) {
                        s.setProfilePic(student.getProfilePic());
                    } else {
                        file = new File("./assets/profilepicicon.jpg");
                        s.setProfilePic(ImageIO.read(file));
                    }
                    int result = 0;
                    if (sp != null) {
                        result = new StudentData().addStudent(s);
                    } else if (am != null && student != null) {
                        result = new StudentData().updateStudentData(student, s);
                    }
                    if (result > 0) {
                        if (sp != null) {
                            if (sp.photoviewscrollpane != null && sp.photoviewscrollpane.isVisible()) {
                                sp.createtablemodel();
                                sp.createphotopanel();
                            } else {
                                sp.createtablemodel();
                            }
                        } else if (am != null && student != null) {
                            am.viewstudentpanel.setVisible(false);
                            am.viewstudentpanel = new ViewStudentPanel(s, am, am.viewstudentpanel.lastpanel);
                            am.viewstudentpanel.setVisible(true);
                            am.viewstudentpanel.setLocation(am.panelx, am.panely);
                            am.getContentPane().add(am.viewstudentpanel);
                        }
                        this.dispose();
                    }

                } catch (NumberFormatException exp) {
                    Errorlabel.setVisible(true);
                    Errorlabel.setText("Characters are not allowed!");
                    Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(),
                            400, 26);
                } catch (RollNumberAvailableException exp) {
                    Errorlabel.setVisible(true);
                    Errorlabel.setText("RollNumber already Exist...!");
                    Errorlabel.setBounds(rollnumberfield.getX(), rollnumberfield.getY() + rollnumberfield.getHeight(),
                            400, 26);
                    exp.printStackTrace();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

@SuppressWarnings("serial")
class RollNumberAvailableException extends Exception {
    public RollNumberAvailableException() {
        super("Roll number already available");
    }
}
