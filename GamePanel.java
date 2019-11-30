/*
 * Liu_delRosario_Summative
 * Elaine Liu, Izach del Rosario
 * January 22, 2017
 * ICS4U1
 * Ms. Strelkovska
 */
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.filechooser.*;
import java.io.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
	private Graphics g;
	private Map map;
	private JPanel p1, p2;
	private JLabel sunCounterLbl, instructionsLbl, saveLbl;
	private JButton backBtn, pauseBtn, resumeBtn, saveBtn, peashooter, sunflower, wallnut, mine, bonkChoy, snowpea, zombie1, zombie2, zombie3;
	private static final int PEA = 0, SUNFLOWER = 1, WALLNUT = 2, MINE = 3, BONKCHOY = 4, SNOWPEA = 5, ZOMBIE1 = 14, ZOMBIE2 = 7, ZOMBIE3 = 8, GRASS = 9; //constants
	private int type, sunCounter = 1000; 
	private boolean painted = false; //indicates if sunshine is already on the panel
	private Timer sunTimer, gameTimer, gameObjectsTimer; //sunshine time
	private SavedMap sm; 
	private JFileChooser fc;
	private boolean paused = false;

	//GamePanel constructor
	public GamePanel(){
		
		//load images
		GameImages.loadImages();
		
		addMouseListener(this); //add mouse listener
		addKeyListener(this); //add key listener to panel
		setFocusable(true); //set focusable true
		requestFocusInWindow(); 
		
		//file chooser
		fc = new JFileChooser();
		
		//set layouts for the panel 1 (adding plants panel)
		setLayout(new BorderLayout());
		p1 = new JPanel (new FlowLayout(FlowLayout.CENTER, 10, 10));
		p1.setPreferredSize(new Dimension(345, 420));
		p1.setBackground(new Color(209, 183, 156));
		add(p1, BorderLayout.EAST);
		
		//instructions panel
		p2 = new JPanel();
		p2.setPreferredSize(new Dimension(700, 160));
		p2.setOpaque(false);
		add(p2, BorderLayout.NORTH);
		
		//sun counter label
		sunCounterLbl = new JLabel("Sun Count: " + sunCounter);
		sunCounterLbl.setFont(new Font("Consolas", Font.BOLD, 28));
		p2.add(sunCounterLbl);
		
		//instructions
		instructionsLbl = new JLabel("Add sunflowers to gain sunlight. Use sunlight to purchase plants to defend your house from the zombies!");
		instructionsLbl.setFont(new Font("Consolas", Font.BOLD, 18));
		p2.add(instructionsLbl);
		
		//Plants Buttons
		peashooter = createBtn(PEA, "100");
		sunflower = createBtn(SUNFLOWER, "50");
		wallnut = createBtn(WALLNUT, "50");
		mine = createBtn(MINE, "25");
		bonkChoy = createBtn(BONKCHOY, "175");
		snowpea = createBtn(SNOWPEA, "275"); 

		//more Buttons with different functions
		backBtn = createBtn("Back"); //back button
		pauseBtn = createBtn("Pause Game"); //pause button
		resumeBtn = createBtn("Resume Game"); //resume button
		saveBtn = createBtn("Save Game"); //save button
		
		//saveLbl to indicate if map is saved properly
		saveLbl = new JLabel("                                                      "); //temp
		p1.add(saveLbl);
		
		//start timers
        sunTimer = new Timer(20, this); 
		sunTimer.start();
		
		gameTimer = new Timer(60, this); 
		gameTimer.start();
		
		gameObjectsTimer = new Timer(700, this);
		gameObjectsTimer.start();
	}
	
	// Set the map on game panel method
	public void setMap(Map m){
		
		//start timers if not running yet
		if (!sunTimer.isRunning())
			sunTimer.start();
		
		if (!gameTimer.isRunning())
			gameTimer.start();
		
		if (!gameObjectsTimer.isRunning())
			gameObjectsTimer.start();
		
		//load map and zombies onto Game Panel
		map = m;	
		map.loadZombies();
		
		//if final battle level, change suncounter to 600
		if (map.getLevel() == 6){
			sunCounter = 600;
			sunCounterLbl.setText("Sun Count: " + sunCounter);
		}
		
		repaint(); //repaint everything
	}
	
	// Set saved map on game panel method
	public void setMap(SavedMap m){
		
		//start timers
		if (!sunTimer.isRunning())
			sunTimer.start();
		
		if (!gameTimer.isRunning())
			gameTimer.start();
		
		if (!gameObjectsTimer.isRunning())
			gameObjectsTimer.start();
		
		//load map onto Game Panel
		map = m;	
		
		//load the saved sunflowers and zombies back onto map
		for (int i = 0; i < map.getAllSunflowers().size(); i++){
			if (!map.getAllSunflowers().get(i).getTimer().isRunning())
				map.getAllSunflowers().get(i).startTimer();
		}
		
		for (int i = 0; i < map.getAllZombies().size(); i++){
			if (!map.getAllZombies().get(i).getTimer().isRunning())
				map.getAllZombies().get(i).resumeTimer();
		}
		
		repaint(); //repaint everything
	}
	
	//Create JButton method
	public JButton createBtn(String s) {
		JButton button = new JButton(s);
		button.setPreferredSize(new Dimension(200, 30));
		button.setFont(new Font("Consolas", Font.BOLD, 14));
		button.addActionListener(this);
		p1.add(button);
		
		return button;
	}
	
	//Create JButton method (for adding plants)
	public JButton createBtn(int type, String s) {
		JButton button = new JButton(s, new ImageIcon(GameImages.getImage(type)));
		button.setPreferredSize(new Dimension(130, 90));
		button.setVerticalTextPosition(SwingConstants.TOP); 	//text at top
		button.setHorizontalTextPosition(SwingConstants.CENTER); 	//centre text
		button.setFont(new Font("Consolas", Font.BOLD, 14));
		button.addActionListener(this);
		p1.add(button);
		
		return button;
	}
	
	// Paint Component method
	public void paintComponent(Graphics g){
		super.paintComponent(g);
			
		//draw background
		g.drawImage(GameImages.getImage(13), 0, 0, null);
		
		//draw all tiles and objects using drawAllObjects method
		drawAllObjects(g);
		
		//set location of instructions
		p2.setLocation(0,0);
		sunCounterLbl.setLocation(10,10);
		instructionsLbl.setLocation(10,60);
		
		//paint falling sunshine
		if (painted == false){
			for (int i = 0; i < 30; i++){
				int x1 = (int)(Math.random() * ((500 - 80) + 1)) + 80; //random x loc between 80 and 560
				int y1 = i * (-1000); //starting y loc 
				int y2 = (int)(Math.random() * ((400 - 120) + 1)) + 120; //random y ending loc between 120 and 400
				map.addSunshine(new Sunshine(x1, y1, y2));
				map.getAllSunshine().get(i).draw(g);
			}
			painted = true;
		}
		else {
		
			//repaint sunshine
			for(int i = 0; i <map.getAllSunshine().size(); i++)
				map.getAllSunshine().get(i).draw(g);
		}
		
		//paint sunflower sunshine
		for (int i = 0; i < map.getAllSunflowers().size(); i++){
			for(int j = 0; j < map.getAllSunflowers().get(i).getSuns().size(); j++)
				map.getAllSunflowers().get(i).getSuns().get(j).draw(g);
		}
	}
	
	public void actionPerformed(ActionEvent ae) {

		//continue to repaint sunshine
		if (ae.getSource() == sunTimer) {
			for(int i = 0; i < map.getAllSunshine().size(); i++){
				if (map.getAllSunshine().get(i).getY() != map.getAllSunshine().get(i).getY2())
					map.getAllSunshine().get(i).move();
			}
			repaint();
		}
		
		//gameObjectsTimer
		if (ae.getSource() == gameObjectsTimer){
			
			//shoot peas from peashooters according to timer delay
			for (int i = 0; i < map.getAllPeashooters().size(); i++)
					map.getAllPeashooters().get(i).shoot();
			
			//shoot snowflakes from snowpeas according to timer delay
			for (int i = 0; i < map.getAllSnowpeas().size(); i++)
				map.getAllSnowpeas().get(i).shoot();
		}
		
		//gameTimer
		if (ae.getSource() == gameTimer) {

			//animate plants and zombies
			animateZombies();
			animateSunflowers();
			animatePeashooters();
			animateBonkChoys();
			animateSnowpeas();
			
			//animate lawn mowers
			for (int i = 0; i < map.getAllLawnMowers().size(); i++){
				if (map.getAllLawnMowers().get(i).getHit())
					map.getAllLawnMowers().get(i).move(); //if lawnmower has been hit by zombie, move forward
			}
			
			//collision
			
			//zombie > sunflower
			collide(map.getAllSunflowers(), map.getAllZombies(), true); 
			
			//zombie > peashooter
			collide(map.getAllPeashooters(), map.getAllZombies(), true); 
			
			//projectile > zombie
			for(int i = 0; i < map.getAllPeashooters().size(); i++)
				collide(map.getAllZombies(), map.getAllPeashooters().get(i).getAllProjectiles(), false);			
			
			//zombie projectile > plant
			for (int i = 0; i < map.getAllZombies().size(); i++)
				collide(map.getAllPlants(), map.getAllZombies().get(i).getAllProjectiles(), false);
			
			//zombie projectile > sunflower
			for (int i = 0; i < map.getAllZombies().size(); i++)
				collide(map.getAllSunflowers(), map.getAllZombies().get(i).getAllProjectiles(), false);

			//zombie projectile > peashooter
			for (int i = 0; i < map.getAllZombies().size(); i++)
				collide(map.getAllPeashooters(), map.getAllZombies().get(i).getAllProjectiles(), false);
			
			//zombie projectile > snowpea
			for (int i = 0; i < map.getAllZombies().size(); i++)
				collide(map.getAllSnowpeas(), map.getAllZombies().get(i).getAllProjectiles(), false);

			//zombie projectile > bonkchoy
			for (int i = 0; i < map.getAllZombies().size(); i++)
				collide(map.getAllBonkChoys(), map.getAllZombies().get(i).getAllProjectiles(), false);
					
			//zombie > snowpea
			collide(map.getAllSnowpeas(), map.getAllZombies(), true);
			
			//snow projectiles > zombie
			for(int i = 0; i < map.getAllSnowpeas().size(); i++)
				collide(map.getAllZombies(), map.getAllSnowpeas().get(i).getAllProjectiles(), false);
			
			//zombie > wallnut/mine
			collide(map.getAllPlants(), map.getAllZombies(), true);
			
			//zombie > bonkchoy
			collide(map.getAllBonkChoys(), map.getAllZombies(), true);
		
			//temp gameobjects to check lawnmower collision
			Tile temp1;
			Tile temp2;
			if  (map.getAllZombies().size() != 0){
				
				for (int i = 0; i < map.getAllZombies().size(); i++){
					temp1 = map.getAllZombies().get(i); //store the current iteration of the zombie into temp1
					
					for (int j = 0; j < map.getAllLawnMowers().size(); j++){
						temp2 = map.getAllLawnMowers().get(j); //store current iteration of lawnmower into temp2
						
						//if the zombie collides with lawn mower
						if (collideCheck(temp1, temp2) && temp1.getLane() == temp2.getLane() && map.getAllLawnMowers().get(j).getType() == 10){
							map.getAllZombies().remove(temp1); //remove the zombie
							map.getAllLawnMowers().get(j).setHit(); //set the lawnmower to hit; this will make the lawnmower move forward using the lawnmower timer
						}
						
						//remove lawnmower when it reaches the end of the lane
						if (temp2.getX() > 700)
							map.getAllLawnMowers().remove(j);
					} 
					
					//check if the game has been lost
					if (map.getAllLawnMowers().size() == 0 && temp1.getX() == 0){
						pauseGame();
						JOptionPane.showMessageDialog(null, "You have lost the game!", "", JOptionPane.PLAIN_MESSAGE );	//show end game message
						System.exit(0);
					}
				}
			}
			
			//if there are no more zombies, you win!
			else if (map.getAllZombies().size() == 0){
				pauseGame();
				JOptionPane.showMessageDialog(null, "You have won the game!", "", JOptionPane.PLAIN_MESSAGE ); //show end game message
				System.exit(0);
			}

			repaint(); //repaint everything
		}
		
		//back button
		else if (ae.getSource() == backBtn){
			pauseGame();
			Menu.getGameFrame().dispose(); //closes frame			
		}
		
		//pause action
		else if (ae.getSource() == pauseBtn)
			pauseGame();

		
		//resume action
		else if (ae.getSource() == resumeBtn)
			resumeGame();
		
		//save action
		else if (ae.getSource() == saveBtn){
			
			//create a saved map
			sm = createSavedMap(map.getLevel());
			
			//pause the timers
			pauseGame();
			
			try{
				int returnVal = fc.showSaveDialog(this); // open save dialog
				if (returnVal == JFileChooser.APPROVE_OPTION) {  //if save dialog = file chooser
					File file = fc.getSelectedFile(); // user chooses a file name
					saveMethod(file); // save method saves as a SavedMap object
				}
				
				//if user cancels save
				else {
					saveLbl.setText("Save command cancelled by user.");
					resumeGame();
				}	
			 }
			 //catch exceptions
			 catch(Exception ex){
				System.out.print(ex);
			 }
		}
		
		//choosing the plant to place on map
		if (!paused){
			if (ae.getSource() == peashooter)
				type = PEA;
			else if (ae.getSource() == sunflower)
				type = SUNFLOWER;
			else if (ae.getSource() == wallnut)
				type = WALLNUT;
			else if (ae.getSource() == mine)
				type = MINE;
			else if (ae.getSource() == bonkChoy)
				type = BONKCHOY;
			else if (ae.getSource() == snowpea)
				type = SNOWPEA;
		}
	}
	
	//collide method
	public void collide(ArrayList arr1, ArrayList arr2, boolean b){
		
		//create temp game objects
		Tile temp1;
		Tile temp2;
		for(int i = 0; i < arr1.size(); i++) { //iterate through the first arraylist
			temp1 = (Tile)arr1.get(i); //store the current iteration of the list in temp 1
			for(int j = 0; j < arr2.size(); j++) { //iterate through the second arraylist
				temp2 = (Tile)arr2.get(j);  //store the current iteration of the list in temp2
				if(collideCheck(temp1, temp2) == true) { //if objects collide
					temp1.damage(temp2.getAttack()); //reduce damage of the first object
					if(b == true) // boolean is to determine the type of collision, b == true is for non-projectile collisions
						temp2.setState(false); //zombie stops moving
					
					if(temp1.getAttack() > 0) //if the plant has damage abilities, reduce the zombie's health by the attack rating
						temp2.damage(temp1.getAttack());
	
					if(temp2.getHealth() <= 0) //if the zombie has 0 health, it is dead
						arr2.remove(temp2);
						
					if(temp1.getHealth() <= 0){ //if the plant has zero health, it is dead
						arr1.remove(temp1);
						if(b == true) 
							temp2.setState(true); //zombie moves forward
					}
				}
			}
		}
	}
	
	//check rectangle collision
	public boolean collideCheck(Rectangle obj1, Rectangle obj2) {
		boolean b = false;
		
		if(obj1.intersects(obj2))
			b = true;
		
		return b;
	}
	
	//method to draw all objects, gets all the object arraylists from map class and draws them
	public void drawAllObjects(Graphics g) {
		for (int i = 0; i < map.getAllTiles().size(); i++)
			map.getAllTiles().get(i).draw(g); //draw all tiles

		for (int i = 0; i < map.getAllLawnMowers().size(); i++)
			map.getAllLawnMowers().get(i).draw(g); //draw lawnmowers

		for (int i = 0; i < map.getAllPlants().size(); i++)
			map.getAllPlants().get(i).draw(g); //draw mine and wallnut plants

		for (int i = 0; i < map.getAllZombies().size(); i++){
			map.getAllZombies().get(i).draw(g); //draw zombies
			for(int j = 0; j < map.getAllZombies().get(i).getAllProjectiles().size(); j++){
				map.getAllZombies().get(i).getAllProjectiles().get(j).draw(g); //if final battle, draw zombie's projectile beams
				map.getAllZombies().get(i).getAllProjectiles().get(j).move(); //beams continue to shoot
			}
		}
		
		for (int i = 0; i < map.getAllSunflowers().size(); i++)
			map.getAllSunflowers().get(i).draw(g); //draw all sunflowers

		for (int i = 0; i < map.getAllPeashooters().size(); i++){
			map.getAllPeashooters().get(i).draw(g); //draw all peashooters
			for(int j = 0; j < map.getAllPeashooters().get(i).getAllProjectiles().size(); j++){
				map.getAllPeashooters().get(i).getAllProjectiles().get(j).draw(g); //draw all peashooter projectiles
				map.getAllPeashooters().get(i).getAllProjectiles().get(j).move(); //projectiles continue to move
				if((int)map.getAllPeashooters().get(i).getAllProjectiles().get(j).getX() > 700 || (int)map.getAllPeashooters().get(i).getAllProjectiles().get(j).getX() == 0){
					map.getAllPeashooters().get(i).getAllProjectiles().remove(j); //remove projectiles if they are off the map
					j--;
				}
			}
		}
		
		for (int i = 0; i < map.getAllBonkChoys().size(); i++)
			map.getAllBonkChoys().get(i).draw(g); //draw all bonkchoys

		for (int i = 0; i < map.getAllSnowpeas().size(); i++){
			map.getAllSnowpeas().get(i).draw(g); //draw all snowpeas
			for (int j = 0; j < map.getAllSnowpeas().get(i).getAllProjectiles().size(); j++){				
				map.getAllSnowpeas().get(i).getAllProjectiles().get(j).draw(g); //draw all snowpea projectiles 
				map.getAllSnowpeas().get(i).getAllProjectiles().get(j).move(); //continue to move projectiles
				if((int)map.getAllSnowpeas().get(i).getAllProjectiles().get(j).getX() > 700){
					map.getAllSnowpeas().get(i).getAllProjectiles().remove(map.getAllSnowpeas().get(i).getAllProjectiles().get(j)); //remove projectiles if they are off map
					j--;
				}
			}
		}
	}
	
	//methods to animate the plants
	public void animateZombies() {
		for(int i = 0; i < map.getAllZombies().size(); i++)
			map.getAllZombies().get(i).setZombieFrameA(map.getAllZombies().get(i).getType()+1); //iterate through the array of zombie images in gameimages class
	}
	
	public void animateSunflowers(){
		for (int i = 0; i < map.getAllSunflowers().size(); i++)
			map.getAllSunflowers().get(i).setSunflowerFrame(map.getAllSunflowers().get(i).getType()+1); //iterate through the array of sunflower images in gameimages class
	}
	
	public void animatePeashooters() {
		for (int i = 0; i < map.getAllPeashooters().size(); i++){
			map.getAllPeashooters().get(i).setPeashooterFrame(map.getAllPeashooters().get(i).getType()+1); //iterate through the array of peashooter images in gameimages class
			map.getAllPeashooters().get(i).setState(true);  
		}
	}

	public void animateBonkChoys(){
		for (int i = 0; i < map.getAllBonkChoys().size(); i++)
			map.getAllBonkChoys().get(i).setBonkChoyFrame(map.getAllBonkChoys().get(i).getType()+1); //iterate through the array of bonkchoy images in gameimages class
	}
	
	public void animateSnowpeas(){
		for (int i = 0; i < map.getAllSnowpeas().size(); i++)
			map.getAllSnowpeas().get(i).setSnowpeaFrame(map.getAllSnowpeas().get(i).getType()+1); //iterate through the array of snowpea images in gameimages class
	}
	
	//not in use
	public void mouseMoved(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	
	//add plant 
	public void mousePressed(MouseEvent me){
		
		if (paused == false){
			//placing the plants on the tiles
			for(int i = 0; i < map.getAllTiles().size(); i++){
				if(map.getAllTiles().get(i).contains(me.getPoint())){ //check which tile was clicked
					int tileX = (int)map.getAllTiles().get(i).getX();
					int tileY = (int)map.getAllTiles().get(i).getY();
					System.out.println(tileX);
					System.out.println(tileY);
									
					//check if it is a grass tile
					if(map.getAllTiles().get(i).getType() == GRASS){
						
						//ints are added to x and y because the image are not placed directly at the corner of the tile, it is placed so it is centered on tile
						//check if wallnut/mine is already placed					
						boolean placed = false;
						for (int j = 0; j < map.getAllPlants().size(); j++){
							if (map.getAllPlants().get(j).getType() == WALLNUT){
								if (map.getAllPlants().get(j).getX() == tileX+10 && map.getAllPlants().get(j).getY() == tileY+5) 
									placed = true;
							}
							
							else if (map.getAllPlants().get(j).getType() == MINE){
								if (map.getAllPlants().get(j).getX() == tileX+5 && map.getAllPlants().get(j).getY() == tileY+5)
									placed = true;
							}							
						}
						
						//check if sunflower is already placed
						for (int j = 0; j < map.getAllSunflowers().size(); j++){
							if (map.getAllSunflowers().get(j).getX() == tileX && map.getAllSunflowers().get(j).getY() == tileY-10)
								placed = true;	
						}
						
						//check if peashooter is already placed
						for (int j = 0; j < map.getAllPeashooters().size(); j++){
							if (map.getAllPeashooters().get(j).getX() == tileX && map.getAllPeashooters().get(j).getY() == tileY)
								placed = true;			
						}
						
						//check if bonkchoy is already placed
						for (int j = 0; j < map.getAllBonkChoys().size(); j++){
							if (map.getAllBonkChoys().get(j).getX() == tileX && map.getAllBonkChoys().get(j).getY() == tileY-50)
								placed = true;		
						}
						
						//check if snowpea is already placed
						for (int j = 0; j < map.getAllSnowpeas().size(); j++){
							if (map.getAllSnowpeas().get(j).getX() == tileX && map.getAllSnowpeas().get(j).getY() == tileY)
								placed = true;		
						}
						
						//if plant is not placed in that tile yet
						if (!placed){
							if(sunCheck(me.getPoint()) == true){ //see sunCheck method
								//check if there is enough money
								int cost = 0;
								if (type == PEA)
									cost = 100;
								else if (type == SUNFLOWER)
									cost = 50;
								else if (type == WALLNUT)
									cost = 50;
								else if (type == MINE)
									cost  = 25;
								else if (type == BONKCHOY)
									cost = 175;
								else if (type == SNOWPEA)
									cost = 275;
								
								//if there is enough money, add the new plant to corresponding arraylist 
								if (sunCounter >= cost){
									
									if (type == PEA)
										map.addPeashooter(new PeashooterObject(map, tileX, tileY, 50, 50, 0));
									else if (type == SUNFLOWER) 
										map.addSunflower(new SunflowerObject(tileX, tileY-10, 70, 70, 0));
									else if (type == BONKCHOY) 
										map.addBonkChoy(new BonkChoyObject(tileX, tileY-10, 70, 70, 0));
									else if (type == WALLNUT)
										map.addPlant(new GameObject(tileX+10, tileY+5, 40, 50, type, map));
									else if (type == MINE)
										map.addPlant(new GameObject(tileX+5, tileY+5, 45,45,type, map));
									else if (type == SNOWPEA)
										map.addSnowpea(new SnowpeaObject(tileX, tileY, 50,50,0));
									
									//update sunCounter
									sunCounter -= cost;
									sunCounterLbl.setText("Sun Count: " + sunCounter);
								}
							}
						}
					}
				}
			}
		}
		
		//picking up the suns
		for (int i = 0; i < map.getAllSunshine().size(); i++){
			if (map.getAllSunshine().get(i).contains(me.getPoint())){ //if the sunshine is in the point clicked
				map.getAllSunshine().get(i).collect(); //see collect method in Sunshine class
				
				//update sunCounter
				sunCounter += 25; 
				sunCounterLbl.setText("Sun Count: " + sunCounter);
			}
		}
		
		//picking up sunflower sunshine
		for (int i = 0; i < map.getAllSunflowers().size(); i++){
			for (int j = 0; j < map.getAllSunflowers().get(i).getSuns().size(); j++){
				if(map.getAllSunflowers().get(i).getSuns().get(j).contains(me.getPoint())){ //check which sun was clicked
					map.getAllSunflowers().get(i).getSuns().remove(j); //remove sun from the arraylist
					
					//update sunCounter
					sunCounter += 25; 
					sunCounterLbl.setText("Sun Count: " + sunCounter);
				}
			}
		}
	}
	
	//checks if the user is intending to click a sun and not to place a plant
	public boolean sunCheck(Point point){ 
		boolean check = true;
		for (int i = 0; i < map.getAllSunshine().size(); i++){
			if(map.getAllSunshine().get(i).contains(point)) //check if falling sun was clicked
				check = false;
		}
		
		for (int i = 0; i < map.getAllSunflowers().size(); i++){
			for (int j = 0; j < map.getAllSunflowers().get(i).getSuns().size(); j++){
				if (map.getAllSunflowers().get(i).getSuns().get(j).contains(point)) //check if sunflower sun was clicked
					check = false;				
			}
		}
		return check;
	}
	
	//save map method creates a saved map
	public SavedMap createSavedMap(int level){
		SavedMap sm = new SavedMap(level); //create saved map
		
		// add existing plants onto sm
		for (int i = 0; i < map.getAllPeashooters().size(); i++ ) 
			sm.addPeashooter(map.getAllPeashooters().get(i));

		for (int i = 0; i < map.getAllBonkChoys().size(); i++)
			sm.addBonkChoy(map.getAllBonkChoys().get(i));

		for (int i = 0; i < map.getAllSnowpeas().size(); i++)
			sm.addSnowpea(map.getAllSnowpeas().get(i));

		for (int i = 0; i < map.getAllPlants().size(); i++)
			sm.addPlant(map.getAllPlants().get(i));

		for (int i = 0; i < map.getAllSunflowers().size(); i++)
			sm.addSunflower(map.getAllSunflowers().get(i));

		//add all existing sunshine onto sm
		for (int i = 0; i < map.getAllSunshine().size(); i++)
			sm.addSunshine(map.getAllSunshine().get(i));

		//add all existing zombies onto sm
		for (int i = 0; i < map.getAllZombies().size(); i++)
			sm.addZombie(map.getAllZombies().get(i));

		return sm;
	}
	
	//save method
	public void saveMethod(File filePath){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {
			fout = new FileOutputStream(filePath); 
			oos = new ObjectOutputStream(fout);
			oos.writeObject(sm); //writes sm into a file
			System.out.println(sm.getAllTiles().get(0).getX());
			saveLbl.setText("                    Map Saved!                    ");
			fout.close();
			oos.close();
		}
		catch (Exception ex) {
			System.out.print(ex);
		} 
	}
	
	//pause game
	public void pauseGame(){
		paused = true;
		
		//pause the timers
		gameTimer.stop();
		sunTimer.stop();
		gameObjectsTimer.stop();
		
		for (int i = 0; i < map.getAllSunflowers().size(); i++)
			map.getAllSunflowers().get(i).pauseTimer(); //pause sunflower timers
		for (int i = 0; i < map.getAllZombies().size(); i++)
			map.getAllZombies().get(i).pauseTimer(); //pause zombie timers
	}
	
	//resume game
	public void resumeGame(){
		paused = false;
			
		//restart all timers
		gameTimer.restart();
		sunTimer.restart();
		gameObjectsTimer.restart();
		
		for (int i = 0; i < map.getAllSunflowers().size(); i++)
			map.getAllSunflowers().get(i).resumeTimer(); //restart sunflower timers
		for (int i = 0; i < map.getAllZombies().size(); i++)
			map.getAllZombies().get(i).resumeTimer(); //restart zombie timers
	}
	
	// not in use
	public void keyTyped(KeyEvent ke) {}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
}
	