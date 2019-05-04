import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Pit extends JPanel implements Observer{

	private int pitIndex;
	private int stoneCount;
	private MancalaModel model;
	

	public Pit(int pitIndex, MancalaModel model) {
		this.pitIndex = pitIndex;
		this.model = model;
		model.attach(this);
		this.setPreferredSize(new Dimension(100, 125));
		this.setBorder(new LineBorder(Color.BLACK, 10, true));
	}
	
	@Override
	public void viewNotify() {
		//draw stones
		stoneCount =  model.getPitScores()[pitIndex];
		this.repaint();
		//System.out.println(pitIndex + ": " + stoneCount);
	}
	
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        Graphics2D g2 = ( Graphics2D ) g;   
        g2.setPaint( Color.RED );
   		for(int i = 0; i < stoneCount; i++) {
	       g2.fill( new Ellipse2D.Double( 15 + (i/6)*15, 15 + i*15 - (i/6)*90, 12, 12 ) );
    	}
    }
    
    public int getIndex() {
    	return pitIndex;
    }

}
