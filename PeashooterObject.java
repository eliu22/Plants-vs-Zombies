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

//PeashooterObject Class
class PeashooterObject extends Tile{
	private int type, y;
	private ArrayList <Projectile> peas = new ArrayList<Projectile>();
	private Map map;

	// PeashooterObject Constructor
	public PeashooterObject (Map map, int x, int y, int w, int h, int type){
		
		// Call tile super class
		super(x,y,w,h,type);
		this.type = type;
		super.x = x;
		this.y = y;
		this.map = map;
		health = 1000;
		state = true;
	}
	
	public void setPeashooterFrame(int a){
		if (a == 29){
			super.setType(0);
		}
		else {
			super.setType(a);
		}
	}
	
	//shooting peas method
	public void shoot(){
		if (state){ 
			ZombieObject follow = null; 
			if(map.getLevel() >= 5) {
				ArrayList<ZombieObject> toPick = new ArrayList<ZombieObject>();
				for(ZombieObject obj : map.getAllZombies()) {
					if(obj.getX() < 800) {
						toPick.add(obj);
					}
				}
				if(toPick.size() > 0) {
					follow = toPick.get((int)(Math.random() * toPick.size()));
				}
			}
			peas.add(new Projectile(x+30, y+15, 15, 15, 14, follow));
		}
	}
	
	public ArrayList<Projectile> getAllProjectiles(){
		return peas;
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getPeashooterFrame(getType());
		g2d.drawImage(img, x, y, width, height, null);
	}
}