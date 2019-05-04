

public class PitModel {
	private int stones;
	private boolean isMancala;
	private int opposite;

	PitModel(int stones, boolean isMancala) {
		this.stones = stones;
		this.isMancala = isMancala;
	}
	PitModel(int stones, boolean isMancala, int opposite) {
		this.stones = stones;
		this.isMancala = isMancala;
		this.opposite = opposite;
	}
	public int getStones() {
		int temp = stones;
		stones = 0;
		return temp;
	}
	public void setStoneCount(int count) {
		stones = count;
	}
	public void addStone() {
		stones++;
	}
	public void addStone(int steal) {
		stones += steal;
	}
	public int getCount() {
		return stones;
	}
	public boolean isMancala() {
		return isMancala;
	}
	public int getOpposite() {
		return opposite;
	}
	
	public PitModel clone() {
		return new PitModel(stones, isMancala, opposite);
	}
	
	@Override
	public String toString() {
		return String.valueOf(stones);
	}
}
