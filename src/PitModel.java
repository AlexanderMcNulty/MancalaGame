
/**
 * Models the the model for the pits
 * 
 * @author Alexander McNulty, James Wang, Keonwoong Min
 *
 */
public class PitModel
{
   private int stones;
   private boolean isMancala;
   private int opposite;

   /**
    * Construct the PitModel for the Mancala with the number of stones and if it is
    * a Mancala
    * 
    * @param stones    is the number of stones for the pit
    * @param isMancala T/F if the pit is a Mancala
    */
   PitModel(int stones, boolean isMancala)
   {
      this.stones = stones;
      this.isMancala = isMancala;
   }

   /**
    * Constructs a PitModel for the pit with number of stones, T/F is Mancala, and
    * its opposite pit
    * 
    * @param stones    is the number of stones for the pit
    * @param isMancala T/F if it is a Mancala
    * @param opposite  its opposite pit
    */
   PitModel(int stones, boolean isMancala, int opposite)
   {
      this.stones = stones;
      this.isMancala = isMancala;
      this.opposite = opposite;
   }

   /**
    * Gets and Sets the stoneCount of the stone to 0 and returns the original
    * stoneCount
    * 
    * @return original stoneCount before setting to 0
    */
   public int getStones()
   {
      int temp = stones;
      stones = 0;
      return temp;
   }

   /**
    * Sets the number of stones for the pit
    * 
    * @param count is the number of stones to set
    */
   public void setStoneCount(int count)
   {
      stones = count;
   }

   /**
    * Adds one stone to the pit
    */
   public void addStone()
   {
      stones++;
   }

   /**
    * Adds a number of stones to the pit
    * 
    * @param steal is the number of stones to add
    */
   public void addStone(int steal)
   {
      stones += steal;
   }

   /**
    * Gets the stone count of the pit
    * 
    * @return the stone count of the pit
    */
   public int getCount()
   {
      return stones;
   }

   /**
    * Gets T/F if the pit is a Mancala
    * 
    * @return T/F if the pit is a Mancala
    */
   public boolean isMancala()
   {
      return isMancala;
   }

   /**
    * Gets the opposite pit index
    * 
    * @return the opposite pit index if the pit to check for opposite is not a
    *         mancala
    */
   public int getOpposite()
   {
      if (isMancala == false)
      {
         return opposite;
      } else
         return -1;
   }

   /**
    * Clone function for undo functionality
    */
   public PitModel clone()
   {
      return new PitModel(stones, isMancala, opposite);
   }

   @Override
   public String toString()
   {
      return String.valueOf(stones);
   }
}
