package com.tetris.playfield;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Shape {

    private BufferedImage block;
    private int[][] coords;
    private Board board;
    private int x, y;

    private int color;

    public Shape(BufferedImage block, int[][] coords, Board board, int color) {
        this.block = block;
        this.coords = coords;
        this.board = board;
        this.color = color;
    }

    public Shape(Shape shape) {
    }

    // Tô màu
    public void render(Graphics g) {
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[row].length; col++) {
                if (coords[row][col] != 0)
                    g.drawImage(block, col * board.getBlockSize() + x * board.getBlockSize(),
                            row * board.getBlockSize() + y * board.getBlockSize(), null);
            }
        }
    }

    public BufferedImage getBlock() {
        return block;
    }

    public int[][] getCoords() {
        return coords;
    }

    public int getColor() {
        return color;
    }
}