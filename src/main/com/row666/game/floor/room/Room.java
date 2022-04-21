package main.com.row666.game.floor.room;

import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.maths.Vector;
import main.com.row666.game.states.GameState;
import main.com.row666.game.tiles.Tile;
import main.com.row666.game.tiles.TileManager;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class Room {
    
    private final GameState state;
    
    private int width, height;
    private Vector spawnPos;

    private final TileManager tileManager;

    private final EntityManager entityManager;
    
    public Vector getSpawnPos() {
        return new Vector(spawnPos);
    }
    
    
    
    
    
    
    
    
    public Room(GameState state, String path) {
        this.state = state;

        this.tileManager = new TileManager(this);
        loadWorld(path);

        this.entityManager = new EntityManager(this);

        
    }
    
    public void tick() {
        entityManager.tick();
    }
    
    public void render(Graphics g) {
        tileManager.render(g);
        entityManager.render(g);
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
        spawnPos = new Vector(Tile.TILE_WIDTH * Integer.parseInt(tokens[2]), Tile.TILE_HEIGHT * Integer.parseInt(tokens[3]));
        
        String[] tileTokens = Arrays.copyOfRange(tokens, 4, tokens.length);
        tileManager.loadTiles(tileTokens, width, height);
    }
    
    public GameState getState() {
        return state;
    }
    
    public TileManager getTileManager() {
        return tileManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}