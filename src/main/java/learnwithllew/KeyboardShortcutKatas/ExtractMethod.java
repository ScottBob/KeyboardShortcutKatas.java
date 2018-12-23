package learnwithllew.KeyboardShortcutKatas;

import com.spun.util.logger.SimpleLogger;

// This is an exercise to write some code that needs to be refactored using a
// single approach over and over in the interests of practicing using the
// keyboard to do refactoring.

// This exercise focuses on the "Extract Method" refactoring operation.
// It may also require the "Rename" refactoring operation as well.
public class ExtractMethod {
    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("Usage: ExtractMethod <row 1 string of spaces and \"#\"s> <row 2 string of spacess and \"#\"s> ...");
            System.exit(0);
        }
        int width = args[0].length();
        int height = args.length;
        boolean[][] board = new boolean[width][height];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                board[x][y] = args[y].charAt(x) == '#';
            }
        }

        ExtractMethod em = new ExtractMethod();
        int width1 = board.length;
        int height1 = board[0].length;
        boolean[][] oldBoard = new boolean[width1][height1];

        int generationCount = 0;

        boolean stillAlive = true;
        while (stillAlive) {
            // Render the board
            StringBuffer out = new StringBuffer();
            for (int j = 0; j < height1; ++j) {
                for (int i = 0; i < width1; ++i) {
                    out.append(board[i][j] ? "#" : ".").append(" ");
                }
                out.append(System.lineSeparator());
            }
            SimpleLogger.message(out.toString());

            // Make a copy of the board
            for (int i = 0; i < width1; ++i) {
                for (int j = 0; j < height1; ++j) {
                    oldBoard[i][j] = board[i][j];
                }
            }

            int neighborCount = 0;
            for (int i = 0; i < width1; ++i) {
                for (int j = 0; j < height1; ++j) {
                    // Rule 1: Any live cell with fewer than two live neighbors dies, as if by underpopulation.
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (!(k == 0 && l == 0)) {
                                int neighborX = i + k;
                                int neighborY = j + l;
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width1 && neighborY < height1) {
                                    neighborCount += oldBoard[neighborX][neighborY] ? 1 : 0;
                                }
                            }
                        }
                    }
                    if (oldBoard[i][j]) {
                        board[i][j] = !(neighborCount == 0 || neighborCount == 1);
                    }
                    // Rule 2: Any live cell with two or three live neighbors lives on to the next generation.
        neighborCount = 0;
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (!(k == 0 && l == 0)) {
                                int neighborX = i + k;
                                int neighborY = j + l;
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width1 && neighborY < height1) {
                                    neighborCount += oldBoard[neighborX][neighborY] ? 1 : 0;
                                }
                            }
                        }
                    }
                    if (oldBoard[i][j]) {
                        board[i][j] = neighborCount == 2 || neighborCount == 3;
                    }
                    // Rule 3: Any live cell with more than three live neighbors dies, as if by overpopulation.
        neighborCount = 0;
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (!(k == 0 && l == 0)) {
                                int neighborX = i + k;
                                int neighborY = j + l;
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width1 && neighborY < height1) {
                                    neighborCount += oldBoard[neighborX][neighborY] ? 1 : 0;
                                }
                            }
                        }
                    }
                    if (oldBoard[i][j]) {
                        if (neighborCount > 3)
                        board[i][j] = false;
                    }
                    // Rule 4: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        neighborCount = 0;
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (!(k == 0 && l == 0)) {
                                int neighborX = i + k;
                                int neighborY = j + l;
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width1 && neighborY < height1) {
                                    neighborCount += oldBoard[neighborX][neighborY] ? 1 : 0;
                                }
                            }
                        }
                    }
                    if (!oldBoard[i][j]) {
                        board[i][j] = neighborCount == 3;
                    }
                }
            }
            String s = "======================== Generation: " + ++generationCount;
            SimpleLogger.message(s);
            int liveCount = 0;
            for (int i = 0; i < width1; ++i) {
                for (int j = 0; j < height1; ++j) {
                    if (board[i][j])
                    ++liveCount;
                }
            }
            // When everyone dies, end the game.
            if (liveCount == 0) {
                SimpleLogger.message("Everyone died! :'(");
                stillAlive = false;
            }

            // If this board is the same as the last board, we're done
            boolean same = true;
            for (int i = 0; i < width1; ++i) {
                for (int j = 0; j < height1; ++j) {
                    if (oldBoard[i][j] != board[i][j]) {
                        same = false;
                        break;
                    }
                }
            }
            if (same) {
                SimpleLogger.message("Board is in Still Life state. Game stopping.");
                stillAlive = false;
            }
        }
    }
}
