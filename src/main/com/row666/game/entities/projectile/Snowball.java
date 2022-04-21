package main.com.row666.game.entities.projectile;

import main.com.row666.game.entities.Entity;
import main.com.row666.game.entities.EntityManager;
import main.com.row666.game.gfx.Assets;
import main.com.row666.game.maths.Vector;

public class Snowball extends Projectile {
    public Snowball(EntityManager manager, Vector pos, Entity creator, Vector vel, int dmg, float range) {
        super(manager, pos, 24, Assets.getInstance().getEntitySprites().get(2), creator, vel, dmg, range);
    }
}
