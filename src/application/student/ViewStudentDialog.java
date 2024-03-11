package application.student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import application.admin.AdminMain;
import application.department.DepartmentData;

import javax.swing.JComboBox;
import javax.swing.JComponent;

/*
 * Title : ViewStudentDialog.java
 * Purpose : Selecting student for finding more details
 */
@SuppressWarnings("serial")
public class ViewStudentDialog extends JDialog implements ActionListener {

    private JComboBox<String> deptnamecombo, semoryearcombo, rollnumbercombo;
    private JButton viewdetails;
    private AdminMain am;
    private JLabel Errorlabel;
    private static ViewStudentDialog dialog;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            dialog = new ViewStudentDialog(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ViewStudentDialog(AdminMain am) {
        super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);

        this.am = am;
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setSize(520, 484);
        getContentPane().setLayout(null);
        JLabel headerlabel = new JLabel("View Student Details");
        headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerlabel.setBounds(0, 0, 514, 53);
        getContentPane().add(headerlabel);
        headerlabel.setBackground(new Color(125, 104, 196));
        headerlabel.setOpaque(true);
        headerlabel.setForeground(new Color(255, 255, 255));
        headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
        headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));

        deptnamecombo = new JComboBox<String>(new DepartmentData().getDeptName());
        deptnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));

        deptnamecombo.setFocusable(false);
        deptnamecombo.setBackground(Color.WHITE);
        deptnamecombo.setBounds(170, 98, 320, 43);
        deptnamecombo.addActionListener(this);
        getContentPane().add(deptnamecombo);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        semoryearcombo.setFocusable(false);
        semoryearcombo.setBackground(Color.WHITE);
        semoryearcombo.setBounds(170, 191, 320, 43);
        semoryearcombo.addActionListener(this);
        getContentPane().add(semoryearcombo);

        rollnumbercombo = new JComboBox<String>();
        rollnumbercombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        rollnumbercombo.setFocusable(false);
        rollnumbercombo.setBackground(Color.WHITE);
        rollnumbercombo.setBounds(170, 286, 320, 43);
        rollnumbercombo.addActionListener(this);
        getContentPane().add(rollnumbercombo);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.GRAY));
        panel.setBounds(0, 402, 514, 53);
        getContentPane().add(panel);
        panel.setLayout(null);

        viewdetails = new JButton("View Details");
        viewdetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewdetails.setFocusable(false);
        viewdetails.setFont(new Font("Segoe UI", Font.BOLD, 14));
        viewdetails.setForeground(new Color(61, 0, 169));
        viewdetails.setBackground(new Color(245, 245, 245));
        viewdetails.addActionListener(this);
        viewdetails.setBounds(351, 11, 139, 33);
        panel.add(viewdetails);

        JLabel lblDept = new JLabel("Department     :");
        lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDept.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 18));
        lblDept.setBounds(24, 98, 136, 43);
        getContentPane().add(lblDept);

        JLabel lblSemyear = new JLabel("Sem/Year     :");
        lblSemyear.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSemyear.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 18));
        lblSemyear.setBounds(24, 191, 136, 43);
        getContentPane().add(lblSemyear);

        JLabel lblRollNumber = new JLabel("Roll no      :");
        lblRollNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRollNumber.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 18));
        lblRollNumber.setBounds(10, 286, 150, 43);
        getContentPane().add(lblRollNumber);

        Errorlabel = new JLabel("This is required question !");
        Errorlabel.setForeground(new Color(255, 0, 0));
        Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        Errorlabel.setBounds(21, 127, 250, 17);
        Errorlabel.setVisible(false);
        getContentPane().add(Errorlabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewdetails) {
            if (deptnamecombo.getSelectedIndex() == 0) {
                showerror(deptnamecombo);
            } else if (semoryearcombo.getSelectedIndex() == 0) {
                showerror(semoryearcombo);
            } else if (rollnumbercombo.getSelectedIndex() == 0) {
                showerror(rollnumbercombo);
            } else {
                String Deptcode = new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + "");
                int sem = semoryearcombo.getSelectedIndex();
                long rollnumber = Long.parseLong(rollnumbercombo.getSelectedItem() + "");
                Student s = new StudentData().getStudentDetails(Deptcode, sem, rollnumber);
                am.viewstudentpanel = new ViewStudentPanel(s, am, am.studentpanel);
                am.viewstudentpanel.setVisible(true);
                am.studentpanel.setVisible(false);
                am.viewstudentpanel.setLocation(238, 0);
                am.viewstudentpanel.setVisible(true);
                am.viewstudentpanel.setFocusable(true);
                am.contentPane.add(am.viewstudentpanel);
                this.dispose();
            }
        }

        if (e.getSource() == deptnamecombo) {
            if (deptnamecombo.getSelectedIndex() == 0) {
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{""}));
            } else {
                String dept = (String) deptnamecombo.getSelectedItem();
                semoryearcombo.setModel(new DefaultComboBoxModel<String>(new DepartmentData().getSemorYear(dept)));
            }
        }
        if (e.getSource() == semoryearcombo) {
            if (semoryearcombo.getSelectedIndex() > 0) {
                String deptcode = new DepartmentData().getDeptcode(deptnamecombo.getSelectedItem() + "");
                int sem = semoryearcombo.getSelectedIndex();
                rollnumbercombo.setModel(new DefaultComboBoxModel<String>(new StudentData().getRollNumber(deptcode, sem)));
            }
        }
    }

    public void showerror(JComponent tf) {
        Errorlabel.setVisible(true);
        Errorlabel.setBounds(tf.getX(), tf.getY() + tf.getHeight() - 5, 250, 26);
    }
}
