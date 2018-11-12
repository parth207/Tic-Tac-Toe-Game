package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Parth Vaishnavi on 23-Jul-17.
 */
public class TTT extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[Board_Size][Board_Size];
    public static final int Board_Size = 3;
    private boolean crossturn = false;

    public enum Gamestatus {
        X_Wins, Z_Wins, Tie, Incomplete
    }

    public TTT() {
        super.setTitle("Tic Tac Toe");
        super.setResizable(false);
        super.setSize(600, 600);

        GridLayout layout = new GridLayout(Board_Size, Board_Size);
        super.setLayout(layout);

        Font font = new Font("Comic Sans", 1, 120);

        for (int row = 0; row < Board_Size; row++) {
            for (int col = 0; col < Board_Size; col++) {
                JButton button = new JButton("");
                buttons[row][col] = button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }
        }
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        makemove(button);

        Gamestatus gs = getGamestatus();

        if (gs != Gamestatus.Incomplete) {
            declarewinner(gs);
        }
    }

    private void declarewinner(Gamestatus gs) {
        if (gs == Gamestatus.X_Wins) {
            JOptionPane.showMessageDialog(this, "X wins");
        } else if (gs == Gamestatus.Z_Wins) {
            JOptionPane.showMessageDialog(this, "Z wins");
        } else if (gs == Gamestatus.Tie) {
            JOptionPane.showMessageDialog(this, "Tie");
        }

        int choice = JOptionPane.showConfirmDialog(this, "Restart?");
        if (choice == JOptionPane.YES_OPTION) {
            for (int row = 0; row < Board_Size; row++) {
                for (int col = 0; col < Board_Size; col++) {
                    buttons[row][col].setText("");
                }
            }
            crossturn = false;
        } else {
            super.dispose();
        }

    }

    private void makemove(JButton button) {
        if (button.getText().length() > 0) {
            JOptionPane.showMessageDialog(this, "invalid move");
            return;
        }

        if (crossturn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        crossturn = !crossturn;
    }

    private Gamestatus getGamestatus() {
        String text1 = "";
        String text2 = "";

        int row = 0;
        int col = 0;
        while (row < Board_Size - 1) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row + 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            col++;
            row++;
        }
        if (row == Board_Size - 1) {
            if (text1.equals("X")) {
                return Gamestatus.X_Wins;
            } else {
                return Gamestatus.Z_Wins;
            }
        }

        row = 0;
        col = Board_Size - 1;
        while (row < Board_Size - 1) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row + 1][col - 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            col--;
            row++;
        }
        if (row == Board_Size - 1) {
            if (text1.equals("X")) {
                return Gamestatus.X_Wins;
            } else {
                return Gamestatus.Z_Wins;
            }
        }

        row = 0;
        while (row < Board_Size - 1) {
            col = 0;
            while (col < Board_Size - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row][col + 1].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                col++;
            }
            if (col == Board_Size - 1) {
                if (text1.equals("X")) {
                    return Gamestatus.X_Wins;
                } else {
                    return Gamestatus.Z_Wins;
                }
            }
            row++;
        }

        col = 0;
        while (col < Board_Size) {
            row = 0;
            while (row < Board_Size - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row + 1][col].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row++;
            }
            if (row == Board_Size - 1) {
                if (text1.equals("X")) {
                    return Gamestatus.X_Wins;
                } else {
                    return Gamestatus.Z_Wins;
                }
            }
            col++;
        }

        for (row = 0; row < Board_Size; row++) {
            for (col = 0; col < Board_Size; col++) {
                if (buttons[row][col].getText().length() == 0) {
                    return Gamestatus.Incomplete;
                }
            }
        }
        return Gamestatus.Tie;
    }

}
