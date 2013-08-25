import java.awt.Color;
import java.awt.Graphics;

//import java.math.*;

//This is basically a gravity class
public class ball {
	
	private double vx,vy;
	private int x,y;//this is center
	public int width,height;//This is boundary
	static double SCALE_CONSTANT; //Basically gravity is scaled 
	static double VELOCITY_CONSTANT;
	public boolean done =false;
	public boolean collison = false;
	static int GRAVITY = 10; //SCALE_CONSTANT*GRAVITY is actual gravity
	static int DELTA_T = 10;
	 
	static int TIME_FOR_FREE_FALL = 4000; //this is the time taken in ms to travel from one top to bottom during free fall ...
	
	
	public ball(){
		vx=vy = 10;
		x=y=100;//some center
		
		width = 5*game.CELL_SIZE;
		height = 5*game.CELL_SIZE;
		int cnt = TIME_FOR_FREE_FALL/game.TIME_PER_FRAME;
		cnt*=cnt;
		int cnt1 = projectile.MAX_POWER;
		cnt1*=cnt1;
		
		
		SCALE_CONSTANT = (double)2*(game.WINDOW_ROW-20)/(cnt*GRAVITY);
		//With 70% power i need to hit the top .... (distance based formula wont work )
		VELOCITY_CONSTANT = (double)(TIME_FOR_FREE_FALL/game.TIME_PER_FRAME)*(SCALE_CONSTANT*GRAVITY)/(double)((0.95)*projectile.MAX_POWER);
		System.out.printf("vel con %.4f",VELOCITY_CONSTANT);
	}
	
	public boolean contains(int x, int y) //Check if this pixel is within ball . 
	{
		boolean c = this.x+(width/2)<=x && this.x-width/2>=x && this.y+height/2<=y && this.y-height/2>=y ;
		System.out.print(c);
		return c;
	}

	
	
	/*Physics */
	public void update_position(){
	//rules according to gravity
		if(x>=game.WINDOW_COLUMN-20 || x<=0 ||y>=game.WINDOW_ROW-20 || y<=0){
			done = true;
			x = min(game.WINDOW_COLUMN-20,max(x,0));
					y = min(game.WINDOW_ROW-20,max(y,0));
			return;
					
		}
		
		x +=(vx);
		y +=(vy);
		vy +=(GRAVITY*SCALE_CONSTANT);
		
		System.out.printf("x %d and y %d\n",x,y);
		 
	}
	
	private int min(int x2, int x1) {
		// TODO Auto-generated method stub
		return x1<x2?x1:x2;
	}

	private int max(int x2, int x1) {
		// TODO Auto-generated method stub
		return x1>x2?x1:x2;
	}

	public double get_vx(){
	return vx;
	}
	
	public double get_vy(){
	return vy;
	
	}
	
	public int get_x(){
		
				
	return x; 
	}
	public int get_y(){
	return y;
	}
	
	
	public void set_vx(double v){
		vx = v*VELOCITY_CONSTANT;
		
		}
		
		public void set_vy(double v){
		vy = v*VELOCITY_CONSTANT;
		
		
		}
		
		public void set_x(int bx){
			if(bx-width/2 >=10)
				x = bx ;
			else 
				x=width/2 +10;
			
			//x=90;
		}
		
		
		public void set_y(int by){
			if(by-height/2 >=10)
				y= by;
			else
				y = height/2 +10 ;
			//y=10;
		
		}
	
	
	//implement draw here based on the current position draw
	public Graphics draw(Graphics g) {
		int start_x = x-width/2;
		int start_y = y-height/2;
		
		g.setColor(Color.blue);
		
		g.fill3DRect(start_x, start_y, width, height, true);
		return g;
		
		
	
	}
	
	public void  clear(){
		x=y=0;
		vx=vy=0;
		done = false;
		collison = false;
		
	}
	
	
}


