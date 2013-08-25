import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;


public class Mybutton extends JButton {
	
    private static final long serialVersionUID = 1L;

    
	static enum button{LAUNCH,CREDITS};
	static button button_type;
	
	public Mybutton(String label){
		super(label);
		button_type = button.LAUNCH;
		
	}
	
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
/*
        Dimension originalSize = super.getPreferredSize();
        int gap = (int) (originalSize.height * 0.2);
        int x = originalSize.width + gap;
        int y = gap;
        int diameter = originalSize.height - (gap * 2);

        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, diameter, diameter);
        */
    }

    
    

}
