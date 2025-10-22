// 211699665 Ilai Pingle

package GameSystem;

import Interfaces.HitListener;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Sprites.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

import Interfaces.Collidable;
import Interfaces.Sprite;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;

/**
 * this class represent the "Arknoid" game.
 * the game has a paddle, blocks and balls.
 */
public class Game {
    public static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 80;
    private static final int PADDLE_HEIGHT = 25;
    private static final int BALL_RADIUS = 5;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int MARGINS = 25;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;
    private final Counter blocksInGame;
    private final Counter ballsInGame;
    private final Counter score;

    /**
     * Constructor.
     * Initializes the game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksInGame = new Counter();
        this.ballsInGame = new Counter();
        this.score = new Counter();
    }

    /**
     * Get the game's environment.
     *
     * @return GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Get the game's sprites.
     *
     * @return SpriteCollection.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Add a collidable to the game's environment.
     *
     * @param c - the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a sprite to the game's sprites.
     *
     * @param s - the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("MyGame", WIDTH, HEIGHT);
        // Create the paddle
        Paddle paddle = createPaddle();
        paddle.addToGame(this);
        // Create the blocks
        createLevel();
        //BOUNDARY
        BallRemover deathZone = (BallRemover) createBounds();
        deathZone.setPaddle(paddle);
        //add the score text:
        createScoreSign();
        // Create the balls
        for (int i = 0; i < 2; i++) {
            Ball ball = createABall();
            paddle.addBall(ball);
        }
    }

    /**
     * Create a paddle.
     *
     * @return the paddle.
     */
    public Paddle createPaddle() {
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        double paddleX = (double) WIDTH / 2 - PADDLE_WIDTH + MARGINS;
        double paddleY = HEIGHT - PADDLE_HEIGHT - MARGINS;
        Point paddleUpperLeft = new Point(paddleX, paddleY);
        Rectangle paddleRectangle = new Rectangle(paddleUpperLeft, PADDLE_WIDTH, PADDLE_HEIGHT);
        return new Paddle(paddleRectangle, keyboard, Color.yellow, WIDTH);
    }

    /**
     * Create a ball.
     *
     * @return the ball.
     */
    public Ball createABall() {
        Random rand = new Random();
        Ball ball = this.generateRandomBall();
        ball.setVelocity(rand.nextInt(5) + 1, rand.nextInt(5) + 1);
        ball.addToGame(this);
        this.ballsInGame.increase(1);
        return ball;
    }

    /**
     * Create the bounds of the game and add them to the game.
     *
     * @return the death zone.
     */
    public HitListener createBounds() {
        HitListener deathZone = new BallRemover(this, this.ballsInGame);
        Block left = new Block(new Rectangle(new Point(0, 0), MARGINS, HEIGHT), Color.GRAY);
        Block up = new Block(new Rectangle(new Point(0, 0), WIDTH, MARGINS), Color.GRAY);
        Block right = new Block(new Rectangle(new Point(WIDTH - MARGINS, 0), MARGINS, HEIGHT), Color.GRAY);
        Block down = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, MARGINS), Color.GRAY);
        down.addHitListener(deathZone);
        left.addToGame(this);
        up.addToGame(this);
        right.addToGame(this);
        down.addToGame(this);
        return deathZone;
    }

    /**
     * Create the level of the game.
     */
    public void createLevel() {
        Color[] color = {Color.darkGray, Color.red, Color.yellow, Color.blue, Color.pink, Color.green};
        HitListener blockRemover = new BlockRemover(this, this.blocksInGame);
        HitListener scoreTracker = new ScoreTrackingListener(score);
        for (int i = 0; i < 6; i++) {
            int xStart = 175, yStart = 150, width = BLOCK_WIDTH, height = BLOCK_HEIGHT;
            for (int j = i; j < 12; j++) {
                Point upperLeft = new Point(xStart + (j * width), yStart + (i * height));
                Block block = new Block(new Rectangle(upperLeft, width, height), color[i]);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracker);
                this.blocksInGame.increase(1);
            }
        }
    }

    /**
     * Create the score sign.
     */
    public void createScoreSign() {
        ScoreIndicator scoreWindow = new ScoreIndicator(this.score);
        scoreWindow.addToGame(this);
    }

    /**
     * Remove a collidable from the game's environment.
     *
     * @param c - the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove a sprite from the game's sprites.
     *
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Run the game.
     * The game will run until the player wins or loses.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            d.setColor(new Color(0, 0, 136));
            d.fillRectangle(0, 0, WIDTH, HEIGHT);
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (blocksInGame.getValue() < 0) {
                break;
            }
            if (ballsInGame.getValue() < 0) {
                break;
            }
            if (blocksInGame.getValue() == 0) {
                System.out.println("WINNER!!!!");
                this.score.increase(100);
                blocksInGame.decrease(1);
            }
            if (ballsInGame.getValue() == 0) {
                ballsInGame.decrease(1);
                System.out.println("GAME OVER");
            }
        }
    }

    /**
     * generate ball on the game's screen randomly.
     *
     * @return ball - the new ball.
     */
    public Ball generateRandomBall() {
        Random rand = new Random();
        Ball ball;
        boolean isUnderBlock;
        do {
            double x = rand.nextInt(WIDTH - 2 * MARGINS - 2 * BALL_RADIUS) + MARGINS + BALL_RADIUS;
            double y = rand.nextInt(HEIGHT - 2 * MARGINS - 2 * BALL_RADIUS) + MARGINS + BALL_RADIUS;
            Point center = new Point(x, y);
            ball = new Ball(center, BALL_RADIUS, Color.WHITE, this.environment);
            isUnderBlock = false;
            for (Collidable collidable : this.environment.getCollidables()) {
                if (this.isBallInsideBlock(ball, collidable.getCollisionRectangle())) {
                    isUnderBlock = true;
                    break;
                }
            }
        } while (isUnderBlock);
        return ball;
    }

    /**
     * Check if the ball is inside a block.
     *
     * @param ball      - the ball
     * @param rectangle - the rectangle to check if the ball is inside.
     * @return true if the ball is inside the block, false otherwise.
     */
    public boolean isBallInsideBlock(Ball ball, Rectangle rectangle) {
        double ballLeftX = ball.getX() - ball.getSize();
        double ballRightX = ball.getX() + ball.getSize();
        double ballTopY = ball.getY() - ball.getSize();
        double ballDownY = ball.getY() + ball.getSize();

        double blockLeftX = rectangle.getUpperLeft().getX();
        double blockRightX = blockLeftX + rectangle.getWidth();
        double blockTopY = rectangle.getUpperLeft().getY();
        double blockBottomY = blockTopY + rectangle.getHeight();
        boolean isHorizontallyAligned = (ballRightX > blockLeftX && ballRightX < blockRightX)
                || (ballLeftX > blockLeftX && ballLeftX < blockRightX);
        boolean isVerticallyAligned = (ballDownY > blockTopY && ballDownY < blockBottomY)
                || (ballTopY < blockBottomY && ballTopY > blockTopY);

        return isHorizontallyAligned && isVerticallyAligned;
    }

}
