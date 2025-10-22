// 211699665 Ilai Pingle

package Sprites;

import GameSystem.Counter;
import GameSystem.Game;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Represents the score indicator in the game.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    /**
     * Add the score indicator to the game.
     *
     * @param game the game to add the score indicator to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }


    @Override
    public void drawOn(DrawSurface d) {
        String scoreString = "SCORE: " + score.getValue();
        d.setColor(Color.BLACK);
        int y = 20;
        int size = 20;
        int x = 340;
        d.drawText(x, y, scoreString, size);
    }

    @Override
    public void timePassed() {

    }
}
