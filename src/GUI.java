import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private boolean boolCanExchange;
	//private Container window;
	private Container contentPane;
	public Board scrabbleBoard;
	public Player[] playerList;//TEMP PUBLIC
	public int intCurrentPlayer;//TEMP PUBLIC
	private JPanel eastPanel, westPanel, centerPanel, southPanel;
	private JRadioButton rdbtnExchange;
	private JRadioButton rdbtnPlace;
	private JButton btnExchange;
	
	
	public GUI(){
		super("Scrabble");
		
		//window.add(contentPane);
//-------------------------------------------------------------------------------------------

//Window setup
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrabbleBoard = new Board();
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
//--------------------------------------------------------------------------------------------
		
//Area Setup		
		JLabel lblWelcomeToScrabble = new JLabel("Welcome to Scrabble");
		contentPane.add(lblWelcomeToScrabble, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(15, 15, 0, 0));
		boardUpdate();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,0,0,0));
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
//--------------------------------------------------------------------------------------------
		
//Content setup	
		rdbtnPlace = new JRadioButton("Place");
		rdbtnPlace.setSelected(true);
		rdbtnPlace.addActionListener(new radioClick());
		eastPanel.add(rdbtnPlace);
		
		rdbtnExchange = new JRadioButton("Exchange");
		rdbtnExchange.addActionListener(new radioClick());
		eastPanel.add(rdbtnExchange);
		
		btnExchange = new JButton("Execute Exchange");
		eastPanel.add(btnExchange);
		btnExchange.addActionListener(new exchangeExecute());
		
		
		setBounds(0, 0, xSize, (ySize - 50));
		setVisible(true);
		repaint();
	}
	
//--------------------------------------------------------------------------------------------
	
//Listeners	

	private class tilePress implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			JButton input =  (JButton) ea.getSource();
			if(rdbtnPlace.isSelected() && input.getBackground() == Color.WHITE){
				System.out.println("test");
				for(int i = 0;i<southPanel.getComponentCount();i++){
					if (southPanel.getComponent(i).getBackground() == Color.green){
						ArrayList<Tile> currentTiles = playerList[intCurrentPlayer].getTiles();
						input.setText(currentTiles.get(i).getLetter()+"");
						input.setBackground(Color.yellow);
						playerList[intCurrentPlayer].removeTile(i);
						southPanel.remove(input);
						rackUpdate();
						boolCanExchange = false;
						break;
					}	
				}
			}
		}
	}
	private class rackPress implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if(rdbtnPlace.isSelected()){
				for (int i = 0; i<southPanel.getComponentCount();i++){
					southPanel.getComponent(i).setBackground(Color.orange);
				}
			}
			switchColor((JButton) ea.getSource());
		}
	}
	private class exchangeExecute implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if(rdbtnExchange.isSelected()){
				for(int i=0; i<playerList[intCurrentPlayer].getTiles().size();){
					if(southPanel.getComponent(i).getBackground() == Color.GREEN){
						System.out.println(playerList[intCurrentPlayer].getTiles());
						playerList[intCurrentPlayer].removeTile(i);
					}
					else
						i++;
				}
			}
			scrabbleBoard.getTiles(playerList[intCurrentPlayer]);
			rackUpdate();
		}
	}
	private class radioClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if(((JRadioButton) ea.getSource()).equals(rdbtnPlace)){
				rdbtnExchange.setSelected(false);
			}
			else if(((JRadioButton) ea.getSource()).equals(rdbtnExchange) && boolCanExchange){
				rdbtnPlace.setSelected(false);
			}
			else{
				rdbtnExchange.setSelected(false);
				//warning
				System.out.println("You can't exchange tiles after you have placed your letters.");
			}
		}
	}
	private class nextPlayerClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if (intCurrentPlayer == playerList.length-1){
				intCurrentPlayer = 0;
			}
			else{
				
			}
		}
	}
	
//--------------------------------------------------------------------------------------------
	
//Functions
	
	public void switchColor(JButton input) {
		if (input.getBackground()==Color.orange){
			input.setBackground(Color.green);
		}
		else if(input.getBackground()==Color.green){
			input.setBackground(Color.orange);
		}
		else if (input.getBackground()==Color.white){
			input.setBackground(Color.yellow);
		}
		else if (input.getBackground()==Color.yellow){
			input.setBackground(Color.white);
		}	
	}
	
	public void boardUpdate(){
		centerPanel.removeAll();
		char [][] tempBoard = scrabbleBoard.getObjBoard();
		for(int i = 0; i < tempBoard.length; i++)
		{
			for(int j = 0; j < tempBoard[i].length; j++)
			{
				JButton temp = new JButton();
				if(tempBoard[i][j] != 0)
				{
					temp.setText(tempBoard[i][j]+"");
					temp.setBackground(Color.CYAN);
				}
				else{
					temp.setBackground(Color.white);
				}
				temp.addActionListener(new tilePress());
				centerPanel.add(temp);
			}	
		}
		boolCanExchange=true;
		centerPanel.revalidate();
		centerPanel.repaint();
		repaint();
	}
	public void startUp(){//Saliva
		//ask # of players
		//create players and add to list
		// set instructions
	}
	
	
	public void westPanelRedraw(){
		//wipe startup west panel
		//display current player
		//display all players score
		
		//draw next turn button in constructor
	}
	
	public void rackUpdate(){
		southPanel.removeAll();
		//scrabbleBoard.getTiles(playerList[intCurrentPlayer]);
		for (Tile tile : playerList[intCurrentPlayer].getTiles()){
			JButton temp = new JButton();
			temp.setText(tile.getLetter()+"");
			temp.setBackground(Color.orange);
			temp.addActionListener(new rackPress());
			southPanel.add(temp);
		}
		southPanel.revalidate();
		southPanel.repaint();
	}
	
}//LAST BRACKET DO NOT REMOVE!!!!!!!
