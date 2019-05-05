import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Pit extends JPanel implements Observer{

	private int pitIndex;
	private int stoneCount;
	private MancalaModel model;
	private RectangularShape rShape;
	private JLabel pitLabel;
	private int count1;
	private int count2;
	private boolean ended;
	

	public Pit(int pitIndex, MancalaModel model, RectangularShape rShape) {
		this.pitIndex = pitIndex;
		this.model = model;
		this.rShape = rShape;
		model.attach(this);
		this.setPreferredSize(new Dimension(100, 125));
		this.setBorder(new LineBorder(Color.BLACK, 10, true));
		pitLabel = new JLabel();
		ended = false;
		
	}
	public void setLabel(String labelString)
	{
		pitLabel.setText(labelString);
		add(pitLabel);
	}
	@Override
	public void viewNotify() {
		//draw stones
		stoneCount =  model.getPitScores()[pitIndex];
//		for(int i = 0; i < 6; i ++)
//		{
//			count1 = count1 +this.
//		}
//		for(int i = 7; i < 13; i ++)
//		{
//			count2= count2 + model.getPitScores()[i];
//		}
//		System.out.println("count2: " + count2);
//		if(count1 == 0 || count2 == 0)
//		{
//			ended = true;
//		}
		this.repaint();
		//System.out.println(pitIndex + ": " + stoneCount);
	}
	public boolean getEnded()
	{
		return ended;
	}
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        Graphics2D g2 = ( Graphics2D ) g;   
        g2.setPaint( Color.RED );
   		for(int i = 0; i < stoneCount; i++) {
   		   rShape.setFrame( 15 + (i/6)*15, 15 + i*15 - (i/6)*90, 12, 12 );
	       g2.fill(rShape);
    	}
    }
    
    public int getIndex() {
    	return pitIndex;
    }

}
