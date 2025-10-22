// 211699665 Ilai Pingle

package GameSystem;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

import Interfaces.Sprite;

/**
 * SpriteCollection class.
 * holds a List of sprites.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructor.
     * initializes the sprites list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * @return the sprites list.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Add the given sprite to the collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * notify all sprites that time has passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }
}

