package com.row666.game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
    public static final int TEXTURE_WIDTH = 16, TEXTURE_HEIGHT = 16;
    
    protected BufferedImage texture;
    protected final int id;
    protected boolean solid;
    
    public Tile(BufferedImage texture, int id) {
        this(texture, id, false);
    }
    public Tile(BufferedImage texture, int id, boolean solid) {
        this.texture = texture;
        this.id = id;
        
        TileManager.addTile(this);
    }
    
    public void tick() {
    
    }
    
    public void render(Graphics g, int x, int y, TileManager tileManager) {
        g.drawImage(texture, (int) (x * TILE_WIDTH - tileManager.getRoom().getState().getCamera().getXOffset()), (int) (y * TILE_HEIGHT - tileManager.getRoom().getState().getCamera().getYOffset()), TILE_WIDTH, TILE_HEIGHT, null);
    }
    
    public boolean isSolid() {
        return solid;
    }
    
    public int getId() {
        return id;
    }
}
