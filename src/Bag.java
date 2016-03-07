/*
 * Tim Brunner
 * 1/28/2016
 * Bag Class (Does not include blank tiles)
 */
import java.util.ArrayList;
import java.util.Random;

public class Bag {

	private int [] numberOfLetters = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
	private int [] letterValue = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	private char [] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private ArrayList<Tile> objBag = new ArrayList<Tile>();
	
	public Bag()//Creates the tiles based on above information.
	{
		for(int j = 0; j < 26; j++)//Cycles through all 26 letters
		{
			for(int h = 0; h < numberOfLetters[j]; h++)
			{
				objBag.add(new Tile(letters[j],letterValue[j]));//References above tables for char and value	
			}
		}	
	}

	public Tile Draw()
	{
		Tile output = objBag.get(0);
		objBag.remove(0);
		return output;
	}
	
	public void Shuffle()//Shuffles bag by selecting random tiles and putting them in the back of the pile. (1000 times)
	{
		
		Random oRand = new Random();
		
		for(int i = 0; i < 1000; i++)
		{
			int random = oRand.nextInt(98);
			Tile output = objBag.get(random);
			objBag.remove(random);
			objBag.add(output);
		}
	}
	public int Size(){//Returns the size of the bag
		return objBag.size();
	}
}
