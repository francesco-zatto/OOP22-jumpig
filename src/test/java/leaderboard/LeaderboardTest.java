package leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.api.Score;
import it.unibo.jumpig.model.impl.LeaderboardImpl;
import it.unibo.jumpig.model.impl.ScoreImpl;

/**
 * Class that tests the correctness of LeaderboardImpl.
 * {@link it.unibo.jumpig.model.impl.LeaderboardImpl}
 */
class LeaderboardTest {

    private static final int HEIGHT_SCORE = 1024;
    private static final int COINS = 20;

    Leaderboard createLeaderboard() {
        return new LeaderboardImpl();
    }

    Iterator<Score> createIteratorOfScores(final List<Score> scores) {
        return scores.iterator();
    }

    @Test
    void testAddScore() {
        final Leaderboard leaderboard = this.createLeaderboard();
        leaderboard.addScore(new ScoreImpl("Alex", HEIGHT_SCORE, COINS / 2));
        leaderboard.addScore(new ScoreImpl("Daniel", HEIGHT_SCORE * 2, COINS));
        leaderboard.addScore(new ScoreImpl("Harry", HEIGHT_SCORE / 2, COINS * 2));
        Iterator<Score> leaderboardIterator = this.createIteratorOfScores(leaderboard.getScores());
        assertEquals("Alex", leaderboardIterator.next().getUsername());
        assertEquals(HEIGHT_SCORE * 2, leaderboardIterator.next().getHeightScore());
        assertEquals(COINS * 2, leaderboardIterator.next().getCoins());
    }
}
