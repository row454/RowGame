package com.row666.game.room;

import com.row666.game.gfx.Camera;
import com.row666.game.states.GameState;
import com.row666.game.tiles.Tile;
import com.row666.game.tiles.TileManager;

import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class Room {
    
    private GameState state;
    
    private int width, height;
    private int spawnX;
    private int spawnY;
    
    private int [][] tiles;
    private TileManager tileManager;
    
    
    public int getSpawnX() {
        return spawnX;
    }
    
    public int getSpawnY() {
        return spawnY;
    }
    
    
    
    
    
    
    
    public Room(GameState state, String path) {
        this.state = state;
        this.tileManager = new TileManager(this);
        loadWorld(path);
        
    }
    
    public void tick() {
    
    }
    
    public void render(Graphics g) {
        tileManager.render(g);
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    private void loadWorld(String path) {
        String file = null;
        try {
            file = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert file != null;
        String[] tokens = file.split("\\s+");
        width = Integer.parseInt(tokens[0]);
        height = Integer.parseInt(tokens[1]);
        spawnX = 32 * Integer.parseInt(tokens[2]);
        spawnY = 32 * Integer.parseInt(tokens[3]);
        
        String[] tileTokens = Arrays.copyOfRange(tokens, 4, tokens.length);
        tileManager.loadTiles(tileTokens, width, height);
    }
    
    public GameState getState() {
        return state;
    }
    
    public TileManager getTileManager() {
        return tileManager;
    }
}