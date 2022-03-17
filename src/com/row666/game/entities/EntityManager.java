package com.row666.game.entities;

import com.row666.game.entities.creature.player.Player;
import com.row666.game.floor.room.Room;
import com.row666.game.tiles.Tile;

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
        this.player = new Player(this, room.getSpawnX(), room.getSpawnY());

        entities = new ArrayList<Entity>();
        addEntity(player);
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
        e.setX(e.x+Tile.TILE_WIDTH/2f);
        e.setY(e.y+ Tile.TILE_HEIGHT/2f);
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
