/*
 * Taylor Scafe and Sylvia Finger
 * 1/17/2016
 * Methods and board creation
 */
import javax.swing.JButton;

public class Board {
	Bag objBag = new Bag();
	public Board(){
		objBag.Shuffle();
	}

	public boolean setSpace(JButton space,JButton input){//Returns true if letter was able to be placed
		if (space.getText().length()==0){
			space.setText(input.getText());
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
}
