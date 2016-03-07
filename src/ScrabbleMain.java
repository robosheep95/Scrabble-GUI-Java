/*
 * Taylor Scafe, Sylvia Finger, Tim Brunner
 * 1/17/2016
 * Main Class
 * This program does not validate words or positioning of letters.
 * This program only give a sandbox for scrabble to be played on.
 */
import java.util.ArrayList;
import java.util.Scanner;
public class ScrabbleMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board objBoard = new Board();
		Scanner scan = new Scanner(System.in);
		
		ArrayList<Player> listPlayers = new ArrayList<Player>();
		
		System.out.println("Welcome to Scrabble!");
		System.out.println("How many players will there be? ");
		int numOfPlayers = scan.nextInt();
		for(int i=0; i < numOfPlayers; i++){
			listPlayers.add(new Player(i));
			objBoard.getTiles(listPlayers.get(i));
		}
		
		boolean notDone = true;
		
		while(notDone){
			for(int i=0; i < numOfPlayers; i++){
				System.out.println("Player " + (i+1) + "  Score = " + listPlayers.get(i).getScore()
						+ ":\n\nBoard:\n" + objBoard.toString() + "\n\n");
				while(objBoard.setLetter(listPlayers.get(i).getTiles(), listPlayers.get(i)));
				objBoard.getTiles(listPlayers.get(i));
			
			}
			System.out.println("Are you done playing? ");
			if(scan.next().toLowerCase() == "yes"){
				notDone = false;
				System.out.println(objBoard);
			}
		}
		
		
		scan.close();
	}
	
}
