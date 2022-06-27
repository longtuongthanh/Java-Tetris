package com.tetris.playfield;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Board extends JPanel {

    private BufferedImage blocks;
    private final int blockSize = 30;
    private final int boardWidth = 10, boardHeight = 20;
    public int[][] board = new int[boardHeight][boardWidth];

    private BufferedImage[] shapes = new BufferedImage[7];

    // Káº» cÃ¡c Ä‘Æ°á»�ng mÃ u Ä‘en phÃ¢n chia táº¡o thÃ nh Board. (CÃ¡c Ã´ vuÃ´ng Ä‘an nhau áº¥y)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < boardHeight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j < boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardHeight * blockSize);
        }
        

        //Long
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0)
                    g.drawImage(shapes[board[row][col]], col * blockSize,
                            row * blockSize, null);
            }
        }
        //Long
    }

    public Board() {
        // chia bá»©c áº£nh ColorRainbow thÃ nh 7 block vá»›i 7 mÃ u khÃ¡c nhau
        try {
            blocks = ImageIO.read(Board.class.getResource("/com/tetris/playfield/ColorRainbow.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // CÃ¡c Shape gá»“m 7 hÃ¬nh gá»“m hÃ¬nh chá»¯ O, I, S, Z, L, J, T
        // Long: change meaning: shapes -> the image associated with each block
        shapes[0] = blocks.getSubimage(0, 0, blockSize, blockSize); // I shape
        shapes[1] = blocks.getSubimage(blockSize, 0, blockSize, blockSize); // Z shape
        shapes[2] = blocks.getSubimage(blockSize * 2, 0, blockSize, blockSize); // S shape
        shapes[3] = blocks.getSubimage(blockSize * 3, 0, blockSize, blockSize); // J shape
        shapes[4] = blocks.getSubimage(blockSize * 4, 0, blockSize, blockSize); // L shape
        shapes[5] = blocks.getSubimage(blockSize * 5, 0, blockSize, blockSize); // T shape
        shapes[6] = blocks.getSubimage(blockSize * 6, 0, blockSize, blockSize); // O shape
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int[][] getBoard() {
        return board;
    }

}