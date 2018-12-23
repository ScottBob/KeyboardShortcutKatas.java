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
        em.live(board);
    }


    // Conway's Game of Life
    private void live(boolean[][] board) {
//        board = InitBHeptominoBoard();
//        board = InitDiehardBoard();
        int width = board.length;
        int height = board[0].length;
        boolean[][] oldBoard = new boolean[width][height];

        int generationCount = 0;

        boolean stillAlive = true;
        while (stillAlive) {
            // Render the board
//            print(Grid.print(width, height, this::showCell));
            print(dumpGrid(board));

            // Make a copy of the board
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    oldBoard[i][j] = board[i][j];
                }
            }

            int neighborCount = 0;
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    // Rule 1: Any live cell with fewer than two live neighbors dies, as if by underpopulation.
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            if (!(k == 0 && l == 0)) {
                                int neighborX = i + k;
                                int neighborY = j + l;
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width && neighborY < height) {
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
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width && neighborY < height) {
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
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width && neighborY < height) {
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
                                if (neighborX >= 0 && neighborY >= 0 && neighborX < width && neighborY < height) {
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
            print("======================== Generation: " + ++generationCount);
            int liveCount = 0;
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    if (board[i][j])
                    ++liveCount;
                }
            }
            // When everyone dies, end the game.
            if (liveCount == 0) {
                print("Everyone died! :'(");
                stillAlive = false;
            }

            // If this board is the same as the last board, we're done
            boolean same = true;
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    if (oldBoard[i][j] != board[i][j]) {
                        same = false;
                        break;
                    }
                }
            }
            if (same) {
                print("Board is in Still Life state. Game stopping.");
                stillAlive = false;
            }
        }
    }

    private void print(String s) {
        SimpleLogger.message(s);
    }

    private String dumpGrid(boolean[][] board) {
        int height = board[0].length;
        int width = board.length;
        StringBuffer out = new StringBuffer();
            for (int j = 0; j < height; ++j) {
                for (int i = 0; i < width; ++i) {
                out.append(board[i][j] ? "#" : ".").append(" ");
            }
            out.append(System.lineSeparator());
        }
        return out.toString();
    }
}
