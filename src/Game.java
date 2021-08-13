
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Game {
    private static Board board;
    ArrayList<Player> players = new ArrayList<>();
	ArrayList<Player> controlledPlayers = new ArrayList<>();
    Guess murderCircumstance;
    ArrayList<Card> weaponCards = new ArrayList<>();
    ArrayList<Card> characterCards = new ArrayList<>();
    ArrayList<Card> estateCards = new ArrayList<>(); 
    Player currentPlayer;
    boolean solved = false;
	private String weapon;
	private String player;
	private int numberOfPlayers;
	private List<Card> cards;
	private List<Card> guessCards;
	private int moves;
	
	public enum Characters {
		lucilla,
		bert,
		malina,
		percy
	}

	
    public Game(int numPlayers, List<String> pNames) {
		board = new Board();
        this.numberOfPlayers = numPlayers;
		playerCreation(pNames);
		createCards();
		chooseMurderCircumstance();
		dealCards();
		selectStartPlayer();
		board.placeCharactersStart(players);
    }


	public void playerCreation(List<String> playerNames){
		players.add( new Player("NPC", String.valueOf(Characters.lucilla), new HashSet<Card>(), 'L')); 
		players.add( new Player("NPC", String.valueOf(Characters.bert), new HashSet<Card>(), 'B'));
		players.add( new Player("NPC", String.valueOf(Characters.malina), new HashSet<Card>(), 'M'));
		players.add( new Player("NPC", String.valueOf(Characters.percy), new HashSet<Card>(), 'P'));

		Collections.shuffle(playerNames);

		for (int i = 0 ; i < numberOfPlayers; i++) {
			players.get(i).setControlled(true);
			players.get(i).setPlayerName(playerNames.remove(0));
			controlledPlayers.add(players.get(i));
		}
	}

	public void createCards(){
		characterCards.add(new Card("lucilla"));
		characterCards.add(new Card("bert"));
		characterCards.add(new Card("malina"));
		characterCards.add(new Card("percy"));
		weaponCards.add(new Card("broom"));
		weaponCards.add(new Card("scissors"));
		weaponCards.add(new Card("knife"));
		weaponCards.add(new Card("shovel"));
		weaponCards.add(new Card("ipad"));
		estateCards.add(new Card("manic manor"));
		estateCards.add(new Card("villa celia"));
		estateCards.add(new Card("haunted house"));
		estateCards.add(new Card("calamity castle"));
		estateCards.add(new Card("calamity castle"));
		guessCards = new ArrayList<>();
		guessCards.addAll(characterCards);
		guessCards.addAll(weaponCards);
	}

	/**
		 * Randomizes each set of card and takes one of each for the murder set
		 */
	public void chooseMurderCircumstance(){
		Collections.shuffle(weaponCards);
		Collections.shuffle(estateCards);
		Collections.shuffle(characterCards);
		Card murderWeapon = (weaponCards.get(0));
		Card murderCharacter = (characterCards.get(0));
		Card murderEstate = (estateCards.get(0));
		murderCircumstance = new Guess(murderEstate.getName(), murderWeapon.getName(), murderCharacter.getName());
		weaponCards.remove(weaponCards.indexOf(murderWeapon));
		characterCards.remove(characterCards.indexOf(murderCharacter));
		estateCards.remove(estateCards.indexOf(murderEstate));

		cards = new ArrayList<Card>();
		cards.addAll(weaponCards);
		cards.addAll(estateCards);
		cards.addAll(characterCards);
		Collections.shuffle(cards);
	}
		 
	/**
	 * Deals the remaining cards to Players
	 */
	public void dealCards(){
		int c = 0;
		while (!cards.isEmpty()) {
			if (c == controlledPlayers.size()) { c = 0; }
			controlledPlayers.get(c).cards.add(cards.remove(0));
			c++;
		}
	}

	/**
	 * Randomly selects the player to start
	 */
	public void selectStartPlayer(){
		int random = (int) (Math.random() * numberOfPlayers);
        currentPlayer = controlledPlayers.get(random);
	}


	public Board getBoard(){
		return board;
	}
    
    public void wait(int sec) {
    	try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * A method that starts the currentPlayers turn
     * Rolls dice
     * gives instructions
     * gives move order to checkLine, if valid passes on to move method
     * */
    public void nextPlayer(int curIndex){
		if (curIndex == numberOfPlayers - 1){
			curIndex = 0;
			currentPlayer = controlledPlayers.get(curIndex);
		} else {
			curIndex++;
			currentPlayer = controlledPlayers.get(curIndex);
		}
    }
    
    
    /**
     * Helper method to help players know which way they can go once in an estate
     * */
	public void checkEstateExits(Location location){
		System.out.println("You can leave the estate via the following exits: \n");
		if (location.getEast() != null){
			System.out.println("East\n");
		}
		if (location.getWest() != null){
			System.out.println("West\n");
		}
		if (location.getNorth() != null){
			System.out.println("North\n");
		}
	    if (location.getSouth() != null){
			System.out.println("South\n");
		}
	}


    /**
     * Moves the player north, if on an estate location starts the makeGuess cycle
     * */
    public int moveNorth(GameGUI gg) {
    	
    	if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
    		Player p = currentPlayer;
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
		}
    	Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getX()-1 != -1){
			if (currentPlayer.location.getNorth() != null) {
	    		if (!currentPlayer.location.getNorth().isWall && !currentPlayer.location.getNorth().hasPlayer()) {
	    			playerLoc.getNorth().setPlayerAtLoc(currentPlayer);
	    			currentPlayer.setLocation(playerLoc.getNorth());
	    			playerLoc.setHasPlayer(false);
	    			if (!(currentPlayer.location instanceof Estate)) {
	    				moves--;
	    			}

	    			if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
	    				Player p = currentPlayer;
	    				int curIndex = players.indexOf(currentPlayer);
	    				nextPlayer(curIndex);
	    				JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
	    			}
	    			
	    			if ((currentPlayer.location instanceof Estate)) {
	    				gg.inEstate = true;
	    				return 0;
	    			}
	    		} 
			}
		}
		return moves;
    }
    
    /**
     * Moves the player south, if on an estate location starts the makeGuess cycle
     * */
    public int moveSouth(GameGUI gg) {
    	if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
    		Player p = currentPlayer;
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
		}
    	Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getX()+1 != 24){
			if (currentPlayer.location.getSouth() != null) {
	    		if (!currentPlayer.location.getSouth().isWall && !currentPlayer.location.getSouth().hasPlayer()) {
	    			playerLoc.getSouth().setPlayerAtLoc(currentPlayer);
	    			currentPlayer.setLocation(playerLoc.getSouth());
	    			playerLoc.setHasPlayer(false);
	    			if (!(currentPlayer.location instanceof Estate)) {
	    				moves--;
	    			}
	    			
	    			if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
	    				Player p = currentPlayer;
	    				int curIndex = players.indexOf(currentPlayer);
	    				nextPlayer(curIndex);
	    				JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
	    			}
	    			
	    			if ((currentPlayer.location instanceof Estate)) {
	    				gg.inEstate = true;
	    				return 0;
	    			}
	    		} 
			}
		}
		return moves;
    }
    
    /**
     * Moves the player east, if on an estate location starts the makeGuess cycle
     * */
    public int moveEast(GameGUI gg) {

    	if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
    		Player p = currentPlayer;
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
		}
    	Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getY()+1 != 24){
			if (currentPlayer.location.getEast() != null) {
	    		if (!currentPlayer.location.getEast().isWall && !currentPlayer.location.getEast().hasPlayer()) {
	    			playerLoc.getEast().setPlayerAtLoc(currentPlayer);
	    			currentPlayer.setLocation(playerLoc.getEast());
	    			playerLoc.setHasPlayer(false);
	    			if (!(currentPlayer.location instanceof Estate)) {
	    				moves--;
	    			}
	//				moveEast();
	    			if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
	    				Player p = currentPlayer;
	    				int curIndex = players.indexOf(currentPlayer);
	    				nextPlayer(curIndex);
	    				JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
	    			}
	    			if ((currentPlayer.location instanceof Estate)) {
	    				gg.inEstate = true;
	    				return 0;
	    			}
	    		} 
			}
		}
		return moves;
    }
    
    /**
     * Moves the player west, if on an estate location starts the makeGuess cycle
     * */
    public int moveWest(GameGUI gg) {

    	if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
    		Player p = currentPlayer;
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
			return 0;
		}
    	Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getY()-1 != -1){
			if (currentPlayer.location.getWest() != null) {
	    		if (!currentPlayer.location.getWest().isWall && !currentPlayer.location.getWest().hasPlayer()) {
	    			playerLoc.getWest().setPlayerAtLoc(currentPlayer);
	    			currentPlayer.setLocation(playerLoc.getWest());
	    			playerLoc.setHasPlayer(false);
	    			if (!(currentPlayer.location instanceof Estate)) {
	    				moves--;
	    			}
	//				moveWest();
	    			if ((moves == 0 && !(currentPlayer.location instanceof Estate))) {
	    				Player p = currentPlayer;
	    				int curIndex = players.indexOf(currentPlayer);
	    				nextPlayer(curIndex);
	    				JOptionPane.showMessageDialog(null, "Your turn is now over, " + p.playerName + " please pass the screen to " + currentPlayer.playerName);
	    			}
	    			if ((currentPlayer.location instanceof Estate)) {
	    				gg.inEstate = true;
	    				return 0;
	    			}
	    		} 
			}
		}
		return moves;
    }
    
    public ArrayList<Player> getPlayerList() {
    	return players;
    }
    
    public ArrayList<String> refutation() {
    	ArrayList<String> playerCards = new ArrayList<>();
    	for (Card card : currentPlayer.getPlayerCards()) {
    		playerCards.add(card.getName());
    	}
    	return playerCards;
    }
    
    /**
     * Get the players guesses and check them
     * */
    public List<String> getCardOptions() {
    	ArrayList<String> cardOptions = new ArrayList<>();
		for (int i = 0; i < guessCards.size(); i++){
			cardOptions.add(guessCards.get(i).getName());
		}
		return cardOptions;
    }

    /**
     * Method to simulate two 6 sided dice
     * @return int: the sum of two dice
     */
    public void roll(){
        int diceOne = (int) (Math.random()*6 + 1);
        int diceTwo = (int) (Math.random()*6 + 1);
        this.moves = diceOne + diceTwo;
    }

	public int getMoves(){
		return this.moves;
	}

	public String getCurrentPlayerName(){
		return currentPlayer.getPlayerName();
	}

	public Character getCurrentPlayerIcon(){
		return currentPlayer.getCharacterIcon();
	}
	
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}
}
