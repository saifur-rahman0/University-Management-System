package application.course;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
/*
 * Title : AddCourseDialog.java
 * Purpose : For adding new course to dept
 */
@SuppressWarnings("serial")
public class AddCourseDialog extends JDialog implements ActionListener {
    private final JPanel contentPanel = new JPanel();
    private final JTextField coursecodefield;
    private final JTextField coursenamefield;
    private final JTextField theorymarksfield;
    private final JButton addcourse;
    private final JComboBox<String> Depttypecombo;
    private final String Deptcode;
    private final int semoryear;
    private final JTable table;
    private final JLabel lblError;
    private static AddCourseDialog dialog;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            dialog = new AddCourseDialog("IT", 1, null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Create the dialog.
     */
    public AddCourseDialog(String deptCode, int sem, JTable datatable) {
        //super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);
        setBackground(new Color(0, 128, 128));
        this.table = datatable;
        this.semoryear = sem;
        this.Deptcode = deptCode;
        setResizable(false);
        setSize(518, 488);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Course Code");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblNewLabel.setBounds(30, 71, 132, 35);
        contentPanel.add(lblNewLabel);

        JLabel lblCourseName = new JLabel("Course Name");
        lblCourseName.setForeground(Color.DARK_GRAY);
        lblCourseName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCourseName.setBounds(30, 134, 132, 35);
        contentPanel.add(lblCourseName);

        JLabel lblDeptType = new JLabel("Course Type");
        lblDeptType.setForeground(Color.DARK_GRAY);
        lblDeptType.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblDeptType.setBounds(30, 198, 132, 36);
        contentPanel.add(lblDeptType);

        JLabel lblTheoryMarks = new JLabel("Marks");
        lblTheoryMarks.setForeground(Color.DARK_GRAY);
        lblTheoryMarks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTheoryMarks.setBounds(30, 265, 132, 35);
        contentPanel.add(lblTheoryMarks);

        /*
        JLabel lblPracticalMarks = new JLabel("Practical Marks");
        lblPracticalMarks.setForeground(Color.DARK_GRAY);
        lblPracticalMarks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPracticalMarks.setBounds(30, 332, 132, 35);
        contentPanel.add(lblPracticalMarks);
        */

        coursecodefield = new JTextField();
        coursecodefield.setEditable(true);
        coursecodefield.setText(new CourseData().createCoursecode(Deptcode, sem));

        coursecodefield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        coursecodefield.setBounds(172, 70, 331, 40);
        contentPanel.add(coursecodefield);
        coursecodefield.setColumns(10);

        coursenamefield = new JTextField();
        coursenamefield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    coursenamefield.setFocusable(false);
                    theorymarksfield.setFocusable(true);
                }
            }
        });
        coursenamefield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                coursenamefield.setFocusable(true);
            }
        });
        coursenamefield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        coursenamefield.setColumns(10);
        coursenamefield.setBounds(172, 136, 331, 40);
        contentPanel.add(coursenamefield);

        theorymarksfield = new JTextField();
        theorymarksfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    theorymarksfield.setFocusable(false);
