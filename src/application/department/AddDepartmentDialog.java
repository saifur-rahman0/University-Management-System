package application.department;

import application.common.HintTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AddDepartmentDialog extends JDialog implements ActionListener {
    private final JTextField deptcodefield;
    private final JTextField deptnamefield;
    private final JTextField totalsemoryearfield;
    private final JComboBox<String> semoryearcombo;
    private final JLabel lblError;
    private DepartmentPanel deptpanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            AddDepartmentDialog dialog = new AddDepartmentDialog();

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public AddDepartmentDialog(DepartmentPanel deptpanel) {
        this();
        this.deptpanel = deptpanel;
    }

    public AddDepartmentDialog() {

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getContentPane().setBackground(Color.WHITE);
        setBounds(100, 100, 476, 452);
        getContentPane().setLayout(null);

        JLabel lblAddNewDept = new JLabel("Add New Department");
        lblAddNewDept.setForeground(new Color(255, 255, 255));
        lblAddNewDept.setBackground(new Color(48, 11, 103, 105));
        lblAddNewDept.setOpaque(true);
        lblAddNewDept.setFont(new Font("Arial", Font.BOLD, 23));
        lblAddNewDept.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddNewDept.setBounds(0, 0, 473, 55);
        getContentPane().add(lblAddNewDept);

        JLabel lblDeptCode = new JLabel("Dept. Code ");
        lblDeptCode.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblDeptCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblDeptCode.setHorizontalAlignment(SwingConstants.LEFT);
        lblDeptCode.setBounds(10, 79, 139, 24);
        lblDeptCode.setFocusable(true);
        getContentPane().add(lblDeptCode);

        JLabel lblDeptName = new JLabel("Dept. Name ");
        lblDeptName.setHorizontalAlignment(SwingConstants.LEFT);
        lblDeptName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblDeptName.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblDeptName.setBounds(10, 147, 139, 24);
        getContentPane().add(lblDeptName);

        JLabel lblSemyear = new JLabel("Sem/Year");
        lblSemyear.setHorizontalAlignment(SwingConstants.LEFT);
        lblSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblSemyear.setBounds(10, 218, 139, 24);
        getContentPane().add(lblSemyear);

        deptcodefield = new HintTextField("");
        deptcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        deptcodefield.setBounds(157, 72, 292, 40);
        getContentPane().add(deptcodefield);
        deptcodefield.setColumns(10);

        deptnamefield = new HintTextField("");
        deptnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        deptnamefield.setColumns(10);
        deptnamefield.setBounds(159, 141, 292, 40);
        getContentPane().add(deptnamefield);

        totalsemoryearfield = new HintTextField("");
        totalsemoryearfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        totalsemoryearfield.setColumns(10);
        totalsemoryearfield.setBounds(157, 278, 292, 40);
        getContentPane().add(totalsemoryearfield);

        semoryearcombo = new JComboBox<String>();
        semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[]{"---Select Sem/Year---", "semester", "year"}));
        semoryearcombo.setBounds(159, 210, 292, 40);
        getContentPane().add(semoryearcombo);

        JLabel lblTotalSemyear = new JLabel("Total Sem/Year");
        lblTotalSemyear.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblTotalSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblTotalSemyear.setBounds(10, 284, 139, 24);
        getContentPane().add(lblTotalSemyear);

        JButton adddeptbutton = new JButton("Add Department");
        adddeptbutton.setBackground(new Color(255, 255, 255, 255));
        adddeptbutton.setForeground(new Color(61, 0, 169));
        adddeptbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        adddeptbutton.setBounds(310, 373, 139, 37);
        adddeptbutton.setBorder(new LineBorder(new Color(92, 9, 134)));
        adddeptbutton.addActionListener(this);
        getContentPane().add(adddeptbutton);

        lblError = new JLabel("This is required question !");
        lblError.setBorder(new MatteBorder(0, 0, 0, 0, new Color(255, 0, 0)));
        lblError.setForeground(new Color(255, 0, 0));
        lblError.setFont(new Font("Candara", Font.PLAIN, 15));
        lblError.setVisible(false);
        lblError.setBounds(157, 115, 355, 21);
        getContentPane().add(lblError);

        JLabel label = new JLabel("");
        label.setBorder(new MatteBorder(0, 0, 2, 0, new Color(192, 192, 192)));
        label.setBounds(0, 346, 470, 14);
        getContentPane().add(label);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        lblError.setForeground(Color.red);
        lblError.setVisible(false);
        lblError.setText("This is required question !");
        String deptname = deptnamefield.getText();
        String deptcode = deptcodefield.getText();
        String semoryear = (String) semoryearcombo.getSelectedItem();
        String strtotalsemoryear = totalsemoryearfield.getText();
        if (deptcode.isEmpty()) {
            lblError.setVisible(true);
            lblError.setBounds(deptcodefield.getX(), deptcodefield.getY() + deptcodefield.getHeight(), lblError.getWidth(), 21);
            deptcodefield.setFocusable(true);
        } else if (deptname.isEmpty()) {
            lblError.setVisible(true);
            lblError.setBounds(deptnamefield.getX(), deptnamefield.getY() + deptnamefield.getHeight(), lblError.getWidth(), 21);
            deptnamefield.setFocusable(true);
        } else if (semoryearcombo.getSelectedIndex() == 0) {
            lblError.setVisible(true);
            lblError.setBounds(semoryearcombo.getX(), semoryearcombo.getY() + semoryearcombo.getHeight(), lblError.getWidth(), 21);
        } else if (strtotalsemoryear.isEmpty()) {

            lblError.setVisible(true);
            lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY() + totalsemoryearfield.getHeight(), lblError.getWidth(), 21);
            totalsemoryearfield.setFocusable(true);
        } else {
            try {
                int totalsemoryear = Integer.parseInt(strtotalsemoryear);
                if (new DepartmentData().isDeptCodeExist(deptcode.toUpperCase())) {
                    lblError.setVisible(true);
                    lblError.setBounds(deptcodefield.getX(), deptcodefield.getY() + deptcodefield.getHeight(), lblError.getWidth(), 21);
                    lblError.setText("Department code already exist !");
                } else if (new DepartmentData().isDepteNameExist(deptname)) {
                    lblError.setVisible(true);
                    lblError.setBounds(deptnamefield.getX(), deptnamefield.getY() + deptnamefield.getHeight(), lblError.getWidth(), 21);
                    deptnamefield.setFocusable(true);
                    lblError.setText("Department name already exist !");
                } else if (totalsemoryear < 1) {
                    lblError.setVisible(true);
                    lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY() + totalsemoryearfield.getHeight(), lblError.getWidth(), 21);
                    lblError.setText("Minimun 1 sem/year required !");
                } else {
                    DepartmentData c = new DepartmentData();
                    int result = c.addDept(deptcode, deptname, semoryear, totalsemoryear);
                    if (result > 0) {
                        if (deptpanel != null) {
                            deptpanel.updatetableData();
                        }
                        this.dispose();
                    }
                }
            } catch (NumberFormatException nexp) {
                lblError.setVisible(true);
                lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY() + totalsemoryearfield.getHeight(), lblError.getWidth(), 21);
                lblError.setText("Characters are not allowed !");
            }

        }
    }
}
