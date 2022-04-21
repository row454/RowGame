package main.com.row666.game.gfx;

import main.com.row666.game.entities.Entity;
import main.com.row666.game.states.GameState;
import main.com.row666.game.tiles.Tile;

public class Camera {
    
    private GameState state;
    private float xOffset, yOffset, xPadding, yPadding;
    
    public Camera(GameState state, float xOffset, float yOffset) {
        this.state = state;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        updatePadding();
    }
    
    
    public void updatePadding() {
    
    }
    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - state.getGame().getDisplay().getCanvas().getWidth() / 2f + e.getWidth() / 2;
        yOffset = e.getY() - state.getGame().getDisplay().getCanvas().getHeight() / 2f + e.getWidth() / 2;
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
        } else if(xOffset > state.getRoom().getWidth() * Tile.TILE_WIDTH - state.getGame().getDisplay().getCanvas().getWidth()) {
            xOffset = state.getRoom().getWidth() * Tile.TILE_WIDTH - state.getGame().getDisplay().getCanvas().getWidth();
        }
        if(yOffset < 0) {
            yOffset = 0;
        } else if(yOffset > state.getRoom().getHeight() * Tile.TILE_HEIGHT - state.getGame().getDisplay().getCanvas().getHeight()) {
            yOffset = state.getRoom().getHeight() * Tile.TILE_HEIGHT - state.getGame().getDisplay().getCanvas().getHeight();
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