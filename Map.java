/*
 * Liu_Summative
 * Elaine Liu
 * December 24, 2017
 * ICS4U1
 * Ms. Strelkovska
 */
 
import java.util.*;
import javax.swing.*;
import java.io.*;

class Map implements Serializable{
	private ArrayList <Tile> allTiles; 
	private ArrayList <GameObject> plants;
	private ArrayList <ZombieObject> zombies;
	private ArrayList <SunflowerObject> sunflowers;
	private ArrayList <PeashooterObject> peashooters;
	private ArrayList <BonkChoyObject> bonkChoys;
	private ArrayList <SnowpeaObject> snowpeas;
	private ArrayList <Projectile> projectiles;
	private ArrayList <Sunshine> sunshine;
	private ArrayList <LawnMower> lawnMower;
	private static final int LANE1 = 140, LANE2 = 210, LANE3 = 280, LANE4 = 350, LANE5 = 420, LANE6 = 490;
	private int [] yLoc; 
	private int level;
	
	public Map(int level){
		this.level = level;
		
		// Arraylist of all tiles
	    allTiles = new ArrayList<Tile>();
		
		// Arraylist of plant objects
		plants = new ArrayList<GameObject>();
		
		// Arraylist of zombie objects
		zombies = new ArrayList<ZombieObject>();
		
		//Arrylist of sunflower objects
		sunflowers = new ArrayList <SunflowerObject>();
		
		//Arrylist of peashooter objects
		peashooters = new ArrayList <PeashooterObject>();
		
		//ArrayList of bonkChoys
		bonkChoys = new ArrayList<BonkChoyObject>();

		//ArrayList of snowpeas
		snowpeas = new ArrayList<SnowpeaObject>();
		
		//ArrayList of projectiles
		projectiles = new ArrayList<Projectile>();
		
		//ArrayList of sunshine
		sunshine = new ArrayList<Sunshine>();
		
		//ArrayList of LawnMowers
		lawnMower = new ArrayList<LawnMower>();
		
		//create grid
		for (int j = 0; j < 6; j++){
			
			//level 1
			if (level == 1){
				//grass line 
				if (j == 2 || j==3){
					for (int i = 0; i < 9; i++){
						addTile(new Tile(80+70*i, 150+70*j, 70, 70, 9));	
					}
				}
				
				//otherwise, dirt line
				else{
					for (int i = 0; i < 9; i++){
						addTile(new Tile(80+70*i, 150+70*j, 70, 70, 11));
					}
				}
			}
			//level 2
			else if (level == 2){
				//grass line 
				if (j == 1 || j==2 || j==3 || j==4){
					for (int i = 0; i < 9; i++){
						addTile(new Tile(80+70*i, 150+70*j, 70, 70, 9));	
					}
				}
				
				//otherwise, dirt line
				else{
					for (int i = 0; i < 9; i++){
						addTile(new Tile(80+70*i, 150+70*j, 70, 70, 11));
					}
				}
			}
			//level 3, 4, 5, 6
			else if (level == 3 || level == 4 || level == 5 || level == 6){
				for (int i = 0; i < 9; i++){
					addTile(new Tile(80+70*i, 150+70*j, 70, 70, 9));	
				}
			}
		}
		
		//add lawn mowers
		for (int i = 0; i < 6; i++){
			addLawnMower(new LawnMower(10,150+70*i,80,70,10));
		}		
	}
	
