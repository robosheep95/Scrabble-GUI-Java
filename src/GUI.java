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
	public Player currentPlayer = new Player();//Will be variable
	public JButton selectedTile;
	public JPanel boardPanel;
	public JPanel selections;
	private JRadioButton rdbtnExchange;
	private JRadioButton rdbtnPlace;
	private JButton btnExchange;
	private JLabel lblInstructions;
	private JPanel rackPanel;
	public GUI() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, xSize, (ySize - 50));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToScrabble = new JLabel("Welcome to Scrabble");
		contentPane.add(lblWelcomeToScrabble, BorderLayout.NORTH);
		
		boardPanel = new JPanel();
		contentPane.add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(15, 15, 0, 0));
		for (int i = 0;i <255;i++){
			JButton temp = new JButton("");
			temp.setBackground(Color.white);
			temp.addActionListener(new TilePress());
			boardPanel.add(temp);
		}
		
		selections = new JPanel();
		contentPane.add(selections, BorderLayout.EAST);
		selections.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdbtnPlace = new JRadioButton("Place");
		rdbtnPlace.setSelected(true);
		rdbtnPlace.addActionListener(new radioClick());
		selections.add(rdbtnPlace);
		
		rdbtnExchange = new JRadioButton("Exchange");
		rdbtnExchange.addActionListener(new radioClick());
		selections.add(rdbtnExchange);
		
		btnExchange = new JButton("Execute Exchange");
		selections.add(btnExchange);
		btnExchange.addActionListener(new exchangeExecute());
		
		lblInstructions = new JLabel("Hello, I am the instruction Label");
		contentPane.add(lblInstructions, BorderLayout.WEST);
		
		rackPanel = new JPanel();
		rackPanel.setLayout(new GridLayout(1,0,0,0));
		contentPane.add(rackPanel, BorderLayout.SOUTH);
		
		rackUpdate();
		
		//temporary
		canExchange = true;

		String firstLetter = (scrabbleBoard.objBag.Draw().getLetter() + "");
		JButton middleTile = (JButton) boardPanel.getComponent(112);
		middleTile.setText(firstLetter);
		middleTile.setBackground(Color.yellow);

		
	}
	private JButton createButton(Tile tile) {
		JButton temp = new JButton();
		temp.setText(tile.getLetter()+"");
		temp.setBackground(Color.orange);
		temp.addActionListener(new rackPress());
		return temp;
	}
	private class TilePress implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			
			JButton input =  (JButton) ea.getSource();
			
			if(rdbtnPlace.isSelected()){
				if(!(selectedTile.getText().isEmpty()) && input.getBackground() == Color.WHITE){	
					switchColor(input);	
					input.setText(selectedTile.getText());
					rackPanel.remove(selectedTile);
					rackPanel.revalidate();
					selectedTile.setText("");
					rackPanel.repaint();
					canExchange = false;
				}	
			}
		}
	}
	private class rackPress implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if(rdbtnPlace.isSelected()){
				for (int i = 0; i<rackPanel.getComponentCount();i++){
					rackPanel.getComponent(i).setBackground(Color.orange);
				}
			}
			selectedTile = (JButton) ea.getSource();
			switchColor(selectedTile);
			System.out.println(selectedTile.getText());
			
		}
	}
	private class exchangeExecute implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			if(rdbtnExchange.isSelected()){
				for(int i=0; i<rackPanel.getComponentCount();){
					if(rackPanel.getComponent(i).getBackground() == Color.GREEN){
						rackPanel.remove(i);
						currentPlayer.removeTile(i);
					}
					else
						i++;
				}
				rackUpdate();
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
	
	private void rackUpdate(){
		rackPanel.removeAll();
		scrabbleBoard.getTiles(currentPlayer);
		for (Tile tile : currentPlayer.getTiles()){
			rackPanel.add(createButton(tile));
		}
		rackPanel.revalidate();
		rackPanel.repaint();
	}
}