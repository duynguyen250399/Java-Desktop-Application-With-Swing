package tictactoe;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    private int width;
    private int height;
    private String title;
    private GameBoard board;

    public GameScreen(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        this.setTitle(this.title);
        board = new GameBoard(this.width, this.height);
        this.add(board);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }



}
