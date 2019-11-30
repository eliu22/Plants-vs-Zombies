import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;

class Projectile extends Tile{
	
	private int attack = 20, a;
	
	private ZombieObject follow;
	
	public int nsplit = 0;
	
	public Projectile(int x, int y, int w, int h, int type, ZombieObject follow){
		super(x, y, w, h, type);
		//this.type = type;
		this.follow = follow;
	}
	
	public void move(){
		if(follow == null) {
			if (type == 16){
				x -= 5;
			}
			else {
				x += 5;
			}
		} 
		else {
			double dx = follow.getX() - x;
			double dy = follow.getY() - y;
			double d = Math.sqrt(dx*dx+dy*dy);
			x += dx / d * 5;
			y += dy / d * 5;
		}
	}
	
	public int getAttack(){
		return attack;
	}
	
	public int getA(){
		return a;
	}
	
	public void draw(Graphics g) {
		if (type == 15){
			int num = (int) Math.random() * (3)  + 5;
			drawStar(x, y, 10, num, g);
		}
		else if (type == 16){
			recursiveShoot(x, y+20, x+10, y+20, 10, g);
		}
		else {
			super.draw(g);
		}
	}
	
	public void drawStar(int x1, int y1, int size1, int num1, Graphics g) {
		int endX, endY;
		
		//stop drawing arms when the length is 2
		if (size1 <= 2)
			return;
		
		//set color
		g.setColor(new Color(178, 237, 235));
		
		//draw arms from start point x,y
		for (int i = 0; i < num1; i++){
			endX = (int)(x1 + size1*Math.cos((2*Math.PI/num1)*i));
			endY = (int)(y1 - size1*Math.sin((2*Math.PI/num1)*i));
			g.drawLine(x1, y1, endX, endY);
			drawStar(endX, endY, size1/3, num1, g);
		}
	}
	
	public void recursiveShoot(int x1, int y1, int x2, int y2, int num, Graphics g){
		if (num == 0)
			return;
		
		//set color
		g.setColor(Color.RED);
		
		Projectile proj = new Projectile(x1, y1, 1, 1, 16, null);
		g.drawLine(x1, y1, x2, y2);
		
		recursiveShoot(x1-40, y1, x2, y2, num-1, g);
	}
}