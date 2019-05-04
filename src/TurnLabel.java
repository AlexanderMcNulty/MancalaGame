import javax.swing.JLabel;

public class TurnLabel extends JLabel implements Observer {
	MancalaModel model;
	
	public TurnLabel(MancalaModel model ) {
		this.model = model;
		model.attach(this);
		viewNotify();
	}
	
	@Override
	public void viewNotify() {
		if(model.checkTopsTurn()) {
			this.setText("It is Top Player's Turn.  ");
		} else {
			this.setText("It is Bottom Player's Turn.  ");
		}
	}

}
