package application.student;

import application.admin.AdminMain;
import application.course.CourseData;
import application.department.DepartmentData;
import application.teacher.TeacherMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Title : AttandanceReportPanel.java
 * Purpose : For displaying student attandance according to class/course/student wice
 */
public class AttandanceReportPanel extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final JComboBox<String> deptnamecombo;
    private final JComboBox<String> semoryearcombo;
    private final JComboBox<String> courseorrollcombo;
    private final JTable table;
    private final JScrollPane scrollPane;
    private int totalstudent = 0;
    private final JLabel Errorlabel;
    private final JButton studentwicebutton;
    private final JButton classwicebutton;
    private final JButton coursewicebutton;
    private final JButton fetchdetailsbutton;
    private final JLabel label3;
    private final JLabel label1;
    private final JLabel label2;
    private final JLabel nodatafoundlabel;
    private JButton backbutton;
    private final JPanel panel;

    /**
     * Create the panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1096, scrollPane.getY() + scrollPane.getHeight() + 40);

    }

    private AttandanceReportPanel() {
        setBorder(null);

        setBackground(new Color(255, 255, 255));
        this.setSize(1116, 544);
        setLayout(null);
        panel = new JPanel();
        panel.setBackground(new Color(48, 11, 103, 105));
        panel.setBounds(10, 0, 1077, 183);
        add(panel);
        panel.setLayout(null);
        JLabel headinglabel = new JLabel("Attandance Report");
        headinglabel.setIcon(null);
        headinglabel.setBounds(10, 65, 272, 44);
        panel.add(headinglabel);
        headinglabel.setBackground(new Color(32, 178, 170, 0));
        headinglabel.setHorizontalAlignment(SwingConstants.LEFT);
        headinglabel.setForeground(Color.WHITE);
        headinglabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headinglabel.setOpaque(true);

        coursewicebutton = new JButton("Course Wice");
        coursewicebutton.setForeground(new Color(61, 0, 169));
        coursewicebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        coursewicebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        coursewicebutton.setBackground(new Color(255, 255, 255));
        coursewicebutton.setBounds(577, 139, 146, 33);

        panel.add(coursewicebutton);

        studentwicebutton = new JButton("Student Wice");
        studentwicebutton.setForeground(new Color(61, 0, 169));
        studentwicebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        studentwicebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        studentwicebutton.setBackground(new Color(255, 255, 255));
        studentwicebutton.setBounds(742, 139, 146, 33);

        panel.add(studentwicebutton);

        classwicebutton = new JButton("Class Wice");
        classwicebutton.setForeground(new Color(61, 0, 169));
        classwicebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        classwicebutton.setBackground(new Color(255, 255, 255));
        classwicebutton.setBounds(907, 139, 146, 33);
        classwicebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(classwicebutton);

        label1 = new JLabel("Dept. Name   :");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setForeground(Color.DARK_GRAY);
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label1.setBounds(29, 213, 163, 37);
        add(label1);

        label2 = new JLabel("Semester or Years   :");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setForeground(Color.DARK_GRAY);
        label2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label2.setBounds(23, 270, 169, 40);
        add(label2);

        label3 = new JLabel("Select Course  :");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        label3.setForeground(Color.DARK_GRAY);
        label3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label3.setBounds(29, 339, 163, 32);
        add(label3);

        deptnamecombo = new JComboBox<String>(new DepartmentData().getDeptName());

        deptnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        deptnamecombo.addActionListener(this);
        deptnamecombo.setFocusable(false);
        deptnamecombo.setBackground(new Color(255, 255, 255));
        deptnamecombo.setBounds(204, 211, 872, 40);
        add(deptnamecombo);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        semoryearcombo.setBackground(Color.WHITE);
        semoryearcombo.setBounds(204, 270, 872, 40);
        semoryearcombo.addActionListener(this);
        semoryearcombo.setFocusable(false);

        add(semoryearcombo);
        courseorrollcombo = new JComboBox<String>();
        courseorrollcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        courseorrollcombo.setFocusable(false);
        courseorrollcombo.addActionListener(this);
        courseorrollcombo.setBackground(Color.WHITE);
        courseorrollcombo.setBounds(204, 335, 872, 40);
        add(courseorrollcombo);

        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(21, 460, 1055, 84);
        for (Component c : scrollPane.getComponents()) {
            c.setBackground(Color.white);
        }
        add(scrollPane);
        scrollPane.setVisible(false);
        table = new JTable();
        table.setBorder(new LineBorder(Color.LIGHT_GRAY));
        table.setForeground(new Color(0, 0, 0));
        table.setRowHeight(40);
        table.getTableHeader().setBackground(new Color(125, 104, 196));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.getTableHeader().setPreferredSize(new Dimension(50, 40));
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.setDragEnabled(false);
        table.setFocusable(false);
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane.setViewportView(table);
        Errorlabel = new JLabel("This is required question  !");
        Errorlabel.setVisible(false);
        Errorlabel.setForeground(Color.RED);
        Errorlabel.setFont(new Font("Arial", Font.PLAIN, 14));
        Errorlabel.setBounds(233, 45, 225, 17);
        add(Errorlabel);
        enableButton(coursewicebutton);
        disableButton(studentwicebutton);
        disableButton(classwicebutton);

        fetchdetailsbutton = new JButton("Fetch Details");
        fetchdetailsbutton.setName("Active");
        fetchdetailsbutton.setForeground(new Color(61, 0, 169));
        fetchdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        fetchdetailsbutton.setFocusPainted(false);
        fetchdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fetchdetailsbutton.addActionListener(this);
        fetchdetailsbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        fetchdetailsbutton.setBackground(new Color(255, 255, 255));
        fetchdetailsbutton.setBounds(926, 399, 151, 37);
        add(fetchdetailsbutton);

        nodatafoundlabel = new JLabel("");
        nodatafoundlabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {

            Image image = ImageIO.read(new File("./assets/notfound2.png"));
            nodatafoundlabel.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        nodatafoundlabel.setText("No Data Found...!");
        nodatafoundlabel.setVerticalTextPosition(JLabel.BOTTOM);
        nodatafoundlabel.setBorder(null);
        nodatafoundlabel.setBackground(new Color(245, 245, 245));
        nodatafoundlabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nodatafoundlabel.setHorizontalTextPosition(JLabel.CENTER);
        nodatafoundlabel.setIconTextGap(20);
        nodatafoundlabel.setVisible(false);
        nodatafoundlabel.setBounds(300, 380, 480, 321);
        add(nodatafoundlabel);

    }

    public void enableButton(JButton button) {
        button.setBorder(new LineBorder(new Color(92, 9, 134)));
        button.setForeground(new Color(61, 0, 169));
        button.setBackground(new Color(255, 255, 255));
        button.setFocusPainted(false);
        button.setName("Active");
    }

    public void disableButton(JButton button) {

        button.setBorder(new LineBorder(new Color(255, 255, 255)));
        button.setForeground(new Color(255, 255, 255));
        button.setBackground(new Color(61, 0, 169));
        button.setFocusPainted(false);
        button.setName("Deactive");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Errorlabel.setVisible(false);
        if (e.getSource() == deptnamecombo) {
            deptnamecombo.setFocusable(false);

            courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            if (deptnamecombo.getSelectedIndex() == 0) {
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));

            } else {
                String dept = (String) deptnamecombo.getSelectedItem();
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(dept)));
            }

        }
        if (e.getSource() == semoryearcombo && semoryearcombo.getSelectedIndex() > 0) {
            String dept = (String) deptnamecombo.getSelectedItem();

            if (coursewicebutton.getName().equals("Active")) {
                String[] totalsub = new CourseData().getCourseDept(new DepartmentData().getDeptcode(dept), semoryearcombo.getSelectedIndex());
                if (totalsub != null) {
                    courseorrollcombo.setModel(new DefaultComboBoxModel<String>(totalsub));
                } else {

                    courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new String[]{"No Course Found"}));
                }
            } else if (studentwicebutton.getName().equals("Active")) {

                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new StudentData().getRollNumber(new DepartmentData().getDeptcode(dept), semoryearcombo.getSelectedIndex())));

            }
        } else if (e.getSource() == semoryearcombo) {
            courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
        }
        if (e.getSource() == fetchdetailsbutton) {
            if (deptnamecombo.getSelectedIndex() == 0) {
                showerror(deptnamecombo);
            } else if (semoryearcombo.getSelectedIndex() == 0) {
                showerror(semoryearcombo);
            } else if (courseorrollcombo.isVisible() && courseorrollcombo.getSelectedItem().equals("No Course Found")) {
                Component tf = courseorrollcombo;
                Errorlabel.setVisible(true);
                Errorlabel.setText("No Course Found !");
                Errorlabel.setBounds(tf.getX(), tf.getY() + tf.getHeight() - 5, 400, 26);
            } else if (courseorrollcombo.isVisible() && courseorrollcombo.getSelectedIndex() == 0) {
                showerror(courseorrollcombo);
            } else {
                this.createtablemodel();
            }
        }
        // TODO Auto-generated method stub

    }

    public void showerror(JComponent tf) {
        Errorlabel.setVisible(true);
        Errorlabel.setText("This is required question !");
        Errorlabel.setBounds(tf.getX(), tf.getY() + tf.getHeight() - 5, 400, 26);
    }

    public AttandanceReportPanel(AdminMain am) {
        this();
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {

                    JTable t = (JTable) e.getSource();
                    int row = t.getSelectedRow();

                    String strsem = table.getValueAt(row, 2) + "";
                    int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
                    String deptcode = strsem.substring(0, strsem.indexOf('-'));
                    String strroll = table.getValueAt(row, 0) + "";
                    long rollnumber = Long.parseLong(strroll);
                    Student s = new StudentData().getStudentDetails(deptcode, sem, rollnumber);

                    am.viewstudentpanel = new ViewStudentPanel(s, am, am.attandancereportpanelscroll);
                    am.viewstudentpanel.setVisible(true);
                    am.attandancereportpanelscroll.setVisible(false);
                    am.viewstudentpanel.setLocation(am.panelx, 0);
                    am.viewstudentpanel.setVisible(true);
                    am.viewstudentpanel.setFocusable(true);
                    am.contentPane.add(am.viewstudentpanel);
                }

            }
        });
        coursewicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                enableButton(coursewicebutton);
                disableButton(studentwicebutton);
                disableButton(classwicebutton);
                courseorrollcombo.setVisible(true);
                label3.setVisible(true);
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), courseorrollcombo.getY() + 65);
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 60);
                label3.setText("Select Course :");
                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
                deptnamecombo.setSelectedIndex(0);
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
                scrollPane.setVisible(false);
            }

        }
        );
        studentwicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                enableButton(studentwicebutton);
                disableButton(coursewicebutton);
                disableButton(classwicebutton);
                label3.setVisible(true);
                courseorrollcombo.setVisible(true);
                label3.setText("Select Roll Number :");
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), courseorrollcombo.getY() + 65);
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 60);
                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
                deptnamecombo.setSelectedIndex(0);
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
                scrollPane.setVisible(false);

            }

        }
        );
        classwicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                enableButton(classwicebutton);
                disableButton(studentwicebutton);
                disableButton(coursewicebutton);
                courseorrollcombo.setVisible(false);
                label3.setVisible(false);
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), courseorrollcombo.getY());
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 60);
                deptnamecombo.setSelectedIndex(0);
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
                scrollPane.setVisible(false);
            }

        }
        );

    }

    public AttandanceReportPanel(TeacherMain fm) {

        this();

        deptnamecombo.setSelectedItem(new DepartmentData().getdeptname(fm.t.getDeptCode()));
        semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(deptnamecombo.getSelectedItem() + "")));
        String[] totalsub = new CourseData().getCourseDept(fm.t.getDeptCode(), fm.t.getSemorYear());
        courseorrollcombo.setModel(new DefaultComboBoxModel<String>(totalsub));
        semoryearcombo.setSelectedIndex(fm.t.getSemorYear());
        courseorrollcombo.setSelectedItem(fm.t.getCourse());
        deptnamecombo.setVisible(false);
        semoryearcombo.setVisible(false);
        label3.setLocation(label1.getLocation());
        label1.setVisible(false);
        label2.setVisible(false);
        courseorrollcombo.setLocation(deptnamecombo.getLocation());
        this.fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), semoryearcombo.getY());
        scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 50);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {

                    JTable t = (JTable) e.getSource();
                    int row = t.getSelectedRow();

                    String strsem = table.getValueAt(row, 2) + "";
                    int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
                    String deptcode = strsem.substring(0, strsem.indexOf('-'));
                    String strroll = table.getValueAt(row, 0) + "";
                    long rollnumber = Long.parseLong(strroll);
                    Student s = new StudentData().getStudentDetails(deptcode, sem, rollnumber);

                    fm.viewstudentpanel = new ViewStudentPanel(s, fm, fm.attandancereportpanelscroll);
                    fm.viewstudentpanel.setVisible(true);
                    fm.attandancereportpanelscroll.setVisible(false);
                    fm.viewstudentpanel.setLocation(fm.panelx, 0);
                    fm.viewstudentpanel.setVisible(true);
                    fm.viewstudentpanel.setFocusable(true);
                    fm.contentPane.add(fm.viewstudentpanel);
                }

            }
        });
        studentwicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                enableButton(studentwicebutton);
                disableButton(coursewicebutton);
                disableButton(classwicebutton);
                label3.setVisible(true);
                courseorrollcombo.setVisible(true);
                label3.setText("Select Roll Number :");
                fetchdetailsbutton.setVisible(true);
                courseorrollcombo.setLocation(deptnamecombo.getLocation());
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), semoryearcombo.getY());
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 50);
                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new StudentData().getRollNumber(fm.t.getDeptCode(), semoryearcombo.getSelectedIndex())));
                scrollPane.setVisible(false);

            }

        }
        );
        coursewicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                enableButton(coursewicebutton);
                disableButton(studentwicebutton);
                disableButton(classwicebutton);
                label3.setVisible(true);
                courseorrollcombo.setVisible(true);
                label3.setText("Select Course :");
                fetchdetailsbutton.setVisible(true);
                courseorrollcombo.setLocation(deptnamecombo.getLocation());
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), semoryearcombo.getY());
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 50);
                String[] totalsub = new CourseData().getCourseDept(fm.t.getDeptCode(), fm.t.getSemorYear());
                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(totalsub));
                scrollPane.setVisible(false);

            }

        }
        );
        classwicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                coursewicebutton.setName("Active");
                enableButton(classwicebutton);
                disableButton(studentwicebutton);
                disableButton(coursewicebutton);
                courseorrollcombo.setVisible(false);
                label3.setVisible(false);
                scrollPane.setLocation(scrollPane.getX(), deptnamecombo.getY());
                fetchdetailsbutton.setVisible(false);
                createtablemodel();
            }

        }
        );

    }

    public AttandanceReportPanel(StudentMain sm, JComponent lastpanel) {
        this(sm);
        backbutton = new JButton("Back");
        backbutton.setContentAreaFilled(false);
        backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
        backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
        backbutton.setFocusable(false);
        backbutton.setForeground(Color.WHITE);
        backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backbutton.setBackground(new Color(32, 178, 170));
        backbutton.setBounds(10, 141, 88, 36);
        panel.add(backbutton);

        backbutton.addActionListener(e
                -> {
            sm.attandancereportpanelscroll.setVisible(false);
            lastpanel.setVisible(true);
        });
    }

    public AttandanceReportPanel(StudentMain sm) {
        this();
        classwicebutton.setVisible(false);
        studentwicebutton.setVisible(false);
        coursewicebutton.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        deptnamecombo.setVisible(false);
        courseorrollcombo.setVisible(false);
        semoryearcombo.setVisible(false);
        this.fetchdetailsbutton.setVisible(false);
        enableButton(studentwicebutton);
        disableButton(coursewicebutton);
        disableButton(classwicebutton);

        deptnamecombo.setSelectedItem(new DepartmentData().getdeptname(sm.s.getDeptCode()));
        semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(deptnamecombo.getSelectedItem() + "")));
        courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new StudentData().getRollNumber(sm.s.getDeptCode(), sm.s.getSemorYear())));
        semoryearcombo.setSelectedIndex(sm.s.getSemorYear());
        courseorrollcombo.setSelectedItem(sm.s.getRollNumber() + "");
        scrollPane.setLocation(scrollPane.getX(), deptnamecombo.getY());
        scrollPane.setVisible(true);

        this.createtablemodel();
        table.setEnabled(false);
        table.setRowSelectionInterval(totalstudent - 1, totalstudent - 1);
        table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(4).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new CellRenderer());

    }

    public void createtablemodel() {
        nodatafoundlabel.setVisible(false);
        if (deptnamecombo.getSelectedIndex() == 0 || (semoryearcombo.isVisible() && semoryearcombo.getSelectedIndex() == 0) || (courseorrollcombo.isVisible() && courseorrollcombo.getSelectedIndex() == 0)) {
            scrollPane.setVisible(false);
        } else {
            Attandance a = new Attandance();
            a.setDeptCode(new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + ""));
            a.setSemorYear(semoryearcombo.getSelectedIndex());
            if (coursewicebutton.getName().equals("Active")) {
                a.setCourseName(courseorrollcombo.getSelectedItem() + "");
                a.setCourseCode(new CourseData().getCourseCode(a.getDeptCode(), a.getSemorYear(), a.getCourseName()));
            } else if (classwicebutton.getName().equals("Active")) {
                a.setCourseName("All");
            } else if (studentwicebutton.getName().equals("Active")) {
                a.setRollNumber(Long.parseLong(courseorrollcombo.getSelectedItem() + ""));

            }
            table.setModel(createModel(a));
            scrollPane.setSize(scrollPane.getWidth(), 40 + (totalstudent * 40));
            this.setSize(1116, scrollPane.getY() + 80 + totalstudent * 40 + 60);

            table.getColumnModel().getColumn(0).setMaxWidth(200);
            table.getColumnModel().getColumn(1).setMaxWidth(250);
            table.getColumnModel().getColumn(2).setMaxWidth(200);
            table.getColumnModel().getColumn(3).setMaxWidth(250);
            table.getColumnModel().getColumn(4).setMaxWidth(230);
            table.getColumnModel().getColumn(5).setMaxWidth(200);
            DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
            cellrenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
            table.getColumnModel().getColumn(2).setCellRenderer(cellrenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(cellrenderer);
            table.getColumnModel().getColumn(4).setCellRenderer(cellrenderer);
            table.getColumnModel().getColumn(5).setCellRenderer(cellrenderer);
            table.setSelectionBackground(new Color(240, 255, 255));
            table.setSelectionForeground(Color.black);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            scrollPane.setVisible(true);
            if (totalstudent == 0) {
                noDataFound();
            }
        }

    }

    public DefaultTableModel createModel(Attandance a) {
        String[] Column = {"Roll Number", "Student Name", "Class", "Course", "Total Attandance", "Percentage"};

        @SuppressWarnings("serial")
        DefaultTableModel model = new DefaultTableModel(Column, 0) {
            final boolean[] isEdit = {false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int row, int column) {
                return isEdit[column];
            }
        };

        ArrayList<Attandance> list = null;
        if (studentwicebutton.getName().equals("Active")) {
            list = new StudentData().getAttandanceReportByStudent(a);
        } else if (coursewicebutton.getName().equals("Active")) {
            list = new StudentData().getAttandanceReportByCourse(a);
        } else if (classwicebutton.getName().equals("Active")) {
            list = new StudentData().getAttandanceReportByClass(a);
        }
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[0]);
            model.setValueAt(list.get(i).getRollNumber(), i, 0);
            model.setValueAt(list.get(i).getStudentName(), i, 1);
            model.setValueAt(a.getDeptCode() + "-" + a.getSemorYear(), i, 2);
            model.setValueAt(list.get(i).getCourseName(), i, 3);
            model.setValueAt(list.get(i).getAttandance() + " out of " + list.get(i).getTotalAttandance(), i, 4);

            try {
                model.setValueAt((list.get(i).getAttandance() * 100) / list.get(i).getTotalAttandance() + " %", i, 5);
            } catch (ArithmeticException exp) {
                model.setValueAt(list.get(i).getTotalAttandance() + " %", i, 5);
            }
        }
        totalstudent = list.size();

        {
            table.setEnabled(true);
            if (!classwicebutton.isVisible() && !studentwicebutton.isVisible() && !coursewicebutton.isVisible()) {
                try {

                    list = new StudentData().getTotalAttandanceReportOfStudent(a);
                    Object[] obj = {"", "Total", "", list.get(0).getCourseName(), list.get(0).getAttandance() + " out of " + list.get(0).getTotalAttandance(), (list.get(0).getAttandance() * 100) / list.get(0).getTotalAttandance() + " %"};
                    model.addRow(obj);
                } catch (ArithmeticException exp) {
                    Object[] obj = {"", "Total", "", list.get(0).getCourseName(), list.get(0).getAttandance() + " out of " + list.get(0).getTotalAttandance(), list.get(0).getTotalAttandance() + " %"};
                    model.addRow(obj);
                }

                totalstudent += 1;
            }
        }
        return model;

    }

    public void noDataFound() {
        scrollPane.setVisible(false);
        nodatafoundlabel.setVisible(true);
        nodatafoundlabel.setLocation(nodatafoundlabel.getX(), scrollPane.getY() - 100);

    }

    @SuppressWarnings("serial")
    private class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            this.setHorizontalAlignment(JLabel.CENTER);
            if (row == (totalstudent - 1)) {

                this.setFont(this.getFont().deriveFont(Font.BOLD));
                if (row == 0) {
                    this.setHorizontalAlignment(JLabel.CENTER);
                }

            }
            return this;
        }
    }

}
