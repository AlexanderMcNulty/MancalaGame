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
		createBoard();
		addInputFeild();
		addStatusFeild();
		
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
		JLabel turn = new JLabel("Bottom Player");
		
		JButton undoButton = new JButton("undo");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mancalaModel.undo();
			}
		});

	      south.add(turn);
	      south.add(undoButton);
	      board.add(south, BorderLayout.SOUTH);
	}
	
	
	public static void createBoard() {

		// create Center of Board
		JPanel mancalaCenter = new JPanel(new BorderLayout());
		JPanel topPits = new JPanel();
		for (int i = 12; i > 6; i--) {
			Pit pit = new Pit(i, mancalaModel);
			pit.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent arg0) {
					mancalaModel.turn(pit.getIndex());
					System.out.println("pit Clicked: " + pit.getIndex());
				}
			});
			topPits.add(pit);
		}
		mancalaCenter.add(topPits, BorderLayout.NORTH);
		
		// create Mancala1
		Pit mancala1 = new Pit(13, mancalaModel);
		mancala1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				System.out.println("pit Clicked: " + mancala1.getIndex());			}
		});
		board.add(mancala1, BorderLayout.WEST);

		// create Center of Board
		mancalaCenter.add(topPits, BorderLayout.NORTH);
		JPanel bottomPits = new JPanel();
		for (int i = 0; i < 6; i++) {
			Pit pit = new Pit(i, mancalaModel);
			pit.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent arg0) {
					mancalaModel.turn(pit.getIndex());
					System.out.println("pit Clicked: " + pit.getIndex());				}
			});
			bottomPits.add(pit);
		}
		mancalaCenter.add(bottomPits, BorderLayout.SOUTH);
		board.add(mancalaCenter, BorderLayout.CENTER);

		// create Mancala2
		Pit mancala2 = new Pit(6, mancalaModel);
		mancala2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("pit Clicked: " + mancala2.getIndex());			}
		});
		board.add(mancala2, BorderLayout.EAST);
		
	}
	
}
