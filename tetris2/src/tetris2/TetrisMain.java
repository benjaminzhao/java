package tetris2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import tetris2.Shape.Tetriminoes;

public class TetrisMain extends JFrame implements ActionListener{

	public static final int WIDTH = 200, HEIGHT = 425;

	final int BoardWidth = 10;
	final int BoardHeight = 20;
	
	boolean isStarted = false;
	boolean isPaused = false;
	boolean isFallingFinished = false;
	
	int numLinesRemoved = 0;
	public int curX = 0;
	public int curY = 0;
	public Shape curPiece;
	
	Tetriminoes[] board;//board array grid
	
	Timer timer;
	
	static TetrisMain game;
	
	public TetrisMain(){
		//final JFrame frame = new JFrame("Tetris");
		setTitle("Tetris");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		KeyGetter.loadKeys();
		try{
			Config.loadConfig();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){	
				keypressed(e);
			}
			public void keyReleased(KeyEvent e){
				keyreleased(e);
			}
		});
		
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH, 25);
		add(bar);
		
		JMenu file = new JMenu("File");
		//file.setBounds(0, 0, 40, 24);
		bar.add(file);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Starting Game...");
				game.start();
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
				alert.setAlwaysOnTop(true);
				
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
		JMenuItem options = new JMenuItem("Options");
		options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Config.openConfig(new JFrame("Options"));
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
		file.add(options);
		file.add(exit);
		
		JMenu about = new JMenu("About");
		bar.add(about);
		JMenuItem aboutinfo = new JMenuItem("Info");
		aboutinfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//System.out.println("about clicked...");
				final JFrame info = new JFrame("Info");
				info.setSize(200, 200);
				info.setLayout(null);
				info.setResizable(false);
				info.setLocationRelativeTo(null);
				info.setVisible(true);
				
				JLabel author = new JLabel("BB&HH Software");
				author.setBounds(35, 30, 130, 40);
				info.add(author);
				
				JLabel version = new JLabel("Ver 1.0");
				version.setBounds(35, 50, 130, 40);
				info.add(version);
				
				JLabel update = new JLabel("2013.11.01");
				update.setBounds(35, 70, 130, 40);
				info.add(update);
			}
		});
		about.add(aboutinfo);
		
		JPanel main_panel = new JPanel();
		main_panel.setBackground(new java.awt.Color(255, 204, 0));
		main_panel.setBounds(0, 25, WIDTH, HEIGHT-25);
		main_panel.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT-25));
		main_panel.setLayout(null);
		add(main_panel);

