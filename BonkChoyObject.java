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


//BonkChoyOject Class
public class BonkChoyObject extends Tile{
	// BonkChoyObject Constructor
	public BonkChoyObject (int x, int y, int w, int h, int type){
		
		// Call tile super class
		super(x,y,w,h,type);
		
		health = 200;
		attack = 10;
	}
	
	public void setBonkChoyFrame(int a){
		if (a == 7){
			super.setType(0);
		}
		else if (a == 23){
			super.setType(0);
		}
		else {
			super.setType(a);
		}
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getBonkChoyFrame(getType());
		g2d.drawImage(img, x, y-50, 110, 130, null); //since the bonkchoy image isnt 70x70 or similar, we have to alter the dimensions of the drawImage
	}
}