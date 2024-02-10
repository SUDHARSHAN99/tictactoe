package com.tictactoe.models;

import java.util.Scanner;

public class Player {

    private long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = PlayerType.HUMAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }


    public Move executeMove() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the row index where you want to make the move: ");
        int row = scanner.nextInt();

        System.out.println("Please enter the col index where you want to make the move: ");
        int col = scanner.nextInt();

        return new Move(new Cell(row, col), this);
    }
}
