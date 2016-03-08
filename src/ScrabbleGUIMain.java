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

public class ScrabbleGUIMain extends JFrame {
	public JButton[][] boardList;
	private JPanel contentPane;
	public Board scrabbleBoard = new Board();
	public Player[] playerList;
	public Player currentPlayer = new Player();
	public JButton selectedTile;
	public JPanel board;
	public JPanel selections;
	private JRadioButton rdbtnExchange;
	private JRadioButton rdbtnPlace;
	private JButton btnExchange;
	private JLabel lblInstructions;
	private ArrayList<JButton> rack = new ArrayList<JButton>();
	private JPanel rackPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleGUIMain frame = new ScrabbleGUIMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScrabbleGUIMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToScrabble = new JLabel("Welcome to Scrabble");
		contentPane.add(lblWelcomeToScrabble, BorderLayout.NORTH);
		
		board = new JPanel();
		contentPane.add(board, BorderLayout.CENTER);
		board.setLayout(new GridLayout(15, 15, 0, 0));
		boardList = new JButton[15][15];
		for (JButton[] boardRow:boardList){
			for (JButton boardTile:boardRow){
				boardTile = new JButton("");
				boardTile.setBackground(Color.white);
				boardTile.addActionListener(new TilePress());
				board.add(boardTile);
			}
		}
		
		selections = new JPanel();
		contentPane.add(selections, BorderLayout.EAST);
		selections.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdbtnPlace = new JRadioButton("Place");
		selections.add(rdbtnPlace);
		
		rdbtnExchange = new JRadioButton("Exchange");
		selections.add(rdbtnExchange);
		
		btnExchange = new JButton("Execute Exchange");
		selections.add(btnExchange);
		
		
		lblInstructions = new JLabel("Hello, I am the instruction Label");
		contentPane.add(lblInstructions, BorderLayout.WEST);
		
		rackPanel = new JPanel();
		rackPanel.setLayout(new GridLayout(1,0,0,0));
		contentPane.add(rackPanel, BorderLayout.SOUTH);
		
		scrabbleBoard.getTiles(currentPlayer);
		for (Tile tile : currentPlayer.getTiles()){
			rackPanel.add(createButton(tile));
		}
		rackUpdate();
		contentPane.repaint();
		
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
		switchColor((JButton) ea.getSource());	
		}
	}
	private class rackPress implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
			selectedTile = (JButton) ea.getSource();
			switchColor(selectedTile);
			System.out.println(selectedTile.getText());
			
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
		for(JButton slot: rack){
			contentPane.add(slot, BorderLayout.SOUTH);
		}
	}
}
