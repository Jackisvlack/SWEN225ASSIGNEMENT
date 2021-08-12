

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

class GameGUI extends JPanel implements ActionListener {
    private Game game;
    private JFrame frame;
    private int squarelWidth, squareHeight = 25;
    private int moves;
    public boolean inEstate = false;


    public GameGUI (Game game, JFrame frame){
        this.game = game;
        this.frame = frame;
        repaint();
    }

    public void paint(Graphics g) { 
        super.paint(g);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = this.getSize().width;
		int y = this.getSize().height;
		Graphics2D gtd = (Graphics2D) g;
		
        drawBoard(gtd, x, y);
        displayCurrentPlayer(gtd);
        addMoveButtons(x, y);
        addButtons(x, y, gtd);
        showMoves(gtd);
        
    }

    public void displayCurrentPlayer(Graphics2D gtd){
        String name = game.getCurrentPlayerName();
        gtd.setColor(Color.black);
        Font font = new Font("Verdana", Font.BOLD, 12);
        gtd.setFont(font);
        gtd.drawString(name + " it's your turn", 510, 20);
    }

    public void displayInformation(Graphics2D gtd, String string){
        String name = game.getCurrentPlayerName();
        gtd.setColor(Color.black);
        Font font = new Font("Verdana", Font.BOLD, 12);
        gtd.setFont(font);
        gtd.drawString(name + " it's your turn", 510, 20);
    }

    public void showMoves(Graphics2D gtd){
        gtd.setColor(Color.black);
        Font font = new Font("Verdana", Font.BOLD, 12);
        gtd.setFont(font);
        gtd.drawString("Moves left: " + moves, 510, 40);
    }

