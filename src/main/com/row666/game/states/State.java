package main.com.row666.game.states;

import main.com.row666.game.Game;

import java.awt.*;

public abstract class State {
    
    
    
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public Game getGame() {
        return game;
    }
}
