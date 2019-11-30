/*
 * Liu_Summative
 * Elaine Liu
 * December 24, 2017
 * ICS4U1
 * Ms. Strelkovska
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//GameObject Class
class SunflowerObject extends Tile implements ActionListener{
	private int type, x, y;
	private Timer sunflowerTimer;
	private ArrayList<Sunshine> suns = new ArrayList<Sunshine>();

	// SunflowerObject Constructor
	public SunflowerObject (int x, int y, int w, int h, int type){
		
		// Call tile super class
		super(x,y,w,h,type);
		this.type = type;
		super.x = x;
		this.x = x;
		this.y = y;
		
		health = 150;
		
		sunflowerTimer = new Timer(10000, this); //speed has been increased
		sunflowerTimer.start();
	}
	
	public void setSunflowerFrame(int a){
		if (a == 8){
			super.setType(0);
		}
		else if (a == 15){
			super.setType(0);
		}
		else {
			super.setType(a);
		}
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getSunflowerFrame(getType());
		g2d.drawImage(img, x, y, width, height, null);
	}

	public ArrayList<Sunshine> getSuns(){
		return suns;
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == sunflowerTimer){
			setSunflowerFrame(9);
			suns.add(new Sunshine(x, y));
		}
	}
	
	public void pauseTimer(){
		sunflowerTimer.stop();
	}
	
	public void resumeTimer(){
		sunflowerTimer.restart();
	}
	
	public void startTimer(){
		sunflowerTimer.start();
	}
	
	public Timer getTimer(){
		return sunflowerTimer;
	}
}