package main.com.row666.game.entities;

import main.com.row666.game.maths.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
    protected boolean active = true;
    protected Vector pos;
    protected float width, height;
    protected Rectangle bounds;
    protected EntityManager manager;
    protected BufferedImage sprite;
    protected boolean solid = true;

    public Entity(EntityManager manager, Vector pos, float width, float height, BufferedImage sprite) {
        this.pos = new Vector(pos);
        this.width = width;
        this.height = height;
        this.manager = manager;
        this.sprite = sprite;

        bounds = new Rectangle(0, 0, (int) width, (int) height);
    }
    public void tick() {
    
    };

    public void render(Graphics g) {
        g.drawImage(sprite, (int) (pos.x
                - manager.getRoom().getState().getCamera().getXOffset()), (int) (pos.y - manager.getRoom().getState().getCamera().getYOffset()), (int) width, (int) height, null);
    };
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (pos.x+bounds.x+xOffset), (int) (pos.y+bounds.y+yOffset), bounds.width, bounds.height);

    }
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for(Entity e : manager.getEntities()) {
            if(e.equals(this)) {
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.solid) {
                return true;
            }

        }
        return false;
    }
    
    public ArrayList<Entity> getEntityCollisions(float xOffset, float yOffset) {
        ArrayList<Entity> collidingWith = new ArrayList<>();
        for(Entity e : manager.getEntities()) {
            if(e.equals(this)) {
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.solid) {
                collidingWith.add(e);
            }
        }
        return collidingWith;
    }
    
    public float getX() {
        return pos.x;
    }
    public void setX(float x) {
        pos.x = x;
    }
    public float getY() {
        return pos.y;
    }
    public void setY(float y) {
        pos.y = y;
    }
    public Vector getPos() {
        return new Vector(pos);
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