//                    practicalmarksfield.setFocusable(true);
                }
            }
        });
        theorymarksfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                theorymarksfield.setFocusable(true);
            }
        });
        theorymarksfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        theorymarksfield.setColumns(10);

        theorymarksfield.setBounds(172, 267, 331, 40);
        contentPanel.add(theorymarksfield);

        Depttypecombo = new JComboBox<String>();
        Depttypecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        Depttypecombo.setModel(new DefaultComboBoxModel<String>(new String[]{"---select---", "core", "optional"}));
        Depttypecombo.setFocusable(false);
        Depttypecombo.setBackground(Color.WHITE);
        Depttypecombo.setBounds(172, 199, 330, 40);
        contentPanel.add(Depttypecombo);

        JLabel headerlabel = new JLabel("   Add New Course");
        headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerlabel.setBackground(new Color(48, 11, 103, 105));
        headerlabel.setOpaque(true);
        headerlabel.setForeground(new Color(255, 255, 255));
        headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
        headerlabel.setBounds(0, 0, 512, 44);
        headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        contentPanel.add(headerlabel);

        addcourse = new JButton("Add Course");
        addcourse.setBorder(new LineBorder(new Color(92, 9, 134)));
        addcourse.setForeground(new Color(61, 0, 169));
        addcourse.setBackground(new Color(255, 255, 255));
        addcourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addcourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addcourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                HandCursor();
            }

            public void mouseExited(MouseEvent e) {
                DefaultCursor();
            }
        });

        addcourse.setBackground(new Color(255, 255, 255));
        addcourse.setForeground(new Color(61, 0, 169));
        addcourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addcourse.setBounds(363, 413, 139, 33);
        addcourse.addActionListener(this);
        addcourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addcourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addcourse.setFocusable(false);

        contentPanel.add(addcourse);

        JLabel borderlabel = new JLabel("");
        borderlabel.setBounds(0, 388, 512, 14);
        contentPanel.add(borderlabel);
        borderlabel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)));

        lblError = new JLabel("This is required question !");
        lblError.setBorder(new MatteBorder(0, 0, 0, 0, new Color(255, 0, 0)));
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Candara", Font.PLAIN, 17));
        lblError.setBounds(172, 107, 331, 30);
        lblError.setVisible(false);
        contentPanel.add(lblError);
    }

    public void HandCursor() {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void DefaultCursor() {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lblError.setVisible(false);
        lblError.setText("This is required question..!=-");
        if (coursenamefield.getText().isEmpty()) {
            lblError.setVisible(true);
            lblError.setBounds(coursenamefield.getX(), coursenamefield.getY() + coursenamefield.getHeight() - 5, 331, 30);
        } else if (Depttypecombo.getSelectedIndex() == 0) {
            lblError.setVisible(true);
            lblError.setBounds(Depttypecombo.getX(), Depttypecombo.getY() + Depttypecombo.getHeight() - 5, 331, 30);
        } else if (theorymarksfield.getText().isEmpty() || coursenamefield.getText().isEmpty()) {
            lblError.setVisible(true);
            lblError.setBounds(theorymarksfield.getX(), theorymarksfield.getY() + theorymarksfield.getHeight() - 5, 331, 30);
        } else if (new CourseData().isExist(Deptcode, semoryear, coursenamefield.getText())) {
            lblError.setVisible(true);
            lblError.setBounds(coursenamefield.getX(), coursenamefield.getY() + coursenamefield.getHeight() - 5, 331, 30);
            lblError.setText("Course name already exist..!");
        } else {
            if (e.getSource() == addcourse) {
                String numbererror = "";
                try {
                    String coursecode = coursecodefield.getText();
                    String coursename = coursenamefield.getText();
                    String coursetype = (String) Depttypecombo.getSelectedItem();
                    numbererror = "theorymarks";
                    int theorymarks = Integer.parseInt(theorymarksfield.getText());

                    Course su = new Course();
                    su.setCourseName(coursename);
                    su.setCourseCode(coursecode);
                    su.setDeptCode(Deptcode);
                    su.setSemorYear(semoryear);
                    su.setCourseType(coursetype);
                    su.setMaxTheoryMarks(theorymarks);
                    int result = new CourseData().addCourse(su);
                    if (result == 1) {
                        ResultSet st = new CourseData().getCourseinfo(Deptcode, semoryear);
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
                        this.dispose();
                    }
                } catch (NumberFormatException exp) {
                    if (numbererror.equals("theorymarks")) {
                        lblError.setBounds(theorymarksfield.getX(), theorymarksfield.getY() + theorymarksfield.getHeight(), 331, 30);
                    }
                    /*if (numbererror.equals("practicalmarks")) {
                        lblError.setBounds(practicalmarksfield.getX(), practicalmarksfield.getY() + practicalmarksfield.getHeight(), 331, 30);
                    }*/
                    lblError.setVisible(true);
                    lblError.setText("Characters are not allowed !");
                }
            }
        }
    }
}
