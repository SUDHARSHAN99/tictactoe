package com.algos.dp.dfs;

import java.util.Arrays;

public class IslandCounter {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static int countIslands(int[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int islandCount = 0;

        boolean[][] visited = new boolean[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (!visited[row][col] && grid[row][col] == 1) {
                    dfs(grid, row, col, visited);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    private static void dfs(int[][] grid, int row, int col, boolean[][] visited) {
        visited[row][col] = true;

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValidCell(grid, newRow, newCol) && !visited[newRow][newCol] && grid[newRow][newCol] == 1) {
                dfs(grid, newRow, newCol, visited);
            }
        }
    }

    private static boolean isValidCell(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}};

        int islands = countIslands(grid);
        System.out.println("Number of islands: " + islands);
    }
}

