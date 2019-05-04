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

	public static boolean tryParse(String text)
	   {
	      int num;
	      try
	      {
	         num = Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e1)
	      {
	         return false;
	      }
	   }
	
	public static void main(String[] args) {

		MancalaModel mancalaModel = new MancalaModel(0);

		JFrame board = new JFrame();

		
		
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
		
		
		
		
		
		

	      JPanel north = new JPanel();
	      JLabel label = new JLabel("Max of 4");
	      north.add(label);
	      north.setPreferredSize(new Dimension(350, 30));
	      JTextField t1;
	      int numStones = 0;
	      t1 = new JTextField("Enter the number of stones per pit: ");

	      t1.addActionListener(new ActionListener()
	      {
	         @Override
	         public void actionPerformed(ActionEvent e)
	         {
	            String input = t1.getText();
	            if (tryParse(input))
	            {
	            	mancalaModel.setStones(Integer.parseInt(input));
	               if (numStones > 0 && numStones < 5)
	               {
	                  label.setText("Game started. ");
	                  //System.out.println(numStones);

	               } else
	               {
	                  label.setText("Invalid stone count, try again.");
	               }
	            } else
	            {
	               label.setText("Invalid stone count, try again.");
	            }
	         }

	      });
	      north.add(t1);
	      north.add(label);

	      // MancalaModel mancalaModel = new MancalaModel(numStones);
	      board.add(north, BorderLayout.NORTH);

	      //mancalaModel.setStones(numStones);

		
		
		
		
		
		
		
		board.setPreferredSize(new Dimension(950, 375));
		board.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.lightGray));
		board.getContentPane().setBackground(Color.WHITE);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.pack();
		board.setVisible(true);

	}

}
