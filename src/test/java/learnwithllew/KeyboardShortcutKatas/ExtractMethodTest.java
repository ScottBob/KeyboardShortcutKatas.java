package learnwithllew.KeyboardShortcutKatas;

import com.spun.util.logger.SimpleLogger;
import org.approvaltests.Approvals;
import org.junit.Test;

public class ExtractMethodTest {

    @Test
    public void testDieHard() {
        StringBuffer log = SimpleLogger.logToString();
        ExtractMethod.main(initDiehardBoard());
        ExtractMethod.main(initBHeptominoBoard());
        Approvals.verify(log);
    }

    // Gets to a static state after 149 generations
    private String[] initBHeptominoBoard() {
        String board[] = new String[40];
        String fortySpaces = "                                        ";
        for (int i = 0; i < board.length; ++i) {
            board[i] = fortySpaces;
        }
        board[6] = "                        # ##            ";
        board[7] = "                        ###             ";
        board[8] = "                         #              ";
        return board;
    }

    // Dies after 130 generations
    private String[] initDiehardBoard() {
        String board[] = new String[30];
        String thirtySpaces = "                              ";
        for (int i = 0; i < board.length; ++i) {
            board[i] = thirtySpaces;
        }
        board[11] = "                 #            ";
        board[12] = "           ##                 ";
        board[13] = "            #   ###           ";
        return board;
    }
}