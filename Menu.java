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
import javax.swing.filechooser.*;

public class Menu extends JFrame implements ActionListener{
	private static Container container;
	private static JPanel panel1;
	private static JLabel label1, title;
	private static JButton playBtn, almanacBtn, exitBtn, resumeBtn;
	private static Font newFont;
	private static GameFrame gameFrame;
	private static String [] choices = {"Level 1","Level 2", "Level 3","Level 4","Level 5","Final Battle"};;
	private static JComboBox cb;
	private JFileChooser fc;
	private SavedMap mapIn;
	private static AlmanacFrame almanac;

	public Menu(){
		//file chooser
		fc = new JFileChooser();
		
		//container
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		// Setting the font
		newFont = new Font("Consolas", Font.BOLD, 27);
		
		//dropdown levels menu
		cb = new JComboBox(choices);
		cb.setVisible(true);
		cb.addActionListener(this);
		container.add(cb);
		
		// Buttons
		playBtn = createBtn("PLAY", newFont);
		resumeBtn = createBtn("RESUME GAME", newFont);
		almanacBtn = createBtn("ALMANAC", newFont);
		exitBtn = createBtn("EXIT", newFont);
		
		//background image
		label1 = new JLabel(new ImageIcon("GameImages/startImg.png"));
		container.add(label1, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e){

		// Instructions 
		if (e.getSource() == almanacBtn){
			almanac = new AlmanacFrame();
			almanac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			almanac.setSize(700, 700);
			almanac.setVisible(true);
			almanac.setResizable(false);
		}
		else if (e.getSource() == playBtn){
			gameFrame = new GameFrame();
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameFrame.setSize(1100, 700);
			gameFrame.setVisible(true);
			gameFrame.setResizable(false);
			
			if (((String) cb.getSelectedItem()).equals("Level 1")){
				gameFrame.getGamePanel().setMap(new Map(1));
			}
			else if (((String) cb.getSelectedItem()).equals("Level 2")){
				gameFrame.getGamePanel().setMap(new Map(2));
			}
			else if (((String) cb.getSelectedItem()).equals("Level 3")){
				gameFrame.getGamePanel().setMap(new Map(3));
			}
			else if (((String) cb.getSelectedItem()).equals("Level 4")){
				gameFrame.getGamePanel().setMap(new Map(4));
			}
			else if (((String) cb.getSelectedItem()).equals("Level 5")){
				gameFrame.getGamePanel().setMap(new Map(5));
			}
			else if (((String) cb.getSelectedItem()).equals("Final Battle")){
				gameFrame.getGamePanel().setMap(new Map(6));
			}				
		}
		
		//open saved game
		else if (e.getSource() == resumeBtn){
			try{
				
				int returnVal = fc.showOpenDialog(this);  
				
				// If user did not click cancel and picked a file
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// File picked by user				
					File file = fc.getSelectedFile(); 
					openMethod(file);
				}
			}
			catch(Exception ex){
				System.out.print(ex);
            }	
		}
		
		// Exit 
		else if (e.getSource() == exitBtn){
			System.exit(0);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		cb.setLocation(200, 300);
		cb.setSize(150, 40);
		playBtn.setLocation(370, 300);
		playBtn.setSize(110, 40);
		resumeBtn.setLocation(230, 370);
		resumeBtn.setSize(220, 40);
		almanacBtn.setLocation (230, 440);
		almanacBtn.setSize(220, 40);
		exitBtn.setLocation(230, 510);
		exitBtn.setSize(220, 40);
	}
	
	//create button method
	public JButton createBtn(String s, Font f){
		JButton button = new JButton(s);
		button.setFont(f);
		button.addActionListener(this);
		container.add(button);
		return button;
	}
	
	public static GameFrame getGameFrame(){
		return gameFrame;
	}
	
	public static AlmanacFrame getAlmanacFrame(){
		return almanac;
	}
	
	// Open map method
	public void openMethod(File filePath){
		FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
		// New Play map
		GameFrame gf = new GameFrame();
		// Not allowed to resize
		gf.setResizable(false);
		try{
			// read files
			fileIn = new FileInputStream(filePath);
			objectIn = new ObjectInputStream(fileIn);
			mapIn = (SavedMap) objectIn.readObject();
			// set the saved map on the game panel
			gf.getGamePanel().setMap(mapIn);
			objectIn.close();
		} 
		catch (Exception ex) {
			System.out.print(ex);
		}
		gf.setTitle("Plants vs. Zombies");
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setSize(1100, 700);
		gf.setVisible(true);
	}
	
	// Main
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.setTitle("Zombies");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(700, 700);
		menu.setVisible(true);
		menu.setResizable(false);
	}

	
}