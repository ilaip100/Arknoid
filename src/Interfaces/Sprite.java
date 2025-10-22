// 211699665 Ilai Pingle

package Interfaces;

import biuoop.DrawSurface;

/**
 * Sprite interface.
 * This interface will be used by things that can be drawn on the screen.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d - the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed - and it is time to move( if necessary).
     */
    void timePassed();
}

