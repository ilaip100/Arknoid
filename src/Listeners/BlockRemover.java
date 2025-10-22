// 211699665 Ilai Pingle

package Listeners;

import Interfaces.HitListener;
import Sprites.Block;
import Sprites.Ball;
import GameSystem.Game;
import GameSystem.Counter;

/**
 * BlockRemover class.
 * This class will remove blocks from the game.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     * @param game the game to remove blocks from.
     * @param remainingBlocks the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the block from the game and removes the hit listener.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
