/*
 * Liu_Summative
 * Elaine Liu
 * December 24, 2017
 * ICS4U1
 * Ms. Strelkovska
 */

import java.awt.Rectangle ; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

// Tile Class
class LawnMower extends Tile implements ActionListener{
	private int type, w, h;   
	private Timer myTimer;
	private boolean hit = false;
	
	// Tile Constructor
	public LawnMower(int x, int y, int w, int h, int type){
		super(x,y,w,h,10);
		this.type = type;
		this.w = w;
		this.h = h;
		
		myTimer = new Timer(10, this);
		myTimer.start();
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getImage(type);
		g2d.drawImage(img, x, y, width, height, null);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == myTimer){
			if(hit == true){ //if lawn mower has been hit, roll down the lane
				super.move();
			}
		}
	}
	
	public void setHit(){
		hit = true;
	}
	
	public boolean getHit(){
		return hit;
	}
}