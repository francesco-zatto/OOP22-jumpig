package leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.impl.LeaderboardImpl;
import it.unibo.jumpig.model.impl.ScoreImpl;

/**
 * Class to test the correctness of LeaderboardImpl.
 * {@link it.unibo.jumpig.model.impl.LeaderboardImpl}
 */
class LeaderboardTest {

    @Test
    void testAddLeaderboard() {
        final Leaderboard leaderboard = new LeaderboardImpl();
        leaderboard.addScore(new ScoreImpl("Alex", 1, 2));
        assertEquals("Alex", leaderboard.getScores().get(0).getUsername());
    }
}
