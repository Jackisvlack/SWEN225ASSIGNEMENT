
import java.util.ArrayList;
import java.util.HashSet;

public class Player {
	
    String playerName;
    String charName;
    HashSet<Card> cards; 
    Location location;
    boolean controlled = false;
	private boolean hasGuessed;
    private char icon;
	
    public Player(String name, String charName, HashSet<Card> cards, char icon) {
        this.playerName = name;
        this.charName = charName;
        this.cards = cards;
        this.icon = icon;
    }
    
    // returns the actual character name
    public String getCharName() {
    	return this.charName;
    }

    public Character getCharacterIcon(){
        return this.icon;
    }

    /**
     * Sets the name of a player
     * @param name
     */
    public void setPlayerName(String name){
        this.playerName = name;
    }


    /**
     *  
     * @return name of the player
     */
    public String getPlayerName(){
        return this.playerName;
    }
    
    // set location of this player
    public void setLocation(Location location){
        this.location = location;
    }
    
    // set if this character is controlled by a player
    public void setControlled(boolean controlled){
        this.controlled = controlled;
    }
    
    // see if this player is controlled by player or bot
    public boolean getControlled(){
        return this.controlled;
    }
    
    // set if this player has guessed
    public void setGuessed(boolean b) {
    	this.hasGuessed = b;
    }
    
    public boolean hasGuessed() {
    	return this.hasGuessed;
    }
}
