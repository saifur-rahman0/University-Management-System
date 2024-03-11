package application.common;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/*
 * Title : HintPasswordField.java
 * Purpose : For giving hint to user in password field
 */
@SuppressWarnings("serial")
public class HintPasswordField extends JPasswordField {
    private final JLabel hintlabel;

    public HintPasswordField(String hint, Color hintforegroundcolor) {
        this(hint);
        hintlabel.setForeground(hintforegroundcolor);
    }

    public HintPasswordField(String hint) {

        hint = hint.trim();

        setForeground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        hintlabel = new JLabel(hint);
        hintlabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        hintlabel.setForeground(new Color(100, 100, 100));
        add(hintlabel, BorderLayout.LINE_START);
        this.getDocument().addDocumentListener(new MyDocumentListener());

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JPasswordField field = (JPasswordField) e.getSource();
                field.setFocusable(true);
            }
        }
        );
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setFocusable(false);
                }
            }
        }
        );
    }

    private class MyDocumentListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            updateLog(e);
        }

        public void removeUpdate(DocumentEvent e) {
            updateLog(e);
        }

        public void changedUpdate(DocumentEvent e) {
            //Plain text components do not fire these events
        }

        public void updateLog(DocumentEvent e) {
            Document doc = e.getDocument();
            hintlabel.setVisible(doc.getLength() == 0);
        }
    }
}
