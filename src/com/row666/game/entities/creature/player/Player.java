package com.row666.game.entities.creature.player;

import com.row666.game.entities.Entity;
import com.row666.game.entities.EntityManager;
import com.row666.game.entities.creature.Creature;
import com.row666.game.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    public Player(EntityManager manager, int x, int y) {
        super(manager, x, y);
        bounds.y = 24;
        bounds.height = 40;

    }

    @Override
    public void tick() {
        getInput();
        move();
        manager.getRoom().getState().getCamera().centerOnEntity(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getInstance().getEntitySprites().get(0), (int) (x - manager.getRoom().getState().getCamera().getXOffset()), (int) (y - manager.getRoom().getState().getCamera().getYOffset()), (int) width, (int) height, null);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(manager.getRoom().getState().getGame().getKeyManager().up)
            yMove = -speed;
        if(manager.getRoom().getState().getGame().getKeyManager().left)
            xMove = -speed;
        if(manager.getRoom().getState().getGame().getKeyManager().down)
            yMove = speed;
        if(manager.getRoom().getState().getGame().getKeyManager().right)
            xMove = speed;
    }
    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for(Entity e : manager.getRoom().getEntityManager().getEntities()) {
            if(e.equals(this)) {
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }

        }
        return false;
    }

    @Override
    public void die() {
        active = false;

    }

}
