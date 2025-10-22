# Arknoid (Java) 

A simple Arknoid-style Java game (student project). Uses the biuoop library (biuoop-1.4.jar). The main entry point is [`Ass5Game`](src/Ass5Game.java).

## Requirements
- JDK 8+ installed
- biuoop-1.4.jar present in the project root (already referenced by the project files)
- Apache Ant (optional, recommended for build/run via provided `build.xml`)

## Build & Run

Using Ant (recommended)
```sh
ant run
```
This target depends on `compile` and runs the main class `Ass5Game`. See [build.xml](build.xml).

Manual (javac/java)
```sh
# compile
javac -cp .;biuoop-1.4.jar -d bin src\**\*.java

# run
java -cp bin;biuoop-1.4.jar Ass5Game
```

## Project structure (source files)
- [Ass5Game.java](src/Ass5Game.java)
- GameSystem/
  - [CollisionInfo.java](src/GameSystem/CollisionInfo.java)
  - [Counter.java](src/GameSystem/Counter.java)
  - [Game.java](src/GameSystem/Game.java)
  - [GameEnvironment.java](src/GameSystem/GameEnvironment.java)
  - [SpriteCollection.java](src/GameSystem/SpriteCollection.java)
- Geometry/
  - [Line.java](src/Geometry/Line.java)
  - [Point.java](src/Geometry/Point.java)
  - [Rectangle.java](src/Geometry/Rectangle.java)
  - [Velocity.java](src/Geometry/Velocity.java)
- Interfaces/
  - [Collidable.java](src/Interfaces/Collidable.java)
  - [HitListener.java](src/Interfaces/HitListener.java)
  - [HitNotifier.java](src/Interfaces/HitNotifier.java)
  - [Sprite.java](src/Interfaces/Sprite.java)
- Listeners/
  - [BallRemover.java](src/Listeners/BallRemover.java)
  - [BlockRemover.java](src/Listeners/BlockRemover.java)
  - [ScoreTrackingListener.java](src/Listeners/ScoreTrackingListener.java)
- Sprites/
  - [Ball.java](src/Sprites/Ball.java)
  - [Block.java](src/Sprites/Block.java)
  - [Paddle.java](src/Sprites/Paddle.java)
  - [ScoreIndicator.java](src/Sprites/ScoreIndicator.java)
- Project files:
  - [.gitignore](.gitignore)
  - [build.xml](build.xml)
  - [ex_5.iml](ex_5.iml)

## Key classes & responsibilities (links to source)
- [`Ass5Game`](src/Ass5Game.java) — main entry point.
- [`GameSystem.Game`](src/GameSystem/Game.java) — game setup, main loop, object creation.
- [`GameSystem.GameEnvironment`](src/GameSystem/GameEnvironment.java) — holds collidables and finds collisions.
- [`GameSystem.SpriteCollection`](src/GameSystem/SpriteCollection.java) — manages sprites and updates/draws them.
- [`Sprites.Ball`](src/Sprites/Ball.java) — ball physics, drawing, collision handling.
- [`Sprites.Block`](src/Sprites/Block.java) — block geometry, drawing, hit notification.
- [`Sprites.Paddle`](src/Sprites/Paddle.java) — player paddle movement and hit behavior.
- [`Sprites.ScoreIndicator`](src/Sprites/ScoreIndicator.java) — UI text showing score.
- Listeners:
  - [`Listeners.BallRemover`](src/Listeners/BallRemover.java) — removes balls when they hit the death zone.
  - [`Listeners.BlockRemover`](src/Listeners/BlockRemover.java) — removes broken blocks and updates counters.
  - [`Listeners.ScoreTrackingListener`](src/Listeners/ScoreTrackingListener.java) — increments score on hits.
- Geometry utilities:
  - [`Geometry.Point`](src/Geometry/Point.java), [`Geometry.Line`](src/Geometry/Line.java), [`Geometry.Rectangle`](src/Geometry/Rectangle.java), [`Geometry.Velocity`](src/Geometry/Velocity.java)
- Interfaces:
  - [`Interfaces.Sprite`](src/Interfaces/Sprite.java), [`Interfaces.Collidable`](src/Interfaces/Collidable.java), [`Interfaces.HitListener`](src/Interfaces/HitListener.java), [`Interfaces.HitNotifier`](src/Interfaces/HitNotifier.java)

## Gameplay notes & behavior
- Game window size and constants are set in [`GameSystem.Game`](src/GameSystem/Game.java) (WIDTH = 800, HEIGHT = 600).
- Levels/blocks are created in `Game.createLevel()`; score sign created in `Game.createScoreSign()`.
- Ball collisions are resolved by `GameEnvironment.getClosestCollision(...)` and `Collidable.hit(...)`.
- `SpriteCollection` copies the sprite list before iteration to allow safe add/remove during updates.

## Tips for development
- To add new levels, modify `Game.createLevel()` or add a level loader.
- Geometry classes (`Point`, `Line`, `Rectangle`, `Velocity`) are good candidates for unit tests.
- Keep biuoop-1.4.jar on the classpath during compile/run.

## Files to open quickly
- Main: [src/Ass5Game.java](src/Ass5Game.java)
- Game loop and setup: [`GameSystem.Game`](src/GameSystem/Game.java)
- Collision handling: [`GameSystem.GameEnvironment`](src/GameSystem/GameEnvironment.java)
- Paddle/Ball/Block: [`Sprites.Paddle`](src/Sprites/Paddle.java), [`Sprites.Ball`](src/Sprites/Ball.java), [`Sprites.Block`](src/Sprites/Block.java)

If you want, I can:
- produce a shorter quick-start section for VSCode/IntelliJ,
- generate unit tests skeletons for Geometry classes,
- or prepare an English README file committed into the repo (this file is ready to save as README.md).
