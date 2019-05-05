import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//import Scene.MancalaModel;

/**
 * 
 * @author Alexander McNulty, James Wang, Keonwoong Min
 * MancalaFrame:
 * - initializes the model
 * - creates views and controllers
 */
public class MancalaFrame {

	// board - an annoymous class with displays and contains pits
	static Board board; 
	// MancalaModel - the model which holds current turn, and pit stone count
	static MancalaModel mancalaModel;
	// north - a JPanel which holds controllers for implementing strategy pattern and mutating model
	static JPanel north;
	// boardFrame - A JFrame which holds all elements in the board
	static JFrame boardFrame;
	
	/**
	 * main - creates the boardFrame Object, initialized model and global variable JPanel north
	 * @param args - default argument of main() - not used
	 */
	public static void main(String[] args) {
		mancalaModel = new MancalaModel(0);

		boardFrame = new JFrame();
		north = new JPanel();
		chooseStyle();

		boardFrame.setPreferredSize(new Dimension(1050, 475));
		boardFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.lightGray));
		boardFrame.setBackground(Color.WHITE);
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardFrame.pack();
		boardFrame.setVisible(true);
	}
	
	/**
	 * chooseStyle - creates two buttons which create a different annoymo
	 * us classes which extend board (Strategy Pattern)
	 */
	public static void chooseStyle() {
		
		// create a button for strategy pattern 1
		JButton rectangleButton = new JButton("Rectangular Stones");
		rectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board = new Board() {
					public void createBoard() {
						RectangularShape specialShape = new Rectangle2D.Double();
						// Create Top Player Pits
						JPanel mancalaCenter = new JPanel(new BorderLayout());
						JPanel topPits = new JPanel();
						for (int i = 12; i > 6; i--) {
							
							Pit pit = new Pit(i, mancalaModel, specialShape);
							String labelString = "B" + (i -6); 
							pit.setLabel(labelString);
							pit.addMouseListener(new MouseAdapter() {
								public void mousePressed(MouseEvent arg0) {
									System.out.println("Clicked Top Pit: " + pit.getIndex());
									if( !pit.getEnded()  &&mancalaModel.checkTopsTurn() == true ) 
									{
										mancalaModel.turn(pit.getIndex(), 1);
									}
									else
									{
										System.out.println("hahahahaended");
									}
								}
							});
							topPits.add(pit);
						}
						mancalaCenter.add(topPits, BorderLayout.NORTH);
						Pit mancala1 = new Pit(13, mancalaModel, specialShape);
						this.add(mancala1, BorderLayout.WEST);

						// Create Bottom Player Pits
						mancalaCenter.add(topPits, BorderLayout.NORTH);////
						JPanel bottomPits = new JPanel();
						for (int i = 0; i < 6; i++) {
							
							
							Pit pit = new Pit(i, mancalaModel, specialShape);
							String labelString = "A" + (i+1); 
							pit.setLabel(labelString);
							pit.addMouseListener(new MouseAdapter() {
								public void mousePressed(MouseEvent arg0) {
									System.out.println("Clicked Top Pit: " + pit.getIndex());
									if(!pit.getEnded()  && mancalaModel.checkTopsTurn() == false
											 ) {
										mancalaModel.turn(pit.getIndex(), 2);
									}			
									else
									{
										System.out.println("hahahahaended");
									}
								}
							});
							bottomPits.add(pit);
						}
						mancalaCenter.add(bottomPits, BorderLayout.SOUTH);/////
						Pit mancala2 = new Pit(6, mancalaModel, specialShape);
						this.add(mancala2, BorderLayout.EAST);
						
						this.add(mancalaCenter, BorderLayout.CENTER);
					}
				};
				boardFrame.add(board, BorderLayout.CENTER);
				addInputFeild();
				boardFrame.revalidate();
				

					
			}
		});
		
		// create a button for strategy pattern 2
		JButton circularButton = new JButton("Circular Stones");
		circularButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board = new Board() {
					public void createBoard() {
						RectangularShape specialShape = new Ellipse2D.Double();
						// Create Top Player Pits
						JPanel mancalaCenter = new JPanel(new BorderLayout());
						JPanel topPits = new JPanel();
						for (int i = 12; i > 6; i--) {
							Pit pit = new Pit(i, mancalaModel, specialShape);
							pit.addMouseListener(new MouseAdapter() {
								public void mousePressed(MouseEvent arg0) {
									System.out.println("Clicked Top Pit: " + pit.getIndex());
									if(!pit.getEnded()  && mancalaModel.checkTopsTurn() == true
											)  {
										mancalaModel.turn(pit.getIndex(), 1);
									}
									else
									{
										System.out.println("hahahahaended");
									}
								}
							});
							topPits.add(pit);
						}
						mancalaCenter.add(topPits, BorderLayout.NORTH);
						Pit mancala1 = new Pit(13, mancalaModel, specialShape);
						this.add(mancala1, BorderLayout.WEST);

						// Create Bottom Player Pits
						mancalaCenter.add(topPits, BorderLayout.NORTH);
						JPanel bottomPits = new JPanel();
						for (int i = 0; i < 6; i++) {
							Pit pit = new Pit(i, mancalaModel, specialShape);
							pit.addMouseListener(new MouseAdapter() {
								public void mousePressed(MouseEvent arg0) {
									System.out.println("Clicked Top Pit: " + pit.getIndex());
									if(!pit.getEnded() && mancalaModel.checkTopsTurn() == false
											 ) {
										mancalaModel.turn(pit.getIndex(), 2);
									}			
									else
									{
										System.out.println("hahahahaended");
									}
								}
							});
							bottomPits.add(pit);
						}
						mancalaCenter.add(bottomPits, BorderLayout.SOUTH);
						Pit mancala2 = new Pit(6, mancalaModel, specialShape);
						this.add(mancala2, BorderLayout.EAST);
						
						this.add(mancalaCenter, BorderLayout.CENTER);
					}
				};boardFrame.add(board, BorderLayout.CENTER);

				addInputFeild();
				boardFrame.revalidate();

			}
		});
		north.add(rectangleButton);
		north.add(circularButton);
		boardFrame.add(north, BorderLayout.NORTH);
	    boardFrame.repaint();

	}

	
	/**
	 * addInputFeild - renders label and input feild to initial stone count in model
	 * 				 - addInputFeild is called after a board strategy has been choosen
	 */
	public static void addInputFeild() {

		  north.removeAll();
		  north.repaint();
		
	      JLabel label = new JLabel("Set stones per pit (Max is 4): ");
	      north.add(label);
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
	               board.createBoard();

	 
            	   			addStatusFeild();
            		 
	      
	       		
	            } else {
	               label.setText("Invalid stone count, try again.");
	            }
	         }
	      });
	      north.add(label);
	      north.add(t1);
	      
		  boardFrame.add(north, BorderLayout.NORTH);	      
	}
	
	/**
	 * tryParse - used to validate text entered into the text feild 
	 * @param text - text is passed when the user hits enter after inputFeild
	 * @return boolean - if the text could be parsed to number 
	 */
	static public boolean tryParse(String text) {
	      int num;
	      try {
	         num = Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e1) {
	         return false;
	      }
	}
	

	/**
	 * addStatusFeild - the status feild contains a controller which allows players to undo a move
	 * 				  - as well as a view which displays which players turn it currently is
	 */
	static public void addStatusFeild() {
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
        boardFrame.add(south, BorderLayout.SOUTH);
	}

}
