import javax.swing.JFrame;

public class ScrabbleMain{
	public static void main(String[] args) {
		GUI Window = new GUI();
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window.intCurrentPlayer=0;
		Window.playerList = new Player[2];
		Window.playerList[0] = new Player();
		Window.playerList[1] = new Player();
		Window.scrabbleBoard.getTiles(Window.playerList[0]);
		Window.scrabbleBoard.getTiles(Window.playerList[1]);
		Window.rackUpdate();
	}
}