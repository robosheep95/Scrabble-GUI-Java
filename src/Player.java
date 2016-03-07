/*
 * Sylvia Finger, Taylor Scafe
 * 1/17/2016
 * Player Class that holds player number and "Rack"
 */
import java.util.ArrayList;

public class Player {
	private int score;
	private int number;
	ArrayList<Tile> myTiles = new ArrayList<Tile>();
	
	
	public Player(int number){
		
		this.setNumber(number);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void addTile(Tile input ){
		myTiles.add(input);
	}
	public ArrayList<Tile> getTiles(){
		return myTiles;
	}
}