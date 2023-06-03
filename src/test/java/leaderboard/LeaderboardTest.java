package leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.impl.LeaderboardImpl;
import it.unibo.jumpig.model.impl.ScoreImpl;

/**
 * Class to test the correctness of LeaderboardImpl.
 * {@link it.unibo.jumpig.model.impl.LeaderboardImpl}
 */
class LeaderboardTest {

    private static final int HEIGHT_SCORE = 100;
    private static final int COINS = 30;
    private static final int NUM_SCORES = 13;
    private static final int TOP_10 = 10;
    private static final Random RANDOM = new Random();

    @Test
    void testAddLeaderboard() {
        final Leaderboard leaderboard = new LeaderboardImpl();
        leaderboard.addScore(new ScoreImpl("Alex", HEIGHT_SCORE, COINS));
        assertEquals("Alex", leaderboard.getScores().get(0).getUsername());
        assertEquals(HEIGHT_SCORE, leaderboard.getScores().get(0).getHeightScore());
        assertEquals(COINS, leaderboard.getScores().get(0).getCoins());
    }

    @Test
    void testCopy() {
        final Leaderboard leaderboard = new LeaderboardImpl();
        leaderboard.addScore(new ScoreImpl("Daniel", HEIGHT_SCORE / 2, COINS));
        final Leaderboard copy = leaderboard.copy();
        assertNotEquals(leaderboard, copy);
        final Leaderboard copy2 = leaderboard;
        assertEquals(leaderboard, copy2);
    }

    /*
     * Check if the number of scores is limited to 10.
     */
    @Test
    void testTop10() {
        final Leaderboard leaderboard = new LeaderboardImpl();
        this.createLeaderboard(leaderboard);
        assertEquals(TOP_10, leaderboard.getScores().stream().count());
    }

    private void createLeaderboard(final Leaderboard leaderboard) {
        for (int i = 0; i < NUM_SCORES; i++) {
            leaderboard.addScore(new ScoreImpl("a" + Integer.toString(i), RANDOM.nextInt(), RANDOM.nextInt()));
        }
    }
}
