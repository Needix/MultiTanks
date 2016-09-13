/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import Game.Items.Abstract.AbstractItem;

/**
 *
 * @author Need
 */
public class Player {
    private String name; public String getName() { return name; }
    private int money; public int getMoney() { return money; }
    private Tank tank; public Tank getTank() { return tank; }
    private final ArrayList<AbstractItem> items = new ArrayList<>(); public Collection<AbstractItem> getItems() { return java.util.Collections.unmodifiableCollection(items); } 
     
    public Player(String name, int money) {
    	this.name = name;
    	this.money = money;
    }
    
    public void SetupTank(int startHP, int angle, Point point) {
    	tank = new Tank(startHP, angle, point);
    }
}
