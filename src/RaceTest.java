/**
 * 
 */
package prj5;

/**
 * This class tests the constructor 
 * and various methods of the Race class.
 * 
 * @author Mohammad Mian, Jae Trimboli, Rahul Menon
 * @version Apr 20, 2022
 */
public class RaceTest extends student.TestCase {

    private Race asian;
    
    /**
     * Initializes test objects.
     */
    public void setUp()
    {
        asian = new Race("asian", 5407, 254);
    }
    
    /**
     * Tests getters.
     */
    public void testGetters()
    {
        assertEquals(asian.getName(), "asian");
        assertEquals(asian.getCases(), 5407);
        assertEquals(asian.getDeaths(), 254);
        assertEquals(asian.getCFR(), 4.7, 0.01);
    }
    
    /**
     * Tests the toString method when a race has 
     * data and when the data is unavailable.
     */
    public void testToString()
    {
        assertEquals(asian.toString(), "asian: 5407 cases, 4.7% CFR");
        
        Race noData = new Race("noData", -1, -1);
        assertEquals(noData.toString(), "noData: -1 cases, -1% CFR");
    }
}
