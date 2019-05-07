import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Anonymous class to model a board with displays and contains pits
 * 
 * @author Alexander McNulty, James Wang, Keonwoong Min
 *
 */
public abstract class Board extends JPanel
{
   private MancalaModel mancalaModel;

   /**
    * To use borderLayout
    */
   public Board()
   {
      this.setLayout(new BorderLayout());
   }

   public abstract void createBoard();

}
