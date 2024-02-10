package com.tictactoe.strategies.winningplayingstrategy;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board,Move move);
    void handleUndo(Board board, Move move);


}
