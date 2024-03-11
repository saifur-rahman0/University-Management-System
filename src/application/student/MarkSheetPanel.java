package application.student;

import application.admin.AdminMain;
import application.common.PrintMarksheetDialog;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Purpose : For displaying student marksheet
 */
@SuppressWarnings("serial")
public class MarkSheetPanel extends JPanel {
    private final JTable table;
    private int totalcourse = 0;
    private final Student s;
    private final JLabel rollnumberlabel;
    private final JLabel studentnamelabel;
    private final JLabel profilepiclabel;
    private final JLabel deptnamelabel;
    private final JScrollPane tableviewpanel;
    private final int rowsize = 50;
    public JButton downloadbutton;
    private final JButton backbutton;
    public String defaultDownloadPath = "C:\\Users\\Saifur Rahman\\Downloads\\";
    private JLabel notdeclaredlabel;
    private final JPanel studentdetailspanel;
    private final JPanel headerpanel;

    /**
     * Create the panel.
     *
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1096, 460 + (totalcourse * rowsize));

    }

    public MarkSheetPanel(AdminMain am, Student s) {
        this(s);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                am.marksheetpanelscroll.setVisible(false);
                am.viewstudentpanel.setVisible(true);
            }
        });
        downloadbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PrintMarksheetDialog ppd = new PrintMarksheetDialog(am, s);
                ppd.setLocationRelativeTo(null);
                ppd.setVisible(true);
            }
        }
        );
    }

    public MarkSheetPanel(TeacherMain tm, Student s) {
        this(s);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tm.marksheetpanelscroll.setVisible(false);
                tm.viewstudentpanel.setVisible(true);
            }
        });
        downloadbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PrintMarksheetDialog ppd = new PrintMarksheetDialog(tm, s);
                ppd.setLocationRelativeTo(null);
                ppd.setVisible(true);
            }
        }
        );
    }

    public MarkSheetPanel(StudentMain sm, Student s) {
        this(s);
        backbutton.setVisible(false);
        downloadbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PrintMarksheetDialog ppd = new PrintMarksheetDialog(sm, s);
                ppd.setLocationRelativeTo(null);
                ppd.setVisible(true);
            }
        }
        );
    }

    public MarkSheetPanel(StudentMain sm, Student s, JComponent lastpanel) {
        this(s);
        backbutton.setVisible(true);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sm.marksheetpanelscroll.setVisible(false);
                lastpanel.setVisible(true);
            }
        });
        downloadbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PrintMarksheetDialog ppd = new PrintMarksheetDialog(sm, s);
                ppd.setLocationRelativeTo(null);
                ppd.setVisible(true);
            }
        }
        );
    }

    public void notDeclared() {
        downloadbutton.setVisible(false);
        studentdetailspanel.setVisible(false);
        tableviewpanel.setVisible(false);
        notdeclaredlabel = new JLabel("");
        notdeclaredlabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            Image image = ImageIO.read(new File("./assets/notfound2.png"));
            notdeclaredlabel.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        notdeclaredlabel.setText("Oops result not declared yet...!");
        notdeclaredlabel.setVerticalTextPosition(JLabel.BOTTOM);
        notdeclaredlabel.setBorder(null);
        notdeclaredlabel.setBackground(new Color(245, 245, 245));
        notdeclaredlabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        notdeclaredlabel.setHorizontalTextPosition(JLabel.CENTER);
        notdeclaredlabel.setIconTextGap(20);
        notdeclaredlabel.setBounds(300, 220, 480, 321);
        add(notdeclaredlabel);
    }

    /**
     * @wbp.parser.constructor
     */
    private MarkSheetPanel(Student s) {
        this.s = s;

        setBackground(new Color(255, 255, 255));
        this.setSize(1116, 705);
        setLayout(null);

        headerpanel = new JPanel();
        headerpanel.setLocation(10, 0);
        headerpanel.setBackground(new Color(48, 11, 103, 105));
        headerpanel.setSize(1076, 100);
        add(headerpanel);
        headerpanel.setLayout(null);

        JLabel lblMarksheet = new JLabel("Marksheet");
        lblMarksheet.setBorder(new EmptyBorder(0, 0, 0, 0));
        lblMarksheet.setBounds(10, 26, 1066, 40);
        headerpanel.add(lblMarksheet);
        lblMarksheet.setHorizontalAlignment(SwingConstants.CENTER);
        lblMarksheet.setForeground(Color.WHITE);
        lblMarksheet.setFont(new Font("Segoe UI", Font.BOLD, 30));

        backbutton = new JButton("Back");
        //backbutton.setContentAreaFilled(false);
        backbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
        backbutton.setFocusable(false);
        backbutton.setForeground(new Color(61, 0, 169));
        backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backbutton.setBackground(new Color(255, 255, 255, 255));
        backbutton.setBounds(10, 47, 88, 36);
        headerpanel.add(backbutton);

        downloadbutton = new JButton("Download");
        downloadbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        downloadbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        downloadbutton.setIcon(new ImageIcon(".\\assets\\downloadbutton.png"));
        downloadbutton.setFocusable(false);
        downloadbutton.setFont(new Font("Segoe UI", Font.BOLD, 17));
        downloadbutton.setForeground(new Color(61, 0, 169));
        downloadbutton.setBackground(new Color(218, 204, 231, 255));
        downloadbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        downloadbutton.setBounds(891, 55, 164, 35);
        headerpanel.add(downloadbutton);

        studentdetailspanel = new JPanel();
        studentdetailspanel.setBackground(Color.WHITE);
        studentdetailspanel.setBounds(10, 111, 1076, 223);
        add(studentdetailspanel);
        studentdetailspanel.setLayout(null);

        JLabel studentnamelbl = new JLabel("Student Name  :");
        studentnamelbl.setBounds(462, 11, 151, 46);
        studentdetailspanel.add(studentnamelbl);
        studentnamelbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        studentnamelbl.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel genderlabel = new JLabel(s.getGender());
        genderlabel.setBounds(623, 125, 216, 46);
        studentdetailspanel.add(genderlabel);
        genderlabel.setHorizontalAlignment(SwingConstants.LEFT);
        genderlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblRollNumber = new JLabel("Roll Number   :");
        lblRollNumber.setBounds(20, 11, 166, 46);
        studentdetailspanel.add(lblRollNumber);
        lblRollNumber.setForeground(Color.BLACK);
        lblRollNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRollNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        studentnamelabel = new JLabel(s.getFullName());
        studentnamelabel.setBounds(623, 11, 252, 46);
        studentdetailspanel.add(studentnamelabel);
        studentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
        studentnamelabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        rollnumberlabel = new JLabel(s.getRollNumber() + "");
        rollnumberlabel.setBounds(203, 11, 262, 46);
        studentdetailspanel.add(rollnumberlabel);
        rollnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
        rollnumberlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        profilepiclabel = new JLabel("image");
        profilepiclabel.setBounds(885, 8, 181, 208);
        studentdetailspanel.add(profilepiclabel);
        profilepiclabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        profilepiclabel.setIcon(new ImageIcon(s.getProfilePic(profilepiclabel.getWidth() + 10, profilepiclabel.getHeight())));
        profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 11));

        JLabel deptlbl = new JLabel("Departemnt   :");
        deptlbl.setBounds(10, 68, 176, 46);
        studentdetailspanel.add(deptlbl);
        deptlbl.setHorizontalAlignment(SwingConstants.RIGHT);
        deptlbl.setForeground(Color.BLACK);
        deptlbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel lblSemesteryear = new JLabel("Semester/Year  :");
        lblSemesteryear.setBounds(462, 68, 152, 46);
        studentdetailspanel.add(lblSemesteryear);
        lblSemesteryear.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSemesteryear.setForeground(Color.BLACK);
        lblSemesteryear.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        deptnamelabel = new JLabel(s.getDeptName());
        deptnamelabel.setBounds(203, 68, 263, 46);
        studentdetailspanel.add(deptnamelabel);
        deptnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
        deptnamelabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel semoryearlabel = new JLabel(new DepartmentData().getsemoryear(s.getDeptCode()) + "-" + s.getSemorYear() + " (" + s.getDeptCode() + ")");
        semoryearlabel.setBounds(623, 68, 252, 46);
        studentdetailspanel.add(semoryearlabel);
        semoryearlabel.setHorizontalAlignment(SwingConstants.LEFT);
        semoryearlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblDateOfBirth = new JLabel("Date of Birth  :");
        lblDateOfBirth.setBounds(10, 125, 176, 46);
        studentdetailspanel.add(lblDateOfBirth);
        lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDateOfBirth.setForeground(Color.BLACK);
        lblDateOfBirth.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel dateofbirthlabel = new JLabel(s.getBirthDate());
        dateofbirthlabel.setBounds(203, 125, 261, 46);
        studentdetailspanel.add(dateofbirthlabel);
        dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateofbirthlabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblGender = new JLabel("Gender   :");
        lblGender.setBounds(475, 125, 138, 46);
        studentdetailspanel.add(lblGender);
        lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
        lblGender.setForeground(Color.BLACK);
        lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        tableviewpanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tableviewpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableviewpanel.getVerticalScrollBar().setUnitIncrement(30);
        tableviewpanel.setBounds(10, 372, 1075, rowsize);
        add(tableviewpanel);

        table = new JTable();
        tableviewpanel.setViewportView(table);
        table.setToolTipText("Mark Sheet");

        table.setDefaultEditor(Object.class, null);
        table.setBorder(new LineBorder(new Color(192, 192, 192)));
        table.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        createtablemodel();
        table.setBackground(Color.white);
        table.setRowHeight(rowsize);
        table.getTableHeader().setBackground(new Color(125, 104, 196));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.getTableHeader().setPreferredSize(new Dimension(50, rowsize));
        table.setDragEnabled(false);
        table.setFocusable(false);
        table.setSelectionModel(new ForcedListSelectionModel());
