
/**
 * Free: instance of location, holds isWall and pos. Is the default triple line (---) that players can move around on
 * */
public class Free extends Location{
    public Free(Position pos) {
        super(pos);
        super.isWall = false;
    }
}
