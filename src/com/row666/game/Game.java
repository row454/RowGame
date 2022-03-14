package com.row666.game;

import com.row666.game.display.Display;
import com.row666.game.states.GameState;
import com.row666.game.states.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
    
    private boolean running = false;
    
    private Display display;
    private String title;
    private int width, height;
    
    private GameState gameState;
    
    private BufferStrategy bs;
    private Graphics g;
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear
        g.clearRect(0, 0, width, height);
        //draw
        
        if(StateManager.getState() != null)
            StateManager.getState().render(g);
        
        //end
        bs.show();
        g.dispose();
    }
    
    private void tick() {
    }
    
    private void init() {
        display = new Display(title, width, height);
        
        gameState = new GameState(this);
        StateManager.setState(gameState);
    }
    
    
    
    public void start() {
        
        init();
    
        int fps = 60;
        int tps = 60;
        double timePerFrame = 1000000000 / fps;
        double timePerTick = 1000000000 / tps;
        double fDelta = 0;
        double tDelta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        int frames = 0;
    
        while(running) {
            now = System.nanoTime();
            fDelta += (now - lastTime) / timePerFrame;
            tDelta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
        
            if(fDelta >= 1){
                render();
                frames++;
                fDelta--;
            }
            if(tDelta >= 1){
                tick();
                ticks++;
                tDelta--;
            }
        
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks + ", " + frames);
                ticks = 0;
                frames = 0;
                timer = 0;
            }
        }
    }
    
    public void run() {
        if(running) return;
        running = true;
        start();
    }
    public void stop() {
        running = false;
    }
    
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
