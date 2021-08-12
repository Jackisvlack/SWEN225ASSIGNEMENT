/** 
 * An instance of location with isWall set to true
 * */
public class Wall extends Location{
    public Wall(Position pos) {
        super(pos);
        super.isWall = true;
    }
}
