package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameBoard extends JPanel {
    private int blockSize;
    private static int player;
    private static Random random = new Random();
    private static boolean canDraw = false;
    private int[][] tictac = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
    };


    public GameBoard(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(50, 50, 50));
        this.blockSize = width / 3;
        player = random.nextInt(2);
        this.addMouseListener(new MouseListener());


//        this.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                System.out.println(e.getX() / blockSize + ", " + e.getY() / blockSize);
//            }
//        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        drawGrid(g2d);
//        for (int i = 0; i < tictac.length; i++){
//            for (int j = 0; j < tictac[i].length; j++){
//
//            }
//        }

    }

    private void drawGrid(Graphics2D g){
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.WHITE);
        for (int i = 0; i < 3; i++){
            g.drawLine(i * blockSize, 0, i * blockSize, this.getHeight());
        }
        for (int i = 0; i < 3; i++){
            g.drawLine(0, i * blockSize, this.getWidth(), i * blockSize);
        }
    }

    private void drawXO(int x, int y, Graphics2D g){

        int offset = 10;
        g.setStroke(new BasicStroke(4));
        g.setColor(Color.WHITE);

        if(player == 0){
            // draw X
            g.drawLine(blockSize * x + offset, blockSize * y + offset, blockSize * (x+1) - offset, blockSize * (y+1) - offset);
            g.drawLine(blockSize * (x+1) - offset, blockSize * y + offset, blockSize * x + offset, blockSize * (y+1) - offset);
        }
        else if(player == 1){
            // draw O
            g.drawOval(blockSize * x + offset, blockSize * y + offset, blockSize - offset*2, blockSize - offset*2);
        }


    }

    private void resetTictactoe(){
        tictac = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                tictac[i][j] = -1;
            }
        }
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawGrid(g2d);
    }

    private class MouseListener extends MouseAdapter{

        private boolean checkWin(){

            for (int i = 0; i < tictac.length; i++){
                // check horizontal
                if(tictac[i][0] == tictac[i][1] && tictac[i][0] == tictac[i][2]
                    && tictac[i][0] != -1 && tictac[i][1] != -1 && tictac[i][2] != -1
                ){
                    return true;
                }

                // check vertical
                if(tictac[0][i] == tictac[1][i] && tictac[0][i] == tictac[2][i]
                        && tictac[0][i] != -1 && tictac[1][i] != -1 && tictac[2][i] != -1
                ) {
                    return true;
                }
            }
            // check diagonal
            if(tictac[0][0] == tictac[1][1] && tictac[0][0] == tictac[2][2]
            && tictac[0][0] != -1 && tictac[1][1] != -1 && tictac[2][2] != -1){
                return true;
            }

            if(tictac[0][2] == tictac[1][1] && tictac[0][2] == tictac[2][0]
                    && tictac[0][2] != -1 && tictac[1][1] != -1 && tictac[2][0] != -1){
                return true;
            }

            return false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(tictac[e.getY() / blockSize][e.getX() / blockSize] != -1) {
                return;
            }
            Graphics2D g2d = (Graphics2D) getGraphics();
            drawXO(e.getX() / blockSize, e.getY() / blockSize, g2d);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(tictac[e.getY() / blockSize][e.getX() / blockSize] != -1) {
                return;
            }
            String p = (player == 0) ? "X" : "O";
            tictac[e.getY() / blockSize][e.getX() / blockSize] = player;
            if(player == 0){
                player = 1;
            }
            else if(player == 1){
                player = 0;
            }

            if(checkWin()){
                JOptionPane.showMessageDialog(null, "Player " + p + " win!");
                resetTictactoe();
            }
        }

    }
}
