package com.row666.game.states;

import com.row666.game.Game;
import com.row666.game.gfx.Camera;
import com.row666.game.room.Room;

import java.awt.*;

public class GameState extends State {
    
    private Room room;
    private Camera camera;
    
    public GameState(Game game) {
        super(game);
        camera = new Camera(this,0,0);
        room = new Room(this, "assets/rooms/room.rm");
    }
    
    @Override
    public void tick() {
    
    }
    
    @Override
    public void render(Graphics g) {
        room.render(g);
    }
    
    public Room getRoom() {
        return room;
    }
    
    public Camera getCamera() {
        return camera;
    }
}