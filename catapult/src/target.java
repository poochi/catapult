import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class target {
	public int x,y;//this is center
	public int width,height;//this is boundary
	private Color color = Color.GREEN;
	private Random rand = new Random(); // For randomly placing the food
	
	public target(){
		x = 0;
		y = 0;
		
		width = 4*game.CELL_SIZE;
		height = 4*game.CELL_SIZE;
	}
	public void regenerate(){
		x = rand.nextInt(game.WINDOW_COLUMN - 100) + 40; // edges are avoided
	    y = rand.nextInt(game.WINDOW_ROW - 100) + 40; //edges are avoided
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean contains(int x,int y){
	
	return ((this.x-width/2)>x && (this.x+width/2)<=x &&  (this.y-height/2)>y && (this.y+height/2)<=y);
	
	}
	
	public Graphics draw(Graphics g) {
		
				g.setColor(Color.BLUE);
				//System.out.format(" targ %d %d\n",x,y);
				
				g.fill3DRect(x , y ,width,height, true);
				return g;
		
	}
	public void clear(){
		x = 0;
		y = 0;
	}
}
	


