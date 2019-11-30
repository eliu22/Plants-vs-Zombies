/*
 * Liu_Summative
 * Elaine Liu
 * December 12, 2017
 * ICS4U1
 * Ms. Strelkovska
 */
 
import java.awt.*;
import javax.swing.*;
import java.io.*;
 
class Sunshine extends Tile{
	private int x, y2;
	
	public Sunshine(int x1, int y1, int y2){
		super(x1, y1, 50, 50, 12);
		
		this.x = x1;
		this.y2 = y2;
	}
	
	public Sunshine(int x1, int y1){
		super(x1, y1, 50, 50, 12);
		
		this.x = x1;
	}
	
	//make sunshine fall
	public void move(){
		y++; 
	}
	
	public int getY2(){
		return y2;
	}
	
	public void collect(){
		//collected = true;
		y = -10000; //starting y loc 
		System.out.println(y);
	}

}