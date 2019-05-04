import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//import Scene.MancalaModel;

public class Initialize {

	static JFrame board; 
	static MancalaModel mancalaModel;
	static boolean isBottom;
	
	
	public static void main(String[] args) {
		mancalaModel = new MancalaModel(0);
		board = new JFrame();
		addInputFeild();
		
		board.setPreferredSize(new Dimension(950, 375));
		board.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.lightGray));
		board.getContentPane().setBackground(Color.WHITE);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.pack();
		board.setVisible(true);
	}
	
	public static boolean tryParse(String text) {
	      int num;
	      try {
	         num = Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e1) {
	         return false;
	      }
	}

	public static void addInputFeild() {
	      JPanel north = new JPanel();
	      JLabel label = new JLabel("Set stones per pit (Max is 4): ");
	      north.add(label);
	      north.setPreferredSize(new Dimension(350, 30));
	      JTextField t1 = new JTextField();
	      t1.setColumns(4);
	      t1.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            String input = t1.getText();
	            if (tryParse(input)) {
	               if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < 5){
	                  label.setText("[Game started] Enter new value to restart: ");
		              mancalaModel.setStones(Integer.parseInt(input));
	               } else {
	                  label.setText("[set to default] Enter new value to restart: ");
		              mancalaModel.setStones(4);
	               }
	               createBoard();
	       		   addStatusFeild();
	       		
	            } else {
	               label.setText("Invalid stone count, try again.");
	            }
	         }
	      });
	      north.add(label);
	      north.add(t1);
	      board.add(north, BorderLayout.NORTH);
	}
	
	
	public static void addStatusFeild() {
		JPanel south = new JPanel();
		
		JButton undoButton = new JButton("Undo last move");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mancalaModel.undo();
			}
		});

		TurnLabel currentTurn = new TurnLabel(mancalaModel);
		south.add(currentTurn);
        south.add(undoButton);
        board.add(south, BorderLayout.SOUTH);
	}
	
	
	public static void createBoard() {
		// Create Top Player Pits
		JPanel mancalaCenter = new JPanel(new BorderLayout());
		JPanel topPits = new JPanel();
		for (int i = 12; i > 6; i--) {
			Pit pit = new Pit(i, mancalaModel);
			pit.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent arg0) {
					System.out.println("Clicked Top Pit: " + pit.getIndex());
					if(mancalaModel.checkTopsTurn() == true) {
						mancalaModel.turn(pit.getIndex(), 1);
					}
				}
			});
			topPits.add(pit);
		}
		mancalaCenter.add(topPits, BorderLayout.NORTH);
		Pit mancala1 = new Pit(13, mancalaModel);
		board.add(mancala1, BorderLayout.WEST);

		// Create Bottom Player Pits
		mancalaCenter.add(topPits, BorderLayout.NORTH);
		JPanel bottomPits = new JPanel();
		for (int i = 0; i < 6; i++) {
			Pit pit = new Pit(i, mancalaModel);
			pit.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent arg0) {
					System.out.println("Clicked Top Pit: " + pit.getIndex());
					if(mancalaModel.checkTopsTurn() == false) {
						mancalaModel.turn(pit.getIndex(), 2);
					}			
				}
			});
			bottomPits.add(pit);
		}
		mancalaCenter.add(bottomPits, BorderLayout.SOUTH);
		Pit mancala2 = new Pit(6, mancalaModel);
		board.add(mancala2, BorderLayout.EAST);
		
		board.add(mancalaCenter, BorderLayout.CENTER);
	}
	
}
