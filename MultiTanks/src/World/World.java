package World;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import MathExpression.Parser;

public class World {
	public List<Tile> Tiles = new ArrayList<>();
	private Random random = new Random(); 
	
	public void GenerateRandomWorld(int size) {
		//Create the tiles
		for(int i = 0; i<size;i++) {
			Tiles.add(new Tile(i, 1, Color.black));
		}
		
		List<Tile> mountainList = new ArrayList<>();
		
		//Create the mountains
		int amountMountains = random.nextInt(3)+2;
		for(int mountains = 0; mountains < amountMountains; mountains++) { //Make x mountains
			int mountainPosition = random.nextInt(size);
			int mountainHeight = random.nextInt(3000);
			Tiles.get(mountainPosition).setY(mountainHeight);
			mountainList.add(Tiles.get(mountainPosition));
		}
		
		//Smoothen the mountains
		for(int i = 0; i<mountainList.size();i++) {
			Tile curMountain = mountainList.get(i);
			if(i+1==mountainList.size()) break;
			Tile nextMountain = mountainList.get(i+1);
			
			int xDistance = nextMountain.getX()-curMountain.getX(); //The x distance between both mountains
			int yDistance = nextMountain.getY()-curMountain.getY(); //The y distance between both mountains
			int distToTurningX = xDistance/2; //Half of the x distance
			int distToTurningY = yDistance/2; //Half of the y distance
			int turningX = curMountain.getX()+distToTurningX; //The x point where the turning point is
			int turningY = curMountain.getY()+distToTurningY; //The y point where the turning point is
			double xPerY = (double)distToTurningX/(double)distToTurningY; //The x distance that is used for every y distance
			double yPerX = (double)distToTurningY/(double)distToTurningX; //The y distance that is used for every x distance
			
			//TODO: Incoperate further smoothing at the start/end. Smoothing will be different depending on if goes to the turning point or the next mountain
			//Smooth the mountain from the current mountain to the turning point
			for(int i2 = 1; i2<distToTurningX; i2++) {
				try {
					Tile m = Tiles.get(i+i2);
					m.setY((int)(curMountain.getY()+i2*yPerX));
				}catch(ArrayIndexOutOfBoundsException ex) { break; }
			}
			//Smooth the mountain from turning point to the next mountain
			for(int i2 = distToTurningX+1; i2<nextMountain.getX(); i2++) {
				try {
					Tile m = Tiles.get(i+i2);
					m.setY((int)(curMountain.getY()+i2*yPerX));
				}catch(ArrayIndexOutOfBoundsException ex) { break; }
			}
		}
	}
	
	
	
	
	
	
	/*
	public void GenerateRandomWorld(int size) throws Exception {
		Tiles.clear();
		String expression = generateRandomExponentionalExpression();
		System.out.println(expression);
		
		for (int i = 0; i < size; i++) {
			System.out.print(Math.round(Parser.evaluateExpression(expression, i)));
			System.out.print("  ");
		}
		System.out.println();
		System.out.println();
	}
	*/
	
	
	/**
	 * Generates a exponentional expression of the form: "y*x^i + y*x^i + ...)
	 * y etc. are randomly generated; x are always x; i range from upper bound to lower bound
	 * IMPORTANT: The y and i are different for every sub-expression  
	 * @return A exponentional expression
	 */
	/*
	public String generateRandomExponentionalExpression() {
		String exp = ""; // The result
		int expUpperBound = 4; //The upper bound for the expression (...+x^10)
		int expLowerBound = -4; //The lower bound for the expression (x^-10+...)
		int notUsed = 0; //Generates at least every x 
		for(int i = expLowerBound; i<expUpperBound; i++) {
			int number = random.nextInt(2);
			if(number == 0 || notUsed>2) {
				double frontNumber = ThreadLocalRandom.current().nextDouble(-50, 50);
				if(frontNumber>=0) exp+="+";
				exp+=frontNumber+"*x^"+i;
				notUsed = 0;
			} else {
				notUsed++;
			}
		}
		
		int number = random.nextInt(150)-50;
		if(number>=0) exp+="+";
		exp+=number;
		
		return exp;
	}
	*/
}
