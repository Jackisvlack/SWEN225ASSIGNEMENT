
/**
 * Estate: instance of location, stores isWall, name and position
 * */
public class Estate extends Location{

    public Estate(Position pos, String name) {
        super(pos, name);
        super.isWall = false;
    }

    public void setWall(){
        super.isWall = true;
    }

    public String estateWelcome(){
        String welcome = "You have entered " + super.name + " you see a " + super.getWeaponName();
        return welcome;
    }
   
}
