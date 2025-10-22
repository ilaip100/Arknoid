// 211699665 Ilai Pingle

package Listeners;

import GameSystem.Game;
import GameSystem.Counter;
import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;

/**
 * BallRemover class.
 * This class will remove balls from the game.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    private Paddle paddle;

    /**
     * Constructor.
     *
     * @param game           - the game.
     * @param remainingBalls - the remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Sets the paddle.
     *
     * @param paddle - the paddle.
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
        paddle.removeBall(hitter);
    }
}
