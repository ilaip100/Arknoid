// 211699665 Ilai Pingle

package Interfaces;

import Sprites.Block;
import Sprites.Ball;

/**
 * HitListener interface.
 * this interface will be used by objects that want to be notified of hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit - the block that was hit.
     * @param hitter   - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
