/*
 * Taylor Scafe and Sylvia Finger
 * 1/17/2016
 * Methods and board creation
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	Bag objBag = new Bag();
	public Board(){//creates new bag and shuffles the bag. Then places a tile on the center position
		objBag.Shuffle();
		setSpace(112, objBag.Draw().getLetter());
	}
	
	private char[][] objBoard = new char[15][15];
	
	public boolean setLetter(ArrayList<Tile> tileList, Player player){ //Validates that player has the letter they are wanting to place then asks for where they want to place it
		Scanner oScan = new Scanner(System.in);			//Returns false when the player is done placing letters
		System.out.println(tileList);
		System.out.println("Choose a Letter or \"-\" to Cancel:  ");
		char input = oScan.nextLine().charAt(0);
		input = Character.toUpperCase(input);
		if (input == '-'){
			return false;
		}
		for(int i = 0;i<tileList.size();i++){
			if (input == tileList.get(i).getLetter()){
				System.out.print("Select position on board:  ");
				int inputPos = oScan.nextInt();
				System.out.println(input);
				if(setSpace(inputPos, input)){
					System.out.println("Letter Placed");
					player.setScore(player.getScore() + tileList.get(i).getValue());
					tileList.remove(i);
					return true;
				}
				else{
					System.out.println("There is already a letter here!");
				}
			}
		}
		System.out.println("You do not have this letter");
		return true;
	}
	private boolean setSpace(int space,char input){//Returns true if letter was able to be placed
		int x = space/15;
		int y = space%15;
		if (objBoard[x][y]<9){
			objBoard[x][y]=input;
			return true;
		}
		return false;
	}
	public void getTiles(Player objPlayer){//Adds tiles to the player's rack until they have 7 or the bag is empty
		int currentNumberofTiles = objPlayer.getTiles().size();
		for (int i = 0;i< 7-currentNumberofTiles;i++){
			if (objBag.Size()>0){
				objPlayer.addTile(objBag.Draw());
			}
		}
	}
	public String toString(){//Prints the table
		String output = "";
		for (int i=0;i<225;i++){
			if(objBoard[i/15][i%15]==0){
				output=output+" | " + String.format("%03d", i);
			}
			else{
				output =output +" |  " + objBoard[i/15][i%15]+" ";
			}
			if (i%15==14 && i/15<14){
				output = output + " |\n"
						+ " +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+\n";
			}
		}
		output = output + " |";
		return output;
	}
}
