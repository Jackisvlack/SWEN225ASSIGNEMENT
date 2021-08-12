
public class Location {
	
    private Location west;
    private Location north;
    private Location south;
    private Position pos;
    private Location east;
    public boolean isWall;
    String name;
    Weapon estatesWeapon;
	private boolean hasPlayer = false;
    private Player player;


    /**
     * Method to assist GUI in drawing, location returns string based on type of Location or if Player is present
     * @return String
     */
    public String getTypeIdentifier(){
        if (this.hasPlayer()) return ( "-" + this.getPlayerIcon() + "-");
        else if (this instanceof Estate && this.isWall) return "+++";
        else if (this instanceof Estate) return "[ ]";
        else if (this instanceof Wall) return  "+++";
        else return "---";
    }


    
    /**
     * set the east neighbor of this square
     * */
    public void setEast(Location east){
        this.east = east;
    }
    
    /**
     * set the west neighbor of this square
     * */
    public void setWest(Location west) {
        this.west = west;
    }
    
    /**
     * set the north neighbor of this square
     * */
    public void setNorth(Location north) {
        this.north = north;
    }
    
    /**
     * set the south neighbor of this square
     * */
    public void setSouth(Location south) {
        this.south = south;
    }

    /**
     * return the east neighbor of this square
     * */
    public Location getEast() {
        return east;
    }
    
    /**
     * return the west neighbor of this square
     * */
    public Location getWest() {
        return west;
    }
    
    /**
     * return the north neighbor of this square
     * */
    public Location getNorth() {
        return north;
    }
    
    /**
     * return the south neighbor of this square
     * */
    public Location getSouth() {
        return south;
    }
    
    /**
     * set only the position of this location
     * */
    public Location(Position pos) {
        this.pos = pos;
    }
    
    /**
     * set position and name of this location
     * */
    public Location(Position pos, String name) {
        this.pos = pos;
        this.name = name;
    }
    
    /**
     * set if this location is a wall
     * */
    public void setWall(){
        this.isWall = true;
    }
    
    /**
     * set if this location has a player
     * */
    public void setHasPlayer(boolean b) {
    	this.player = null;
    	this.hasPlayer = b;
    }
    
    public boolean hasPlayer() {
    	return this.hasPlayer;
    }
    
    /**
     * set this locations weapon
     * */
    public void setWeapon(Weapon weapon){
        this.estatesWeapon = weapon;
    }

    /**
     * gets the name of this locations weapon
     * */
    public String getWeaponName(){
        return estatesWeapon.getName();
    }
    
    public Player playerAtLoc(){
        return this.player;
    }

    /**
     * Takes player and sets them to this locaton, sets hasPlayer to true
     * @param player
     */
    public void setPlayerAtLoc(Player player){
        hasPlayer = true;
        this.player = player;
    }

    /**
     * Helper method to assist board in drawing
     * @return
     */
    public String getPlayerIcon(){
        String icon = "";
        if (player.getCharName().equals("lucilla")){
            icon = "L";
        }
        if (player.getCharName().equals("bert")){
            icon = "B";
        }
        if (player.getCharName().equals("percy")){
            icon = "P";
        }
        if (player.getCharName().equals("malina")){
            icon = "M";
        }
        return icon;
    }

    public boolean isOutOfBounds(){
        if (this.getPos().getX() < 0 || this.getPos().getX() > 23){
            return true;
        }
        if (this.getPos().getY() < 0 || this.getPos().getY() > 23){
            return true;
        }
        return false;
    }
    
    /**
     * Identifies whether location has a player on it, is a wall, or is free
     * */
    public String toString() {
    	if (this.hasPlayer) { return "Player"; }
    	else if (isWall) {
    		return "Wall";
    	} else {
    		return "Free";
    	}
    }
    
    /**
     * get this locations position (x and y)
     * */
    public Position getPos() {
    	return this.pos;
    }
}
