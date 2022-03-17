package com.row666.game.entities;

import java.awt.*;

public abstract class Entity {
    protected boolean active = true;
    protected float x, y;
    protected float width, height;
    protected Rectangle bounds;
    protected EntityManager manager;

    public Entity(EntityManager manager, float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.manager = manager;

        bounds = new Rectangle(0, 0, width - 1, height - 1);
    }
    public abstract void tick();

    public abstract void render(Graphics g);
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x+bounds.x+xOffset), (int) (y+bounds.y+yOffset), bounds.width, bounds.height);

    }
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for(Entity e : manager.getEntities()) {
            if(e.equals(this)) {
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }

        }
        return false;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}