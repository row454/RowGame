package main.com.row666.game.entities.creature;

import main.com.row666.game.entities.Entity;
import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.maths.Vector;
import main.com.row666.game.tiles.Tile;

import java.awt.image.BufferedImage;

public abstract class Creature extends Entity {
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected Vector vel = new Vector();

    public Creature(EntityManager manager, Vector pos, BufferedImage sprite) {
        super(manager, pos, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT, sprite);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        vel = new Vector();
    }
    
    public Creature(EntityManager manager, Vector pos, BufferedImage sprite, int health, float speed) {
        super(manager, pos, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT, sprite);
        this.health = health;
        this.speed = speed;
        vel = new Vector();
    }
    
    public void hurt(int amt) {
        health -= amt;
        if (health <= 0) {
            die();
        }
    }
    public abstract void die();
    
    public void move() {
        if (!checkEntityCollisions(vel.x, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, vel.y)) {
            moveY();
        }

    }

    public void moveX() {
        if(vel.x > 0) { //Moving right
            int tx = (int) (pos.x + vel.x + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if(tileIsNotSolid(tx, (int) (pos.y + bounds.y) / Tile.TILE_HEIGHT) &&
                    tileIsNotSolid(tx, (int) (pos.y + bounds.y + bounds.height - 1) / Tile.TILE_HEIGHT)) {
                pos.x += vel.x;
            } else {
                pos.x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width;
            }
        } else if(vel.x < 0) { //Moving left
            int tx = (int) (pos.x + vel.x + bounds.x) / Tile.TILE_WIDTH;

            if(tileIsNotSolid(tx, (int) (pos.y + bounds.y) / Tile.TILE_HEIGHT) &&
                    tileIsNotSolid(tx, (int) (pos.y + bounds.y + bounds.height - 1) / Tile.TILE_HEIGHT)) {
                pos.x += vel.x;
            } else {
                pos.x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if(vel.y < 0){//Up
            int ty = (int) (pos.y + vel.y + bounds.y) / Tile.TILE_HEIGHT;

            if(tileIsNotSolid((int) (pos.x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    tileIsNotSolid((int) (pos.x + bounds.x + bounds.width - 1) / Tile.TILE_WIDTH, ty)){
                pos.y += vel.y;
            } else {
                pos.y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }else if(vel.y > 0){//Down
            int ty = (int) (pos.y + vel.y + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if(tileIsNotSolid((int) (pos.x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    tileIsNotSolid((int) (pos.x + bounds.x + bounds.width - 1) / Tile.TILE_WIDTH, ty)){
                pos.y += vel.y;
            } else {
                pos.y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height;
            }
        }
    }

    protected boolean tileIsNotSolid(int x, int y) {
        return !manager.getRoom().getTileManager().getTile(x, y).isSolid();

    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getXMove() {
        return vel.x;
    }
    public void setXMove(float x) {
        this.vel.x = x;
    }
    public float getYMove(){
        return vel.y;
    }
    public void setYMove(float y) {
        this.vel.y = y;
    }
}
