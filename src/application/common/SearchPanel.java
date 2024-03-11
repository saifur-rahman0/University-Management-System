package application.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import application.admin.AdminMain;
import application.department.DepartmentData;
import application.teacher.Teacher;
import application.teacher.TeacherData;
import application.teacher.TeacherMain;
import application.teacher.ViewTeacherPanel;
import application.student.Student;
import application.student.StudentData;
import application.student.StudentMain;
import application.student.ViewStudentPanel;
import net.proteanit.sql.DbUtils;


/*
 * Title : SearchPanel.java
 * Purpose : For searching student of teacher
 */
@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener {

    private JTable table;
    private JScrollPane tableviewscroll;
    private JTextField searchfield;
    private JComboBox<String> deptnamecombo;
    private JComboBox<String> semoryearcombo;
    private JComboBox<String> studentandteachercombo;

    private JButton searchbutton;

    /**
     * Create the panel.
     */
    public SearchPanel(AdminMain am) {
        this();
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {
                    if (studentandteachercombo.getSelectedIndex() == 0) {
                        JTable t = (JTable) e.getSource();
                        int row = t.getSelectedRow();
                        String deptcode = table.getValueAt(row, 0) + "";
                        String strsem = table.getValueAt(row, 4) + "";
                        int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
                        String strroll = table.getValueAt(row, 1) + "";
                        long rollnumber = Long.parseLong(strroll);
                        Student s = new StudentData().getStudentDetails(deptcode, sem, rollnumber);

                        am.viewstudentpanel = new ViewStudentPanel(s, am, am.searchpanel);
                        am.viewstudentpanel.setVisible(true);
                        am.searchpanel.setVisible(false);
                        am.viewstudentpanel.setLocation(am.panelx, 0);
                        am.viewstudentpanel.setVisible(true);
                        am.viewstudentpanel.setFocusable(true);
                        am.contentPane.add(am.viewstudentpanel);
                    } else {
                        JTable t = (JTable) e.getSource();
                        int fid = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0) + "");
                        Teacher f = new TeacherData().getTeacherInfobyId(fid);

                        am.viewteacherpanel = new ViewTeacherPanel(f, am, am.searchpanel);
                        am.viewteacherpanel.setVisible(true);
                        am.searchpanel.setVisible(false);
                        am.viewteacherpanel.setLocation(am.panelx, am.panely);
                        am.viewteacherpanel.setVisible(true);
                        am.viewteacherpanel.setFocusable(true);
                        am.contentPane.add(am.viewteacherpanel);
                    }
                }
            }
        });

    }

    public SearchPanel(TeacherMain tm) {
        this();
        deptnamecombo.setSelectedItem(new DepartmentData().getdeptname(tm.t.getDeptCode()));
        deptnamecombo.setEnabled(false);
        deptnamecombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                super.paint(g);
            }
        });
        semoryearcombo.setSelectedIndex(tm.t.getSemorYear());
        semoryearcombo.setEnabled(false);
        semoryearcombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                super.paint(g);
            }
        });
        this.createtablemodel();
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {
                    if (studentandteachercombo.getSelectedIndex() == 0) {
                        JTable t = (JTable) e.getSource();
                        int row = t.getSelectedRow();
                        String deptcode = table.getValueAt(row, 0) + "";
                        String strsem = table.getValueAt(row, 4) + "";
                        int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
                        String strroll = table.getValueAt(row, 1) + "";
                        long rollnumber = Long.parseLong(strroll);
                        Student s = new StudentData().getStudentDetails(deptcode, sem, rollnumber);

                        tm.viewstudentpanel = new ViewStudentPanel(s, tm, tm.searchpanel);
                        tm.viewstudentpanel.setVisible(true);
                        tm.searchpanel.setVisible(false);
                        tm.viewstudentpanel.setLocation(tm.panelx, 0);
                        tm.viewstudentpanel.setVisible(true);
                        tm.viewstudentpanel.setFocusable(true);
                        tm.contentPane.add(tm.viewstudentpanel);
                    } else {
                        JTable t = (JTable) e.getSource();
                        int fid = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0) + "");
                        Teacher f = new TeacherData().getTeacherInfobyId(fid);

                        tm.viewteacherpanel = new ViewTeacherPanel(f, tm, tm.searchpanel);
                        tm.viewteacherpanel.setVisible(true);
                        tm.searchpanel.setVisible(false);
                        tm.viewteacherpanel.setLocation(tm.panelx, tm.panely);
                        tm.viewteacherpanel.setVisible(true);
                        tm.viewteacherpanel.setFocusable(true);
                        tm.contentPane.add(tm.viewteacherpanel);
                    }
                }
            }
        });

    }

    public SearchPanel(StudentMain sm) {
        this();
        deptnamecombo.setSelectedItem(new DepartmentData().getdeptname(sm.s.getDeptCode()));
        deptnamecombo.setEnabled(false);
        deptnamecombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                super.paint(g);
            }
        });
        semoryearcombo.setSelectedIndex(sm.s.getSemorYear());
        semoryearcombo.setEnabled(false);
        semoryearcombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
                super.paint(g);
            }
        });
        this.createtablemodel();
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {
                    if (studentandteachercombo.getSelectedIndex() == 0) {
                        JTable t = (JTable) e.getSource();
                        int row = t.getSelectedRow();
                        String deptcode = table.getValueAt(row, 0) + "";
                        String strsem = table.getValueAt(row, 4) + "";
                        int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
                        String strroll = table.getValueAt(row, 1) + "";
                        long rollnumber = Long.parseLong(strroll);
                        Student s = new StudentData().getStudentDetails(deptcode, sem, rollnumber);

                        sm.viewstudentpanel = new ViewStudentPanel(s, sm, sm.searchpanel);
                        sm.viewstudentpanel.setVisible(true);
                        sm.searchpanel.setVisible(false);
                        sm.viewstudentpanel.setLocation(sm.panelx, 0);
                        sm.viewstudentpanel.setVisible(true);
                        sm.viewstudentpanel.setFocusable(true);
                        sm.contentPane.add(sm.viewstudentpanel);
                    } else {
                        JTable t = (JTable) e.getSource();
                        int fid = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0) + "");
                        Teacher f = new TeacherData().getTeacherInfobyId(fid);

                        sm.viewteacherpanel = new ViewTeacherPanel(f, sm, sm.searchpanel);
                        sm.viewteacherpanel.setVisible(true);
                        sm.searchpanel.setVisible(false);
                        sm.viewteacherpanel.setLocation(sm.panelx, sm.panely);
                        sm.viewteacherpanel.setVisible(true);
                        sm.viewteacherpanel.setFocusable(true);
                        sm.contentPane.add(sm.viewteacherpanel);
                    }
                }
            }
        });

    }

    public SearchPanel() {
        this.setName("Search Panel");
        setBackground(new Color(255, 255, 255));
        this.setSize(1116, 705);
        setLayout(null);

        tableviewscroll = new JScrollPane();
        tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableviewscroll.setBounds(10, 194, 1096, 500);
        for (Component c : tableviewscroll.getComponents()) {
            c.setBackground(Color.white);
        }
        add(tableviewscroll);

        table = new JTable();

        table.setBorder(new LineBorder(Color.LIGHT_GRAY));

        table.getTableHeader().setBackground(new Color(125, 104, 196));
        table.getTableHeader().setForeground(Color.white);
        table.setSelectionBackground(new Color(218, 204, 231, 255));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.setModel(DbUtils.resultSetToTableModel(new StudentData().getStudentinfo("")));
        table.getTableHeader().setPreferredSize(new Dimension(50, 40));
        table.setFocusable(false);
        table.setDragEnabled(false);
        table.setRowHeight(40);
        table.setDefaultEditor(Object.class, null);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);
        tableviewscroll.setViewportView(table);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(48, 11, 103, 105));
        panel.setBounds(10, 0, 1096, 183);
        add(panel);
        panel.setLayout(null);
        JLabel lblStudentManagement = new JLabel("Search");
        lblStudentManagement.setIcon(null);
        lblStudentManagement.setBounds(10, 38, 224, 44);
        panel.add(lblStudentManagement);
        lblStudentManagement.setBackground(new Color(204, 115, 115, 0));
        lblStudentManagement.setHorizontalAlignment(SwingConstants.LEFT);
        lblStudentManagement.setForeground(Color.WHITE);
        lblStudentManagement.setFont(new Font("Segoe UI", Font.BOLD, 50));
        lblStudentManagement.setOpaque(true);

        studentandteachercombo = new JComboBox<String>();
        studentandteachercombo.setModel(new DefaultComboBoxModel<String>(new String[]{"Students", "Teachers"}));
        this.arrangeStudentTable();
        studentandteachercombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        studentandteachercombo.setBounds(10, 128, 205, 40);
        studentandteachercombo.addActionListener(this);
        panel.add(studentandteachercombo);

        String deptcode[] = new DepartmentData().getDeptName();
        deptcode[0] = "All Departments";
        deptnamecombo = new JComboBox<String>(deptcode);

        deptnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        deptnamecombo.setBounds(225, 128, 255, 40);
        deptnamecombo.addActionListener(this);
        panel.add(deptnamecombo);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        semoryearcombo.setBounds(490, 128, 214, 40);
        semoryearcombo.addActionListener(this);
        panel.add(semoryearcombo);

        searchfield = new HintTextField("Search");
        searchfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        searchfield.setForeground(Color.DARK_GRAY);
        searchfield.setBounds(714, 129, 248, 40);
        panel.add(searchfield);
        searchfield.setColumns(10);

        searchbutton = new JButton();
        searchbutton.setForeground(new Color(61, 0, 169));
        searchbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        searchbutton.setText("Search");
        searchbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchbutton.setBackground(new Color(255, 255, 255));
        searchbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchbutton.setIcon(new ImageIcon("./assets/search.png"));
        searchbutton.setBounds(972, 129, 114, 40);
        searchbutton.addActionListener(this);
        searchbutton.setFocusable(false);
        panel.add(searchbutton);
    }

    public void arrangeStudentTable() {
        table.getColumnModel().getColumn(0).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(300);
        table.getColumnModel().getColumn(4).setMaxWidth(150);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    public void arrangeTeacherTable() {
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(300);
        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(3).setMaxWidth(250);
        table.getColumnModel().getColumn(4).setMaxWidth(250);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
        cellrenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deptnamecombo) {
            if (deptnamecombo.getSelectedIndex() == 0) {
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            } else {
                String dept = (String) deptnamecombo.getSelectedItem();
                String semoryear[] = new DepartmentData().getSemorYear(dept);
                semoryear[0] = "All " + semoryear[1].substring(0, semoryear[1].indexOf(' '));
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(semoryear));
            }
        }
        if (e.getSource() == searchbutton) {
            createtablemodel();
        }
    }

    public void createtablemodel() {
        String searchtext = searchfield.getText().trim();
        if (studentandteachercombo.getSelectedIndex() == 0) {
            String defaultquery = "select s.Departmentcode as 'Class' ,s.rollnumber as 'Roll Number',concat(s.firstname,' ',s.lastname) as 'Student Name',d.DepartmentName as 'Dept. Name',concat(d.semoryear,'-',s.semoryear) as 'Sem/Year' from students s left join departments d on s.Departmentcode=d.Departmentcode ";
            String query = defaultquery;
            if (deptnamecombo.getSelectedIndex() > 0) {
                String deptcode = new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + "");
                query += " where s.Departmentcode='" + deptcode + "'";
                if (semoryearcombo.getSelectedIndex() > 0) {
                    query += " and s.semoryear=" + semoryearcombo.getSelectedIndex();
                }
            }
            if (!searchtext.isEmpty()) {
                String searchquery = "s.firstname like '" + searchtext + "%' or s.lastname like '" + searchtext + "%' or s.rollnumber like '" + searchtext + "%' ";
                if (!query.contains("where")) {
                    query += "where " + searchquery;
                } else {
                    query += " and ( " + searchquery + " ) ";
                }
            }
            table.setModel(DbUtils.resultSetToTableModel(new StudentData().searchStudent(query)));
            this.arrangeStudentTable();
        } else if (studentandteachercombo.getSelectedIndex() == 1) {
            String defaultquery = "select teacher_id as 'Teacher ID',teacherName as 'Teacher Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from teachers t ";
            String query = defaultquery;
            if (deptnamecombo.getSelectedIndex() > 0) {
                String deptcode = new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + "");
                query += " where t.Departmentcode='" + deptcode + "'";
                if (semoryearcombo.getSelectedIndex() > 0) {
                    query += " and t.semoryear=" + semoryearcombo.getSelectedIndex();
                }
            }
            if (!searchtext.equals("Search") && !searchtext.isEmpty()) {
                String searchquery = " t.teacherName like '" + searchtext + "%' or f.teacher_id like '" + searchtext + "%' ";
                if (!query.contains("where")) {
                    query += "where " + searchquery;
                } else {
                    query += " and ( " + searchquery + " ) ";
                }
            }
            table.setModel(DbUtils.resultSetToTableModel(new TeacherData().searchTeacher(query)));
            this.arrangeTeacherTable();
        }
    }
}
