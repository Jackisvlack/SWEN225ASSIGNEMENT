
import java.util.Collections;
import java.util.ArrayList;

public class Board {
    Location[][] squares = new Location[24][24];
    ArrayList<Weapon> weapons = new ArrayList<>();
    public Board() {
        /**
         * Creating the board objects in an array
         */
        weaponDistribution();
        Weapon hhWeapon = weapons.get(0);
        Weapon ccWeapon = weapons.get(0);
        Weapon mmWeapon = weapons.get(0);
        Weapon ppWeapon = weapons.get(0);
        Weapon vcWeapon = weapons.get(0);
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                if ((1 < i && i < 7) && (1 < j && j < 7))
                    squares[i][j] = new Estate(new Position(i, j), "haunted house"); // haunted house
                else if ((16 < i && i < 22) && (1 < j && j < 7))
                    squares[i][j] = new Estate(new Position(i, j), "calamity castle"); // calamity castle
                else if ((1 < i && i < 7) && (16 < j && j < 22))
                    squares[i][j] = new Estate(new Position(i, j), "manic manor"); // manic manor
                else if ((16 < i && i < 22) && (16 < j && j < 22))
                    squares[i][j] = new Estate(new Position(i, j), "peril palace"); // peril palace
                else if ((9 < i && i < 14) && (8 < j && j < 15))
                    squares[i][j] = new Estate(new Position(i, j), "villa ciella"); // villa ciella
                /**
                 * Create Pillars aka. "Walls"
                 */
                else if ((4 < i && i < 7) && (10 < j && j < 13))
                    squares[i][j] = new Wall(new Position(i, j));
                else if ((16 < i && i < 19) && (10 < j && j < 13))
                    squares[i][j] = new Wall(new Position(i, j));
                else if ((10 < i && i < 13) && (16 < j && j < 19))
                    squares[i][j] = new Wall(new Position(i, j));
                else if ((10 < i && i < 13) && (4 < j && j < 7))
                    squares[i][j] = new Wall(new Position(i, j));
                /**
                 * Deaut Square aka "free"
                 */
                else squares[i][j] = new Free(new Position(i, j));
            }
        }
        /**
         * Assigning the board objects they're neighbours
         */
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                if (i - 1 > -1) squares[i][j].setNorth(squares[i - 1][j]);
                if (i + 1 < 24) squares[i][j].setSouth(squares[i + 1][j]);
                if (j - 1 > -1) squares[i][j].setWest(squares[i][j - 1]);
                if (j + 1 < 24) squares[i][j].setEast(squares[i][j + 1]);
            }
        }
        /**
         * Re-Assigning locations in estate's neighbours as squares outside entrances
         * Making all the estate squares have only the squares outside they're entrance as neighbours so that moving while inside an estate pushes you out one of the entrances
         */
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                /**
                 * Haunted house squares
                 * setting north and west neighbours as the north exit
                 *  & setting east and south neighbours as the east exit
                 */
                
                if ((1 < i && i < 7) && (1 < j && j < 7)) {
                    if ((i == 3 && j == 6) ||(i == 6 && j == 5)){
                        squares[i][j].setWeapon(hhWeapon);
                    } else if ((i >= 3 && i <= 5) && (j >= 3 && j <= 5)){
                        continue;
                    }else {
                        squares[i][j].setWall();
                    }
                    squares[i][j].setNorth(null);
                    squares[i][j].setWest(null);
                    squares[i][j].setEast(squares[3][7]);
                    squares[i][j].setSouth(squares[7][5]);
                }
                /**
                 * Calamity Castle Squares
                 * Setting south and west neighbours as the south exit
                 * and north and east as the east exit
                 */
                
                else if ((16 < i && i < 22) && (1 < j && j < 7)){
                    if ((i == 17 && j == 3) ||(i == 18 && j == 6)){
                        squares[i][j].setWeapon(ccWeapon);
                    } else if ((i >= 18 && i <= 20) && (j >= 3 && j <= 5)){
                        continue;
                    } else {
                        squares[i][j].setWall();
                    }
                    squares[i][j].setNorth(squares[16][3]);
                    squares[i][j].setWest(null);
                    squares[i][j].setEast(squares[18][7]);
                    squares[i][j].setSouth(null);
                }
                /**
                 * Manic Manor Squares
                 * setting south and east neighbours as south exit
                 * and west and north as west exit
                 */
                
                else if ((1 < i && i < 7) && (16 < j && j < 22)){
                    if ((i == 5 && j == 17) ||(i == 6 && j == 20)){
                        squares[i][j].setWeapon(mmWeapon);
                    } else if ((i >= 3 && i <= 5) && (j >= 18 && j <= 20)){
                        continue;
                    } else {
                        squares[i][j].setWall();
                    }
                    squares[i][j].setNorth(null);
                    squares[i][j].setWest(squares[5][16]);
                    squares[i][j].setEast(null);
                    squares[i][j].setSouth(squares[7][20]);
                }
                /**
                 * peril Palace
                 * setting south and west neighbours as west exit
                 * and north and east neighbours as north exit
                 */
                
                else if ((16 < i && i < 22) && (16 < j && j < 22)){
                    if ((i == 17 && j == 18) ||(i == 20 && j == 17)){
                        squares[i][j].setWeapon(ppWeapon);
                    } else if ((i >= 18 && i <= 20) && (j >= 18 && j <= 20)){
                        continue;
                    } else {
                        squares[i][j].setWall();
                    }
                    squares[i][j].setNorth(null);
                    squares[i][j].setNorth(squares[16][18]);
                    squares[i][j].setWest(squares[20][16]);
                    squares[i][j].setSouth(null);
                }
                /**
                 * villa Celia
                 * Setting north neighbour as north exit, south as south etc...
                 */
                
                else if ((9 < i && i < 14) && (8 < j && j < 15)){
                    if ((i == 10 && j == 12) || (i == 12 && j == 9) || (i == 11 && j == 14) || (i == 13 && j == 11)){
                        squares[i][j].setWeapon(vcWeapon);
                    } else if ((i >= 11 && i <= 12) && (j >= 10 && j <= 13)){
                        continue;
                    } else {
                        squares[i][j].setWall();
                    }
                    squares[i][j].setNorth(squares[9][12]);
                    squares[i][j].setWest(squares[12][8]);
                    squares[i][j].setEast(squares[11][15]);
                    squares[i][j].setSouth(squares[14][11]);
                }



            }
        }
    }

    /**
     * Create all the weapons and randomises them.
     *  Broom • Scissors • Knife • Shovel • iPad
     */
    public void weaponDistribution(){
        weapons.add(new Weapon("knife"));
        weapons.add(new Weapon("broom"));
        weapons.add(new Weapon("scissors"));
        weapons.add(new Weapon("ipad"));
        weapons.add(new Weapon("shovel"));
        Collections.shuffle(weapons);
    }

    /**
     * Takes list of players from game and then places each according to character starting position
     * @param players
     */
    public void placeCharactersStart(ArrayList<Player> players){
      for (Player player : players){
            if (player.getCharName().equals("lucilla")){
                squares[1][11].setPlayerAtLoc(player);
                player.setLocation(squares[1][11]);
            }
            else if (player.getCharName().equals("bert")){
                squares[9][1].setPlayerAtLoc(player);
                player.setLocation(squares[9][1]);
            }
            else if (player.getCharName().equals("malina")){
                squares[22][9].setPlayerAtLoc(player);
                player.setLocation(squares[22][9]);
            }
            else if (player.getCharName().equals("percy")){
                squares[14][22].setPlayerAtLoc(player);
                player.setLocation(squares[14][22]);
            }
        }
    }


    public Location[][] getLocationSet(){
        return this.squares;
    }
    
}

