package main.com.row666.game;

import main.com.row666.game.display.Display;
import main.com.row666.game.input.KeyManager;
import main.com.row666.game.states.GameState;
import main.com.row666.game.states.StateManager;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

public class Game {
    
    private boolean running = false;
    
    private Display display;
    private String title;
    
    private GameState gameState;

    private KeyManager keyManager;
    private int width, height;
    private BufferStrategy bs;
    private Graphics g;
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear
        g.clearRect(0, 0, display.getCanvas().getWidth(), display.getCanvas().getHeight());
        
        //draw
        
        if(StateManager.getState() != null)
            StateManager.getState().render(g);
        //end
        bs.show();
        g.dispose();
    }
    
    private void tick() {
        keyManager.tick();
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }

    }
    
    private void init() {
        display = new Display(title, width, height);

        display.getJFrame().addKeyListener(keyManager);
        display.getCanvas().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            
            }
        });
        gameState = new GameState(this);
        StateManager.setState(gameState);
    }
    
    
    public static float fps = 60, tps = 60;
    public void start() {
        
        init();
        
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

            if(tDelta >= 1){
                tick();
                ticks++;
                tDelta--;
            }

            if(fDelta >= 1){
                render();
                frames++;
                fDelta--;
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
    
    public Display getDisplay() {
        return display;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
}
