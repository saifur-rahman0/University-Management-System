package application.course;

import application.admin.AdminMain;
import application.department.DepartmentData;
import application.student.StudentMain;
import application.teacher.TeacherMain;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/*
 * Title : CoursePanel.java
 * Purpose : Displaying all the course in given dept and sem
 */
@SuppressWarnings("serial")
public class CoursePanel extends JPanel implements ActionListener {
    /**
     * Create the panel.
     */
    private final JComboBox<String> deptnamecombo;
    private final JComboBox<String> semoryearcombo;
    private final JButton addcourse;
    private final String[] Deptcode;
    private final JTable table;
    private final JScrollPane scrollPane;
    private final JLabel selectdeptlabel;
    private final JLabel selectsemlabel;
    private final JLabel headerlabel;
    private JButton backbutton;

    public CoursePanel(AdminMain am) {
        this();
    }

    public CoursePanel(StudentMain sm) {
        this();
        deptnamecombo.setVisible(false);
        semoryearcombo.setVisible(false);
        addcourse.setVisible(false);
        selectsemlabel.setVisible(false);
        selectdeptlabel.setVisible(false);

        scrollPane.setVisible(true);
        headerlabel.setBounds(10, 0, 1096, 183);
        headerlabel.setText("Courses ");
        scrollPane.setBounds(10, headerlabel.getY() + headerlabel.getHeight() + 20, 1096, this.getHeight() - headerlabel.getHeight() - 20);
        headerlabel.setHorizontalAlignment(JLabel.LEFT);
        this.createtablemodel(sm.s.getDeptCode(), sm.s.getSemorYear());

    }

    public CoursePanel(TeacherMain fm) {
        this();
        deptnamecombo.setVisible(false);
        semoryearcombo.setVisible(false);
        addcourse.setVisible(false);
        selectsemlabel.setVisible(false);
        selectdeptlabel.setVisible(false);

        scrollPane.setVisible(true);
        headerlabel.setBounds(10, 0, 1096, 183);
        headerlabel.setText(" Courses");
        scrollPane.setBounds(10, headerlabel.getY() + headerlabel.getHeight() + 20, 1096, this.getHeight() - headerlabel.getHeight() - 20);
        headerlabel.setHorizontalAlignment(JLabel.LEFT);
        this.createtablemodel(fm.t.getDeptCode(), fm.t.getSemorYear());

    }

    public CoursePanel(TeacherMain fm, JComponent lastpanel) {
        this(fm);
        headerlabel.setLayout(null);
        backbutton = new JButton("Back");
        backbutton.setContentAreaFilled(false);
        backbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
        backbutton.setFocusable(false);
        backbutton.setForeground(new Color(61, 0, 169));
        backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backbutton.setBackground(new Color(255, 255, 255));
        backbutton.setBounds(10, 141, 88, 36);
        headerlabel.add(backbutton);

        backbutton.addActionListener(e
                -> {
            this.setVisible(false);
            lastpanel.setVisible(true);
        });
    }

    public CoursePanel(StudentMain sm, JComponent lastpanel) {
        this(sm);
        headerlabel.setLayout(null);
        backbutton = new JButton("Back");
        backbutton.setContentAreaFilled(false);
        backbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
        backbutton.setFocusable(false);
        backbutton.setForeground(new Color(61, 0, 169));
        backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backbutton.setBackground(new Color(255, 255, 255));
        backbutton.setBounds(10, 141, 88, 36);
        headerlabel.add(backbutton);

        backbutton.addActionListener(e
                -> {
            this.setVisible(false);
            lastpanel.setVisible(true);
        });
    }

