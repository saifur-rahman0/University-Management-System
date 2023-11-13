package application.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import application.admin.AdminData;
import application.admin.AdminMain;
import application.common.HintPasswordField;
import application.common.HintTextField;
import application.faculty.Faculty;
import application.faculty.FacultyData;
import application.faculty.FacultyMain;
import application.common.UserData;
import application.student.Student;
import application.student.StudentData;
import application.student.StudentMain;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener {

    public HintTextField useridfield;
    public JPasswordField passwordfield;
    public JButton loginbutton;
    String loginprofile;
    private LoginPageFrame loginpageframe;

    /**
     * Create the panel.
     */
    public LoginPanel(String loginprofile, ImageIcon imageicon, LoginPageFrame lpf) {

        this.loginprofile = loginprofile;
        this.loginpageframe = lpf;
        setBorder(new LineBorder(new Color(72, 72, 72)));
        setBackground(new Color(0, 0, 0, 80));
        setBounds(490, 206, 420, 434);
        setLayout(null);

        JLabel lblPassword = new JLabel("");
        lblPassword.setOpaque(true);
        lblPassword.setBackground(new Color(0, 0, 0, 80));
        lblPassword.setIcon(new ImageIcon(".\\assets\\login_Panal\\password1.png"));
        lblPassword.setBounds(20, 272, 60, 44);
        add(lblPassword);
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPassword.setBorder(new LineBorder(new Color(0, 0, 0, 80)));

        useridfield = new HintTextField("Userid");
        useridfield.setBorder(new EmptyBorder(0, 3, 0, 0));
        useridfield.setToolTipText("User Id");
        useridfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        useridfield.setBounds(80, 196, 323, 44);
        useridfield.setForeground(Color.DARK_GRAY);
        add(useridfield);
        useridfield.setColumns(10);

        JLabel lblEmailId = new JLabel("");
        lblEmailId.setOpaque(true);
        lblEmailId.setFocusable(true);
        lblEmailId.setBackground(new Color(0, 0, 0, 80));
        lblEmailId.setIcon(new ImageIcon(".\\assets\\login_Panal\\userid.png"));
        lblEmailId.setBounds(20, 196, 60, 44);
        add(lblEmailId);
        lblEmailId.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmailId.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        loginbutton = new JButton("LOGIN");
        loginbutton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginbutton.setForeground(new Color(255, 255, 255));
        loginbutton.addActionListener(this);
        loginbutton.setBackground(new Color(105, 104, 104, 176));
        loginbutton.setBounds(135, 355, 150, 44);
        loginbutton.setFocusable(false);
        loginbutton.setFocusPainted(false);
        loginbutton.setBorderPainted(false);
        add(loginbutton);

        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginbutton.setBackground(new Color(255,255,255));
                loginbutton.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent e){
                loginbutton.setBackground(UIManager.getColor("control"));
                loginbutton.setBackground(new Color(105,104,104, 176));
                loginbutton.setForeground(Color.white);
            }
        });


        JLabel lblStudentLogin = new JLabel(loginprofile + " Login");
        lblStudentLogin.setForeground(new Color(255, 255, 255));
        lblStudentLogin.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblStudentLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblStudentLogin.setBounds(10, 121, 420, 38);
        add(lblStudentLogin);

        JLabel userprofilelabel = new JLabel();
        userprofilelabel.setIcon(imageicon);
        userprofilelabel.setBounds(169, 28, 100, 98);
        add(userprofilelabel);

        passwordfield = new HintPasswordField("Password");
        passwordfield.setBorder(useridfield.getBorder());
        passwordfield.setToolTipText("Password");
        passwordfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordfield.setBounds(80, 272, 261, 44);
        add(passwordfield);

        JButton showandhidebutton = new JButton("");
        String showpic= "./assets/login_Panal/show.png", hidepic= "./assets/login_Panal/hide.png";
        showandhidebutton.setForeground(new Color(255, 255, 255));
        showandhidebutton.setBounds(341, 272, 62, 44);
        showandhidebutton.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(0,0,0)));
        showandhidebutton.setName("show");
        showandhidebutton.setIcon(new ImageIcon(showpic));
        showandhidebutton.setFocusable(false);
        showandhidebutton.setFocusPainted(false);
        showandhidebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        showandhidebutton.setBackground(new Color(255, 255, 255));
        showandhidebutton.setBorderPainted(true);
        showandhidebutton.addActionListener(e
                -> {
            if (showandhidebutton.getName().equals("show")) {
                passwordfield.setEchoChar('\u0000');
                showandhidebutton.setIcon(new ImageIcon(hidepic));
                showandhidebutton.setName("hide");
            } else {
                passwordfield.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                showandhidebutton.setIcon(new ImageIcon(showpic));
                showandhidebutton.setName("show");
            }
        });
        add(showandhidebutton);
    }

    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        if (loginprofile.equals("Admin")) {
            boolean result = new AdminData().checkPassword(useridfield.getText(), passwordfield.getText());
            if (result == true) {

                AdminMain am = new AdminMain();
                am.setVisible(true);
                am.setLocationRelativeTo(null);
                loginpageframe.timer.stop();
                loginpageframe.imagetimer.stop();
                System.out.println("Timer running " + loginpageframe.timer.isRunning());

                loginpageframe.dispose();

            }

        } else if (loginprofile.equals("Faculty")) {
            boolean result = new FacultyData().checkPassword(useridfield.getText(), passwordfield.getText());
            if (result == true) {
                Faculty f = new FacultyData().getFacultyInfobyUserId(useridfield.getText());
                if (!f.getCourceCode().equals("Not Assigned")) {

                    new UserData().addFacultyLoginTime(f);
                    FacultyMain fm = new FacultyMain(f);
                    fm.setVisible(true);
                    fm.setLocationRelativeTo(null);
                    loginpageframe.timer.stop();
                    loginpageframe.imagetimer.stop();
                    loginpageframe.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Your account is not activated. contact principal", "Login Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (loginprofile.equals("Student")) {
            boolean result = new StudentData().checkPassword(useridfield.getText(), passwordfield.getText());
            if (result == true) {
                Student s = new StudentData().getStudentDetailsByUserId(useridfield.getText());
                new UserData().addStudentLoginTime(s);
                StudentMain sm = new StudentMain(s);
                sm.setVisible(true);
                sm.setLocationRelativeTo(null);
                loginpageframe.timer.stop();
                loginpageframe.imagetimer.stop();
                loginpageframe.dispose();

            }
        }
    }




}
