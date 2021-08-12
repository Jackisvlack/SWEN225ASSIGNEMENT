

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class PlayerSelect extends JPanel implements MouseListener {
	
	private JFrame frame;
	private int np;
	private String characters[] = { "Percy", "Malina", "Lucilla", "Bert" };
	private List<String> pNames;
	JTextField p1;
	JTextField p2;
	JTextField p3;
	JTextField p4;
	
	public PlayerSelect(JFrame frame, int np) {
		this.setLayout(null);
		this.frame = frame;
		this.np = np;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = this.getSize().width;
		int y = this.getSize().height;
		Graphics2D gtd = (Graphics2D) g;
		
		this.removeAll();
		
		paintBackgroundAndTitle(gtd, screenSize);

        drawOptions(x, y, gtd);
		
        addButtons(x, y);
        
	}
	
	public void addButtons(int x, int y) {
		JButton back = new JButton("<<");
		back.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				getInstructions();
			} 
			
        });
		back.setBounds(this.getSize().width/50, this.getSize().height/50, 50, 50);
		back.setVisible(true);
		this.add(back);
		back.grabFocus();
		
		JButton play = new JButton("PLAY");
		play.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				pNames = new ArrayList<>();
				 if (np == 3) {
					pNames.clear();
					if (checkP1()) {
						pNames.add(p1.getText());
					}
					if (checkP2()) {
						pNames.add(p2.getText());
					}
					if (checkP3()) {
						pNames.add(p3.getText());
					}
					if (pNames.size() == 3) {
						startGame();
					}
				} else {
					pNames.clear();
					if (checkP1()) {
						pNames.add(p1.getText());
					}
					if (checkP2()) {
						pNames.add(p2.getText());
					}
					if (checkP3()) {
						pNames.add(p3.getText());
					}
					if (checkP4()) {
						pNames.add(p4.getText());
					}
					if (pNames.size() == 4) {
						startGame();
					}
				}
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		play.setBounds(x/2-100, y-100-76, 200, 76);
		play.setVisible(true);
		this.add(play);
		play.grabFocus();
	}
	
	public boolean checkP1() {
		if (p1.getText().contains("player") || p1.getText().contains("name") || pNames.contains(p1.getText())) {
			JOptionPane.showMessageDialog(frame,
				    "Enter something unique player 1. >:(");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkP2() {
		if (p2.getText().contains("player") || p2.getText().contains("name") || pNames.contains(p2.getText())) {
			JOptionPane.showMessageDialog(frame,
				    "Enter something unique player 2. >:(");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkP3() {
		if (p3.getText().contains("player") || p3.getText().contains("name") || pNames.contains(p3.getText())) {
			JOptionPane.showMessageDialog(frame,
				    "Enter something unique player 3. >:(");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkP4() {
		if (p4.getText().contains("player") || p4.getText().contains("name") || pNames.contains(p4.getText())) {
			JOptionPane.showMessageDialog(frame,
				    "Enter something unique player 4. >:(");
			return false;
		} else {
			return true;
		}
	}
	
	public void drawOptions(int x, int y, Graphics2D gtd) {
		Font font = new Font("Verdana", Font.BOLD, 12);
        gtd.setFont(font);
		
        if (np == 3) {
        	playerOneOpt(x/2-x/4-50, y/3, 5, gtd);
        	playerTwoOpt(x/2-50, y/3, 5, gtd);
        	playerThreeOpt(x/2+x/4-50, y/3, 5, gtd);
        } else {
        	playerOneOpt(x/2-x/3-50, y/3, 5, gtd);
        	playerTwoOpt(x/2-x/7-50, y/3, 5, gtd);
        	playerThreeOpt(x/2+x/7-50, y/3, 5, gtd);
        	playerFourOpt(x/2+x/3-50, y/3, 5, gtd);
        }
	}
	
	public void playerOneOpt(int x, int y, int padding, Graphics2D gtd) {
		gtd.drawString("Player One: ", x, y-padding);
        
		p1 = new JTextField("james");
		p1.setEditable(true);
		p1.setVisible(true);
		p1.setBounds(x, y, 100, 20);
		this.add(p1);
	}
	
	public void playerTwoOpt(int x, int y, int padding, Graphics2D gtd) {
		gtd.drawString("Player Two: ", x, y-padding);
        
		p2 = new JTextField("john");
		p2.setEditable(true);
		p2.setVisible(true);
		p2.setBounds(x, y, 100, 20);
		this.add(p2);
	}
	
	public void playerThreeOpt(int x, int y, int padding, Graphics2D gtd) {
		gtd.drawString("Player Three: ", x, y-padding);
        
		p3 = new JTextField("bonnie");
		p3.setEditable(true);
		p3.setVisible(true);
		p3.setBounds(x, y, 100, 20);
		this.add(p3);
	}

	public void playerFourOpt(int x, int y, int padding, Graphics2D gtd) {
		gtd.drawString("Player Four: ", x, y-padding);
        
		p4 = new JTextField("honn");
		p4.setEditable(true);
		p4.setVisible(true);
		p4.setBounds(x, y, 100, 20);
		this.add(p4);
	}
	
	private void paintBackgroundAndTitle(Graphics2D gtd, Dimension screenSize) {
		GradientPaint col = new GradientPaint(0, 0, new Color(201, 13, 0), 0, screenSize.height,  new Color(0, 0, 0));
		gtd.setPaint(col);
        gtd.fillRect(0, 0, screenSize.width, screenSize.height);
        
        Font font = new Font("Verdana", Font.BOLD, 40);
        gtd.setFont(font);
        gtd.setColor(Color.WHITE);
        gtd.drawString("MURDER - MADNESS", this.getSize().width/2-220, this.getSize().height/7);
        font = new Font("Verdana", Font.BOLD, 20);
        gtd.setFont(font);
        gtd.drawString("PLAYER SELECTION", this.getSize().width/2-120, this.getSize().height/5);
	}
	
	public void getInstructions() {
		Instructions ins = new Instructions(this.frame, new Menu(this.frame));
		ins.setLocation(0,0);
		ins.setSize(this.frame.getSize());
		ins.setBackground(new Color(102, 204, 255));
		ins.setVisible(true);
		this.frame.remove(this);
		this.frame.add(ins);
		ins.repaint();
	}
	
//	public void drawCharacters(Graphics2D gtd) {
//		try {
//			percy = ImageIO.read(new File("C:\\Users\\New User\\OneDrive\\Desktop\\Trim2\\Swen225\\Assignments\\as1\\MurderMadness\\Assignment 1\\src\\GUI\\assets\\percy.png"));
//		} catch (IOException e) {e.printStackTrace();}
//		
//		gtd.drawImage(percy, this.getSize().width/22+10, this.getSize().height/5-20, null);
//		
//		try {
//			lucilla = ImageIO.read(new File("C:\\Users\\New User\\OneDrive\\Desktop\\Trim2\\Swen225\\Assignments\\as1\\MurderMadness\\Assignment 1\\src\\GUI\\assets\\lucilla.png"));
//		} catch (IOException e) {e.printStackTrace();}
//		
//		gtd.drawImage(lucilla, this.getSize().width/3+10, this.getSize().height/5-20, null);
//		
//		try {
//			malina = ImageIO.read(new File("C:\\Users\\New User\\OneDrive\\Desktop\\Trim2\\Swen225\\Assignments\\as1\\MurderMadness\\Assignment 1\\src\\GUI\\assets\\malina.png"));
//		} catch (IOException e) {e.printStackTrace();}
//		
//		gtd.drawImage(malina, this.getSize().width/2+this.getSize().width/22+10, this.getSize().height/5-20, null);
//		
//		try {
//			bert = ImageIO.read(new File("C:\\Users\\New User\\OneDrive\\Desktop\\Trim2\\Swen225\\Assignments\\as1\\MurderMadness\\Assignment 1\\src\\GUI\\assets\\bert.png"));
//		} catch (IOException e) {e.printStackTrace();}
//		
//		gtd.drawImage(bert, this.getSize().width/2+this.getSize().width/3+10, this.getSize().height/5-20, null);
//	}

	

	public void startGame(){
		Game game = new Game(np, pNames);
		GameGUI playScreen = new GameGUI(game, frame);
		playScreen.setLocation(0,0);
		playScreen.setSize(this.frame.getSize());
		playScreen.setBackground(new Color(102, 204, 255));
		playScreen.setVisible(true);
		this.frame.remove(this);
		this.frame.add(playScreen);
		playScreen.repaint();
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
}
