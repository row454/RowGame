package com.row666.game;

public class Launcher {
    
    public static void main(String... args) {
        Game game = new Game("TileGame", 800, 600);
        game.run();
    }
}
