import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.lang.*;


public class projectile {

	public int power;	//to calculate power associated with projectile during start 
	public int marked_x,marked_y;
	public double vx,vy; //starting velocity of projectile
	public int x,y;
	public int width,height;
	private Color color = Color.RED;
	private Random rand = new Random(); // For randomly placing the food
	static int MAX_POWER = 100; 
	
	
	
	
	public projectile(){
		power = 0;
		marked_x=marked_y = 10;
		width = 4*game.CELL_SIZE; 
		height = 4*game.CELL_SIZE;
		
		 
	}
	public void regenerate(){
		x = rand.nextInt(game.WINDOW_COLUMN - 100) + 40; // edges are avoided
	    y = rand.nextInt(game.WINDOW_ROW - 100) + 40; //edges are avoided
	}
	
	public void set_marker(int x,int y){
		marked_x = x;
		marked_y = y;
	}
	
	public void set_power(int p){
		
		power = p;
		System.out.printf("set power %d",power);
	}
	
	
	public double vx(){
		
		double distance = (this.x+this.width/2-marked_x)*(this.x-marked_x) + (this.y+this.height/2-marked_y)*(this.y+this.height/2-marked_y);
		distance = Math.sqrt(distance);
		vx= power*(marked_x-this.x-this.width/2)/(distance);
		System.out.printf("vx = %f\n",vx);
		return vx;
	}
	
	public double vy(){
		double distance = (this.x+this.width/2-marked_x)*(this.x+this.width/2-marked_x) + (this.y+this.height/2-marked_y)*(this.y+this.height/2-marked_y);
		distance = Math.sqrt(distance);
		
		vy= power*(marked_y-this.y-this.height/2)/(distance);
		System.out.printf("vy = %f \n",vy);
		return vy;
	}
	public int start_x(){
	return x+this.width/2;
	}
	public int start_y(){
		return y+this.height/2;
	}
	
	public boolean contains(int x,int y){
		return ((x==this.x)&& (y==this.y));
		
	}
	
	public Graphics draw(Graphics g) {
		{
				g.setColor(color);
				//System.out.format(" proj %d %d\n",x,y);
				g.fill3DRect(x, y,
	                     width, height, true);
		return g;
		}
	
	}
	
public void clear(){
	power = 0;
	marked_x=marked_y=10;
	
}
}
