// Snake game code taken from: https://zetcode.com/javagames/snake/

package SnakeGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {

        initUI();
    }

    private void initUI() {

        add(new SnakeGame.Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
