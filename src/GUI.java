import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private boolean canExchange;
	
	private JPanel contentPane;
	public Board scrabbleBoard = new Board();
	public Player[] playerList;
	public int intCurrentPlayer;
	public int intSelectedTile;
	public JPanel eastPanel, westPanel, centerPanel, southPanel;
	private JRadioButton rdbtnExchange;
	private JRadioButton rdbtnPlace;
	private JButton btnExchange;
	
	
	public GUI(){
		super("Scrabble");
//-------------------------------------------------------------------------------------------

//Window setup
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBounds(0, 0, xSize, (ySize - 50));
		contentPane.setVisible(true);
		
//--------------------------------------------------------------------------------------------
		
//Area Setup		
		JLabel lblWelcomeToScrabble = new JLabel("Welcome to Scrabble");
		contentPane.add(lblWelcomeToScrabble, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(15, 15, 0, 0));
		
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
					playerList[intCurrentPlayer].removeTile(i);
					southPanel.remove(i);
					southPanel.revalidate();
					southPanel.repaint();
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
				for(int i=0; i<southPanel.getComponentCount();){
					if(southPanel.getComponent(i).getBackground() == Color.GREEN){
						playerList[intCurrentPlayer].removeTile(i);
					}
					else
						i++;
				}
			}	
		}
	}
	private class radioClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if(((JRadioButton) ea.getSource()).equals(rdbtnPlace)){
				rdbtnExchange.setSelected(false);
			}
			else if(((JRadioButton) ea.getSource()).equals(rdbtnExchange) && canExchange){
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
	
	private void boardUpdate(){
		
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
	
	private void rackUpdate(){
		southPanel.removeAll();
		scrabbleBoard.getTiles(playerList[intCurrentPlayer]);
		for (Tile tile : playerList[intCurrentPlayer].getTiles()){
			
		}
		southPanel.revalidate();
		southPanel.repaint();
	}
	
}//LAST BRACKET DO NOT REMOVE!!!!!!!