    public void addButtons(int x, int y, Graphics2D gtd){
        JButton roll = new JButton("ROLL");
		roll.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (moves == 0) {
					game.roll();
	                moves = game.getMoves();
	                JOptionPane.showMessageDialog(null, "You have " + moves + " moves!" );
	                repaint();
				} else {
					JOptionPane.showMessageDialog(null, "You have just rolled! Use up your moves." );
				}
			} 
        });

        roll.setBounds(x-200, y-100-210, 100, 100);
		roll.setVisible(true);
		this.add(roll);
        roll.grabFocus();

        JButton guess = new JButton("GUESS");
		guess.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.currentPlayer.location instanceof Estate && !game.currentPlayer.hasGuessed()) {
    				makeGuess(game.currentPlayer.location, gtd);
    				int curIndex = game.getPlayerList().indexOf(game.currentPlayer);
					game.nextPlayer(curIndex);
    			} else {
    				JOptionPane.showMessageDialog(null, "You not currently in an estate, or you have guessed already!" );
    			}
			} 
        });

        guess.setBounds(x-200, y-100-100, 100, 100);
		guess.setVisible(true);
		this.add(guess);
        guess.grabFocus();
    }
    
    public void makeGuess(Location loc, Graphics2D gtd) {
    	List<String> cOptions = game.getCardOptions();
    	
    	addOptionButtons(cOptions);
    }

    private void addOptionButtons(List<String> cOptions) {
    	
    	ButtonGroup bg = new ButtonGroup();
        int top = this.getSize().width/2+this.getSize().width/4;
        
            JRadioButton wop1 = new JRadioButton(cOptions.get(0));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4,this.getSize().height/3, 100, 20);
            JRadioButton wop2 = new JRadioButton(cOptions.get(1));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 22,this.getSize().height/3, 100, 20);
            JRadioButton wop3 = new JRadioButton(cOptions.get(2));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 44, this.getSize().height/3, 100, 20);
            JRadioButton wop4 = new JRadioButton(cOptions.get(3));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 66, this.getSize().height/3, 100, 20);
            JRadioButton wop5 = new JRadioButton(cOptions.get(4));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 88, this.getSize().height/3, 100, 20);
            JRadioButton wop6 = new JRadioButton(cOptions.get(5));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 110, this.getSize().height/3, 100, 20);
            JRadioButton wop7 = new JRadioButton(cOptions.get(6));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 132, this.getSize().height/3, 100, 20);
            JRadioButton wop8 = new JRadioButton(cOptions.get(7));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 154, this.getSize().height/3, 100, 20);
            JRadioButton wop9 = new JRadioButton(cOptions.get(8));
            wop1.setBounds(this.getSize().width/2+this.getSize().width/4 + 176, this.getSize().height/3, 100, 20);
            wop1.setVisible(true);
            bg.add(wop1);
            this.add(wop1);
            wop1.grabFocus();
            wop2.setVisible(true);
            bg.add(wop2);
            this.add(wop2);
            wop2.grabFocus();
            wop3.setVisible(true);
            bg.add(wop3);
            this.add(wop3);
            wop3.grabFocus();
            wop4.setVisible(true);
            bg.add(wop4);
            this.add(wop4);
            wop4.grabFocus();
            wop5.setVisible(true);
            bg.add(wop5);
            this.add(wop5);
            wop5.grabFocus();
            wop6.setVisible(true);
            bg.add(wop6);
            this.add(wop6);
            wop6.grabFocus();
            wop7.setVisible(true);
            bg.add(wop7);
            this.add(wop7);
            wop7.grabFocus();
            wop8.setVisible(true);
            bg.add(wop8);
            this.add(wop8);
            wop8.grabFocus();
            wop9.setVisible(true);
            bg.add(wop9);
            this.add(wop9);
            wop9.grabFocus();
	}

	public void addMoveButtons(int x, int y) {
		JButton north = new JButton("N");
		north.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (moves != 0) {
					moves = moveNorth();
	                repaint();
	                if (inEstate) {
	                	if (game.currentPlayer.location.getWeaponName() != null) {
		                	JOptionPane.showMessageDialog(null, "You have entered " + game.currentPlayer.location.name + "!" + "\n" + "You see " 
								+ game.currentPlayer.location.getWeaponName() + ", mysterious..." + "\n"
			                	+ "press the 'Guess' button to start a guessing cycle." );
							inEstate = false;
	                	}
					}
				} else if (!inEstate) {
					JOptionPane.showMessageDialog(null, "Sorry, " + game.getCurrentPlayerName() + " you have no moves! Please roll." );
				}
						
			} 
        });

        JButton west = new JButton("W");
		west.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (moves != 0) {
					moves = moveWest();
	                repaint();
	                if (inEstate) {
	                	if (game.currentPlayer.location.getWeaponName() != null) {
		                	JOptionPane.showMessageDialog(null, "You have entered " + game.currentPlayer.location.name + "!" + "\n" + "You see " 
								+ game.currentPlayer.location.getWeaponName() + ", mysterious..." + "\n"
			                	+ "press the 'Guess' button to start a guessing cycle." );
							inEstate = false;
	                	}
					}
				} else if (!inEstate) {
					JOptionPane.showMessageDialog(null, "Sorry, " + game.getCurrentPlayerName() + " you have no moves! Please roll." );
				}
			} 
        });

        JButton east = new JButton("E");
		east.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (moves != 0) {
					moves = moveEast();
	                repaint();
	                if (inEstate) {
	                	if (game.currentPlayer.location.getWeaponName() != null) {
		                	JOptionPane.showMessageDialog(null, "You have entered " + game.currentPlayer.location.name + "!" + "\n" + "You see " 
								+ game.currentPlayer.location.getWeaponName() + ", mysterious..." + "\n"
			                	+ "press the 'Guess' button to start a guessing cycle." );
							inEstate = false;
	                	}
					}
				} else if (!inEstate) {
					JOptionPane.showMessageDialog(null, "Sorry, " + game.getCurrentPlayerName() + " you have no moves! Please roll." );
				}
			} 
        });

        JButton south = new JButton("S");
		south.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (moves != 0) {
					moves = moveSouth();
	                repaint();
	                if (inEstate) {
	                	if (game.currentPlayer.location.getWeaponName() != null) {
		                	JOptionPane.showMessageDialog(null, "You have entered " + game.currentPlayer.location.name + "!" + "\n" + "You see " 
								+ game.currentPlayer.location.getWeaponName() + ", mysterious..." + "\n"
			                	+ "press the 'Guess' button to start a guessing cycle." );
							inEstate = false;
	                	}
					}
				} else if (!inEstate) {
					JOptionPane.showMessageDialog(null, "Sorry, " + game.getCurrentPlayerName() + " you have no moves! Please roll." );
				}
			} 
        });

        north.setBounds(x/2-100, y-100-100, 50, 50);
		north.setVisible(true);
		this.add(north);
        north.grabFocus();

        west.setBounds(x/2-150, y-100-50, 50, 50);
		west.setVisible(true);
		this.add(west);
        west.grabFocus();

        east.setBounds(x/2-50, y-100-50, 50, 50);
		east.setVisible(true);
		this.add(east);
        east.grabFocus();

        south.setBounds(x/2-100, y-100-50, 50, 50);
		south.setVisible(true);
		this.add(south);
        south.grabFocus();
    
    }
    
    public int moveWest() {
    	return game.moveWest(this);
    }
    
    public int moveEast() {
    	return game.moveEast(this);
    }
    
    public int moveNorth() {
    	return game.moveNorth(this);
    }
    
    public int moveSouth() {
    	return game.moveSouth(this);
    }

    public void drawBoard(Graphics2D gtd, int x, int y){
        Board board = game.getBoard();
        Location[][] locations = board.getLocationSet();

        int left = 10;
        int top = 10;
        for (int i = 0; i < 24; i++){
            for (int j = 0; j < 24; j++){
                if(locations[i][j].getTypeIdentifier().equals("---")){
                    gtd.setColor(Color.white);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.black);
                    gtd.drawRect(left, top, 20, 20);
                    left += 20;
                } else if (locations[i][j].getTypeIdentifier().equals("[ ]")){
                    gtd.setColor(Color.white);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.white);
                    gtd.drawRect(left, top, 20, 20);
                    left += 20;
                }  else if (locations[i][j].getTypeIdentifier().equals("-" + game.getCurrentPlayerIcon() + "-")){
                    gtd.setColor(Color.green);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.green);
                    gtd.drawRect(left, top, 20, 20);
                    gtd.setColor(Color.black);
                    Font font = new Font("Verdana", Font.BOLD, 12);
                    String name = locations[i][j].getPlayerIcon();
                    gtd.setFont(font);
                    gtd.drawString(name, left + 6, top + 12);
                    left += 20;
               } else if (locations[i][j].hasPlayer()){
                    gtd.setColor(Color.yellow);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.yellow);
                    gtd.drawRect(left, top, 20, 20);
                    gtd.setColor(Color.black);
                    Font font = new Font("Verdana", Font.BOLD, 12);
                    String name = locations[i][j].getPlayerIcon();
                    gtd.setFont(font);
                    gtd.drawString(name, left + 6, top + 12);
                    left += 20;
               } else {
                    gtd.setColor(Color.blue);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.black);
                    gtd.drawRect(left, top, 20, 20);
                    left += 20;
                }
                
            }
            top += 20;
            left = 10;
        }
    }

    public void getStatus(){
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}