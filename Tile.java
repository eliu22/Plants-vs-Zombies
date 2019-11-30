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
import java.util.*;

import java.awt.image.BufferedImage;

// Tile Class
class Tile extends Rectangle /*implements Serializable*/{
	private int w, h;
	protected int attack = 0;
	protected int health = 0;
	protected int type;
	protected boolean state;
	// Tile Constructor
	public Tile(int x, int y, int w, int h, int type){
		// Call rectangle super class
		super(x,y,w,h);
		this.type = type;
		this.w = w;
		this.h = h;
	}
	
	// Draw tile method
	public void draw(Graphics g) {
		Graphics g2d = (Graphics2D)g;
		BufferedImage img = GameImages.getImage(type);
		g2d.drawImage(img, x, y, width, height, null);
	}
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
	
	// Get tile type method
	public int getType(){
		return type;
	}
	
	public void setType(int t) {
		type = t;
	}
	
	public int getAttack(){
		return attack;
	}
	
	public int getHealth(){
		System.out.println(health);
		return health;
	}
	
	public void damage(int attack){
		health -= attack;
	}
	public void setState(boolean b){
		state = b;
	}
	
	public int getLane(){
		return (int)(y-140)/70;
	}
	
	public void move(){
		x++;
	}
}