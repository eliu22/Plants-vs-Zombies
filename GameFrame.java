/*
 * Liu_Summative
 * Elaine Liu
 * December 24, 2017
 * ICS4U1
 * Ms. Strelkovska
 */
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//gameframe is the frame where the game will be played
class GameFrame extends JFrame{
	private Container c;
	private GamePanel panel;
	
	//play map constructor
	public GameFrame() {
		c = getContentPane();
		panel = new GamePanel();
		c.add(panel);
		c.addKeyListener(panel);
		
		c.addMouseListener(panel);
	}
	
	public GamePanel getGamePanel(){
		return panel;
	}
}