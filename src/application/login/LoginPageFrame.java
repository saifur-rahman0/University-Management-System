package application.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import application.admin.Admin;
import application.admin.AdminData;
import application.common.DataBaseConnection;

@SuppressWarnings("serial")
public class LoginPageFrame extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton teacherbutton;
    private JButton studentbutton;
    private JButton adminbutton;
    private JButton fullscrnbtn;
    private LoginPanel studentloginpanel, teacherloginpanel, adminloginpanel;
    private boolean adminchanging = false, studentchanging = false, teacherchanging = false;
    private int adminpanelx = -2300, adminpanely = 240;
    private int teacherpanelx = -900, teacherpanely = 240;
    private int studentpanelx = 500, studentpanely = 240;
    private int underlinelabelx = 280, underlinelabelwidth = 140;
    public Timer timer;
    private int imagenumber = 1;
    private JLabel bgimagelabel;
    private JLabel underlinelabel;
    private JPanel loginbuttonpanel;
    public Timer imagetimer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    if (DataBaseConnection.checkconnection()) {
                        LoginPageFrame frame = new LoginPageFrame();
                        frame.setVisible(true);
                        frame.setLocation(-7, 0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Start the Database Server first", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public LoginPageFrame() {
        timer = new Timer(5, this);
        imagetimer = new Timer(5000, this);
        imagetimer.start();
        setTitle("Login");
        setIconImage(new AdminData().getProfilePic());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1380, 733);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        contentPane.setBackground(new Color(255, 255, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Admin ad = new AdminData().getAdminData();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(109, 109, 112, 131));
        panel.setBounds(0, 26, 1364, 159);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblSilverOakCollage = new JLabel(ad.getCollageName());
        lblSilverOakCollage.setForeground(Color.WHITE);
        lblSilverOakCollage.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblSilverOakCollage.setHorizontalAlignment(SwingConstants.LEFT);
        lblSilverOakCollage.setBounds(160, 43, 749, 57);
        panel.add(lblSilverOakCollage);

        JLabel lblLogo = new JLabel("logo");
        lblLogo.setBounds(10, 10, 140, 140);
        lblLogo.setIcon(new ImageIcon(ad.getRoundedProfilePic(lblLogo.getWidth(), lblLogo.getHeight(), lblLogo.getWidth())));
        panel.add(lblLogo);

        studentloginpanel = new LoginPanel("Student", new ImageIcon("./assets/login_Panal/studentlogin.png"), this);
        studentloginpanel.setVisible(true);
        studentloginpanel.setLocation(studentpanelx, studentpanely);

        teacherloginpanel = new LoginPanel("Teacher", new ImageIcon("./assets/login_Panal/teacherlogin.png"), this);
        teacherloginpanel.setVisible(true);
        teacherloginpanel.setLocation(teacherpanelx, teacherpanely);

        adminloginpanel = new LoginPanel("Admin", new ImageIcon("./assets/login_Panal/adminlogin.png"), this);
        adminloginpanel.setVisible(true);
        adminloginpanel.setLocation(adminpanelx, adminpanely);

        contentPane.add(studentloginpanel);
        contentPane.add(teacherloginpanel);
        contentPane.add(adminloginpanel);

        loginbuttonpanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        loginbuttonpanel.setOpaque(false);
        loginbuttonpanel.setBackground(new Color(0, 0, 0, 120));
        loginbuttonpanel.setBounds(500, 189, 420, 40);
        loginbuttonpanel.setLayout(null);
        contentPane.add(loginbuttonpanel);

        adminbutton = new JButton("Admin");
        adminbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeButton(adminbutton);
                disableButton(teacherbutton);
                disableButton(studentbutton);
                adminchanging = true;
                studentchanging = false;
                teacherchanging = false;
                timer.start();
            }
        });
        this.buttonStyle(adminbutton);
        adminbutton.setBounds(0, 0, 140, 35);
        loginbuttonpanel.add(adminbutton);

        teacherbutton = new JButton("Teacher");
        teacherbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeButton(teacherbutton);
                disableButton(studentbutton);
                disableButton(adminbutton);
                teacherchanging = true;
                adminchanging = false;
                studentchanging = false;
                timer.start();
            }
        });
        this.buttonStyle(teacherbutton);
        teacherbutton.setBounds(140, 0, 140, 35);
        loginbuttonpanel.add(teacherbutton);

        studentbutton = new JButton("Student");
        studentbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeButton(studentbutton);
                disableButton(teacherbutton);
                disableButton(adminbutton);
                studentchanging = true;
                adminchanging = false;
                teacherchanging = false;
                timer.start();
            }
        });
        studentbutton.setBounds(280, 0, 140, 35);
        this.buttonStyle(studentbutton);
        loginbuttonpanel.add(studentbutton);
        activeButton(studentbutton);

        underlinelabel = new JLabel("");
        underlinelabel.setBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(23, 182, 11)));
        underlinelabel.setBounds(underlinelabelx, 37, underlinelabelwidth, 4);
        loginbuttonpanel.add(underlinelabel);

        bgimagelabel = new JLabel("image");
        bgimagelabel.setBounds(0, 11, 1380, 683);
        contentPane.add(bgimagelabel);
        this.setBackgroundImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!adminchanging && !studentchanging && !teacherchanging) {
            imagenumber++;
            if (imagenumber > 6) {
                imagenumber = 1;
            }
            this.setBackgroundImage();
        }
        if (adminchanging) {
            if (adminpanelx == 500) {
                adminchanging = false;
                timer.stop();
            }
            else {
                adminpanelx += 50;
                studentpanelx += 50;
                teacherpanelx += 50;
                underlinelabelx -= 5;
            }
        }
        else if (teacherchanging) {
            if (teacherpanelx == 500) {
                teacherchanging = false;
                timer.stop();
            }
            else {
                if (teacherpanelx > 500) {
                    adminpanelx -= 50;
                    studentpanelx -= 50;
                    teacherpanelx -= 50;
                    underlinelabelx += 5;
                }
                else {
                    adminpanelx += 50;
                    studentpanelx += 50;
                    teacherpanelx += 50;
                    underlinelabelx -= 5;
                }
            }
        }
        else if (studentchanging) {
            if (studentpanelx == 500) {
                studentchanging = false;
                timer.stop();
            }
            else {
                adminpanelx -= 50;
                studentpanelx -= 50;
                teacherpanelx -= 50;
                underlinelabelx += 5;
            }
        }
        studentloginpanel.setLocation(studentpanelx, studentpanely);
        teacherloginpanel.setLocation(teacherpanelx, teacherpanely);
        adminloginpanel.setLocation(adminpanelx, adminpanely);
        underlinelabel.setLocation(underlinelabelx, underlinelabel.getY());
        this.repaint();
    }

    public void buttonStyle(JButton button) {
        button.setFocusable(true);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setBackground(Color.black);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
    }

    public void activeButton(JButton button) {
        button.setForeground( new Color(23, 182, 11));
    }

    public void disableButton(JButton button) {
        button.setForeground(Color.white);
    }

    public void setBackgroundImage() {
        try {
            //System.out.println(imagenumber);
            Image image = ImageIO.read(new File(".//assets//background image//backgroundimage" + imagenumber + ".jpg"));
            bgimagelabel.setIcon(new ImageIcon(image.getScaledInstance(bgimagelabel.getWidth(), bgimagelabel.getHeight(), Image.SCALE_SMOOTH)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeall(){

    }
}
