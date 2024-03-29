package it.unibo.jumpig.model.impl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.api.LeaderboardLoader;
import it.unibo.jumpig.model.api.Score;

/**
 * Class that creates the leaderboard for the game.
 */
public class LeaderboardImpl implements Leaderboard {

    private static final int TOP_SCORES = 10;
    private final LeaderboardLoader loader = new LeaderboardLoaderImpl();
    private final List<Score> scoreLeaderboard;

    /**
     * Public constructor for an empty leaderboard.
     */
    public LeaderboardImpl() {
        this.scoreLeaderboard = new LinkedList<>();
    }

    /**
     * Private constructor for defensive copy.
     * @param scoreLeaderboard list of scores
     */
    private LeaderboardImpl(final List<Score> scoreLeaderboard) {
        this.scoreLeaderboard = scoreLeaderboard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getScores() {
        return this.scoreLeaderboard
        .stream()
        .collect(Collectors.toMap(
            Score::getUsername, 
            Function.identity(), 
            (s1, s2) -> s1.getHeightScore() > s2.getHeightScore() ? s1 : s2
            )
        )
        .values()
        .stream()
        .sorted(Comparator.comparingInt(
            Score::getHeightScore)
            .thenComparingInt(Score::getCoins)
            .reversed()
        )
        .limit(TOP_SCORES)
        .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore(final Score score) {
        this.scoreLeaderboard.add(score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(26);
        for (final Score score : this.getScores()) {
            s.append(score.toString());
        }
        return s.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Leaderboard copy() {
        return new LeaderboardImpl(this.scoreLeaderboard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadScores() {
        this.scoreLeaderboard.addAll(this.loader.loadScores());
        this.loader.saveScores(this.scoreLeaderboard);
    }
}
