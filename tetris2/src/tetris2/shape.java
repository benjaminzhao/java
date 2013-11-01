package tetris2;

import java.util.*;
import java.lang.*;

public class shape {

	enum Tetriminoes{NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape};
	
	private Tetriminoes pieceShape;
	private int coords[][];
	private int[][][] coordsTable;
	
	public shape(){
		coords = new int[4][2];
		setShape(Tetriminoes.NoShape);
	}
	
	public void setShape(Tetriminoes sh){
		coordsTable = new int[][][]{
				{{ 0, 0},{ 0, 0},{ 0, 0},{ 0, 0}},
				{{ 0,-1},{ 0, 0},{-1, 0},{-1, 1}},
				{{ 0,-1},{ 0, 0},{ 1, 0},{ 1, 1}},
				{{ 0,-1},{ 0, 0},{ 0, 1},{ 0, 2}},
				{{-1, 0},{ 0, 0},{ 1, 0},{ 0, 1}},
				{{ 0, 0},{ 1, 0},{ 0, 1},{ 1, 1}},
				{{-1,-1},{ 0,-1},{ 0, 0},{ 0, 1}},
				{{ 1,-1},{ 0,-1},{ 0, 0},{ 0, 1}}
		};
		
		for(int i=0; i<4; i++){
			for(int j=0; j<2; j++){
				coords[i][j] = coordsTable[sh.ordinal()][i][j];
			}
		}
		pieceShape = sh;
	}
	public Tetriminoes getShape(){
		return pieceShape;
	}
	
	private void setX(int index, int x){
		coords[index][0] = x;
	}
	private void setY(int index, int y){
		coords[index][1] = y;
	}
	public int x(int index){
		return coords[index][0];
	}
	public int y(int index){
		return coords[index][1];
	}
	public int minX(){
		int m = coords[0][0];
		for(int i=0; i<4; i++){
			m = Math.min(m, coords[i][0]);
		}
		return m;
	}
	public int minY(){
		int m = coords[0][1];
		for(int i=0; i<4; i++){
			m = Math.min(m, coords[i][1]);
		}
		return m;
	}
	
	public void setRandomShape(){
		Random r = new Random();
		int x = Math.abs(r.nextInt()%7+1);
		Tetriminoes[] values = Tetriminoes.values();
		setShape(values[x]);
	}

	public shape rotateLeft(){
		if(pieceShape == Tetriminoes.SquareShape)
			return this;
		
		shape result = new shape();
		result.pieceShape = pieceShape;
		
		for(int i=0; i<4; i++){
			result.setX(i, y(i));
			result.setY(i, -x(i));
		}
		return result;
	}

	public shape rotateRight(){
		if(pieceShape == Tetriminoes.SquareShape)
			return this;
		
		shape result = new shape();
		result.pieceShape = pieceShape;
		
		for(int i=0; i<4; i++){
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}
		return result;
	}
}
