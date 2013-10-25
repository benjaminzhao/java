package tetris2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;


public class TetrisMain extends Canvas implements Runnable, KeyListener{

	public static final int WIDTH = 400, HEIGHT = 565;
	
	public static void main(String args[]){
		
		JFrame frame = new JFrame("Tetris");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH, 25);
		frame.add(bar);
		
		JMenu file = new JMenu("File");
		//file.setBounds(0, 0, 40, 24);
		bar.add(file);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Starting Game...");
			}
		});
		JMenuItem highScore = new JMenuItem("High Score");
		highScore.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int highscore = 0;
				final JFrame alert = new JFrame("high score");
				alert.setSize(200, 200);
				alert.setLayout(null);
				alert.setResizable(false);
				alert.setLocationRelativeTo(null);
				alert.setVisible(true);
				
				JLabel score = new JLabel("Your High Score is: "+highscore);
				score.setBounds(35, 50, 130, 40);
				alert.add(score);
				
				JButton okayButton = new JButton("OK");
				okayButton.setBounds(50, 120, 100, 30);
				okayButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						alert.dispose();
					}
				});
				alert.add(okayButton);
			}
		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Closing...");
				System.exit(0);
			}
		});
		file.add(newGame);
		file.add(highScore);
		file.add(exit);
		
		JMenu about = new JMenu("About");
		bar.add(about);
		JMenuItem aboutinfo = new JMenuItem("Info");
		aboutinfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("about clicked...");
				final JFrame info = new JFrame("Info");
				info.setSize(200, 200);
				info.setLayout(null);
				info.setResizable(false);
				info.setLocationRelativeTo(null);
				info.setVisible(true);
				
				JLabel version = new JLabel("Ver 1.0");
				version.setBounds(35, 50, 130, 40);
				info.add(version);
			}
		});
		about.add(aboutinfo);
		
		TetrisMain tm = new TetrisMain();
		tm.setBounds(0,	25, WIDTH, HEIGHT-25);
		frame.add(tm);
		
		frame.setVisible(true);
		tm.start();
	}

	public void start(){
		Thread t = new Thread(this);
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}
	
	public void run(){
		boolean running = true;
		while(running)
		{
			update();
			BufferStrategy buf = getBufferStrategy();
			if(buf == null){
				createBufferStrategy(3);
				continue;
			}
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}
	}
	
	public void update(){
		
		
	}
	
	public void render(Graphics2D g){
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Calibri", Font.PLAIN, 40));
		g.drawString("Tetris", 150,	80);
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyReleased(KeyEvent e){
		
	}
}
