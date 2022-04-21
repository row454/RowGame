package test.com.row666.game.weapon;

import main.com.row666.game.entities.Entity;
import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.entities.projectile.Snowball;
import main.com.row666.game.maths.Vector;
import main.com.row666.game.weapon.Weapon;

public class SnowballGun extends Weapon {
    public SnowballGun(Entity owner) {
        super("Snowball Gun", 5, owner);
    }
    
    @Override
    public void attack(EntityManager manager, Vector vel, int strength, float range) {
        manager.addEntity(new Snowball(manager, owner.getPos(), owner, vel, strength+power, range));
    }
}
