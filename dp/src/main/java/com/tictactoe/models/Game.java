package com.tictactoe.models;

import com.tictactoe.controllers.GameController;
import com.tictactoe.exceptions.InvalidBotCountException;
import com.tictactoe.exceptions.InvalidMoveException;
import com.tictactoe.exceptions.InvalidNumberOfPlayersException;
import com.tictactoe.strategies.winningplayingstrategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    List<Player> players;
    private GameState gameState;
    List<Move> moves;
    private Player winner;
    private int nextPlayerMove;
    private List<GameWinningStrategy> gameWinningStrategies;

    private Game(int dimension, List<Player> players, List<GameWinningStrategy> winningStrategies){
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMove = 0;
        this.winner = null;
        this.gameWinningStrategies = winningStrategies;
        this.players = players;

    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMove);

        Move currentMove = currentPlayer.executeMove();
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();
        System.out.println(currentPlayer.getName() + " has made a move at row: " + row + " & col: " + col);

        if (!validateMove(currentMove)) {
            throw new InvalidMoveException("Player is trying to make an invalid move");
        }

        nextPlayerMove = (nextPlayerMove + 1) % players.size();
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(cell, currentPlayer);
        moves.add(finalMove);
        //Check if the move made by the player is a winning move or not.

        if (checkWinner(board, finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getSize() * board.getSize()) {
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Board board, Move finalMove) {
        for (GameWinningStrategy winningStrategy : gameWinningStrategies) {
            if(winningStrategy.checkWinner(board,finalMove)){
                return true;
            }
        }
        return false;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            return false;
        }

        System.out.println("hello");
        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void undo() {
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }

    public static class Builder{
        private List<Player> players;
        private List<GameWinningStrategy> winningStrategies;
        private  int dimension;


        public Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<GameWinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<GameWinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws InvalidNumberOfPlayersException, InvalidBotCountException {
            validate();
            return new Game(dimension,players,winningStrategies);
        }

        private void validate() throws InvalidNumberOfPlayersException, InvalidBotCountException {
            if(players.size() != dimension-1){
                throw new InvalidNumberOfPlayersException("Number of Players should be less than 1");
            }
            if(!validateBotCount()){
                throw new InvalidBotCountException("Bot count should be <=1");
            }
        }

        private boolean validateBotCount() {
            int botcount= 0;
            for (Player player : players) {
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botcount +=1;
                }

            }
            return botcount <=1;
        }
    }


}
