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
class ZombieObject extends Tile implements ActionListener{
	private int type, y, level;
	private Timer walkTimer;
	private ArrayList <Projectile> beams = new ArrayList<Projectile>();
	
	// ZombieObject Constructor
	public ZombieObject (int x, int y, int w, int h, int type, int level){
		
		// Call tile super class
		super(x,y,w,h,type);
		this.type = type;
		super.x = x; //super because the tile needs to move
		this.y = y;
		this.level = level;
		
		health = 100;
		attack = 5;
		state = true;
		
		
		walkTimer = new Timer(50, this);
		walkTimer.start();
	}
	
	public void setZombieFrameA(int a){
		if (a == 6){
			setType(0);
		}
		else {
			setType(a);
		}
	}
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == walkTimer){
			if(state == true){
				move();
			}
			if (level == 6){
				beams.add(new Projectile(x, y+15, 25, 40, 16, null));
			}
		}
	}
	public void pauseTimer(){
		walkTimer.stop();
	}
	
	public void resumeTimer(){
		walkTimer.restart();
	}
	
	public Timer getTimer(){
		return walkTimer;
	}
	//make zombie move
	public void move(){
		x--; 
	}
	
	//get beams method
	public ArrayList<Projectile> getAllProjectiles() {
		return beams;
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getZombieFrame(getType());
		g2d.drawImage(img, x, y, width, height, null);
	}
}