	public void loadZombies(){
		//create zombies
		if (level == 1){
			//possible locations of zombies
			yLoc = new int [] {LANE3, LANE4};
			
			//create 5 zombies
			for (int i = 0; i < 5; i++){
				int x = (int)(Math.random() * ((1200 - 650) + 1)) + 650; //random x loc between 650 and 1200
				int y = yLoc[(int)(Math.random() * 2)]; //random y loc either 280 or 330
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 1));
			}
		}
		else if (level == 2){
			//possible locations of zombies
			yLoc = new int [] {LANE2, LANE3, LANE4, LANE5};
			
			//create first wave of zombies
			for (int i = 0; i < 5; i++){
				int x = (int)(Math.random() * ((1000 - 800) + 1)) + 800; //random x loc between 800 and 1000
				int y = yLoc[(int)(Math.random() * 4)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 2));
			}
			
			//create second wave of zombies
			for (int i = 0; i < 5; i++){
				int x = (int)(Math.random() * ((1200 - 1400) + 1)) + 1400; //random x loc between 1200 and 1400
				int y = yLoc[(int)(Math.random() * 4)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 2));
			}
		}
		else if (level == 3){
			//possible locations of zombies
			yLoc = new int [] {LANE1, LANE2, LANE3, LANE4, LANE5, LANE6};
			
			//create first wave of zombies
			for (int i = 0; i < 7; i++){
				int x = (int)(Math.random() * ((1000 - 800) + 1)) + 800; //random x loc between 800 and 1000
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 3));
			}
			
			//create second wave of zombies
			for (int i = 0; i < 8; i++){
				int x = (int)(Math.random() * ((1200 - 1400) + 1)) + 1400; //random x loc between 1200 and 1400
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 3));
			}
		}
		else if (level == 4){
			//possible locations of zombies
			yLoc = new int [] {LANE1, LANE2, LANE3, LANE4, LANE5, LANE6};
			
			//create first wave of zombies
			for (int i = 0; i < 10; i++){
				int x = (int)(Math.random() * ((1000 - 800) + 1)) + 800; //random x loc between 800 and 1000
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 4));
			}
			
			//create second wave of zombies
			for (int i = 0; i < 10; i++){
				int x = (int)(Math.random() * ((1400 - 1200) + 1)) + 1200; //random x loc between 1200 and 1400
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 4));
			}
			
			//create final wave of zombies
			for (int i = 0; i < 10; i++){
				int x = (int)(Math.random() * ((1800 - 1600) + 1)) + 1600; //random x loc between 1600 and 1800
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 4));
			}
		}
		else if (level == 5){
			//possible locations of zombies
			yLoc = new int [] {LANE1, LANE2, LANE3, LANE4, LANE5, LANE6};
			
			//create first wave of zombies
			for (int i = 0; i < 15; i++){
				int x = (int)(Math.random() * ((1000 - 800) + 1)) + 800; //random x loc between 800 and 1000
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 5));
			}
			
			//create second wave of zombies
			for (int i = 0; i < 15; i++){
				int x = (int)(Math.random() * ((1400 - 1200) + 1)) + 1200; //random x loc between 1200 and 1400
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 5));
			}
			
			//create final wave of zombies
			for (int i = 0; i < 15; i++){
				int x = (int)(Math.random() * ((1800 - 1600) + 1)) + 1600; //random x loc between 1600 and 1800
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 5));
			}
		}
		//final battle
		else if (level == 6){
			//possible locations of zombies
			yLoc = new int []{LANE1, LANE2, LANE3, LANE4, LANE5, LANE6};
			
			//create first wave of zombies
			for (int i = 0; i < 20; i++){
				int x = (int)(Math.random() * ((1000 - 800) + 1)) + 800; //random x loc between 800 and 1000
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 6));
			}
			
			//create second wave of zombies
			for (int i = 0; i < 20; i++){
				int x = (int)(Math.random() * ((1400 - 1200) + 1)) + 1200; //random x loc between 1200 and 1400
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 6));
			}
			
			//create final wave of zombies
			for (int i = 0; i < 20; i++){
				int x = (int)(Math.random() * ((1800 - 1600) + 1)) + 1600; //random x loc between 1600 and 1800
				int y = yLoc[(int)(Math.random() * 6)]; //random y lane
				zombies.add(new ZombieObject(x, y, 50, 70, 0, 6));
			}
			
		}
	}
	
	public int getLevel(){
		return level;
	}
	
	//set new arraylist as tiles arraylist
	public void setAllTiles(ArrayList<Tile> al){
		allTiles = al;
	}
	
	//get all tiles method
	public ArrayList<Tile> getAllTiles(){
		return allTiles;
	}
	
	//get all plant game objects method
	public ArrayList<GameObject> getAllPlants(){
		return plants;
	}
	
	//get all zombie game objects method
	public ArrayList <ZombieObject> getAllZombies(){
		return zombies;
	}
	
	//get all sunflower objects method
	public ArrayList<SunflowerObject> getAllSunflowers(){
		return sunflowers;
	}
	
	//get all peashooter objects method
	public ArrayList<PeashooterObject> getAllPeashooters(){
		return peashooters;
	}
	
	//get all peashooter objects method
	public ArrayList<BonkChoyObject> getAllBonkChoys(){
		return bonkChoys;
	}
	
	//get all peashooter objects method
	public ArrayList<SnowpeaObject> getAllSnowpeas(){
		return snowpeas;
	}
	
	public ArrayList<Projectile> getAllProjectiles(){
		return projectiles;
	}
	
	public ArrayList<Sunshine> getAllSunshine(){
		return sunshine;
	}
	
	public ArrayList<LawnMower> getAllLawnMowers(){
		return lawnMower;
	}
	
	//add tile to arraylist method
	public void addTile(Tile t){
		allTiles.add(t);
	}
	
	//add GameObject to arraylist
	public void addPlant(GameObject go){
		plants.add(go);
	}
	
	//add zombie to arraylist
	public void addZombie(ZombieObject zo){
		zombies.add(zo);
	}
	
	//add sunflower to arraylist
	public void addSunflower(SunflowerObject so){
		sunflowers.add(so);
	}
	
	//add peashooter to arraylist
	public void addPeashooter(PeashooterObject po){
		peashooters.add(po);
	}
	
	//add bonkchoy to arraylist
	public void addBonkChoy(BonkChoyObject bo){
		bonkChoys.add(bo);
	}
	
	//add snowpea to arraylist
	public void addSnowpea(SnowpeaObject so){
		snowpeas.add(so);
	}
	
	public void addProjectile(Projectile p){
		projectiles.add(p);
	}
	
	public void addSunshine(Sunshine s){
		sunshine.add(s);
	}
	
	public void addLawnMower(LawnMower lo){
		lawnMower.add(lo);
	}
}  