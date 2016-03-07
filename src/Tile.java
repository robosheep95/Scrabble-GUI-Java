/*
 * Tim Brunner
 * 1/17/2016
 * Tile Class
 */
public class Tile {

	private char letter;
	private int value;
	
	public Tile(char letter, int value) {//Constructor that takes a letter and a value for the tile
		this.letter = letter;
		this.value = value;
	}

	public char getLetter() {
		return letter;
	}
	
	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return "[" + letter + "-" + value + "]";
	}
	
	
	
}
