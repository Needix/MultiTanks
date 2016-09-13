/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import GUI.GUI;
import Game.Player.Player;
import Game.Player.Tank;
import Main.Main;

/**
 *
 * @author Need
 */
public class GameController {
	//GUI
	private GUI gui;
	
	//GameControl
	private GameState gameState;
	
	
    public void CreateGUI() {
        final GameController c = this;
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui = GUI.CreateGUI(c);
                StartGame();
            }
        });
    }

    public void StartGame() {
    	Player p1 = new Player("Test1", 1000);
    	Player p2 = new Player("Test1", 1000);
    	gameState.addPlayer(p1);
    	gameState.addPlayer(p2);
    }
    
    public void Draw(Graphics2D g2, JFrame frame) {
    	g2.setBackground(Color.white);
    	g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());
    	
    	for (Player player : gameState.Player) {
    		Tank tank = player.getTank();
    		Point pos = tank.getPosition();
    		Point barrelPos = tank.calculateBarrelPosition();
        	g2.drawImage(tank.getTankImage(), (int)pos.getX(), (int)pos.getY(), null);
        	g2.drawImage(tank.getBarrelImage(), (int)barrelPos.getX(), (int)barrelPos.getY(), null);
		}
    }
}
