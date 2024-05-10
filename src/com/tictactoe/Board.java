package com.tictactoe;
public class Board {
    protected char[][] board;
    private final int SIZE = 3;

    public Board() {
        board = new char[SIZE][SIZE];
    }

    public void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("----+---+----");
        }
    }
    // Add methods to check for winner, make moves, and check if the board is full
}
