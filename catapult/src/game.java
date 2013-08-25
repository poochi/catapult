
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

public class game extends JFrame implements ChangeListener  {
	
	static enum state{
	START,INITIALIZE,LAUNCHED,END
	}
	static int WINDOW_COLUMN = 500;
	static int WINDOW_ROW = 500;
	static int CELL_SIZE = 5; //filling quantum
	public state game_state = state.INITIALIZE; 
	static int TIME_PER_FRAME = 100;//ms
	
	
	private GameCanvas canvas;
	Background Bck;
	public JPopupMenu Credits;
	//Scoreboard scb;
	
	
	
	
	projectile P;
	ball B;
	target T;
	JSlider powerSlider; //custom class to make give the slider a much neater look
	Mybutton Launch; //custom class to make the button a bit nicer . 
	int power =projectile.MAX_POWER/2;
	
	mouselistener m ;
	boolean success = false;
	
	
	class GameCanvas extends JPanel  {
	      // Constructor
	      public GameCanvas() {
	         setFocusable(true);  // so that can receive key-events
	         requestFocus();
	               }
	   
	      // Override paintComponent to do custom drawing.
	      // Called back by repaint().
	      @Override
	      public void paintComponent(Graphics g) {
	         Graphics2D g2d = (Graphics2D)g;
	         super.paintComponent(g2d);   // paint background
	         
	         setBackground(Color.GREEN);  // may use an image for background
	   
	         //System.out.print("trying to draw ");
	         // Draw the game objects
	         assert(Bck!=null);
	         Bck.gameDraw(g);
	         gameDraw(g);
	        
	      }
	      
	    
	      
	   }

	
	public void gameDraw(Graphics g) {
		{
			switch(game_state){
			case INITIALIZE:
				//System.out.print("trying to draw init");
				g = P.draw(g);
				g = T.draw(g);
				//game_state = state.INITIALIZE;
				break;
			case LAUNCHED:
				g = P.draw(g);
				g = T.draw(g);
				g = B.draw(g);
				break;
			case END:
				break;
			case START:
				//System.out.print("trying to draw start");
				break;
			default:
				//System.out.print("trying to draw ooo");
				break;
				
					
			
			
			}
		}
	
	}
	
	
	public game() {
		
		
	canvas = new GameCanvas();
	canvas.setPreferredSize(new Dimension(WINDOW_COLUMN, WINDOW_ROW));
	this.setContentPane(canvas);
  
	//game_init();
    /*New button*/
    Launch = new Mybutton("Launch");
	Launch.setBounds(50, 60, 80, 30);
	
   
	Launch.addActionListener(new ActionListener() {
       	@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
       		
       		if(game_state == state.INITIALIZE){
	       		//pwr = power;
	       		//ang = m.angle;
       			
	       		P.set_power(power);
	       		
	       		B.set_vx(P.vx());
	       		B.set_vy(P.vy());
	       		B.set_x(P.start_x());
	       		B.set_y(P.start_y());
	       		game_state = state.LAUNCHED;
       		}
       		//System.exit(0);
	       
		}
    });
	
	/*New slider*/
	powerSlider = new JSlider(JSlider.HORIZONTAL,0,P.MAX_POWER,40);
	powerSlider.setMajorTickSpacing(10);
	powerSlider.setPaintTicks(true);
    powerSlider.setPaintLabels(true);
    powerSlider.addChangeListener(this);
    //powerSlider.setLocation(40,40);
    
    
    /*Credits */
    
    JMenuItem menuItem;
    JMenuBar menuBar;
    JMenu menu, submenu;
    
  //Create the menu bar.
    menuBar = new JMenuBar();

    //Build the first menu.
    menu = new JMenu("A Menu");
    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription(
            "The only menu in this program that has menu items");
    menuBar.add(menu);
    
    //...where the GUI is constructed:
    //Create the popup menu.
    Credits = new JPopupMenu();
    menuItem = new JMenuItem("A popup menu item");
    Credits.add(menuItem);
    
    
    class PopupListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
        	System.out.printf("pressed");
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        
    }
    
    
    MouseListener popupListener = new PopupListener();
    //output.addMouseListener(popupListener);
    menuBar.addMouseListener(popupListener);
    //menuBar.add(Box.createHorizontalGlue());
    
   
	
	
	
	/*Set window*/
    m = new mouselistener();
    this.addMouseMotionListener(m);
    this.addMouseListener(m);
    this.add(Launch);
	this.add(powerSlider);
	this.add(menuBar);
	
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.pack();
	this.setTitle("CATAPULT");
	this.setVisible(true);
	P = new projectile();
	T = new target();
	B = new ball();
	Bck = new Background();
	game_state = state.START;
	
	
	
	
	
	gameStart();
	
	}
	
	


	public void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            Credits.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }


	public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        power  = (int)source.getValue();
	        //System.out.printf("power %d",power);
	        
	        
	        
	    }
	}
	
	
	public void game_init(){
	if(game_state == state.START){
		P.clear();
		T.clear();
		B.clear();
		int x,y;
			do{
		P.regenerate();
		T.regenerate();
		x = T.getX();
		y = T.getY();
		
		}while(P.contains(x,y));
			
		game_state =state.INITIALIZE ;
		success = false;
		
	}
	
	}
	
	
	
	 public void gameStart() { 
	      // Create a new thread
	      Thread gameThread =  new Thread() {
	         // Override run() to provide the running behavior of this thread.
	         @Override
	         public void run() {
	            gameLoop();
	         }
	      };
	      // Start the thread. start() calls run(), which in turn calls gameLoop().
	      gameThread.start();
	   }
	 
	 void gameLoop(){
	long beginTime ; 
	
	 while(true){
		 beginTime = System.nanoTime();
		 game_update();
		 repaint();
		 long timeLeft = (TIME_PER_FRAME)-((System.nanoTime()-beginTime))/1000000;
		 //timeLeft = 100;
		 //System.out.print(System.nanoTime()-beginTime);
		 
		 if(timeLeft<=0)
			 continue;
			 
		 try {
	            // Provides the necessary delay and also yields control so that other thread can do work.
	            Thread.sleep(timeLeft);
	         } catch (InterruptedException ex) {//What to do ?
	        	
	         
	         }
	         
		 
		 
	 }
	 
	 }
	   
	public void game_update(){
		
		switch(game_state){
		case START:
			game_init();
			break;
		case LAUNCHED:
			
			B.update_position();
			
			int x1 =B.width/2+B.get_x()+1;
			int x2 =B.get_x()-B.width/2-1;
			int y1 =B.height/2 +B.get_y()+1;
			int y2 =B.get_y() - B.height/2 -1;
			
			success = success || T.contains(x1,y1) || T.contains(x2,y2) || T.contains(x2,y1) || T.contains(x1,y2);
			System.out.format("Launched");
			
			
			if(B.done ==true || success == true){
				System.out.format("ENded");
				game_state = state.END;
			}
			break;
		case END:
			game_state = state.START;
			
			
		}
		
		
	
	}
	
	
	
public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                game ex = new game();
                ex.setVisible(true);
            }
        });

}



private class mouselistener extends MouseInputAdapter{

	int p_x,p_y;
	
	
	public void mousePressed(MouseEvent e) {
        int p_x = e.getX();
		int p_y = e.getY();
		P.set_marker(p_x,p_y);
		//angle = 10000;
		
		
        
        
    }
	
	public void mouseReleased(MouseEvent e) {
		
		
    }
	
	
}

}
