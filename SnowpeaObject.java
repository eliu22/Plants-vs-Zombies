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

//SnowpeaOject Class
class SnowpeaObject extends Tile{
	private int type, x, y;
	private ArrayList <Projectile> snowpeas = new ArrayList<Projectile>();
	private boolean state = true;
	//private Timer shootTimer;

	// SnowpeaObject Constructor
	public SnowpeaObject (int x, int y, int w, int h, int type){
		
		// Call tile super class
		super(x,y,w,h,type);
		this.type = type;
		this.x = x;
		this.y = y;

		health = 175;
	}
	
	public void setSnowpeaFrame(int a){
		if (a == 26){
			super.setType(0);
		}
		else {
			super.setType(a);
		}
	}
	
	public ArrayList<Projectile> getAllProjectiles(){
		return snowpeas;
	}
	public void shoot(){
		int size = snowpeas.size();
		for (int i = 0; i < size; i++) {
			Projectile curPea = snowpeas.get(i);
			if(curPea.nsplit < 2) {
				Projectile proj1 = new Projectile((int)curPea.getX(), (int)curPea.getY()+30, 15, 15, 15, null);
				proj1.nsplit = curPea.nsplit + 1;
				snowpeas.add(proj1);
				Projectile proj2 = new Projectile((int)curPea.getX(), (int)curPea.getY()-30, 15, 15, 15, null);
				proj2.nsplit = curPea.nsplit + 1;
				snowpeas.add(proj2);
				snowpeas.remove(i);
				i--;
			}
		}
		snowpeas.add(new Projectile(x+30, y+15, 15, 15, 15, null));
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getSnowpeaFrame(super.getType());
		g2d.drawImage(img, x, y, width, height, null);
	}
}