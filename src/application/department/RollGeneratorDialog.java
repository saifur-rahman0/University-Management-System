package application.department;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Title : RollGeneratorDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : Dialog for generating rollnumber
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class RollGeneratorDialog extends JDialog implements ActionListener {

    JComboBox<String> deptnamecombo;
    JScrollPane scrollPane;
    JTable table;
    JPanel panel;
    JButton btnSave;
    JTextField[] tf;
    JLabel lblError;
    RollGeneratorPanel rp = null;
    JLabel lblSelectDept;
    JScrollPane rollgenscroll;
    private int sem;
    private String deptcode = "";
    private static RollGeneratorDialog dialog;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            dialog = new RollGeneratorDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocation(400, 100);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @paramdeptnamecombo
     */
    public RollGeneratorDialog() {
        super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);
        this.setLocation(400, 100);
        getContentPane().setBackground(new Color(255, 255, 255));
        setResizable(false);
        setSize(620, 282);
        getContentPane().setLayout(null);

        JLabel headingLabel = new JLabel("Roll number Generator ");
        headingLabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        headingLabel.setOpaque(true);
        headingLabel.setBounds(0, 0, this.getWidth(), 44);
        headingLabel.setBackground(new Color(48, 11, 103, 105));
        headingLabel.setForeground(new Color(255, 255, 255));
        headingLabel.setFont(new Font("Arial", Font.BOLD, 23));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(headingLabel);

        panel = new JPanel();
        panel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(192, 192, 192)));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, this.getHeight() - 82, 599, 53);
        getContentPane().add(panel);
        panel.setLayout(null);

        btnSave = new JButton("Save");
        btnSave.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSave.setFocusable(false);
        btnSave.setBackground(new Color(114, 14, 220));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(this);
        btnSave.setFont(new Font("Arial", Font.BOLD, 17));
        btnSave.setBounds(450, 11, 139, 33);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnSave);

        lblError = new JLabel("Error : Only number allowed");
        lblError.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblError.setForeground(Color.RED);
        lblError.setBounds(10, 11, 396, 29);
        lblError.setVisible(false);
        panel.add(lblError);

        deptnamecombo = new JComboBox<String>(new DepartmentData().getRollDeptName());
        deptnamecombo.setForeground(Color.DARK_GRAY);
        deptnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        deptnamecombo.setFocusable(false);
        deptnamecombo.addActionListener(this);
        deptnamecombo.setBackground(Color.WHITE);
        deptnamecombo.setBounds(182, 93, 407, 37);
        getContentPane().add(deptnamecombo);

        lblSelectDept = new JLabel("Select Course  :");
        lblSelectDept.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSelectDept.setForeground(new Color(0, 0, 0));
        lblSelectDept.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSelectDept.setBounds(10, 93, 162, 37);
        getContentPane().add(lblSelectDept);

        rollgenscroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(rollgenscroll);
        this.setSize(620, 262);
        panel.setBounds(0, this.getHeight() - 82, this.getWidth() - 6, 53);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == deptnamecombo && deptnamecombo.getSelectedIndex() > 0) {
            if (rollgenscroll != null) {
                rollgenscroll.setVisible(false);
            }

            {
                sem = new DepartmentData().getTotalsemoryear("" + deptnamecombo.getSelectedItem());
                deptcode = new DepartmentData().getDeptcode("" + deptnamecombo.getSelectedItem());

                rp = new RollGeneratorPanel(deptcode, sem);
                rp.setLocation(0, 0);
                rp.setVisible(true);
                rollgenscroll.setViewportView(rp);

                rollgenscroll.setLocation(0, 150);
                rollgenscroll.getVerticalScrollBar().setUnitIncrement(80);
                rollgenscroll.setVisible(true);
                rollgenscroll.setSize(620, sem < 5 ? rp.getHeight() + 5 : 4 * 62);
                this.setSize(620, rollgenscroll.getHeight() + 262);
                panel.setBounds(0, this.getHeight() - 82, this.getWidth() - 6, 53);
            }
        } else if (e.getSource() == deptnamecombo && deptnamecombo.getSelectedIndex() == 0) {
            if (rollgenscroll != null) {
                rollgenscroll.setSize(620, 0);
                this.setSize(620, rollgenscroll.getHeight() + 262);
                panel.setBounds(0, this.getHeight() - 82, this.getWidth() - 6, 53);
                rollgenscroll.setVisible(false);
            }
        }

        if (e.getSource() == btnSave && !CheckError() && deptnamecombo.getSelectedIndex() != 0) {
            String courcecode = new DepartmentData().getDeptcode("" + deptnamecombo.getSelectedItem());
            this.adddatatotable(courcecode, sem);
            this.dispose();
        }

    }

    public boolean CheckError() {
        int limit = sem;
        for (int i = 0; i < limit; i++) {
            if (rp.textField[i].getText().isEmpty()) {
                rp.textField[i].setBorder(new LineBorder(Color.RED, 1));
                lblError.setVisible(true);
                lblError.setText("Error : Enter Roll Number in sem/year " + (i + 1));
                return true;
            } else {
                try {
                    Long.parseLong(rp.textField[i].getText());
                } catch (NumberFormatException exp) {
                    rp.textField[i].setBorder(new LineBorder(Color.RED, 1));
                    lblError.setVisible(true);
                    lblError.setText("Must be a Number !");
                    return true;

                }
                lblError.setVisible(false);
                rp.textField[i].setBorder(new LineBorder(Color.gray, 1));
            }
        }
        return false;

    }

    public void adddatatotable(String courcecode, int limit) {
        for (int i = 1; i <= limit; i++) {
            long roll = Long.parseLong(rp.textField[i - 1].getText());
            new RollNumberData().adddata(courcecode, i, roll);

        }
    }
}