    private CoursePanel() {
        new Timer(100, this);
//		timer.start();
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        this.setSize(1116, 705);
        setLayout(null);
        headerlabel = new JLabel("Course Management");
        headerlabel.setBackground(new Color(48, 11, 103, 105));
        headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerlabel.setForeground(new Color(255, 255, 255));
        headerlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headerlabel.setBounds(10, 0, 1096, 66);
        headerlabel.setOpaque(true);
        add(headerlabel);

        selectdeptlabel = new JLabel("Select Department  ");
        selectdeptlabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectdeptlabel.setForeground(Color.DARK_GRAY);
        selectdeptlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        selectdeptlabel.setBounds(30, 89, 248, 42);
        add(selectdeptlabel);

        deptnamecombo = new JComboBox<String>(new DepartmentData().getDeptName());
        deptnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        deptnamecombo.setBackground(Color.WHITE);
        deptnamecombo.setFocusable(false);
        deptnamecombo.addActionListener(this);
        deptnamecombo.setBounds(300, 88, 806, 42);
        add(deptnamecombo);

        selectsemlabel = new JLabel("Select Semester/Year  ");
        selectsemlabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectsemlabel.setBackground(Color.DARK_GRAY);
        selectsemlabel.setForeground(Color.DARK_GRAY);
        selectsemlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        selectsemlabel.setBounds(30, 165, 248, 40);
        add(selectsemlabel);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setMaximumRowCount(16);
        semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        semoryearcombo.setBackground(Color.WHITE);
        semoryearcombo.setFocusable(false);
        semoryearcombo.addActionListener(this);
        semoryearcombo.setBounds(300, 165, 806, 42);
        add(semoryearcombo);

        Deptcode = new DepartmentData().getDeptcode();

        addcourse = new JButton("Add New Course");
        addcourse.setBorder(new LineBorder(new Color(92, 9, 134)));
        addcourse.addActionListener(this);
        addcourse.setForeground(new Color(255, 255, 255));
        addcourse.setBackground(new Color(90, 14, 201));
        addcourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addcourse.setBounds(937, 242, 169, 37);
        addcourse.setVisible(false);
        addcourse.setFocusable(false);
        addcourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(addcourse);

        scrollPane = new JScrollPane();
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setBounds(10, 311, 1096, 361);
        for (Component c : scrollPane.getComponents()) {
            c.setBackground(Color.white);
        }
        add(scrollPane);
        table = new JTable();
        table.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        table.setBackground(Color.white);
        table.setRowHeight(40);

        table.getTableHeader().setBackground(new Color(125, 104, 196));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.getTableHeader().setPreferredSize(new Dimension(50, 40));
        table.setDragEnabled(false);

        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(false);
        scrollPane.setViewportView(table);
        scrollPane.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (deptnamecombo.getSelectedIndex() == 0 || semoryearcombo.getSelectedIndex() == 0) {
            scrollPane.setVisible(false);
        }
        if (e.getSource() == deptnamecombo) {
            scrollPane.setVisible(false);
            addcourse.setVisible(false);
            if (deptnamecombo.getSelectedIndex() == 0) {
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            } else {
                String dept = (String) deptnamecombo.getSelectedItem();
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(dept)));
            }
        }
        if (e.getSource() == semoryearcombo) {
            if (deptnamecombo.getSelectedIndex() > 0 && semoryearcombo.getSelectedIndex() > 0) {
                scrollPane.setVisible(true);
                int sem = semoryearcombo.getSelectedIndex();
                int index = deptnamecombo.getSelectedIndex();
                this.createtablemodel(Deptcode[index - 1], sem);

            } else if (semoryearcombo.getSelectedIndex() == 0) {
                scrollPane.setVisible(false);
                addcourse.setVisible(false);
            } else {
                scrollPane.setVisible(false);
            }
        }
        if (deptnamecombo.getSelectedIndex() > 0 && semoryearcombo.getSelectedIndex() > 0) {
            addcourse.setVisible(true);
        }

        if (e.getSource() == addcourse) {
            int sem = semoryearcombo.getSelectedIndex();
            int index = deptnamecombo.getSelectedIndex();
            AddCourseDialog sd = new AddCourseDialog(Deptcode[index - 1], sem, table);
            sd.setLocationRelativeTo(null);
            sd.setVisible(true);
            scrollPane.setVisible(true);
        }
    }

    public void createtablemodel(String deptcode, int sem) {
        ResultSet st = new CourseData().getCourseinfo(deptcode, sem);
        table.setModel(DbUtils.resultSetToTableModel(st));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(400);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(200);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
    }
}
