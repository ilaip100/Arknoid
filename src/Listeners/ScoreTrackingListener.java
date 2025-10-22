// 211699665 Ilai Pingle

package Listeners;

import Interfaces.HitListener;

import GameSystem.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * ScoreTrackingListener class.
 * This class will track the score of the player.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /** Constructor.
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It increases the score by 5 points.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);

    }

}
