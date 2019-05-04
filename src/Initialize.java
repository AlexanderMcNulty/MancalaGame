import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//import Scene.MancalaModel;

public class Initialize {

	public static void main(String[] args) {

		MancalaModel mancalaModel = new MancalaModel(4);
//		mancalaModel.printMancala();
//		mancalaModel.turn(0);
//		mancalaModel.printMancala();
//		mancalaModel.turn(6);
//		mancalaModel.printMancala();
//		mancalaModel.turn(7);
//		mancalaModel.printMancala();
//		mancalaModel.turn(3);
//		mancalaModel.printMancala();
//		mancalaModel.turn(7);
//		mancalaModel.printMancala();
//		mancalaModel.turn(3);
//		mancalaModel.printMancala();

		JFrame board = new JFrame();

		
		// create Center of Board
		JPanel mancalaCenter = new JPanel(new BorderLayout());
		JPanel topPits = new JPanel();
		for (int i = 12; i > 6; i--) {
			System.out.println(i);
			Pit pit = new Pit(i, mancalaModel);
			mancalaModel.attach(pit);
			pit.setPreferredSize(new Dimension(100, 130));
			pit.setBorder(new LineBorder(Color.BLACK, 10, true));
			pit.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					mancalaModel.turn(pit.getIndex());
					System.out.println(pit.getIndex());
				}
			});
			topPits.add(pit);
		}
		mancalaCenter.add(topPits, BorderLayout.NORTH);
		// create Mancala1
		Pit mancala1 = new Pit(13, mancalaModel);
		mancalaModel.attach(mancala1);
		mancala1.setPreferredSize(new Dimension(100, 200));
		mancala1.setBorder(new LineBorder(Color.BLACK, 10, true));
		mancala1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println(mancala1.getIndex());
			}
		});
		board.add(mancala1, BorderLayout.WEST);

		// create Center of Board
		mancalaCenter.add(topPits, BorderLayout.NORTH);
		JPanel bottomPits = new JPanel();
		for (int i = 0; i < 6; i++) {
			Pit pit = new Pit(i, mancalaModel);
			mancalaModel.attach(pit);
			pit.setPreferredSize(new Dimension(100, 130));
			pit.setBorder(new LineBorder(Color.BLACK, 10, true));
			pit.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					mancalaModel.turn(pit.getIndex());
					System.out.println(pit.getIndex());
				}
			});
			bottomPits.add(pit);
		}
		mancalaCenter.add(bottomPits, BorderLayout.SOUTH);
		board.add(mancalaCenter, BorderLayout.CENTER);

		// create Mancala2
		Pit mancala2 = new Pit(6, mancalaModel);
		mancalaModel.attach(mancala2);
		mancala2.setPreferredSize(new Dimension(100, 200));
		mancala2.setBorder(new LineBorder(Color.BLACK, 10, true));
		mancala2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println(mancala2.getIndex());
			}
		});
		board.add(mancala2, BorderLayout.EAST);
		
		board.setPreferredSize(new Dimension(950, 375));
		board.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.lightGray));
		board.getContentPane().setBackground(Color.WHITE);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.pack();
		board.setVisible(true);

	}

}
