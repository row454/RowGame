package main.com.row666.game.entities;

import main.com.row666.game.gfx.Assets;
import main.com.row666.game.item.Item;
import main.com.row666.game.maths.Vector;

import java.awt.*;


public class ItemHolder extends Entity {
    
    private float itemHeight;
    
    public ItemHolder(EntityManager manager, Vector pos, float width, float height, Item item) {
        super(manager, pos, width, height, Assets.getInstance().getEntitySprites().get(4));
    }
    
    public void render(Graphics g) {
        
        
        g.drawImage(sprite, (int) (pos.x
                - manager.getRoom().getState().getCamera().getXOffset()), (int) (pos.y - manager.getRoom().getState().getCamera().getYOffset()), (int) width, (int) height, null);
    };
}
