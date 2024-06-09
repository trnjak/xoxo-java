import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class VSComp extends JFrame implements ActionListener {
    Color bg = new Color(0x546A76), fg = new Color(0xFAD4D8);

    JButton[] btns = new JButton[9];
    JLabel xScore = new JLabel(), oScore = new JLabel();
    public static final Random RANDOM = new Random();
    int move = 0;
    int x1 = 0, o1 = 0;

    public VSComp(int x, int o) {
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

        for(int i = 0; i < 9; i++) {
            btns[i] = new JButton();
        }

        btns[0].setBounds(24, 16, 64, 64);
        btns[1].setBounds(104, 16, 64, 64);
        btns[2].setBounds(184, 16, 64, 64);

        btns[3].setBounds(24, 96, 64, 64);
        btns[4].setBounds(104, 96, 64, 64);
        btns[5].setBounds(184, 96, 64, 64);

        btns[6].setBounds(24, 176, 64, 64);
        btns[7].setBounds(104, 176, 64, 64);
        btns[8].setBounds(184, 176, 64, 64);

        for(int i = 0; i < 9; i++) {
            btns[i].addActionListener(this);

            btns[i].setBackground(fg);
            btns[i].setForeground(bg);

            btns[i].setBorderPainted(false);
            btns[i].setFocusPainted(false);

            btns[i].setFont(new Font("Consolas", Font.BOLD, 16));

            this.add(btns[i]);
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++) {
            if(e.getSource() == btns[i]) {
                if(move % 2 == 0) {
                    btns[i].setText("X");
                    btns[i].removeActionListener(this); 
                    if(checkForWinner(getBoard(btns)) == 1) {
                        JOptionPane.showMessageDialog(null, "X WINS!");
                        x1++;
                        new VSComp(x1, o1);
                        this.dispose();
                    }
                    else if(isFull(getBoard(btns))) {
                        JOptionPane.showMessageDialog(null, "DRAW!");
                        new VSComp(x1, o1);
                        this.dispose();
                    }
                    else {
                        int m = doMove(getBoard(btns));
                        btns[m].setText("O");
                        btns[m].removeActionListener(this);
                        if(checkForWinner(getBoard(btns)) == 2) {
                            JOptionPane.showMessageDialog(null, "O WINS!");
                            o1++;
                            new VSComp(x1, o1);
                            this.dispose();
                        }
                        else if(isFull(getBoard(btns))) {
                            JOptionPane.showMessageDialog(null, "DRAW!");
                            new VSComp(x1, o1);
                            this.dispose();
                        }
                        move += 2;
                    }
                }
            }
        }
    }

    public int doMove(int[] board) {
        for(int i = 0; i < 9; i++) {
            if(board[i] == 0) {
                board[i] = 2;
                if(checkForWinner(board) == 2) {
                    board[i] = 0;
                    return i;
                }
                board[i] = 0;
            }
        }
        for(int i = 0; i < 9; i++) {
            if(board[i] == 0) {
                board[i] = 1;
                if(checkForWinner(board) == 1) {
                    board[i] = 0;
                    return i;
                }
                board[i] = 0;
            }
        }
        ArrayList<Integer> emptySpots = new ArrayList<>();
        if(board[4] == 0) {return 4;}
        for(int i : new int[]{0, 2, 6, 8}) {
            if(board[i] == 0) {emptySpots.add(i);}
        }
        if(!emptySpots.isEmpty()) {
            return emptySpots.get(RANDOM.nextInt(emptySpots.size()));
        }
        for(int i : new int[]{1, 3, 5, 7}) {
            if(board[i] == 0) {emptySpots.add(i);}
        }
        return emptySpots.get(RANDOM.nextInt(emptySpots.size()));
    }

    public int[] getBoard(JButton[] btns) {
        int[] board = new int[9];
        for(int i = 0; i < 9; i++) {
            if(btns[i].getText().equals("X")) {
                board[i] = 1;
            }
            if(btns[i].getText().equals("O")) {
                board[i] = 2;
            }
            if(btns[i].getText().equals("")) {
                board[i] = 0;
            }
        }
        return board;   
    }
	
	private static int checkForWinner(int board[]) {
		int w = checkHorizontally(board);
		if(w != 0) {return w;}
		w = checkVertically(board);
		if(w != 0) {return w;}
		w = checkDiagonally(board,1);
		if(w != 0) {return w;}
		w = checkDiagonally(board,2);
		if(w != 0) {return w;}
		return 0;
	}
	private static int checkVertically(int board[]) {
		for(int x =0; x < 3; x++) {
			boolean b1 = true;
			boolean b2 = true;
			for(int y = 0; y < 3; y++) {
				if(board[y*3+x] != 1) {
					b1 = false;
				}
				if(board[y*3+x] != 2) {
					b2 = false;
				}
			}
			if(b1) {return 1;}
			if(b2) {return 2;}
		}
		return 0;
	}
	private static int checkHorizontally(int board[]) {
		for(int y = 0; y < 3; y++) {
			boolean b1 = true;
			boolean b2 = true;
			for(int x = 0; x < 3; x++) {
				if(board[y*3+x] != 1) {
					b1 = false;
				}
				if(board[y*3+x] != 2) {
					b2 = false;
				}
			}
			if(b1) {return 1;}
			if(b2) {return 2;}
		}
		return 0;
	}
	private static int checkDiagonally(int board[], int color) {
		boolean b1 = true;
		boolean b2 = true;
		for(int x = 0; x < 3; x++) {
			if(board[x*3+x] != color) {b1 = false;}
			if(board[(2-x)*3+x] != color) {b2 = false;}
		}
		if(b1 || b2) return color;
		return 0;
	}
	private static boolean isFull(int[] board) {
		for(int i = 0; i < board.length; i++) {
			if(board[i] == 0) {
				return false;
			}
		}
		return true;
	}
}