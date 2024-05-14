package com.tictactoe;
import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;

public class Game extends Board {
    protected Board newBoard;
    private final Player cpuPlayer;
    private final Player userPlayer;
    private HashSet<Integer> chosenPositions; // Store chosen positions

    public Game(){
        newBoard = new Board();
        cpuPlayer = new Player("cpu");
        userPlayer = new Player("player");
        chosenPositions = new HashSet<>(); // Initialize the HashSet
    }

    public void start(){
        while(true){
            newBoard.displayBoard(); // Display the board
            userPlay();
            if(checkWinner(userPlayer)) {
                System.out.println("Congratulations! You win!");
                break;
            }
            cpuPlay();
            if(checkWinner(cpuPlayer)) {
                System.out.println("Sorry, you lose!");
                break;
            }
        }
        System.out.println("Wining Board");
        newBoard.displayBoard();

        if(checkWinner(cpuPlayer)) {
            System.out.println("Sorry, you lose!");
        }else {
            System.out.println("Congratulations! You win!");
        }
    }

    private void cpuPlay(){
        Random rand = new Random();
        int cpuPos;
        do {
            cpuPos = rand.nextInt(9) + 1;
        } while (!isValidPosition(cpuPos)); // Keep generating random positions until a valid one is found
        place(cpuPos, cpuPlayer);
    }

    private void userPlay(){
        int pos;
        do {
            System.out.print("Enter your place : ");
            Scanner element = new Scanner(System.in);
            pos = element.nextInt();
        } while (!isValidPosition(pos)); // Keep prompting user until a valid position is entered
        place(pos, userPlayer);
    }

    private boolean isValidPosition(int pos) {
        if (pos < 1 || pos > 9) return false; // Check if position is within valid range
        if (chosenPositions.contains(pos)) return false; // Check if position has already been chosen
        chosenPositions.add(pos); // Add the chosen position to the set of chosen positions
        return true;
    }

    public void place(int p, Player curPlayer){
        char symbol = ' ';
        if (curPlayer.type.equals("player")){
            symbol ='x';
        } else if (curPlayer.type.equals("cpu")){
            symbol = 'o';
        } else {
            return; // Invalid player type, return without placing symbol
        }
        switch (p) {
            case 1 -> newBoard.board[0][0] = symbol;
            case 2 -> newBoard.board[0][1] = symbol;
            case 3 -> newBoard.board[0][2] = symbol;
            case 4 -> newBoard.board[1][0] = symbol;
            case 5 -> newBoard.board[1][1] = symbol;
            case 6 -> newBoard.board[1][2] = symbol;
            case 7 -> newBoard.board[2][0] = symbol;
            case 8 -> newBoard.board[2][1] = symbol;
            case 9 -> newBoard.board[2][2] = symbol;
            default -> {
                break;
            }
        }
    }

    // Method to check if a player has won
    private boolean checkWinner(Player player) {
        char symbol = player.type.equals("player") ? 'x' : 'o'; // Get the symbol for the player
        // Check rows, columns, and diagonals for winning combinations
        for (int i = 0; i < 3; i++) {
            if (newBoard.board[i][0] == symbol && newBoard.board[i][1] == symbol && newBoard.board[i][2] == symbol) return true; // Check rows
            if (newBoard.board[0][i] == symbol && newBoard.board[1][i] == symbol && newBoard.board[2][i] == symbol) return true; // Check columns
        }
        if (newBoard.board[0][0] == symbol && newBoard.board[1][1] == symbol && newBoard.board[2][2] == symbol) return true; // Check diagonal
        return newBoard.board[0][2] == symbol && newBoard.board[1][1] == symbol && newBoard.board[2][0] == symbol; // Check anti-diagonal
// No winning combination found
    }
}