//		setVisible(true);
//		setFocusable(true);
		
	}
	
	public static void main(String args[]) {
		game = new TetrisMain();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
		
	}

	public void start(){
		
		curPiece = new Shape();
		timer = new Timer(1000,this);
				
		board = new Tetriminoes[BoardWidth*BoardHeight];
		clearBoard();
		
		if(isPaused)
			return;
		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		System.out.println("game starting...");
		clearBoard();
		newPiece();
		timer.start();
	}
	
	public void pause(){
		if(!isStarted)
			return;
		isPaused = false;
		if(isPaused){
			timer.stop();
			
		}else{
			timer.start();
		}
		repaint();
	}
	
	public void actionPerformed(ActionEvent e){
		if(isFallingFinished){
			isFallingFinished = false;
			System.out.println("new piece");
			newPiece();
		}else{
			System.out.println("one line down");
			oneLineDown();
		}
	}
	
	int squareWidth(){
		int w = (int)game.getSize().getWidth()/BoardWidth;
		//System.out.println("square width "+w);
		return w;
	}
	
	int squareHeight(){
		int h = (int)(getSize().getHeight()-25)/BoardHeight;
		//System.out.println("square height "+h);
		return h;
	}
	
	Tetriminoes shapeAt(int x, int y){
		return board[(y*BoardWidth)+x];
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		
		int boardTop = (int)(getSize().getHeight()) - BoardHeight * squareHeight();
		//System.out.println("board top "+boardTop);
		
		//draw all pieces
		for(int i=0; i<BoardHeight; ++i){
			for(int j=0; j<BoardWidth; ++j){
				Tetriminoes shape = shapeAt(j, BoardHeight-i-1);
				if(shape != Tetriminoes.NoShape)
					drawSquare(g, 0+j*squareWidth(), boardTop+i*squareHeight(),shape);
			}
		}
		//draw curPiece
		if(curPiece.getShape() != Tetriminoes.NoShape){
			for(int i=0; i<4; ++i){
				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);
				drawSquare(g, 0+x*squareWidth(), boardTop+(BoardHeight-y-1)*squareHeight(), curPiece.getShape());
			}
		}
	}
	
	private void drawSquare(Graphics g, int x, int y, Tetriminoes shape){
		
		Color colors[] = {new Color(0,0,0),	new Color(204,102,102),
			new Color(102,204,102),	new Color(102,102,204),
			new Color(204,204,102),	new Color(204,102,204),
			new Color(102,204,204),	new Color(218,170,  0)
		};
		
		Color color = colors[shape.ordinal()];//get tetri color
		
		g.setColor(color);
		g.fillRect(x+1, y+1, squareWidth()-2, squareHeight()-2);
		
		//draw square 3d effect
		g.setColor(color.brighter());
		g.drawLine(x, y+squareHeight()-1, x, y);//|
		g.drawLine(x, y, x+squareWidth()-1, y);//-
		//draw square 3d effect
		g.setColor(color.darker());
		g.drawLine(x+1, y+squareHeight()-1, x+squareWidth()-1, y+squareHeight()-1);//-
		g.drawLine(x+squareWidth()-1, y+squareHeight()-1, x+squareWidth()-1, y+1);//|
	}
	
	public void dropDown(){
		int newY = curY;
		while(newY>0){
			if(!tryMove(curPiece, curX, newY-1))
				break;
			--newY;
		}
		pieceDropped();
	}
	
	private void oneLineDown(){
		if(!tryMove(curPiece, curX, curY-1)){
			//if move down failed, piece dropped
			pieceDropped();
		}
	}
	
	private void clearBoard(){
		//fill board with empty NoShape
		for(int i=0; i<BoardHeight*BoardWidth; ++i){
			board[i] = Tetriminoes.NoShape;
		}
		System.out.println("board cleared");
		
	}
	
	private void pieceDropped(){
		
		for(int i=0; i<4; ++i){
			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			board[y*BoardWidth+x] = curPiece.getShape();
		}
		
		removeFullLines();
		
		if(!isFallingFinished)
			newPiece();
	}
	
	private void newPiece(){
		curPiece.setRandomShape();
		System.out.println(curPiece.getShape().toString());
		
		curX = BoardWidth/2+1;
		curY = BoardHeight-1+curPiece.minY();
		
		if(!tryMove(curPiece, curX, curY)){
			//if new piece can not move, game over
			curPiece.setShape(Tetriminoes.NoShape);
			timer.stop();
			isStarted = false;
			{
				System.out.println("Game Over!");
				final JFrame gameover = new JFrame("Game Over");
				gameover.setSize(200, 200);
				gameover.setLayout(null);
				gameover.setResizable(false);
				gameover.setLocationRelativeTo(null);
				gameover.setVisible(true);
				
				JLabel gover = new JLabel("Game Over");
				gover.setBounds(35, 30, 130, 40);
				gameover.add(gover);
			}
		}
	}
	
	private boolean tryMove(Shape newPiece, int newX, int newY){
		
		for(int i=0; i<4; ++i){
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if(x<0 || x>=BoardWidth || y<0 || y>=BoardHeight){
				return false;
			}
			if(shapeAt(x, y) != Tetriminoes.NoShape){
				return false;
			}
		}
		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();
		return true;
	}
	
	private void removeFullLines(){
		
		int numFullLines = 0;
		
		for(int i=BoardHeight-1; i>=0; --i){
			boolean LineIsFull = true;
			for(int j=0; j<BoardWidth; ++j){
				if(shapeAt(j, i) == Tetriminoes.NoShape){
					LineIsFull =false;
					break;
				}
			}
			if(LineIsFull){
				++numFullLines;
				for(int k=i; k<BoardHeight-1; ++k){
					for(int j=0; j<BoardWidth; ++j){
						board[k*BoardWidth+j] = shapeAt(j, k+1);
					}
				}
			}
		}
		
		if(numFullLines >= 4)
			numFullLines = 7;//bonus
		if(numFullLines > 0){
			numLinesRemoved += numFullLines;
			isFallingFinished = true;
			curPiece.setShape(Tetriminoes.NoShape);
			repaint();
		}
	}
	
	
//	public void render(Graphics2D g){
//		
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(0, 0, WIDTH, HEIGHT);
//		g.setColor(Color.YELLOW);
//		g.setFont(new Font("Calibri", Font.PLAIN, 40));
//		g.drawString("Tetris", 150,	80);	
//	}

	public void keypressed(KeyEvent e){
		
		String abc;
		int keycode = e.getKeyCode();
		
		if(KeyEvent.getKeyText(keycode).equals(Config.Left)){
			tryMove(curPiece, curX-1, curY);
			abc = "left";
		}
		else if(KeyEvent.getKeyText(keycode).equals(Config.Right)){
			tryMove(curPiece, curX+1, curY);
			abc = "right";
		}
		else if(KeyEvent.getKeyText(keycode).equals(Config.Down)){
			oneLineDown();
			abc = "down";
			//tryMove();
		}
		else if(KeyEvent.getKeyText(keycode).equals(Config.Rotate)){
			tryMove(curPiece.rotateLeft(), curX, curY);
			//tryMove(curPiece.rotateRight(),curX,curY);*3
			abc = "rotate";
		}
		else if(KeyEvent.getKeyText(keycode).equals(Config.Harddrop)){
			dropDown();
			abc = "harddrop";
		}
		else if(KeyEvent.getKeyText(keycode).equals(Config.Pause)){
			pause();
			abc = "pause";
		}
		else{
			abc = "other";
		}
		System.out.println("key "+abc+" pressed");
	}
	
	public static void keyreleased(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_S){
			
		}
	}
}
