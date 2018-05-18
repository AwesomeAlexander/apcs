package apcsproject2;

/**
 * UnitMaps - a class purely for static references to maps of units to use.
 */
public class UnitMaps {

    /* 
     * This class is purely for static references of what map you would like to use for your player and the ai.
     * Each map goes from left to right and back to front
     * 
     * Characters:
     *      'n' = Adds an empty space
     *      ' ' = Does nothing
     *      'w' = Warrior Unit
     *      'a' = Archer Unit
     *      'c' = Cavalry Unit
     * 
     * Use these two functions to pass in the AI's map and your map, respectively.
     * You can use these predefined templates, or create your own custom map!
     */

    public static char[][] getAIMap() {
        // This determines the AI's unit map
        return genMap(
            midCav // <-- You can replace this variable with any map below
        );
    }

    public static char[][] getPlayerMap() {
        // This determines your unit map
        return genMap(
            defaultArmy // <-- You can replace this variable with any map below
            );
    }
    
    /************************ MAPS ************************/

    // Default army
    static String[] defaultArmy = {
        "nnaaaaannwnccnwnnaaaaann",
        "nwwwwwnnwwnccnwwnnwwwwwn",
        "ccccccnwwwnccnwwwncccccc"
    };

    // Basic - Archers behind, Warriors in front
    static String[] basic = {
        "a n a n a n a n a n a n a n a n a n a n a n a",
        "w w w w w w w w w w w w w w w w w w w w w w w"
    };
    
    // Two sets of: Archers in the Back, Warriors in the Middle, Cavalry on the side
    static char[][] dualLegion = {
		{'n','n','a','a','a','n','a','a','a','n','n','n','n','n','a','a','a','n','a','a','a','n','n'},
		{'n','c','n','n','n','w','n','n','n','c','n','n','n','c','n','n','n','w','n','n','n','c','n'},
		{'c','c','n','w','w','w','w','w','n','c','c','n','c','c','n','w','w','w','w','w','n','c','c'}
	};

    // Cavalry in the middle, armies on the sides.
    static String[] midCav = {
        "nanananana ccccc ananananan",
        "wnwnwnwnwn ccccc nwnwnwnwnw",
        "nwnwnwnwnw ccccc wnwnwnwnwn"
    };
    
    // Army of all Cavalry
    static String cavalry = repeat(repeat("c",23)+"\n",2);

    // Army of all Warriors
    static String warriors = repeat(repeat("w",23)+"\n",2);

    // Army of all Archers
    static String archers = repeat(repeat("a",23)+"\n",2);

    // The typical arrow formation - four sets of it
    static String[] arrowFormation = {
        "cwawccwawccwawccwawccwawc",
        "ncwcnncwcnncwcnncwcnncwcn",
        "nncnnnncnnnncnnnncnnnncnn"
    };
    
    // Create your own map/s!!! Replace the 'n's with 'a's, 'c's, and 'w's. Make sure change it in getPlayerMap()
    static char[][] customMap = {
        {'n','n','n','n','n','n','n','n','n','n','n'},
        {'n','n','n','n','n','n','n','n','n','n','n'},
        {'n','n','n','n','n','n','n','n','n','n','n'}
    };

    /************************ UTILS ************************/

    public static String repeat(String s, int times) {
        String out = "";
        for (int i=0;i<times;i++) out+=s;
        return out;
    }

    public static char[][] genMap(char[][] map) {return map;}
	public static char[][] genMap(String map) {return genMap(map.split("\n"));}
	public static char[][] genMap(String[] lines) {
		char[][] out = new char[lines.length][]; int r=0;
		for (String line : lines) out[r++] = line.toCharArray();
		return out;
	}
}