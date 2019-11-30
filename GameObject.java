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

//GameObject Class for mine and wallnut
class GameObject extends Tile /*implements Serializable*/{
	private int type, x, y;
	//public boolean right, left, up, down;
	
	// GameObject Constructor
	public GameObject (int x, int y, int w, int h, int type, Map map){
		// Call tile super class
		super(x,y,w,h,type);
		this.type = type;
		this.x = x;
		this.y = y;
		
		if(type == 2){
			attack = 0;
			health = 7000;
		}
		else if(type == 3){
			attack = 100;
			health = 1;
		}
	}
}

