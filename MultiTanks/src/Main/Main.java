/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.*;
import Game.GameController;
import MathExpression.Parser;
import World.World;

/**
 *
 * @author Need
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	new GameController();
    	World w = new World();
    	try {
			w.GenerateRandomWorld(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
