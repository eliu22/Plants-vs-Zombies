/*
 * Liu_Summative
 * Elaine Liu
 * December 24, 2017
 * ICS4U1
 * Ms. Strelkovska
 */

import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import javax.swing.ImageIcon;
import java.awt.*;

public class GameImages {
	
	// Create Buffered Images array
	public static BufferedImage almanac[] = new BufferedImage[9];
	public static BufferedImage images[] = new BufferedImage[35];
	public static BufferedImage zombieA[] = new BufferedImage[6];
	public static BufferedImage sunflower[] = new BufferedImage[15];
	public static BufferedImage peashooter[] = new BufferedImage[39];
	public static BufferedImage bonkChoy[] = new BufferedImage[23];
	public static BufferedImage snowpea[] = new BufferedImage[27];
	
	// Load images method
	public static void loadImages(){	
		try{
			//almanac
			almanac[0] = ImageIO.read(new File("GameImages/almanac/peashooter.png"));
			almanac[1] = ImageIO.read(new File("GameImages/almanac/sunflower.png"));
			almanac[2] = ImageIO.read(new File("GameImages/almanac/wallnut.png"));
			almanac[3] = ImageIO.read(new File("GameImages/almanac/potmine.png"));
			almanac[4] = ImageIO.read(new File("GameImages/almanac/bonkchoy.png"));
			almanac[5] = ImageIO.read(new File("GameImages/almanac/snowpea.png"));
			almanac[6] = ImageIO.read(new File("GameImages/almanac/flyingjoe.png"));
			almanac[7] = ImageIO.read(new File("GameImages/almanac/conehead.png"));
			almanac[8] = ImageIO.read(new File("GameImages/almanac/athlete.png"));
			
			//Adding images from the folder 'GameImages' into an array
			images[0] = ImageIO.read(new File("GameImages/peashooter.png"));
			images[1] = ImageIO.read(new File("GameImages/sunflower.png"));
			images[2] = ImageIO.read(new File("GameImages/wallnut.png"));
			images[3] = ImageIO.read(new File("GameImages/bomb.png"));
			images[4] = ImageIO.read(new File("GameImages/bonkChoy.gif"));
			images[5] = ImageIO.read(new File("GameImages/snowpea.png"));
			images[6] = ImageIO.read(new File("GameImages/zombie1.png"));
			images[7] = ImageIO.read(new File("GameImages/zombie2.png"));
			images[8] = ImageIO.read(new File("GameImages/zombie3.png"));
			images[9] = ImageIO.read(new File("GameImages/grass.png"));
			images[10] = ImageIO.read(new File("GameImages/mower.png"));
			images[11] = ImageIO.read(new File("GameImages/dirt.png"));
			images[12] = ImageIO.read(new File("GameImages/sunshine.png"));
			images[13] = ImageIO.read(new File("GameImages/bg.png"));
			images[14] = ImageIO.read(new File("GameImages/pea.png"));
			images[15] = ImageIO.read(new File("GameImages/pea.png"));
			
			//zombie gif
			zombieA[0] = ImageIO.read(new File("GameImages/zombieA/zombieA1.gif"));
			zombieA[1] = ImageIO.read(new File("GameImages/zombieA/zombieA2.gif"));
			zombieA[2] = ImageIO.read(new File("GameImages/zombieA/zombieA3.gif"));
			zombieA[3] = ImageIO.read(new File("GameImages/zombieA/zombieA4.gif"));
			zombieA[4] = ImageIO.read(new File("GameImages/zombieA/zombieA5.gif"));
			zombieA[5] = ImageIO.read(new File("GameImages/zombieA/zombieA6.gif"));
			
			//sunflower gif
			sunflower[0] = ImageIO.read(new File("GameImages/sunflower/sunflower1.gif"));
			sunflower[1] = ImageIO.read(new File("GameImages/sunflower/sunflower2.gif"));
			sunflower[2] = ImageIO.read(new File("GameImages/sunflower/sunflower3.gif"));
			sunflower[3] = ImageIO.read(new File("GameImages/sunflower/sunflower4.gif"));
			sunflower[4] = ImageIO.read(new File("GameImages/sunflower/sunflower5.gif"));
			sunflower[5] = ImageIO.read(new File("GameImages/sunflower/sunflower6.gif"));
			sunflower[6] = ImageIO.read(new File("GameImages/sunflower/sunflower7.gif"));
			sunflower[7] = ImageIO.read(new File("GameImages/sunflower/sunflower8.gif"));
			sunflower[8] = ImageIO.read(new File("GameImages/sunflower/sunProd1.gif"));
			sunflower[9] = ImageIO.read(new File("GameImages/sunflower/sunProd2.gif"));
			sunflower[10] = ImageIO.read(new File("GameImages/sunflower/sunProd3.gif"));
			sunflower[11] = ImageIO.read(new File("GameImages/sunflower/sunProd4.gif"));
			sunflower[12] = ImageIO.read(new File("GameImages/sunflower/sunProd5.gif"));
			sunflower[13] = ImageIO.read(new File("GameImages/sunflower/sunProd6.gif"));
			sunflower[14] = ImageIO.read(new File("GameImages/sunflower/sunProd7.gif"));
			
			//peashooter gif
			peashooter[0] = ImageIO.read(new File("GameImages/peashooter/peashooter11.gif"));
			peashooter[1] = ImageIO.read(new File("GameImages/peashooter/peashooter12.gif"));
			peashooter[2] = ImageIO.read(new File("GameImages/peashooter/peashooter13.gif"));
			peashooter[3] = ImageIO.read(new File("GameImages/peashooter/peashooter14.gif"));
			peashooter[4] = ImageIO.read(new File("GameImages/peashooter/peashooter15.gif"));
			peashooter[5] = ImageIO.read(new File("GameImages/peashooter/peashooter16.gif"));
			peashooter[6] = ImageIO.read(new File("GameImages/peashooter/peashooter17.gif"));
			peashooter[7] = ImageIO.read(new File("GameImages/peashooter/peashooter18.gif"));
			peashooter[8] = ImageIO.read(new File("GameImages/peashooter/peashooter19.gif"));
			peashooter[9] = ImageIO.read(new File("GameImages/peashooter/peashooter20.gif"));
			peashooter[10] = ImageIO.read(new File("GameImages/peashooter/peashooter21.gif"));
			peashooter[11] = ImageIO.read(new File("GameImages/peashooter/peashooter22.gif"));
			peashooter[12] = ImageIO.read(new File("GameImages/peashooter/peashooter23.gif"));
			peashooter[13] = ImageIO.read(new File("GameImages/peashooter/peashooter24.gif"));
			peashooter[14] = ImageIO.read(new File("GameImages/peashooter/peashooter25.gif"));
			peashooter[15] = ImageIO.read(new File("GameImages/peashooter/peashooter26.gif"));
			peashooter[16] = ImageIO.read(new File("GameImages/peashooter/peashooter27.gif"));
			peashooter[17] = ImageIO.read(new File("GameImages/peashooter/peashooter28.gif"));
			peashooter[18] = ImageIO.read(new File("GameImages/peashooter/peashooter29.gif"));
			peashooter[19] = ImageIO.read(new File("GameImages/peashooter/peashooter30.gif"));
			peashooter[20] = ImageIO.read(new File("GameImages/peashooter/peashooter31.gif"));
			peashooter[21] = ImageIO.read(new File("GameImages/peashooter/peashooter32.gif"));
			peashooter[22] = ImageIO.read(new File("GameImages/peashooter/peashooter33.gif"));
			peashooter[23] = ImageIO.read(new File("GameImages/peashooter/peashooter34.gif"));
			peashooter[24] = ImageIO.read(new File("GameImages/peashooter/peashooter35.gif"));
			peashooter[25] = ImageIO.read(new File("GameImages/peashooter/peashooter36.gif"));
			peashooter[26] = ImageIO.read(new File("GameImages/peashooter/peashooter37.gif"));
			peashooter[27] = ImageIO.read(new File("GameImages/peashooter/peashooter38.gif"));
			peashooter[28] = ImageIO.read(new File("GameImages/peashooter/peashooter39.gif"));
			
			//bonkChoy gif
			bonkChoy[0] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy1.gif"));
			bonkChoy[1] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy2.gif"));
			bonkChoy[2] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy3.gif"));
			bonkChoy[3] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy4.gif"));
			bonkChoy[4] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy5.gif"));
			bonkChoy[5] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy6.gif"));
			bonkChoy[6] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoy7.gif"));
			bonkChoy[7] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch1.gif"));
			bonkChoy[8] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch2.gif"));
			bonkChoy[9] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch3.gif"));
			bonkChoy[10] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch4.gif"));
			bonkChoy[11] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch5.gif"));
			bonkChoy[12] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch6.gif"));
			bonkChoy[13] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch7.gif"));
			bonkChoy[14] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch8.gif"));
			bonkChoy[15] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch9.gif"));
			bonkChoy[16] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch10.gif"));
			bonkChoy[17] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch11.gif"));
			bonkChoy[18] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch12.gif"));
			bonkChoy[19] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch13.gif"));
			bonkChoy[20] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch14.gif"));
			bonkChoy[21] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch15.gif"));
			bonkChoy[22] = ImageIO.read(new File("GameImages/bonkChoy/bonkChoyPunch16.gif"));
			
			//snowpea gif
			snowpea[0] = ImageIO.read(new File("GameImages/snowpea/0.gif"));
			snowpea[1] = ImageIO.read(new File("GameImages/snowpea/1.gif"));
			snowpea[2] = ImageIO.read(new File("GameImages/snowpea/2.gif"));
			snowpea[3] = ImageIO.read(new File("GameImages/snowpea/3.gif"));
			snowpea[4] = ImageIO.read(new File("GameImages/snowpea/4.gif"));
			snowpea[5] = ImageIO.read(new File("GameImages/snowpea/5.gif"));
			snowpea[6] = ImageIO.read(new File("GameImages/snowpea/6.gif"));
			snowpea[7] = ImageIO.read(new File("GameImages/snowpea/7.gif"));
			snowpea[8] = ImageIO.read(new File("GameImages/snowpea/8.gif"));
			snowpea[9] = ImageIO.read(new File("GameImages/snowpea/9.gif"));
			snowpea[10] = ImageIO.read(new File("GameImages/snowpea/10.gif"));
			snowpea[11] = ImageIO.read(new File("GameImages/snowpea/11.gif"));
			snowpea[12] = ImageIO.read(new File("GameImages/snowpea/12.gif"));
			snowpea[13] = ImageIO.read(new File("GameImages/snowpea/13.gif"));
			snowpea[14] = ImageIO.read(new File("GameImages/snowpea/14.gif"));
			snowpea[15] = ImageIO.read(new File("GameImages/snowpea/15.gif"));
			snowpea[16] = ImageIO.read(new File("GameImages/snowpea/16.gif"));
			snowpea[17] = ImageIO.read(new File("GameImages/snowpea/17.gif"));
			snowpea[18] = ImageIO.read(new File("GameImages/snowpea/18.gif"));
			snowpea[19] = ImageIO.read(new File("GameImages/snowpea/19.gif"));
			snowpea[20] = ImageIO.read(new File("GameImages/snowpea/20.gif"));
			snowpea[21] = ImageIO.read(new File("GameImages/snowpea/21.gif"));
			snowpea[22] = ImageIO.read(new File("GameImages/snowpea/22.gif"));
			snowpea[23] = ImageIO.read(new File("GameImages/snowpea/23.gif"));
			snowpea[24] = ImageIO.read(new File("GameImages/snowpea/24.gif"));
			snowpea[25] = ImageIO.read(new File("GameImages/snowpea/25.gif"));
			snowpea[26] = ImageIO.read(new File("GameImages/snowpea/26.gif"));
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}		
	}
	
	// Get image method
	public static BufferedImage getAlmanacImage(int type) {
		return almanac[type];
	}
	
	// Get image method
	public static BufferedImage getImage(int type) {
		return images[type];
	}
	
	//get zombie frame iteration
	public static BufferedImage getZombieFrame(int type){
		return zombieA[type];
	}
	
	//get sunflower frame iteration
	public static BufferedImage getSunflowerFrame(int type){
		return sunflower[type];
	}
	
	//get peashooter frame iteration
	public static BufferedImage getPeashooterFrame(int type){
		return peashooter[type];
	}
	
		//get bonkchoy frame iteration
	public static BufferedImage getBonkChoyFrame(int type){
		return bonkChoy[type];
	}
	
	//get bonkchoy frame iteration
	public static BufferedImage getSnowpeaFrame(int type){
		return snowpea[type];
	}
}

