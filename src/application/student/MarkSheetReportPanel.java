package application.student;

import application.admin.AdminMain;
import application.course.CourseData;
import application.department.Department;
import application.department.DepartmentData;
import application.teacher.TeacherMain;

import javax.imageio.ImageIO;
import javax.swing.*;
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
 * Title : MarkSheetReportPanel.java
 * Purpose : To display all students marks in class/course/student wice
 */
@SuppressWarnings("serial")
public class MarkSheetReportPanel extends JPanel implements ActionListener {
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
    private final JButton declareresultbutton;
    private final JButton submitbutton;
    private final JLabel nodatafoundlabel;

    /**
     * Create the panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1096, this.getHeight());
    }

    private MarkSheetReportPanel() {
        setBorder(null);
        setBackground(new Color(255, 255, 255));
        this.setSize(1116, 544);
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(48, 11, 103, 105));
        panel.setBounds(10, 0, 1077, 183);
        add(panel);
        panel.setLayout(null);
        JLabel headingLabel = new JLabel("Marksheet Report");
        headingLabel.setIcon(null);
        headingLabel.setBounds(10, 65, 272, 44);
        panel.add(headingLabel);
        headingLabel.setBackground(new Color(32, 178, 170, 0));
        headingLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headingLabel.setOpaque(true);

        declareresultbutton = new JButton("Declare Result");
        declareresultbutton.setForeground(new Color(61, 0, 169));
        declareresultbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        declareresultbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        declareresultbutton.setBackground(new Color(255, 255, 255));
        declareresultbutton.setBounds(30, 139, 146, 33);
        declareresultbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        declareresultbutton.setFocusable(false);
        this.disableButton(declareresultbutton);
        declareresultbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                activeButton(declareresultbutton);
                disableButton(classwicebutton);
                disableButton(coursewicebutton);
                disableButton(studentwicebutton);
                semoryearcombo.setVisible(false);
                courseorrollcombo.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                scrollPane.setVisible(false);
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), semoryearcombo.getY());
                scrollPane.setLocation(scrollPane.getX(), courseorrollcombo.getY());
                deptnamecombo.setSelectedIndex(0);
            }
        });
        panel.add(declareresultbutton);
        coursewicebutton = new JButton("Course Wice");
        coursewicebutton.setForeground(new Color(61, 0, 169));
        coursewicebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        coursewicebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        coursewicebutton.setBackground(new Color(255, 255, 255));
        coursewicebutton.setBounds(577, 139, 146, 33);
        coursewicebutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        panel.add(coursewicebutton);

        studentwicebutton = new JButton("Student Wise");
        studentwicebutton.setForeground(new Color(61, 0, 169));
        studentwicebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        studentwicebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        studentwicebutton.setBackground(new Color(255, 255, 255));
        studentwicebutton.setBounds(742, 139, 146, 33);
        studentwicebutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        panel.add(studentwicebutton);

        classwicebutton = new JButton("Class Wise");
        classwicebutton.setForeground(new Color(61, 0, 169));
        classwicebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        classwicebutton.setBackground(new Color(255, 255, 255));
        classwicebutton.setBounds(907, 139, 146, 33);
        classwicebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        classwicebutton.setBorder(new LineBorder(new Color(92, 9, 134)));
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
        scrollPane.getVerticalScrollBar().setUnitIncrement(80);
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
        table.getTableHeader().setForeground(Color.white);
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
        activeButton(coursewicebutton);
        disableButton(studentwicebutton);
        disableButton(classwicebutton);
        disableButton(declareresultbutton);

        fetchdetailsbutton = new JButton("Fetch Details");
        fetchdetailsbutton.setForeground(new Color(61, 0, 169));
        fetchdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        fetchdetailsbutton.setFocusPainted(false);
        fetchdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fetchdetailsbutton.addActionListener(this);
        fetchdetailsbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        fetchdetailsbutton.setBackground(new Color(255, 255, 255));
        fetchdetailsbutton.setBounds(926, 399, 151, 37);
        add(fetchdetailsbutton);

        submitbutton = new JButton("Declare Result");
        submitbutton.setForeground(new Color(61, 0, 169));
        submitbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submitbutton.setVisible(false);
        submitbutton.setFocusPainted(false);
        submitbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitbutton.addActionListener(new DeclareResult());
        submitbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        submitbutton.setBackground(new Color(255, 255, 255));
        submitbutton.setBounds(926, 399, 151, 37);
        add(submitbutton);

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

    public MarkSheetReportPanel(AdminMain am) {
        this();
        table.addMouseListener(new MouseAdapterForTable(am));
        coursewicebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                activeButton(coursewicebutton);
                disableButton(studentwicebutton);
                disableButton(classwicebutton);
                disableButton(declareresultbutton);

                label2.setVisible(true);
                deptnamecombo.setVisible(true);
                semoryearcombo.setVisible(true);
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
                activeButton(studentwicebutton);
                disableButton(coursewicebutton);
                disableButton(classwicebutton);
                disableButton(declareresultbutton);
                label2.setVisible(true);
                deptnamecombo.setVisible(true);
                semoryearcombo.setVisible(true);
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
                coursewicebutton.setName("Active");
                deptnamecombo.setVisible(true);
                semoryearcombo.setVisible(true);
                activeButton(classwicebutton);
                disableButton(studentwicebutton);
                disableButton(coursewicebutton);
                disableButton(declareresultbutton);
                label2.setVisible(true);
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

    public MarkSheetReportPanel(TeacherMain tm) {
        this();
        declareresultbutton.setVisible(false);
        deptnamecombo.setSelectedItem(new DepartmentData().getdeptname(tm.t.getDeptCode()));
        semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(deptnamecombo.getSelectedItem() + "")));
        String[] totalsub = new CourseData().getCourseDept(tm.t.getDeptCode(), tm.t.getSemorYear());
        courseorrollcombo.setModel(new DefaultComboBoxModel<String>(totalsub));
        semoryearcombo.setSelectedIndex(tm.t.getSemorYear());
        courseorrollcombo.setSelectedItem(tm.t.getCourse());
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

                    tm.viewstudentpanel = new ViewStudentPanel(s, tm, tm.marksheetreportpanelscroll);
                    tm.viewstudentpanel.setVisible(true);
                    tm.marksheetreportpanelscroll.setVisible(false);
                    tm.viewstudentpanel.setLocation(tm.panelx, 0);
                    tm.viewstudentpanel.setVisible(true);
                    tm.viewstudentpanel.setFocusable(true);
                    tm.contentPane.add(tm.viewstudentpanel);
                }
            }
        });
        studentwicebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                activeButton(studentwicebutton);
                disableButton(coursewicebutton);
                disableButton(classwicebutton);
                disableButton(declareresultbutton);
                label3.setVisible(true);
                courseorrollcombo.setVisible(true);
                label3.setText("Select Roll Number :");
                fetchdetailsbutton.setVisible(true);
                courseorrollcombo.setLocation(deptnamecombo.getLocation());
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), semoryearcombo.getY());
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 50);
                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(new StudentData().getRollNumber(tm.t.getDeptCode(), semoryearcombo.getSelectedIndex())));
                scrollPane.setVisible(false);
            }
        }
        );
        coursewicebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                activeButton(coursewicebutton);
                disableButton(studentwicebutton);
                disableButton(classwicebutton);
                disableButton(declareresultbutton);
                label3.setVisible(true);
                courseorrollcombo.setVisible(true);
                label3.setText("Select Course :");
                fetchdetailsbutton.setVisible(true);
                courseorrollcombo.setLocation(deptnamecombo.getLocation());
                fetchdetailsbutton.setLocation(fetchdetailsbutton.getX(), semoryearcombo.getY());
                scrollPane.setLocation(scrollPane.getX(), fetchdetailsbutton.getY() + 50);
                String[] totalsub = new CourseData().getCourseDept(tm.t.getDeptCode(), tm.t.getSemorYear());
                courseorrollcombo.setModel(new DefaultComboBoxModel<String>(totalsub));
                scrollPane.setVisible(false);
            }
        });
        classwicebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                coursewicebutton.setName("Active");
                activeButton(classwicebutton);
                disableButton(studentwicebutton);
                disableButton(coursewicebutton);
                disableButton(declareresultbutton);
                courseorrollcombo.setVisible(false);
                label3.setVisible(false);
                scrollPane.setLocation(scrollPane.getX(), deptnamecombo.getY());
                fetchdetailsbutton.setVisible(false);
                createtablemodel();
            }
        }
        );
    }

    public void activeButton(JButton button) {
        if (submitbutton != null) {
            submitbutton.setVisible(false);
        }
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
            if (declareresultbutton.getName().equals("Active")) {
                if (deptnamecombo.getSelectedIndex() == 0) {
                    showerror(deptnamecombo);
                } else {
                    createTableForDeclareResult(deptnamecombo.getSelectedItem() + "");
                }
            } else {
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
        }
    }

    public void showerror(JComponent tf) {
        Errorlabel.setVisible(true);
        Errorlabel.setText("This is required question !");
        Errorlabel.setBounds(tf.getX(), tf.getY() + tf.getHeight() - 5, 400, 26);
    }

    public void createTableForDeclareResult(String dept) {
        submitbutton.setVisible(true);
        String[] columnname = {"Deparmet", "Sem", "Department Name", ""};
        DefaultTableModel model = new DefaultTableModel(columnname, 0) {
            final boolean[] isEdit = {false, false, false, true};
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEdit[column];
            }
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        ArrayList<Department> list = new DepartmentData().getDeptsForDeclareResult(dept);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[0]);
            Department c = list.get(i);
            model.setValueAt(c.getDeptCode(), i, 0);
            model.setValueAt(c.getSemorYear(), i, 1);
            model.setValueAt(c.getDeptName(), i, 2);
            model.setValueAt(c.getIsDeclared(), i, 3);
        }
        table.setModel(model);
        totalstudent = list.size();
        scrollPane.setSize(scrollPane.getWidth(), 40 + (totalstudent * 40));
        submitbutton.setLocation(submitbutton.getX(), scrollPane.getY() + scrollPane.getHeight() + 20);
        this.setSize(1116, scrollPane.getY() + 80 + totalstudent * 40 + 40);

        table.getColumnModel().getColumn(3).setHeaderRenderer(
                new HeaderRendererForCheckBox(table.getTableHeader(), 3));

        DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
        cellrenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellrenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellrenderer);
        table.setSelectionBackground(new Color(240, 255, 255));
        table.setSelectionForeground(Color.black);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        this.setIcons(table, 3, new ImageIcon("./assets/unselectedcheckboxicon.png"), new ImageIcon("./assets/selectedcheckboxicon.png"));
        scrollPane.setVisible(true);
        if (list.size() == 0) {
            noDataFound();
        }
    }

    public void setIcons(JTable table, int column, Icon icon, Icon selectedIcon) {
        JCheckBox cellRenderer = (JCheckBox) table.getCellRenderer(0, column);
        cellRenderer.setSelectedIcon(selectedIcon);
        cellRenderer.setIcon(icon);

        DefaultCellEditor cellEditor = (DefaultCellEditor) table.getCellEditor(0, column);
        JCheckBox editorComponent = (JCheckBox) cellEditor.getComponent();
        editorComponent.setSelectedIcon(selectedIcon);
        editorComponent.setIcon(icon);
    }

    public void createtablemodel() {
        nodatafoundlabel.setVisible(false);
        Marks m = new Marks();
        m.setDeptCode(new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + ""));
        m.setSemorYear(semoryearcombo.getSelectedIndex());
        if (coursewicebutton.getName().equals("Active")) {
            m.setCourseName(courseorrollcombo.getSelectedItem() + "");
            m.setCourseCode(new CourseData().getCourseCode(m.getDeptCode(), m.getSemorYear(), m.getCourseName()));
        } else if (classwicebutton.getName().equals("Active")) {
            m.setCourseName("All");
        } else if (studentwicebutton.getName().equals("Active")) {
            m.setRollNumber(Long.parseLong(courseorrollcombo.getSelectedItem() + ""));
        }
        table.setModel(createModel(m));
        scrollPane.setSize(scrollPane.getWidth(), 40 + (totalstudent * 40));
        this.setSize(1116, scrollPane.getY() + scrollPane.getHeight() + 40);

        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(250);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(250);
        table.getColumnModel().getColumn(4).setMaxWidth(230);

        DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
        cellrenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellrenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellrenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellrenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellrenderer);

        table.setSelectionBackground(new Color(240, 255, 255));
        table.setSelectionForeground(Color.black);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        scrollPane.setVisible(true);
        if (totalstudent == 0) {
            noDataFound();
        }
    }

    public DefaultTableModel createModel(Marks m) {
        String[] Column = {"Roll Number", "Student Name", "Course Code", "Obtained Marks", "Latter Grade"};
        DefaultTableModel model = new DefaultTableModel(Column, 0) {
            final boolean[] isEdit = {false, false, false, false, false, false};
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEdit[column];
            }
        };

        ArrayList<Marks> list = null;
        if (studentwicebutton.getName().equals("Active")) {
            list = new StudentData().getMarkssheetOfStudent(m.getDeptCode(), m.getSemorYear(), m.getRollNumber());
        } else if (coursewicebutton.getName().equals("Active")) {
            list = new StudentData().getMarksheetReportByCourse(m);
        } else if (classwicebutton.getName().equals("Active")) {
            list = new StudentData().getMarksheetReportByClass(m);
        }
//        list = new CourseData().getCourseCode();
        for (int i = 0; i < list.size(); i++) {
            String lGrade = numtolattergrade(list.get(i).getTheoryMarks());
            model.addRow(new Object[0]);
            model.setValueAt(list.get(i).getRollNumber(), i, 0);
            model.setValueAt(list.get(i).getStudentName(), i, 1);
            model.setValueAt(m.getDeptCode() + "-" + m.getSemorYear(), i, 2);
            model.setValueAt(list.get(i).getTheoryMarks(), i, 3);
            model.setValueAt(lGrade, i, 4);
        }
        totalstudent = list.size();
        table.setEnabled(true);
        return model;

    }
    public String numtolattergrade(int num){
        String lattergrade= " ";
        if(num >= 80)
            lattergrade = "A+";
        else if(num >= 75)
            lattergrade = "A";
        else if (num >= 70)
            lattergrade = "A-";
        else if (num >= 65)
            lattergrade = "B+";
        else if (num >= 60)
            lattergrade = "B";
        else if (num >= 55)
            lattergrade = "B-";
        else if (num >= 50)
            lattergrade = "C+";
        else if (num >= 45)
            lattergrade = "C";
        else if (num >= 40)
            lattergrade = "D";
        else if (num < 40)
            lattergrade = "F";
        return lattergrade;
    }

    public void noDataFound() {
        scrollPane.setVisible(false);
        nodatafoundlabel.setVisible(true);
        nodatafoundlabel.setLocation(nodatafoundlabel.getX(), scrollPane.getY() - 100);

    }

    class MouseAdapterForTable extends MouseAdapter {
        AdminMain am = null;
        public MouseAdapterForTable(AdminMain am) {
            this.am = am;
        }
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1 && !declareresultbutton.getName().equals("Active")) {

                JTable t = (JTable) e.getSource();
                int row = t.getSelectedRow();

                String strsem = table.getValueAt(row, 2) + "";
                int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
                String deptcode = strsem.substring(0, strsem.indexOf('-'));
                String strroll = table.getValueAt(row, 0) + "";
                long rollnumber = Long.parseLong(strroll);
                Student s = new StudentData().getStudentDetails(deptcode, sem, rollnumber);

                am.viewstudentpanel = new ViewStudentPanel(s, am, am.marksheetreportpanelscroll);
                am.viewstudentpanel.setVisible(true);
                am.marksheetreportpanelscroll.setVisible(false);
                am.viewstudentpanel.setLocation(am.panelx, 0);
                am.viewstudentpanel.setVisible(true);
                am.viewstudentpanel.setFocusable(true);
                am.contentPane.add(am.viewstudentpanel);
            }
        }

    }

    private class DeclareResult implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < table.getRowCount(); i++) {
                Department department = new Department();
                department.setDeptCode(table.getValueAt(i, 0) + "");
                department.setSemorYear(Integer.parseInt(table.getValueAt(i, 1) + ""));
                department.setIsDeclared(Boolean.parseBoolean(table.getValueAt(i, 3) + ""));
                new DepartmentData().declareResult(department);
            }
            JOptionPane.showMessageDialog(null, "Result Declared");
            scrollPane.setVisible(false);
            submitbutton.setVisible(false);
            setSize(getWidth(), 600);
        }

    }
}
