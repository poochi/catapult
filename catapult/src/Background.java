
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;


public class Background  {
	public Image cloud,bg;
	public int cloud_x,cloud_y;
	boolean looping ;
	//Clip clip;
	//AudioInputStream ais;
	
	//boolean first_time;
	//JLabel bg=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
    
	

	public Background(){
		cloud_x = 20;
		cloud_y = 20;
		
		try {                
	          cloud = ImageIO.read(new File("res\\cloud_2.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	    	   
	       }
		
		try {                
	          bg = ImageIO.read(new File("res\\back_land.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	    	   
	       }
		
		
		/*clip = AudioSystem.getClip();
	    AudioInputStream ais = AudioSystem.
	      getAudioInputStream("C:\\Users\\gowtham\\Downloads\\Java_game\\back\\Test1.wav");
	    
	    clip.open(ais);
	    clip.loop(5);*/
		
		looping = true;
		
		
	}
	
	
	public void gameDraw(Graphics g){
		
			
		if(looping == true)
			cloud_x +=(10);
		cloud_x %=game.WINDOW_COLUMN;
		
		g.drawImage(bg,0,0,null);
		g.drawImage(cloud,cloud_x,cloud_y,null);
		
		
		
		
		
	}
	



}





