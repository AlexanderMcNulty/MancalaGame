import javax.swing.JLabel;

/**
 * JLabel for indicating which player's turn
 * 
 * @author Alexander McNulty, James Wang, Keonwoong Min
 *
 */
public class TurnLabel extends JLabel implements Observer
{
   MancalaModel model;

   public TurnLabel(MancalaModel model)
   {
      this.model = model;
      model.attach(this);
      viewNotify();
   }

   @Override
   public void viewNotify()
   {
      if (model.getEndOfGame() == true)
      {
         this.setText("The game is over!!  ");
      } else if (model.checkTopsTurn())
      {

         this.setText("It is Top Player's Turn.  ");

      } else
      {
         this.setText("It is Bottom Player's Turn.  ");
      }
   }

}
