/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Need
 */
public class GUI extends JPanel {
    private JFrame GUIMain;
    
    private static boolean abort;
    private Thread thread;
    
    public GUI(JFrame frame) {
        GUIMain = frame;
    }
    
    public static void CreateGUI() {    	
        JFrame GUIMain = new JFrame();
        GUI gui = new GUI(GUIMain);
        GUIMain.setVisible(true);
        GUIMain.setSize(new Dimension(1000,600));
       // GUIMain.setDefaultCloseOperation(arg0);
        GUIMain.add(gui);
        Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!abort) {
					//gui.invalidate();
					gui.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
        });
        thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setBackground(Color.BLACK);
    	g2.fillRect(0, 0, GUIMain.getWidth(), GUIMain.getHeight());
    	
    	g2.setColor(Color.white);
    	g2.setStroke(new BasicStroke(1));
    	Random r = new Random();
    	for(int i = 0; i<200; i++) {
    		int x = r.nextInt(1000);
    		int y = r.nextInt(600);
    		g2.drawLine(x, y, x, y);
    	}
    }
}
