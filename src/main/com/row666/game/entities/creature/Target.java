package main.com.row666.game.entities.creature;

import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.gfx.Assets;
import main.com.row666.game.maths.Vector;

public class Target extends Creature {
    
    private int totalDmgTaken = 0;
    
    public Target(EntityManager manager, Vector pos) {
        super(manager, pos, Assets.getInstance().getEntitySprites().get(3), 1, 0);
        
        solid = false;
    }
    
    @Override
    public void die() {
    
    }
    
    @Override
    public void hurt(int amt) {
        totalDmgTaken += amt;
        System.out.println("I was dealt: " + amt + " damage");
        System.out.println("This makes a total of: " + totalDmgTaken + " damage");
    }
}
