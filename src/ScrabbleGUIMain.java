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

import javax.swing.JButton;

public class ScrabbleGUIMain extends JFrame {

	private JPanel contentPane;

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
		
		JPanel board = new JPanel();
		contentPane.add(board, BorderLayout.CENTER);
		board.setLayout(new GridLayout(15, 15, 0, 0));
		JButton[][] boardList = new JButton[15][15];
		for (JButton[] boardRow:boardList){
		for (JButton boardTile:boardRow){
			boardTile = new JButton("");
			boardTile.setBackground(Color.white);
			boardTile.addActionListener(new ButtonPress());
			board.add(boardTile);
		}
		}
		board.setEnabled(false);
	}
	private class ButtonPress implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ea) {
		}
	}
}
