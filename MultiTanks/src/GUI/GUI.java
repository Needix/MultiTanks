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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.GameController;

/**
 *
 * @author Need
 */
public class GUI extends JPanel {
    private JFrame GUIMain;
    
    private GameController controller;
    
    private static boolean abort;
    private Thread thread; public void setThread(Thread t) { thread = t; }
    
    public GUI(JFrame frame, GameController controller) {
        GUIMain = frame;
        this.controller = controller;
    }
    
    public static GUI CreateGUI(GameController controller) { 
        JFrame GUIMain = new JFrame();
        final GUI gui = new GUI(GUIMain, controller);
        
        GUIMain.setVisible(true);
        GUIMain.setSize(new Dimension(1000,600));
        GUIMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUIMain.add(gui);
        Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!abort) {
					gui.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
        });
        gui.setThread(thread);
        thread.start();
        return gui;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	controller.Draw((Graphics2D)g, GUIMain);
    }
}
