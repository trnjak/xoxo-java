import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.awt.*;

public class Start extends JFrame implements ActionListener{
    Color bg = new Color(0x546A76), fg = new Color(0xFAD4D8);

    JButton vsP = new JButton("VS Player"), vsC = new JButton("VS Computer");
    JPanel lmao = new JPanel();
    public Start() {
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setTitle("xoxo");
        this.setSize(256, 128);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(bg);

        vsP.setPreferredSize(new Dimension(118, 150));
        vsC.setPreferredSize(new Dimension(118, 150));

        vsP.setFocusPainted(false);
        vsP.setBackground(bg);
        vsP.setForeground(fg);
        vsP.setBorder(new LineBorder(fg));
        vsP.setFont(new Font("Consolas", Font.BOLD, 16));
        vsP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        vsC.setFocusPainted(false);
        vsC.setBackground(bg);
        vsC.setForeground(fg);
        vsC.setBorder(new LineBorder(fg));
        vsC.setFont(new Font("Consolas", Font.BOLD, 16));
        vsC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        vsP.addActionListener(this);
        vsC.addActionListener(this);

        lmao.setLayout(new BorderLayout());
        lmao.add(vsP, BorderLayout.WEST);
        lmao.add(vsC, BorderLayout.EAST);
        lmao.setBackground(bg);
        
        this.add(lmao, BorderLayout.CENTER);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vsP) {
            new VSPlayer(0, 0);
            this.dispose();
        }
        if(e.getSource() == vsC) {
            new VSComp(0, 0);
            this.dispose();
        }
    }
    
}
