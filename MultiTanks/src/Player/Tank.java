/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Items.Item;
import java.util.ArrayList;

/**
 *
 * @author Need
 */
public class Tank {
    private double angle;
    private double hp;
    private double armor;
    private boolean isDestroyed;
    private final ArrayList<Item> itemsInUse = new ArrayList<>();
    
    public Tank(double hp, double angle) {
        this.angle = angle;
        this.hp = hp;
    }
    
    public void Hit(double damage) {
        hp -= (damage-(armor/10f));
        if(hp<=0) {
            isDestroyed = true;
        }
    }
}
