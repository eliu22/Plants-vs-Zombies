/*
 * Liu_Summative
 * Elaine Liu
 * December 24, 2017
 * ICS4U1
 * Ms. Strelkovska
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class AlmanacFrame extends JFrame{
	private Container c;
	private Almanac panel;
	
	public AlmanacFrame(){
		//title
		setTitle("Plants and Zombies Almanac");
		c = getContentPane();
		panel = new Almanac();
		c.add(panel);
	}
	
	public void closeFrame(){
		dispose();
	}

}