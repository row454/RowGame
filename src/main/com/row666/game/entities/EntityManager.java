package main.com.row666.game.entities;

import main.com.row666.game.entities.creature.Target;
import main.com.row666.game.entities.creature.player.Player;
import main.com.row666.game.floor.room.Room;
import main.com.row666.game.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class EntityManager {


    private final Player player;
    private final ArrayList<Entity> entities;
    public ListIterator<Entity> it;
    private final Comparator<Entity> renderSorter = (a, b) -> Float.compare(a.getY() + a.getHeight(), b.getY() + b.getHeight());

    private final Room room;
    public EntityManager(Room room){
        this.room = room;
        player = new Player(this, room.getSpawnPos());
        

        entities = new ArrayList<Entity>();
        addEntity(player);
        addEntity(new Target(this, room.getSpawnPos()));
    }
    public void tick() {
        it = entities.listIterator();

        while(it.hasNext()) {
            Entity e = it.next();
            e.tick();
            if (!e.active)
                it.remove();
        }

    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public void render(Graphics g) {
        entities.sort(renderSorter);
        for(Entity e : entities) {

            e.render(g);
        }
    }
    public void addEntity(Entity e) {
        e.setX(e.pos.x+ Tile.TILE_WIDTH/2f);
        e.setY(e.pos.y+ Tile.TILE_HEIGHT/2f);
        if (it != null)
            it.add(e);
        else
            entities.add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getRoom() {
        return room;
    }
}
