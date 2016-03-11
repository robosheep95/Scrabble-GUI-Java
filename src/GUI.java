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
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
	private JButton btnExchange, nextTurnBtn;
	private JTextField txtField;
	private JLabel askNumPlayers;
	
	
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
		btnExchange.setEnabled(false);
		eastPanel.add(btnExchange);
		btnExchange.addActionListener(new exchangeExecute());
		
		//this is where startup begins, i only have
		//westPanelRedraw(); here because then it paints it so I can see what i'm doing
		//i dont know how to redraw it otherwise
		
		
		//westPanelRedraw();
		//startUp();
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
				for(int i = 0;i<southPanel.getComponentCount();i++){
					if (southPanel.getComponent(i).getBackground() == Color.green){
						ArrayList<Tile> currentTiles = playerList[intCurrentPlayer].getTiles();
						input.setText(currentTiles.get(i).getLetter()+"");
						input.setBackground(Color.yellow);
						playerList[intCurrentPlayer].setScore(playerList[intCurrentPlayer].getScore() + currentTiles.get(i).getValue());
						playerList[intCurrentPlayer].removeTile(i);
						
						for(int j=0;j<centerPanel.getComponentCount();j++){
							JButton test = (JButton) centerPanel.getComponent(j);
							if(input.equals(test)){
								scrabbleBoard.setSpace(j, input.getText().charAt(0));
							}
						}
						
						southPanel.remove(input);
						rackUpdate();
						System.out.println("trigger");
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
				scrabbleBoard.getTiles(playerList[intCurrentPlayer]);
				nextTurnBtn.doClick();
				rdbtnPlace.doClick();
			}
			
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

	private class txtFieldButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			try{
				int temp = Integer.parseInt(txtField.getText());
				
				playerList = new Player[temp];
				
				if(temp<1){
					System.err.println("Illegal input");
				}
				else{
					for(int i=0; i < temp; i++){
						playerList[i] = new Player();
						scrabbleBoard.getTiles(playerList[i]);
						//System.out.println("worked");	
					}
					intCurrentPlayer = 0;
					btnExchange.setEnabled(true);
					westPanelRedraw();
					rackUpdate();
					//System.out.println(playerList.length);
				}
			}
			catch(NumberFormatException ex){
				System.err.println("Illegal input");
			}

		}
	}

	private class changePlayers implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			scrabbleBoard.getTiles(playerList[intCurrentPlayer]);
			if(intCurrentPlayer == playerList.length-1){
				intCurrentPlayer = 0;
			}
			else{
				intCurrentPlayer++;
			}
			boolCanExchange=true;
			westPanelRedraw();
			boardUpdate();
			rackUpdate();
			
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
		
		centerPanel.revalidate();
		centerPanel.repaint();
		repaint();
	}
	public void startUp(){
		JLabel askNumPlayers = new JLabel("How many players will there be? ");
		//not sure how to make the text box any smaller 
		txtField = new JTextField(1);
		westPanel.add(askNumPlayers, BorderLayout.WEST);
		westPanel.add(txtField, BorderLayout.WEST);
		txtField.addActionListener(new txtFieldButton());
		boolCanExchange = true;
	}
	
	
	public void westPanelRedraw(){
		westPanel.removeAll();
		
		//westPanel = new JPanel();
		//contentPane.add(westPanel, BorderLayout.WEST);
		//westPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel displayPlayer = new JLabel("Player " + (intCurrentPlayer+1) + "'s turn.");
		westPanel.add(displayPlayer);
		
		//displays players scores
		//it was throwing a NullPointerException
		for(int i=0; i<playerList.length; i++){
			JLabel displayScores = new JLabel();
			displayScores.setText("Player " + (i+1) + ": " + playerList[i].getScore());
			westPanel.add(displayScores);
		}
		
		//dont know how to repaint when the function isnt placed in the constructor
		
		nextTurnBtn = new JButton("Next Player");
		westPanel.add(nextTurnBtn);
		nextTurnBtn.addActionListener(new changePlayers());
		westPanel.revalidate();
		westPanel.repaint();

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
