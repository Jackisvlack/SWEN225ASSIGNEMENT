

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

@SuppressWarnings("serial")
public class Instructions extends JPanel implements ActionListener {
	private int numberOfPlayers = 0;
	private JFrame frame;
	
	public Instructions(JFrame frame, Menu menu) {
		this.frame = frame;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Graphics2D gtd = (Graphics2D) g;
		
		this.removeAll();	
		
        paintBackgroundAndTitle(gtd, screenSize);
		
        printRules(gtd);
        
        gtd.drawString("How many people are playing?", this.getSize().width/28, this.getSize().height-120);
        addButtons();
        
	}
	
	private void paintBackgroundAndTitle(Graphics2D gtd, Dimension screenSize) {
		GradientPaint col = new GradientPaint(0, 0, new Color(201, 13, 0), 0, screenSize.height,  new Color(0, 0, 0));
		gtd.setPaint(col);
        gtd.fillRect(0, 0, screenSize.width, screenSize.height);
        
        Font font = new Font("Verdana", Font.BOLD, 40);
        gtd.setFont(font);
        gtd.setColor(Color.WHITE);
        gtd.drawString("MURDER - MADNESS", this.getSize().width/2-220, this.getSize().height/7);
	}
	
	private void printRules(Graphics2D gtd) {
		Font font = new Font("Verdana", Font.BOLD, 20);
        gtd.setFont(font);
        gtd.drawString("INSTRUCTIONS/RULES", this.getSize().width/2-220, this.getSize().height/5);
        
        font = new Font("Verdana", Font.BOLD, 13);
        gtd.setFont(font);
        
        ArrayList<String> rules = new ArrayList<>(Arrays.asList(
        		"Players cannot move through walls, or other players.",
        		"A players goal is to get in to an estate, only in an estate can a guess attempt be made.\n",
        		"One of the 4 characters is a murderer, a player may move around the board trying to get",
        		"in to one of the five estates. Once inside an estate, a player can guess the murderer\n",
        		"and their murder weapon (choosing the respective cards) and whichever estate they\n",
        		"are in will be chosen as the third aspect of that guess. If the accused player and\n",
        		"murder weapon are not already inside that estate, they will be moved to sed estate.\n",
        		"Players then go around refuting the current players accusation by presenting cards which\n",
        		"match the guessed cards. If a refutation can be made, it must. If a player has more\n",
        		"than one elegible card, it is up to the player to choose between them. If none of the\n",
        		"players are able to present a refutation card, the current players accusation is likely ",
        		"close to a winning guess. If a player guesses the exact circumstances of the murder, \n",
        		"they win. however, if a player does not get the guess correct, that player is excluded\n",
        		"for the rest of the game from making accusations and winning. An excluded player can still",
        		"present refuation cards. Should every player fail to guess the murder circumstances, \n",
        		"the murderer has won :O"));
        for (int i = 0; i < rules.size(); i++) {
        	gtd.drawString(rules.get(i), this.getSize().width-680, this.getSize().height/4+(20*i));
        }
	}
	
	private void addButtons() {
		ButtonGroup bg = new ButtonGroup();
        
        
        JRadioButton three = new JRadioButton("3");
        three.setBounds(this.getSize().width/10, this.getSize().height-100, 40, 20);
        three.setVisible(true);
        bg.add(three);
        this.add(three);
        three.grabFocus();
        JRadioButton four = new JRadioButton("4");
        four.setBounds(this.getSize().width/6, this.getSize().height-100, 40, 20);
        four.setVisible(true);
        bg.add(four);
        this.add(four);
        four.grabFocus();
        
        
        three.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				numberOfPlayers = Integer.valueOf(three.getText());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				numberOfPlayers = Integer.valueOf(three.getText());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        four.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				numberOfPlayers = Integer.valueOf(four.getText());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				numberOfPlayers = Integer.valueOf(four.getText());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        JButton start = new JButton("I UNDERSTAND, LET'S GO!");
        start.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numberOfPlayers == 0) {
					JOptionPane.showMessageDialog(frame,
						    "Please select the number of players playing.");
				} else {
					getPlayerSelect();
				}
			} 
			
        });
		start.setBounds(this.getSize().width-300, this.getSize().height-150, 200, 50);
		start.setVisible(true);
		this.add(start);
		start.grabFocus();
		
		JButton back = new JButton("<<");
		back.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				getMenu();
			} 
			
        });
		back.setBounds(this.getSize().width/50, this.getSize().height/50, 50, 50);
		back.setVisible(true);
		this.add(back);
		back.grabFocus();
	}
	
	protected void getPlayerSelect() {
		PlayerSelect ps = new PlayerSelect(frame, numberOfPlayers);
		ps.setLocation(0,0);
		ps.setSize(this.frame.getSize());
		ps.setBackground(new Color(102, 204, 255));
		ps.setVisible(true);
		this.frame.remove(this);
		this.frame.add(ps);
		ps.repaint();
	}

	public void getMenu() {
		Menu menu = new Menu(frame);
		menu.setLocation(0,0);
		menu.setSize(this.frame.getSize());
		menu.setBackground(new Color(102, 204, 255));
		menu.setVisible(true);
		this.frame.remove(this);
		this.frame.add(menu);
		menu.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
