package apcsproject2;

/**
 * UnitMaps - a class purely for static references to maps of units to use.
 */
public class UnitMaps {

    /* 
     * This class is purely for static references of what map you would like to use for your player and the ai.
     * Each map goes from left to right (length ≤25) and back to front
     * 
     * Characters:
     *      'n' = Adds an empty space
     *      ' ' = Does nothing
     *      'w' = Warrior Unit
     *      'm' = Musketeer Unit
     *      'c' = Cavalry Unit
     *      'b' = Brutes Unit
     *      'k' = Kamikaze Unit
     *      'a' = Artillary Unit
     * 
     * Modify these two functions to pass in the AI's map and your map, respectively.
     * You can use any of these predefined templates or even create your own custom map!
     */

    public static char[][] getAIMap() {
        // This determines the AI's unit map
        return genMap(
            defaultArmy // <-- You can replace this variable with any map below
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
        "nmmammnwwwnbbnwwwnmmammn",
        "nwwwwwnwwwnbbnwwwnwwwwwn",
        "ncccccnwwwnbbnwwwncccccn"
    };

    // Basic - Archers behind, Warriors in front
    static String[] basic = {
        "m n m n m n m n m n m n m n m n m n m n m n m",
        "w w w w w w w w w w w w w w w w w w w w w w w"
    };
    
    // Two sets of: Archers in the Back, Warriors in the Middle, Cavalry on the side
    static char[][] dualLegion = {
		{'n','n','m','m','m','n','m','m','m','n','n','a','a','a','n','n','m','m','m','n','m','m','m','n','n'},
		{'n','c','n','n','n','w','n','n','n','c','n','b','b','b','n','c','n','n','n','w','n','n','n','c','n'},
		{'c','c','n','w','w','w','w','w','n','c','c','n','n','n','c','c','n','w','w','w','w','w','n','c','c'}
	};

    // Cavalry in the middle, armies on the sides.
    static String[] midCav = {
        "nmnmnmnmnb ccccc bnmnmnmnmn",
        "wnwnwnwnwb ccccc bwnwnwnwnw",
        "nwnwnwnwnb ccccc bnwnwnwnwn"
    };
    
    // Army of all Cavalry
    static String cavalry = repeat(repeat('c',25)+"\n",2);

    // Army of all Warriors
    static String warriors = repeat(repeat('w',25)+"\n",2);

    // Army of all Musketeers
    static String musketeers = repeat(repeat('m',25)+"\n",2);
    
    // Army of all Brutes
    static String brutes = repeat(repeat('b',25)+"\n",2);
    
    // Army of all Kamikaze
    static String kamikaze = repeat(repeat('k',25)+"\n",2);
    
    // Army of all Artillary
    static String artillary = repeat(repeat('a',25)+"\n",2);
    
    // Brutes in the front, Musketeers in the back
    static String protectedMuskets = lineUp(repeat('m',25),repeat('b',23));

    // The typical arrow formation, four variations
    static String[] arrowFormations = {
        "cmmmccwmwcwbabwccccccwwwc",
        "ncmcnncwcnnwbwnncccnnckcn",
        "nncnnnncnnnnwnnnncnnnncnn"
    };
    
    // Four Squads
    static String[] squads = {
        "wwwnncnnbmmmammmbnncnnwww",
        "wkwncccnbmmmmmmmbncccnwkw",
        "wwwnncnnbbbbbbbbbnncnnwww"
    };
    
    // Flanks of Cavalry, warriors, brutes, and musketeers surrounding kamikaze traps
    static String[] itsatrap = {
    	"cccnmawwwwwwwwwwwwwamnccc",
    	"cccnmbwnnnnnnnnnnnwbmnccc",
    	"cccnmbnnnnnnknnnnnnbmnccc",
    	"nnnnmbnnnnkncnknnnnbmnnnn"
    };
    
    // Create your own map/s!!! Replace the 'n's with characters as defined at the top.
    static char[][] customMap = {
        {'n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n'},
        {'n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n'},
        {'n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n','n'}
    };
    
    /* P.S. You can also use the string utilities below to
     * built up your own simple custom map
     * e.g. lineUp(lineOf('b'), lineOf('w'), lineOf('c'));
     * gives you a 3 line army of brutes in the back,
     * warriors in the middle, and cavalry in the front
     */

    /************************ STRING UTILS ************************/

    public static String lineOf(char c) {return repeat(c,25)+"\n";}
    
    public static String lineUp(String... strings) {
    	String out = "";
    	for (String s : strings) out += s + (s.endsWith("\n")?"":"\n");
    	return out;
    }
    
    public static String repeat(char c, int times) {return repeat(""+c,times);}
    public static String repeat(String s, int times) {
        String out = "";
        for (int i=0;i<times;i++) out += s;
        return out;
    }

    public static char[][] genMap(char[][] map) {return map;}
	public static char[][] genMap(String map) {return genMap(map.split("\n"));}
	public static char[][] genMap(String[] lines) {
		char[][] out = new char[lines.length][]; int r = 0;
		for (String line : lines) out[r++] = line.toLowerCase().toCharArray();
		return out;
	}
}