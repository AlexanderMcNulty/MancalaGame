import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Models a pit
 * 
 * @author Alexander McNulty, James Wang, Keonwoong Min
 *
 */
public class Pit extends JPanel implements Observer
{
   private int pitIndex;
   private int stoneCount;
   private MancalaModel model;
   private RectangularShape rShape;
   private JLabel pitLabel;
   private boolean ended;

   /**
    * Constructs pit with its index on the board (model), and its shape for the
    * view
    * 
    * @param pitIndex is the index of the pit
    * @param model    is the model of the board
    * @param rShape   is the shape of the pit for the view
    */
   public Pit(int pitIndex, MancalaModel model, RectangularShape rShape)
   {
      this.pitIndex = pitIndex;
      this.model = model;
      this.rShape = rShape;
      model.attach(this);
      this.setPreferredSize(new Dimension(100, 125));
      this.setBorder(new LineBorder(Color.BLACK, 10, true));
      pitLabel = new JLabel();
      ended = false;

   }

   /**
    * Sets the label of the pit to show its index to user
    * 
    * @param labelString is the label to set
    */
   public void setLabel(String labelString)
   {
      pitLabel.setText(labelString);
      add(pitLabel);
   }

   @Override
   public void viewNotify()
   {
      stoneCount = model.getPitScores()[pitIndex];
      this.repaint();
   }

   /**
    * Gets if the game has ended
    * 
    * @return T/F if the game ended
    */
   public boolean getEnded()
   {
      return ended;
   }

   /**
    * Paints the pit
    */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setPaint(Color.RED);
      for (int i = 0; i < stoneCount; i++)
      {
         rShape.setFrame(15 + (i / 6) * 15, 15 + i * 15 - (i / 6) * 90, 12, 12);
         g2.fill(rShape);
      }
   }

   /**
    * Gets the index of the pit
    * 
    * @return the index of the pit
    */
   public int getIndex()
   {
      return pitIndex;
   }

}
