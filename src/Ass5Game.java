// 211699665 Ilai Pingle

import GameSystem.Game;

/**
 * this class is the main class of the game.
 */
public class Ass5Game {
    /**
     * the main function.
     * creates a new game and runs it.
     *
     * @param args - the arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

