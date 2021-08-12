

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

class GameGUI extends JPanel implements ActionListener {
    private Game game;
    private JFrame frame;
    private int squarelWidth, squareHeight = 25;
    private int moves;
    public boolean inEstate = false;
	private String character = "";
	private String weapon = "";


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
		
		if (this.getComponentCount() > 1) {
			this.removeAll();
		}
		
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
    	System.out.println(game.murderCircumstance);
    	addOptionButtons(cOptions, gtd);
    	JOptionPane.showMessageDialog(null, "Choose one Weapon and one Character, then click submit to submit your guess!" );
    }

    private void addOptionButtons(List<String> cOptions, Graphics2D gtd) {
    	
    	ButtonGroup ch = new ButtonGroup();
    	ButtonGroup wep = new ButtonGroup();
        int top = this.getSize().width/2+this.getSize().width/4;
        List<String> chB = new ArrayList<String>(Arrays.asList("lucilla", "malina", "bert", "percy"));
        List<String> wepB = new ArrayList<String>(Arrays.asList("knife", "broom", "ipad", "scissors", "shovel"));
        List<JRadioButton> radios = new ArrayList<>();
        
        JRadioButton wop1 = new JRadioButton(cOptions.get(0));
        wop1.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7, 100, 20);
        JRadioButton wop2 = new JRadioButton(cOptions.get(1));
        wop2.setBounds(this.getSize().width/2+this.getSize().width/4,this.getSize().height/7 + 20, 100, 20);
        JRadioButton wop3 = new JRadioButton(cOptions.get(2));
        wop3.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 40, 100, 20);
        JRadioButton wop4 = new JRadioButton(cOptions.get(3));
        wop4.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 60, 100, 20);
        JRadioButton wop5 = new JRadioButton(cOptions.get(4));
        wop5.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 100, 100, 20);
        JRadioButton wop6 = new JRadioButton(cOptions.get(5));
        wop6.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 120, 100, 20);
        JRadioButton wop7 = new JRadioButton(cOptions.get(6));
        wop7.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 140, 100, 20);
        JRadioButton wop8 = new JRadioButton(cOptions.get(7));
        wop8.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 160, 100, 20);
        JRadioButton wop9 = new JRadioButton(cOptions.get(8));
        wop9.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 180, 100, 20);
        
        wop1.setVisible(true); ch.add(wop1); this.add(wop1); wop1.grabFocus(); radios.add(wop1);
        wop2.setVisible(true); ch.add(wop2); this.add(wop2); wop2.grabFocus(); radios.add(wop2);
        wop3.setVisible(true); ch.add(wop3); this.add(wop3); wop3.grabFocus(); radios.add(wop3);
        wop4.setVisible(true); ch.add(wop4); this.add(wop4); wop4.grabFocus(); radios.add(wop4);
        wop5.setVisible(true); wep.add(wop5); this.add(wop5); wop5.grabFocus(); radios.add(wop5);
        wop6.setVisible(true); wep.add(wop6); this.add(wop6); wop6.grabFocus(); radios.add(wop6);
        wop7.setVisible(true); wep.add(wop7); this.add(wop7); wop7.grabFocus(); radios.add(wop7);
        wop8.setVisible(true); wep.add(wop8); this.add(wop8); wop8.grabFocus(); radios.add(wop8);
        wop9.setVisible(true); wep.add(wop9); this.add(wop9); wop9.grabFocus(); radios.add(wop9);
        
        wop1.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop1.getText())){character = wop1.getText();} else {weapon = wop1.getText();} } });
        wop2.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop2.getText())){character = wop2.getText();} else {weapon = wop2.getText();} } });
        wop3.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop3.getText())){character = wop3.getText();} else {weapon = wop3.getText();} } });
        wop4.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop4.getText())){character = wop4.getText();} else {weapon = wop4.getText();} } });
                                                                                                  
        wop5.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop5.getText())){character = wop5.getText();} else {weapon = wop5.getText();} } });
        wop6.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop6.getText())){character = wop6.getText();} else {weapon = wop6.getText();} } });
        wop7.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop7.getText())){character = wop7.getText();} else {weapon = wop7.getText();} } });
        wop8.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop8.getText())){character = wop8.getText();} else {weapon = wop8.getText();} } });
        wop9.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { if(chB.contains(wop9.getText())){character = wop9.getText();} else {weapon = wop9.getText();} } });
        
        
        JButton submit = new JButton("SUBMIT GUESS");
		submit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(weapon + " " + character + " " + game.currentPlayer.location.name);
				
				Guess guess = new Guess(game.currentPlayer.location.name, weapon, character);
				
				if (game.murderCircumstance.equals(guess)) {
					JOptionPane.showMessageDialog(null, "Congratulations, " + game.currentPlayer.charName + " - YOU HAVE WON THE GAME!" );
					removeOptions(radios, submit);
				} else {
					startRefutation(radios, submit);
				}
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {} 
        });

		submit.setBounds(this.getSize().width/2+this.getSize().width/4, this.getSize().height/7 + 210, 100, 40);
		submit.setVisible(true);
		this.add(submit);
		submit.grabFocus();
	}
    
    private void startRefutation(List<JRadioButton> radios, JButton submit) {
		removeOptions(radios, submit);
		int curIndex = game.getPlayerList().indexOf(game.currentPlayer);
		JOptionPane.showMessageDialog(null, game.currentPlayer.charName + " guessed " + character + " in " +  game.currentPlayer.location.name + " with the " + weapon + "\n"
				+ "Do you agree?");
		game.nextPlayer(curIndex);
		JOptionPane.showMessageDialog(null, "Please pass the screen to " + game.currentPlayer.charName);
	}
    
    public void removeOptions(List<JRadioButton> buttons, JButton submit) {
		for (int i = 0; i < buttons.size(); i++) { buttons.get(i).setVisible(false); }
		submit.setVisible(false);
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