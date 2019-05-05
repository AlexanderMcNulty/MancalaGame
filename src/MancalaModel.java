
import java.util.ArrayList;

/**
 * Models a Mancala Board with pits and player logic
 * 
 * @author Alexander McNulty, James Wang, Keonwoong Min
 * 
 */
public class MancalaModel
{
   private PitModel[] pits;
   private PitModel[] undoPits;
   private ArrayList<Observer> observers;
   private boolean isTopPlayersTurn;
   private boolean cannotUndo;
   private boolean endOfGame;
   private int player1Moves;
   private int player2Moves;
   private int lastP1Status;
   private int lastP2Status;
   private int currentPitForUndo;
   /**
    * Constructs and initializes model with number of stones per pit
    * 
    * @param stones is the number of stones
    */
   public MancalaModel(int stones)
   {
      isTopPlayersTurn = false;
      cannotUndo = false;
      endOfGame = false;
      player1Moves = 0;
      player2Moves = 0;
      lastP1Status = 0;
      lastP2Status = 0;

      observers = new ArrayList<>();
      pits = new PitModel[14];
      pits[0] = new PitModel(stones, false, 12);
      pits[1] = new PitModel(stones, false, 11);
      pits[2] = new PitModel(stones, false, 10);
      pits[3] = new PitModel(stones, false, 9);
      pits[4] = new PitModel(stones, false, 8);
      pits[5] = new PitModel(stones, false, 7);
      pits[6] = new PitModel(0, true);
      pits[7] = new PitModel(stones, false, 5);
      pits[8] = new PitModel(stones, false, 4);
      pits[9] = new PitModel(stones, false, 3);
      pits[10] = new PitModel(stones, false, 2);
      pits[11] = new PitModel(stones, false, 1);
      pits[12] = new PitModel(stones, false, 0);
      pits[13] = new PitModel(0, true);
      undoPits = clone();
   }

   /**
    * Method to clone the previous pit models for undo functionality
    */
   public PitModel[] clone()
   {
      PitModel[] toReturn = new PitModel[14];
      for (int i = 0; i < 14; i++)
      {
         toReturn[i] = pits[i].clone();
      }
      return toReturn;
   }

   /**
    * Method for undoing player moves conforming to game rules
    */
   public void undo()
   {
      if (!(lastP1Status == player1Moves && lastP2Status == player2Moves))
      {
         if (cannotUndo == false)
         {
            endOfGame = false;
            lastP1Status = player1Moves;
            lastP2Status = player2Moves;
            System.out.println(currentPitForUndo);
            if(currentPitForUndo == 7 || currentPitForUndo == 14)
            {
            	this.isTopPlayersTurn = this.isTopPlayersTurn;
            }
            else
            {
            	this.isTopPlayersTurn = !this.isTopPlayersTurn;
            }
            
            pits = undoPits;
            stateChanged();
         }
      }
   }

   /**
    * Initializes the number of stones
    * 
    * @param stones the number of stones to set
    */
   public void initializeStones(int stones)
   {
      endOfGame = false;
      for (int i = 0; i < 6; i++)
      {
         pits[i].setStoneCount(stones);
      }
      for (int i = 7; i < 13; i++)
      {
         pits[i].setStoneCount(stones);
      }
      pits[6].setStoneCount(0);
      pits[13].setStoneCount(0);

      stateChanged();
   }

   /**
    * Gets the number of stones in every pit, then returns that number as an array
    * in the indexing of a mancala board
    * 
    * @return the number of stones present in every pit
    */
   public int[] getPitScores()
   {
      int[] toReturn = new int[14];
      for (int i = 0; i < 14; i++)
      {
         toReturn[i] = pits[i].getCount();
      }
      return toReturn;
   }

   /**
    * Method to give a player a turn following game rules
    * 
    * @param pit    is the pit in which the turn is initiated on
    * @param player is which player is taking the turn
    */
   public void turn(int pit, int player)
   {
      if (pits[pit].getCount() != 0)
      {
         if (isTopPlayersTurn)
         {
            player1Moves++;
            player2Moves = 0;
         } else
         {
            player2Moves++;
            player1Moves = 0;
         }

         // check if one side is empty
         endOfGame = true;
         for (int i = 0; i < 6; i++)
         {
            if (pits[i].getCount() > 0 && endOfGame == true)
            {
               endOfGame = false;
            }
         }
         if (endOfGame == false)
         {
            endOfGame = true;
            for (int i = 12; i > 6; i--)
            {
               if (pits[i].getCount() > 0)
               {
                  endOfGame = false;
               }
            }
         }

         if (endOfGame == false)
         {

            if (player2Moves > 3 || player1Moves > 3)
            {
               cannotUndo = true;
            } else
            {
               cannotUndo = false;
            }
            // sets for undo functionality
            undoPits = this.clone();
           
            int currentStones = pits[pit].getStones();
            int currentPit = pit + 1;
            while (currentStones != 0)
            {
               if (currentPit > 13)
               {
                  currentPit = 0;
               }
               pits[currentPit].addStone();
               currentStones--;
               currentPit++;
               currentPitForUndo = currentPit;
            }
            currentPit--;
            if (currentPit > 13)
            {
               currentPit = 0;
            }
            
            if(pits[currentPit].getCount() == 1 && pits[currentPit].isMancala() == false
                  && pits[pits[currentPit].getOpposite()].getCount() > 0)
            {
            	
               int steal = pits[pits[currentPit].getOpposite()].getStones() + pits[currentPit].getStones();
               if (currentPit < 7)
               {
                  pits[6].addStone(steal); // A's mancala
               } else
               {
                  pits[13].addStone(steal); // B's mancala
               }
               
            }
            //(!pits[currentPit].isMancala())
            if(!pits[currentPit].isMancala())
            {
            	this.isTopPlayersTurn = !this.isTopPlayersTurn;
            }
//            else
//            {
//               this.isTopPlayersTurn = !this.isTopPlayersTurn;
//            }
         }

         // check if one side is empty
         endOfGame = true;
         for (int i = 0; i < 6; i++)
         {
            if (pits[i].getCount() > 0 && endOfGame == true)
            {
               endOfGame = false;
            }
         }
         if (endOfGame == false)
         {
            endOfGame = true;
            for (int i = 12; i > 6; i--)
            {
               if (pits[i].getCount() > 0)
               {
                  endOfGame = false;
               }
            }
         }
         stateChanged();
      }
   }

   /**
    * Attaches observers
    * 
    * @param observer is the observer to attach
    */
   public void attach(Observer observer)
   {
      observers.add(observer);
      stateChanged();
   }

   /**
    * State changed for observers
    */
   public void stateChanged()
   {
      for (Observer o : observers)
      {
         o.viewNotify();
      }
   }

   /**
    * Checks if it is player B's turn
    * 
    * @return T/F if it is player B's turn
    */
   public boolean checkTopsTurn()
   {
      return isTopPlayersTurn;
   }

   /**
    * Checks if it is the end of a game
    * 
    * @return T/F if the game ended
    */
   public boolean getEndOfGame()
   {
      return endOfGame;
   }

}
