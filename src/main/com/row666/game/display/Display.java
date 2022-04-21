package main.com.row666.game.display;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Display {
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){

        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(3);

        frame.setSize(width, height);
        frame.setResizable(true);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        canvas.setBackground(Color.BLACK);
        
        frame.add(canvas);
        frame.pack();


    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    public JFrame getJFrame() {
        return frame;
    }
    
}
