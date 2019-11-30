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

public class Almanac extends JPanel implements ActionListener{
	private Container container;
	private JPanel panel1, panel2, panel3, panel4, panel5;
	private JLabel title, subtitle1, subtitle2, imageLbl;
	private JButton peashooter, sunflower, wallnut, mine, snowpea, bonkChoy, zombie1, zombie2, zombie3, backBtn;
	private static final int PEA = 0, SUNFLOWER = 1, WALLNUT = 2, MINE = 3, BONKCHOY = 4, SNOWPEA = 5, ZOMBIE1 = 6, ZOMBIE2 = 7, ZOMBIE3 = 8;
	private Font newFont;
	private BufferedImage image;
	private Graphics g;
	
	public Almanac(){
		setLayout(new BorderLayout());
		
		//load images
		GameImages.loadImages();
		
		//right side panel4
		panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(350, 560));
		add(panel4, BorderLayout.EAST);
		image = GameImages.getAlmanacImage(0);
		imageLbl = new JLabel("");
		imageLbl.setIcon(new ImageIcon(image)); 
		panel4.add(imageLbl);
		
		//left side panel1
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel1.setPreferredSize(new Dimension(350, 550));
		
		//plants subtitle
		subtitle1 = new JLabel("Plants");
		subtitle1.setFont(new Font("Showcard Gothic", Font.BOLD, 27));
		panel1.add(subtitle1);
		
		add(panel1, BorderLayout.WEST);
		
		//plants panel
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,3,10,10));
		panel1.add(panel2);
		
		//Plants grid
		peashooter = createBtn("Peashooter", PEA, panel2);
		sunflower = createBtn("Sunflower", SUNFLOWER, panel2);		
		wallnut = createBtn("Wallnut", WALLNUT, panel2);	
		mine = createBtn("Potato Mine", MINE, panel2);		
		bonkChoy = createBtn("Bonk Choy", BONKCHOY, panel2);		
		snowpea = createBtn("Snowpea", SNOWPEA, panel2);
		
		//Zombies
		subtitle2 = new JLabel("Zombies", SwingConstants.CENTER);
		subtitle2.setFont(new Font("Showcard Gothic", Font.BOLD, 27));
		subtitle2.setPreferredSize(new Dimension(350,60));
		panel1.add(subtitle2);
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1,3,10,10));
		panel1.add(panel3);
		
		zombie1 = createBtn("Flying Joe", ZOMBIE1, panel3);		
		zombie2 = createBtn("Conehead", ZOMBIE2, panel3);		
		zombie3 = createBtn("Athlete", ZOMBIE3, panel3);
		
		//title
		title = new JLabel("", new ImageIcon("GameImages/logo.png"), SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(700, 140));
		add(title, BorderLayout.NORTH);

		//backbtn
		panel5 = new JPanel();
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(130, 30));
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.addActionListener(this);
		panel5.add(backBtn);
		add(panel5, BorderLayout.SOUTH);

	}
	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == backBtn)
			Menu.getAlmanacFrame().dispose(); //closes frame
		else if (evt.getSource() == peashooter) {
			image = GameImages.getAlmanacImage(0);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == sunflower){
			image = GameImages.getAlmanacImage(1);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == wallnut){
			image = GameImages.getAlmanacImage(2);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == mine){
			image = GameImages.getAlmanacImage(3);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == bonkChoy){
			image = GameImages.getAlmanacImage(4);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == snowpea){
			image = GameImages.getAlmanacImage(5);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == zombie1){
			image = GameImages.getAlmanacImage(6);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == zombie2){
			image = GameImages.getAlmanacImage(7);
			imageLbl.setIcon(new ImageIcon(image));
		}
		else if (evt.getSource() == zombie3){
			image = GameImages.getAlmanacImage(8);
			imageLbl.setIcon(new ImageIcon(image));
		}
		imageLbl.setIcon(new ImageIcon(image));
		
	}
	// Paint Component method
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	public JButton createBtn(String s, int t, JPanel p){
		JButton button = new JButton(s);
		button.setIcon(new ImageIcon(GameImages.getImage(t)));	
		button.setVerticalTextPosition(SwingConstants.TOP); 	//text at top
		button.setHorizontalTextPosition(SwingConstants.CENTER); 	//centre text
		button.setPreferredSize(new Dimension(105,105));
		p.add(button); 
		button.addActionListener(this);
		return button;
	}

}