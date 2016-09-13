/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Player;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Need
 */
public class Tank {
    private double angle = 45;
    private double hp = 100;
    private double armor = 0;
    private boolean isDestroyed = false;
    private Point position; public Point getPosition() { return position; }
    
    private Image tankImage; public Image getTankImage() { return tankImage; }
    private Image barrelImage; public Image getBarrelImage() { return barrelImage; }
    
    public Tank(double hp, double angle, Point position) {
        this.angle = angle;
        this.hp = hp;
        this.position = position;

        LoadImages();
    }
    
    public Point calculateBarrelPosition() {
    	int x = position.x+10;
    	int y = position.y;
    	Point p = new Point(x,y);
    	return position;
    }
    
    private void LoadImages() {
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	InputStream tankStream = classLoader.getResourceAsStream("Tank.png");
    	InputStream barrelStream = classLoader.getResourceAsStream("Barrel.png");
    	
    	try { tankImage = ImageIO.read(tankStream); } catch (IOException e) { }
    	try { barrelImage = ImageIO.read(barrelStream); } catch (IOException e) { }
    }
    
    public void Hit(double damage) {
        hp -= (damage-(armor/10f));
        if(hp<=0) {
            isDestroyed = true;
        }
    }
}
