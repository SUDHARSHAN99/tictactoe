package com.tictactoe;

import com.tictactoe.controllers.GameController;
import com.tictactoe.exceptions.InvalidBotCountException;
import com.tictactoe.exceptions.InvalidMoveException;
import com.tictactoe.exceptions.InvalidNumberOfPlayersException;
import com.tictactoe.models.Game;
import com.tictactoe.models.GameState;
import com.tictactoe.models.Player;
import com.tictactoe.models.Symbol;
import com.tictactoe.strategies.winningplayingstrategy.ColWinningStrategy;
import com.tictactoe.strategies.winningplayingstrategy.DiagonalWinningStrategy;
import com.tictactoe.strategies.winningplayingstrategy.GameWinningStrategy;
import com.tictactoe.strategies.winningplayingstrategy.RowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException, InvalidNumberOfPlayersException, InvalidMoveException {
        GameController gameController =  new GameController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter game dimension");
        int dimension = scanner.nextInt();

        List<Player> players = List.of(
                new Player("Deepak", new Symbol('X')),
                new Player("Umes", new Symbol('O'))
        );
        List<GameWinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        Game game = gameController.startGame(dimension, players, winningStrategies);

        while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {
            gameController.displayBoard(game);

            System.out.println("Do you want to undo ?");
            String moveType = scanner.next();

            if (moveType.equalsIgnoreCase("y")) {
                gameController.undo(game);
                continue;
            }

            gameController.makeMove(game);
        }

        //While loop will be over if the game has ENDED or game has DRAWN.
        System.out.println("Game finished");
        gameController.displayBoard(game);
        if (gameController.getGameState(game).equals(GameState.ENDED)) {
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        } else {
            System.out.println("Game DRAWN");
        }
    }
}
