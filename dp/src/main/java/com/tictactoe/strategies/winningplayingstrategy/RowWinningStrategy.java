package com.tictactoe.strategies.winningplayingstrategy;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;
import com.tictactoe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements  GameWinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> roMap = rowMaps.get(row);

        if (roMap.containsKey(symbol)) {
            roMap.put(symbol, roMap.get(symbol) + 1);
        } else {
            roMap.put(symbol, 1);
        }

        return roMap.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> rowMap = rowMaps.get(col);
        rowMap.put(symbol, rowMap.get(symbol) - 1);
    }
}
