package tetris2;

import javax.swing.*;

import tetris2.Shape.Tetriminoes;

import java.awt.*;
import java.awt.event.*;



public class Board extends JPanel implements ActionListener{

	boolean isStarted = false;
	boolean isPaused = false;
	boolean isFallingFinished = false;
	
	int numLineRemoved = 0;
	int curX = 0;
	int curY = 0;
	Shape curPiece;
	Shape.Tetriminoes[] board;
	
	static Timer timer;
	
	public Board(){
		//setFocusable(true);
		curPiece = new Shape();
		timer = new Timer(400,this);
		timer.start();
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(!isFallingFinished){
			isFallingFinished = false;
			newPiece();
		}else{
			oneLineDown();
		}
	}
	
	
	
	public void start(){
		
	}
	
	public void pause(){
		
	}
	
	public void paint(Graphics g){
		
	}
	
	private void dropDown(){
		
	}
	
	private void oneLineDown(){
		
	}
	
	private void clearBoard(){
		
	}
	
	private void pieceBoard(){
		
	}
	
	private void newPiece(){
		
	}
	
	private boolean tryMove(Shape newPiece, int newX, int newY){
		
	}
	
	private void removeFullLines(){
		
	}
	
	private void drawSquare(){
		
	}
	
	class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(!isStarted || curPiece.getShape() == Tetriminoes.NoShape){
				return;
			}
			int keycode = e.getKeyCode();
			
			if()
			
				
		}
	}
	
}
