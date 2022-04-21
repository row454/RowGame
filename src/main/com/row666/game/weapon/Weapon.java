package main.com.row666.game.weapon;

import main.com.row666.game.entities.Entity;
import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.item.Item;
import main.com.row666.game.maths.Vector;

public abstract class Weapon {
    
    protected String name;
    protected int power;
    protected Entity owner;
    
    public Weapon(String name, int power, Entity owner) {
        this.name = name;
        this.power = power;
        this.owner = owner;
    }
    
    
    /*
    Projectile:
    
    public void attack(EntityManager manager, float xMove, float vel.y, Entity creator, int strength, float range) {
        manager.addEntity(new Projectile(manager, creator.getX()-12+creator.getWidth()*Integer.signum((int) (xMove-Integer.signum((int) xMove))), creator.getY()-12+creator.getHeight()*Integer.signum((int) (vel.y-Integer.signum((int) vel.y))), 24, 24, Assets.getInstance().getEntitySprites().get(2), creator, xMove, vel.y, dmg, range));
    }
    
    Melee:
    
    
     */
    
    public abstract void attack(EntityManager manager, Vector vel, int strength, float range);
    
}
