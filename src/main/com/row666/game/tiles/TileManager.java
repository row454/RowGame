package main.com.row666.game.tiles;

import main.com.row666.game.gfx.Assets;
import main.com.row666.game.floor.room.Room;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {
    
    private static Assets assets = Assets.getInstance();
    
    private static Tile[] tiles = new Tile[256];
    private static final Tile floorTile = new Tile(assets.getTiles().get(0), 1);
    private static final Tile wallTile = new ConnectingTile(assets.getTiles().subList(1, 6).toArray(new BufferedImage[0]), 0, true);
    
    private boolean initiated = false;
    private Tile[][] tileMap;
    private Room room;
    
    public TileManager(Room room) {
        this.room = room;
        
    }
    public void loadTiles(String[] tokens, int width, int height) {
        
        tileMap = new Tile[width][height];
        for(int y = 0;y < height;y++) {
            for(int x = 0;x < width;x++) {
                tileMap[x][y] = tiles[Integer.parseInt(tokens[x + y * width])];
            }
        }
    }
    
    public void render(Graphics g) {
        
        
        int xStart = (int) Math.max(0, room.getState().getCamera().getXOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(room.getWidth(), (room.getState().getCamera().getXOffset() +  room.getState().getGame().getDisplay().getCanvas().getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, room.getState().getCamera().getYOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(room.getHeight(), (room.getState().getCamera().getYOffset() + room.getState().getGame().getDisplay().getCanvas().getHeight()) / Tile.TILE_HEIGHT + 1);
        
        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, x, y, this);
            }
            
        }
        
    }
    
    public static void addTile(Tile t) {
        tiles[t.getId()] = t;
    }
    
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= tileMap.length || y >= tileMap[0].length)
            return floorTile;
        Tile t = tileMap[x][y];
        if (t == null)
            return floorTile;
        return t;
    }
    
    public Room getRoom() {
        return room;
    }
}
