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
    private int[][] board = new int[boardHeight][boardWidth];

    private Shape[] shapes = new Shape[7];

    // Káº» cÃ¡c Ä‘Æ°á»�ng mÃ u Ä‘en phÃ¢n chia táº¡o thÃ nh Board. (CÃ¡c Ã´ vuÃ´ng Ä‘an nhau áº¥y)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < boardHeight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j < boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardHeight * blockSize);
        }
    }

    public Board() {
        // chia bá»©c áº£nh ColorRainbow thÃ nh 7 block vá»›i 7 mÃ u khÃ¡c nhau
        try {
            blocks = ImageIO.read(Board.class.getResource("ColorRainbow.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // CÃ¡c Shape gá»“m 7 hÃ¬nh gá»“m hÃ¬nh chá»¯ O, I, S, Z, L, J, T
        shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][] { { 1, 1, 1, 1 } // I shape
        }, this, 1);
        shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize),
                new int[][] { { 1, 1, 0 }, { 0, 1, 1 } // Z shape
                }, this, 2);
        shapes[2] = new Shape(blocks.getSubimage(blockSize * 2, 0, blockSize, blockSize),
                new int[][] { { 0, 1, 1 }, { 1, 1, 0 } // S shape
                }, this, 3);
        shapes[3] = new Shape(blocks.getSubimage(blockSize * 3, 0, blockSize, blockSize),
                new int[][] { { 1, 1, 1 }, { 0, 0, 1 } // J shape
                }, this, 4);
        shapes[4] = new Shape(blocks.getSubimage(blockSize * 4, 0, blockSize, blockSize),
                new int[][] { { 1, 1, 1 }, { 1, 0, 0 } // L shape
                }, this, 5);
        shapes[5] = new Shape(blocks.getSubimage(blockSize * 5, 0, blockSize, blockSize),
                new int[][] { { 1, 1, 1 }, { 0, 1, 0 } // T shape
                }, this, 6);
        shapes[6] = new Shape(blocks.getSubimage(blockSize * 6, 0, blockSize, blockSize),
                new int[][] { { 1, 1 }, { 1, 1 } // O shape
                }, this, 7);

    }

    public int getBlockSize() {
        return blockSize;
    }

    public int[][] getBoard() {
        return board;
    }

}