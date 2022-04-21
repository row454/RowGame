package main.com.row666.game.entities.projectile;

import main.com.row666.game.entities.Entity;
import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.entities.creature.Creature;
import main.com.row666.game.gfx.Assets;
import main.com.row666.game.maths.Vector;
import main.com.row666.game.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Projectile extends Entity {
    
    private Vector vel;
    private Entity creator;
    private int dmg;
    private float range;
    private Vector distance;
    private float projectileHeight;
    private float fallSpeed;
    
    public Projectile(EntityManager manager, Vector pos, float size, BufferedImage sprite, Entity creator, Vector vel, int dmg, float range) {
        super(manager, pos, size, size, sprite);
        this.vel = new Vector(vel);
        solid = false;
        this.creator = creator;
        this.dmg = dmg;
        this.range = range*Tile.TILE_WIDTH;
        projectileHeight = 20+range*2;
        fallSpeed = 0.1f;
        distance = new Vector();
    }
    
    public void tick() {
        move();
        distance.add(vel);
        
        fallSpeed += distance.len2() < range*range ? 0f : 0.5f;
        projectileHeight -= fallSpeed;
        
        
        if(projectileHeight <= 0) {
            this.active = false;
        }
    }
    
    public void render(Graphics g) {
        g.setColor(new Color(0,0,0, 128));
        g.fillOval((int) (pos.x - manager.getRoom().getState().getCamera().getXOffset()),(int) (pos.y - manager.getRoom().getState().getCamera().getYOffset() + height/2), (int) width, (int) height/2);
        g.drawImage(sprite, (int) (pos.x - manager.getRoom().getState().getCamera().getXOffset()), (int) (pos.y - manager.getRoom().getState().getCamera().getYOffset() - projectileHeight), (int) width, (int) height, null);
    };
    
    public void move() {
        if (!checkEntityCollisions(vel.x, 0f)) {
            moveX();
        } else {
            for(Entity e : getEntityCollisions(vel.x, 0f)) {
                if(e instanceof Creature) ((Creature) e).hurt(dmg);
            }
            active = false;
        }
        if (!checkEntityCollisions(0f, vel.y)) {
            moveY();
        } else {
            for(Entity e : getEntityCollisions(0f, vel.y)) {
                if(e instanceof Creature) ((Creature) e).hurt(dmg);
            }
            active = false;
        }
        
    }
    
    
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for(Entity e : manager.getEntities()) {
            if(e.equals(this) || e.equals(creator) || e instanceof Projectile) {
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
            
        }
        return false;
    }
    @Override
    public ArrayList<Entity> getEntityCollisions(float xOffset, float yOffset) {
        ArrayList<Entity> collidingWith = new ArrayList<>();
        for(Entity e : manager.getEntities()) {
            if(e.equals(this) || e.equals(creator) || e instanceof Projectile) {
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                collidingWith.add(e);
            }
        }
        return collidingWith;
    }
    
    public void moveX() {
        if (vel.x > 0) { //Moving right
            int tx = (int) (pos.x + vel.x + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            
            if (tileIsNotSolid(tx, (int) (pos.y + bounds.y) / Tile.TILE_HEIGHT) &&
                    tileIsNotSolid(tx, (int) (pos.y + bounds.y + bounds.height - 1) / Tile.TILE_HEIGHT)) {
                pos.x += vel.x;
            } else {
                active = false;
            }
        } else if (vel.x < 0) { //Moving left
            int tx = (int) (pos.x + vel.x + bounds.x) / Tile.TILE_WIDTH;
            
            if (tileIsNotSolid(tx, (int) (pos.y + bounds.y) / Tile.TILE_HEIGHT) &&
                    tileIsNotSolid(tx, (int) (pos.y + bounds.y + bounds.height - 1) / Tile.TILE_HEIGHT)) {
                pos.x += vel.x;
            } else {
                active = false;
            }
        }
    }
    
    public void moveY() {
        if (vel.y < 0) {//Up
            int ty = (int) (pos.y + vel.y + bounds.y) / Tile.TILE_HEIGHT;
    
            if (tileIsNotSolid((int) (pos.x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    tileIsNotSolid((int) (pos.x + bounds.x + bounds.width - 1) / Tile.TILE_WIDTH, ty)) {
                pos.y += vel.y;
            } else {
                active = false;
            }
        } else if (vel.y > 0) {//Down
            int ty = (int) (pos.y + vel.y + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
    
            if (tileIsNotSolid((int) (pos.x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    tileIsNotSolid((int) (pos.x + bounds.x + bounds.width - 1) / Tile.TILE_WIDTH, ty)) {
                pos.y += vel.y;
            } else {
                active = false;
            }
    
        }
        
    }
    protected boolean tileIsNotSolid(int x, int y){
        return !manager.getRoom().getTileManager().getTile(x, y).isSolid();
        
    }
}