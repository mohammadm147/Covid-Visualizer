/**
 * 
 */
package prj5;

/**
 * This class tests the methods and constructor of the state class.
 * 
 * @author Mohammad Mian, Rahul Menon, Jae Trimboli
 * @version Apr 20, 2022
 */
public class StateTest extends student.TestCase {
    
    private State va;
    private Race white;
    private Race black;
    private Race latinX;
    private Race asian;
    private Race other;
    private DLList<Race> races;
    
    /**
     * Initializes test objects.
     */
    public void setUp()
    {
        white = new Race("white", 70678, 1924);
        black = new Race("black", 179563, 13365);
        latinX = new Race("latinX", 97118, 2269);
        asian = new Race("asian", 5408, 254);
        other = new Race("other", 91880, -1);
        
        races = new DLList<Race>();
        races.add(white);
        races.add(black);
        races.add(latinX);
        races.add(asian);
        races.add(other);

        va = new State("va", races);
    }

    /**
     * Tests the class' getters.
     */
    public void testGetters() 
    {
        assertEquals(va.getName(), "va");
        assertEquals(va.getRaces(), races);
    }
    
    /**
     * Tests the sortAlpha method to ensure 
     * races were sorted by alphabetical order.
     */
    public void testSortAlpha()
    {
        DLList<Race> sortedAlpha = va.sortAlpha();
        
        assertEquals(sortedAlpha.get(0), asian);
        assertEquals(sortedAlpha.get(1), black);
        assertEquals(sortedAlpha.get(2), latinX);
        assertEquals(sortedAlpha.get(3), other);
        assertEquals(sortedAlpha.get(4), white);
        
        // Ensures original list was not changed.
        assertEquals(va.getRaces().get(0), white);
    }
    
    /**
     * Tests the sortCFR method to make sure 
     * the resulting list is correctly sorted by CFR.
     */
    public void testSortCFR()
    {
        DLList<Race> sortedCFR = va.sortCFR();
        
        assertEquals(sortedCFR.get(0), black);
        assertEquals(sortedCFR.get(1), asian);
        assertEquals(sortedCFR.get(2), white);
        assertEquals(sortedCFR.get(3), latinX);
        assertEquals(sortedCFR.get(4), other);
        
        // Ensures original list was not changed.
        assertEquals(va.getRaces().get(0), white);
    }
    
    /**
     * Tests the sortCFR method to make sure the resulting list is 
     * correctly being sorted when two races have the same CFR percentage.
     */
    public void testSortCFREqualCFR()
    {
        Race w = new Race("white", 70678, 1924);
        Race b = new Race("black", 70678, 1924);
        Race l = new Race("latinX", 97118, 2269);
        Race a = new Race("asian", 5408, 254);
        Race o = new Race("other", 91880, -1);
        
        DLList<Race> race = new DLList<Race>();
        race.add(w);
        race.add(b);
        race.add(l);
        race.add(a);
        race.add(o);

        State dc = new State("dc", race);
        
        DLList<Race> sortedCFR = dc.sortCFR();
        
        assertEquals(sortedCFR.get(0), a);
        assertEquals(sortedCFR.get(1), b);
        assertEquals(sortedCFR.get(2), w);
        assertEquals(sortedCFR.get(3), l);
        assertEquals(sortedCFR.get(4), o);
        
    }
    
    /**
     * Tests the toString method.
     */
    public void testToString()
    {
        assertEquals(va.toString(), "va" + "\n" + 
            "asian: 5408 cases, 4.7% CFR" + 
            "\n" + "black: 179563 cases, 7.4% CFR"
            + "\n" + "latinX: 97118 cases, 2.3% CFR"
            + "\n" + "other: 91880 cases, -1% CFR"
            + "\n" + "white: 70678 cases, 2.7% CFR"
            + "\n" + "====="
            + "\n" + "black: 179563 cases, 7.4% CFR"
            + "\n" + "asian: 5408 cases, 4.7% CFR"
            + "\n" + "white: 70678 cases, 2.7% CFR"
            + "\n" + "latinX: 97118 cases, 2.3% CFR"
            + "\n" + "other: 91880 cases, -1% CFR"
            + "\n" + "=====" + "\n");
    }
}
