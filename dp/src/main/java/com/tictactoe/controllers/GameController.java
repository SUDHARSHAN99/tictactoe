package com.tictactoe.controllers;

import com.tictactoe.exceptions.InvalidBotCountException;
import com.tictactoe.exceptions.InvalidMoveException;
import com.tictactoe.exceptions.InvalidNumberOfPlayersException;
import com.tictactoe.models.Game;
import com.tictactoe.models.GameState;
import com.tictactoe.models.Player;
import com.tictactoe.strategies.winningplayingstrategy.GameWinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<GameWinningStrategy> winningStategies) throws InvalidBotCountException, InvalidNumberOfPlayersException {
        return Game.getBuilder().setDimension(3)
                .setPlayers(players)
                .setWinningStrategies(winningStategies)
                .build();
    }

    public  void makeOver(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game){
        return  game.getGameState();
    }

    public  void undo(Game game){
        game.undo();
    }

    public  void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public  Player getWinner(Game game){
        return game.getWinner();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }
}
