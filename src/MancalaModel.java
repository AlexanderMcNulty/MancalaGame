

import java.util.ArrayList;

public class MancalaModel {
	PitModel[] pits;
	PitModel[] undoPits;
	ArrayList<Observer> observers;
	
	boolean isTopPlayersTurn;
	boolean cannotUndo;
	int player1Moves;
	int player2Moves;
	
	public MancalaModel(int stones) {
		isTopPlayersTurn = false;
		cannotUndo = false;
		player1Moves = 0;
		player2Moves = 0;
		
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
	
	public PitModel[] clone() {
		PitModel[] toReturn = new PitModel[14];
		for(int i = 0; i < 14; i++) {
			toReturn[i] = pits[i].clone();
		}
		return toReturn;
	}
	
	public void undo() {
		if(cannotUndo == false) {
			this.isTopPlayersTurn = !this.isTopPlayersTurn;
			pits = undoPits;
			stateChanged();
		}
	}
	
	public void setStones(int stones){
		for(int i = 0; i < 6; i++) {
			pits[i].setStoneCount(stones);
		}
		for(int i = 7; i < 13; i++) {
			pits[i].setStoneCount(stones);
		}
		stateChanged();
	}
	
	public int[] getPitScores() {
		int[] toReturn = new int[14];
		for(int i = 0; i < 14; i++) {
			toReturn[i] = pits[i].getCount();
		}
		return toReturn;
	}
	
	public void printMancala() {
		System.out.println("   "+pits[12]+" "+pits[11]+" "+pits[10]+" "+pits[9]+" "+pits[8]+" "+pits[7]);
		System.out.println(pits[13]+"               "+pits[6]);
		System.out.println("   "+pits[0]+" "+pits[1]+" "+pits[2]+" "+pits[3]+" "+pits[4]+" "+pits[5]+"  \n\n");
	}
	
	public void turn(int pit, int player) {
		if(isTopPlayersTurn) {
			player1Moves++;
			player2Moves = 0;
		} else {
			player2Moves++;
			player1Moves = 0;
		}
		if(player2Moves > 3 || player1Moves > 3) {
			cannotUndo = true;
		} else {
			cannotUndo = false;
		}
		
		undoPits = this.clone();
		int currentStones = pits[pit].getStones();
		int currentPit = pit+1;
		while(currentStones != 0) {
			if(currentPit > 13) {
				currentPit = 0;
			}
			pits[currentPit].addStone();
			currentStones--;
			currentPit++;
		}
		currentPit--;
		if(currentPit > 13) {
			currentPit = 0;
		}
		if(pits[currentPit].getCount() == 1 && pits[currentPit].isMancala() == false) {
			int steal = pits[pits[currentPit].getOpposite()].getStones();
			if(currentPit < 7) {
				pits[6].addStone(steal);
			} else {
				pits[13].addStone(steal);
			}
		}
		this.isTopPlayersTurn = !this.isTopPlayersTurn;
		stateChanged();
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
		stateChanged();
	}
	
	public void stateChanged() {
		for(Observer o : observers) {
			o.viewNotify();
		}
	}
	
	public boolean checkTopsTurn() {
		return isTopPlayersTurn;
	}
	
}
