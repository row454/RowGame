package com.row666.game.gfx;

import com.row666.game.entities.Entity;
import com.row666.game.states.GameState;
import com.row666.game.tiles.Tile;

public class Camera {
    
    private GameState state;
    private float xOffset, yOffset;
    
    public Camera(GameState state, float xOffset, float yOffset) {
        this.state = state;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - state.getGame().getWidth() / 2f + e.getWidth() / 2;
        yOffset = e.getY() - state.getGame().getHeight() / 2f + e.getWidth() / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }
    
    public void checkBlankSpace() {
        if(xOffset < 0) {
            xOffset = 0;
        } else if(xOffset > state.getRoom().getWidth() * Tile.TILE_WIDTH - state.getGame().getWidth()) {
            xOffset = state.getRoom().getWidth() * Tile.TILE_WIDTH - state.getGame().getWidth();
        }
        if(yOffset < 0) {
            yOffset = 0;
        } else if(yOffset > state.getRoom().getHeight() * Tile.TILE_HEIGHT - state.getGame().getHeight()) {
            yOffset = state.getRoom().getHeight() * Tile.TILE_HEIGHT - state.getGame().getHeight();
        }
        
    }
    
    public float getXOffset() {
        return xOffset;
    }
    
    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }
    
    public float getYOffset() {
        return yOffset;
    }
    
    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
}