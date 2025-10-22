# 🎮 Arknoid Game

![Arknoid Game Banner](https://img.shields.io/badge/Arkanoid-Java%20Game-orange)
![Java](https://img.shields.io/badge/language-Java-blue)
![OOP](https://img.shields.io/badge/paradigm-Object%20Oriented-green)

## 🚀 Overview

A simple **Arkanoid-style** arcade game implemented in **Java**.  
Developed as part of an academic project, it demonstrates **Object-Oriented Programming (OOP)** concepts using the `biuoop` graphics library.  
The player controls a paddle to bounce balls and break all blocks on the screen.

## ✨ Features

- **Interactive Gameplay** – Move the paddle with keyboard arrows  
- **Collision System** – Detects ball hits with walls, blocks, and paddle  
- **Score Tracking** – Gain points for each block destroyed  
- **Multiple Objects** – Balls, blocks, paddle, and boundaries  
- **Game Over & Win Detection** – Ends when all blocks are cleared or all balls are lost  

## 🏗️ Architecture

The project follows modular OOP design and separation of concerns:

```
├── GameSystem       // Main game logic, loop, and environment
├── Sprites          // Game objects (Ball, Block, Paddle, ScoreIndicator)
├── Geometry         // Shapes & motion (Point, Line, Rectangle, Velocity)
├── Listeners        // Event-based systems for scoring and object removal
└── Interfaces       // Sprite, Collidable, HitListener, HitNotifier
```

## 🧩 Core Classes

- **Ass5Game** – Main entry point that starts the game  
- **Game** – Handles initialization, level setup, and main game loop  
- **Paddle, Ball, Block** – Main interactive game elements  
- **ScoreIndicator** – Displays the player’s score  

## 🧠 OOP Principles Demonstrated

- **Abstraction** – Clear contracts via interfaces: `Sprite`, `Collidable`, `HitListener`, `HitNotifier`  
- **Encapsulation** – Each object manages its own state and exposes only needed methods  
- **Polymorphism** – The game loop operates on `Sprite` references (draw/update), and collisions are resolved through the `Collidable` interface  
- **(Minimal) Inheritance** – Concrete types implement shared interfaces; inheritance is kept lean to prefer composition

## 🧩 Design Patterns (lightweight)

- **Observer** – Blocks and other objects notify listeners of hits via `HitNotifier` → `HitListener` (e.g., `BlockRemover`, `BallRemover`, `ScoreTrackingListener`)  
- **Strategy (via interfaces)** – Different collision behaviors come from distinct `Collidable.hit(...)` implementations (`Block` vs `Paddle`)  
- **Iterator‑style collection** – `SpriteCollection` centralizes iteration and time updates safely  
- **Factory‑style helpers** – Object creation is organized in methods like `Game.createABall()`, `Game.createPaddle()`, and `Game.createLevel()`

## 🕹️ Controls

- **← / →** — Move paddle left or right  

## ⚙️ Running the Game

### Requirements
- **Java JDK 8+**  
- **biuoop-1.4.jar** in the project root  

### Run with Ant (recommended)
```bash
ant run
```

### Or manually
-Windows (CMD/PowerShell)
```bash
mkdir bin
javac -cp ".;biuoop-1.4.jar" -d bin -sourcepath src src\Ass5Game.java
java  -cp "bin;biuoop-1.4.jar" Ass5Game
```
-macOS / Linux
```bash
mkdir -p bin
javac -cp ".:biuoop-1.4.jar" -d bin -sourcepath src src/Ass5Game.java
java  -cp "bin:biuoop-1.4.jar" Ass5Game
```

## 👨‍💻 Author
Developed by **Ilai Pingle**
