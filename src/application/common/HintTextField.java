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
 * Title : HintTextField.java
 * Purpose : For giving hint to user in text field
 */
@SuppressWarnings("serial")
public class HintTextField extends JTextField {
    private final JLabel hintlabel;

    /**
     *
     */

    public HintTextField(String hint, Color hintforegroundcolor) {
        this(hint);
        hintlabel.setForeground(hintforegroundcolor);
    }

    public HintTextField(String hint) {

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
                JTextField field = (JTextField) e.getSource();
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

//    this.addFocusListener(new FocusAdapter() {  
//  
//      @Override  
//      public void focusGained(FocusEvent e) {  
//        if (getText().equals(hint)) {  
//          setText("");  
//        } else {  
//          setText(getText());  
//          
//        }  
//      }  
//  
//      @Override  
//      public void focusLost(FocusEvent e) {  
//        if (getText().equals(hint)|| getText().length()==0) {  
//          setText(hint);  
//        } else {  
//          setText(getText());  
//        }  
//      }  
//    });  
    }

    private class MyDocumentListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            updateLog(e, "inserted into");
        }

        public void removeUpdate(DocumentEvent e) {
            updateLog(e, "removed from");
        }

        public void changedUpdate(DocumentEvent e) {
            //Plain text components do not fire these events
        }

        public void updateLog(DocumentEvent e, String action) {
            Document doc = e.getDocument();
            hintlabel.setVisible(doc.getLength() == 0);
        }
    }
}
