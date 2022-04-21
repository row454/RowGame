package main.com.row666.game.entities.creature.player;

import main.com.row666.game.Game;
import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.entities.creature.Creature;
import main.com.row666.game.gfx.Assets;
import main.com.row666.game.maths.Vector;
import test.com.row666.game.weapon.SnowballGun;
import main.com.row666.game.weapon.Weapon;

public class Player extends Creature {

    
    private float timer = 0;
    private Vector attackVel = new Vector();
    
    private int strength;
    private float attackRate;
    private float projectileSpeed;
    private float range;
    private Weapon activeWeapon;
    
    
    public Player(EntityManager manager, Vector pos) {
        super(manager, pos, Assets.getInstance().getEntitySprites().get(0));
        bounds.y = 24;
        bounds.height = 40;
        
        strength = 0;
        attackRate = 2.4f;
        range = 6;
        projectileSpeed = 7;
        activeWeapon = new SnowballGun(this);
    }

    @Override
    public void tick() {
        getInput();
        move();
        manager.getRoom().getState().getCamera().centerOnEntity(this);
        if(timer > 0) timer--;
        if(timer < 0) timer = 0;
    }

    
    private void getInput() {
        vel.zero();
        attackVel.zero();
        
        if(manager.getRoom().getState().getGame().getKeyManager().up)
            vel.add(new Vector(Vector.UP).scale(speed));
        if(manager.getRoom().getState().getGame().getKeyManager().left)
            vel.add(new Vector(Vector.LEFT).scale(speed));
        if(manager.getRoom().getState().getGame().getKeyManager().down)
            vel.add(new Vector(Vector.DOWN).scale(speed));
        if(manager.getRoom().getState().getGame().getKeyManager().right)
            vel.add(new Vector(Vector.RIGHT).scale(speed));
        
        if(manager.getRoom().getState().getGame().getKeyManager().upFire && timer == 0) {
            timer = Game.tps/attackRate;
            attackVel = new Vector(Vector.UP).scale(projectileSpeed);
        }
        if(manager.getRoom().getState().getGame().getKeyManager().leftFire && timer == 0) {
            timer = Game.tps/attackRate;
            attackVel = new Vector(Vector.LEFT).scale(projectileSpeed);
        }
        if(manager.getRoom().getState().getGame().getKeyManager().downFire && timer == 0) {
            timer = Game.tps/attackRate;
            attackVel = new Vector(Vector.DOWN).scale(projectileSpeed);
        }
        if(manager.getRoom().getState().getGame().getKeyManager().rightFire && timer == 0) {
            timer = Game.tps/attackRate;
            attackVel = new Vector(Vector.RIGHT).scale(projectileSpeed);
        }
        if(!attackVel.equals(Vector.ZERO)) {
            attackVel.add(new Vector(vel).sign().signGate(attackVel));
            activeWeapon.attack(manager, attackVel, strength, range);
        }
        
    }
    

    @Override
    public void die() {
        active = false;

    }

}