//		table.setSelectionBackground(new Color(95, 158, 160));
        table.setSelectionBackground(new Color(218, 204, 231, 255));
//		table.setSelectionForeground(new Color(255, 255, 255));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
        cellrenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);

        if ((totalcourse - 1) == 0) {
            table.setSelectionBackground(Color.white);
            table.setSelectionForeground(Color.DARK_GRAY);
        }
        table.addRowSelectionInterval(totalcourse - 1, totalcourse - 1);
        table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());
    }

    private void createtablemodel() {
        ArrayList<Marks> list = new StudentData().getMarkssheetOfStudent(s.getDeptCode(), s.getSemorYear(), s.getRollNumber());
        totalcourse = list.size();
        Marks m = new Marks();
        String[] ColumnName = {"Course Code", "Course Name", "Latter Grade", "GPA"};
        DefaultTableModel model = new DefaultTableModel(ColumnName, 0);
        int totalGPA = 0;
        String lGrade;
        float gpa;
        for (int i = 0; i < list.size(); i++) {
            lGrade = numtolattergrade(list.get(i).getTheoryMarks());
            gpa = numtogpa(list.get(i).getTheoryMarks());
            totalGPA+=gpa;
            Object[] data = {" " + list.get(i).getCourseCode(), list.get(i).getCourseName(), lGrade, gpa};
            model.addRow(data);
        }
        float avgGPA = (totalGPA/totalcourse);
        String letterGrade = (avgGPA >= 4) ? "A+" :
                (avgGPA >= 3.75) ? "A" : (avgGPA >= 3.50) ? "A-" : (avgGPA >= 3.25) ? "B+" : (avgGPA >= 3.00) ? "B" : (avgGPA >= 2.75) ? "B-" : (avgGPA >= 2.50) ? "C+" : (avgGPA >= 2.25) ? "C" : (avgGPA >= 2.00) ? "D" : "F";

        if (totalcourse > 0) {
            Object[] result = {"", "Average GPA", letterGrade, avgGPA};
            model.addRow(result);
        } else {
            Object[] result = {"-", "No Course Found", "-", "-", "-", "-"};
            model.addRow(result);
        }
        totalcourse++;
        table.setModel(model);

        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(600);
        table.getColumnModel().getColumn(2).setMaxWidth(175);
        table.getColumnModel().getColumn(3).setMaxWidth(175);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setFocusable(false);

        tableviewpanel.setSize(975, rowsize + (totalcourse * rowsize));
        table.setEnabled(false);
        this.setSize(1096, 460 + (totalcourse * rowsize));
        System.out.println("Marksheet :" + new DepartmentData().isDeclared(s.getDeptCode(), s.getSemorYear()));
        if (!new DepartmentData().isDeclared(s.getDeptCode(), s.getSemorYear())) {
            notDeclared();
        }
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
    public float numtogpa(int num){
        float gpa= 0;
        if(num >= 80)
            gpa= 4.00F;
        else if(num >= 75)
            gpa= 3.75F;
        else if (num >= 70)
            gpa= 3.50F;
        else if (num >= 65)
            gpa= 3.25F;
        else if (num >= 60)
            gpa= 3.00F;
        else if (num >= 55)
            gpa= 2.75F;
        else if (num >= 50)
            gpa= 2.50F;
        else if (num >= 45)
            gpa= 2.25F;
        else if (num >= 40)
            gpa= 2.0F;
        else if (num < 40)
            gpa= 0;
        return gpa;
    }

    public void disablebutton() {
        downloadbutton.setVisible(false);
        backbutton.setVisible(false);
    }

    public void enablebutton() {
        downloadbutton.setVisible(true);
        backbutton.setVisible(true);
    }

    private class CellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == (totalcourse - 1)) {
                this.setFont(this.getFont().deriveFont(Font.BOLD));
                if (row == 0) {
                    this.setHorizontalAlignment(JLabel.CENTER);
                }
            }
            return this;
        }
    }
}
