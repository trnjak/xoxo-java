import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class VSPlayer extends JFrame implements ActionListener {
    Color bg = new Color(0x546A76), fg = new Color(0xFAD4D8);

    JButton[][] btns = new JButton[3][3];
    JLabel xScore = new JLabel(), oScore = new JLabel();
    int move = 0;
    int x1 = 0, o1 = 0;

    public VSPlayer(int x, int o) {
        x1 = x;
        o1 = o;
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setTitle("xoxo");
        this.setSize(289, 324);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(bg);

        xScore.setText("X: " + Integer.toString(x));
        oScore.setText("O: " + Integer.toString(o));

        xScore.setBounds(24, 192, 128, 128);
        oScore.setBounds(184, 192, 128, 128);

        xScore.setForeground(fg);
        oScore.setForeground(fg);

        xScore.setFont(new Font("Consolas", Font.BOLD, 16));
        oScore.setFont(new Font("Consolas", Font.BOLD, 16));

        this.add(xScore);
        this.add(oScore);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                btns[i][j] = new JButton();
            }
        }

        btns[0][0].setBounds(24, 16, 64, 64);
        btns[0][1].setBounds(104, 16, 64, 64);
        btns[0][2].setBounds(184, 16, 64, 64);

        btns[1][0].setBounds(24, 96, 64, 64);
        btns[1][1].setBounds(104, 96, 64, 64);
        btns[1][2].setBounds(184, 96, 64, 64);

        btns[2][0].setBounds(24, 176, 64, 64);
        btns[2][1].setBounds(104, 176, 64, 64);
        btns[2][2].setBounds(184, 176, 64, 64);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                btns[i][j].setBackground(fg);
                btns[i][j].setForeground(bg);

                btns[i][j].setBorderPainted(false);
                btns[i][j].setFocusPainted(false);

                btns[i][j].addActionListener(this);

                btns[i][j].setFont(new Font("Consolas", Font.BOLD, 16));

                this.add(btns[i][j]);
            }
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(e.getSource() == btns[i][j]) {
                    if(move % 2 == 0) {
                        btns[i][j].setText("X");
                        btns[i][j].removeActionListener(this); 
                        if(checkWin(btns, i, j, "X", move) == 1) {
                            JOptionPane.showMessageDialog(null, "X WINS!");
                            x1++;
                            new VSPlayer(x1, o1);
                            this.dispose();
                        }
                        else if(checkWin(btns, i, j, "X", move) == 0) {
                            JOptionPane.showMessageDialog(null, "DRAW!");
                            new VSPlayer(x1, o1);
                            this.dispose();
                        }
                        else {
                            move++;
                        }
                    }
                    else {
                        btns[i][j].setText("O");
                        btns[i][j].removeActionListener(this); 
                        if(checkWin(btns, i, j, "O", move) == 1) {
                            JOptionPane.showMessageDialog(null, "O WINS!");
                            o1++;
                            new VSPlayer(x1, o1);
                            this.dispose();
                        }
                        else if(checkWin(btns, i, j, "O", move) == 0) {
                            JOptionPane.showMessageDialog(null, "DRAW!");
                            new VSPlayer(x1, o1);
                            this.dispose();
                        }
                        else {
                            move++;
                        }
                    }
                }
            }
        }
    }

    public static int checkWin(JButton[][] btns, int x, int y, String s, int move) {
        String[][] board = new String[3][3];
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                board[i][j] = btns[i][j].getText();
            }
        }
        for(int i = 0; i < 3; i++){
            if(board[x][i] != s)
                break;
            if(i == 3-1){
                return 1;
            }
        }
        for(int i = 0; i < 3; i++){
            if(board[i][y] != s)
                break;
            if(i == 3-1){
                return 1;
            }
        }
        if(x == y){
            for(int i = 0; i < 3; i++){
                if(board[i][i] != s)
                    break;
                if(i == 3-1){
                    return 1;
                }
            }
        }
        if(x + y == 3-1){
            for(int i = 0; i < 3; i++){
                if(board[i][(3-1)-i] != s)
                    break;
                if(i == 3-1){
                    return 1;
                }
            }
        }
        if(move == (Math.pow(3, 2) - 1)){
            return 0;
        }

        return -1;
    }
}