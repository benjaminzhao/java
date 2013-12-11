

import java.awt.*;
import javax.swing.*;

public class Tetris extends JFrame {
	JLabel statusbar;
	
	public Tetris()
	{
//		statusbar = new JLabel("  0");
//		add(statusbar, BoarderLayout.SOUTH);
//		Board.board = new Board(this);
//		add(board);
//		board.start();
		super();
		InitUI();
	}
	
	private void InitUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("hello");
		button.setBounds(10, 10, 70, 30);
		
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		panel.add(button);
		
		setTitle("tetris");
		setSize(300,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] arg) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				tetris ex = new tetris();
				ex.setVisible(true);
			}
		});
		
	}
	

}