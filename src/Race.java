/**
 * 
 */
package prj5;

import java.text.DecimalFormat;

/**
 * This class represents the data for a single race
 * 
 * @author Mohammad Mian, Rahul Menon, Jae Trimboli
 * @version Apr 20, 2022
 */
public class Race {

    private String name;
    private int cases;
    private int deaths;
    private double cfr;
    
    /**
     * Initializes a new Race object.
     * 
     * @param n Race's name.
     * @param c Number of Cases.
     * @param d Number of Deaths.
     */
    public Race(String n, int c, int d)
    {
        name = n;
        cases = c;
        deaths = d;
        
        // Sets cfr to -1 if race has no case or death data
        if (cases == -1 || deaths == -1)
        {
            cfr = -1;
            return;
        }
        
        // Formats cfr percentage to one decimal place
        DecimalFormat df = new DecimalFormat("##.#");
        String cfrValue = df.format(((double)deaths / cases) * 100);
        cfr = Double.parseDouble(cfrValue);
    }
    
    /**
     * Returns the race's name.
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the number of cases.
     * 
     * @return Number of cases.
     */
    public int getCases()
    {
        return cases;
    }
    
    /**
     * Returns the number of deaths.
     * 
     * @return Number of deaths.
     */
    public int getDeaths()
    {
        return deaths;
    }
    
    /**
     * Gets the cfr value of the race.
     * 
     * @return Returns a double representing the race's cfr.
     */
    public double getCFR()
    {
        return cfr;
    }
    
    /**
     * Converts the race to a string.
     * 
     * @return A string representing the race.
     */
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        
        data.append(name + ": ");
        
        if (cases == -1)
        {
            data.append("-1 cases, ");
        }
        else
        {
            data.append(cases + " cases, ");
        }
        
        if (cfr == -1)
        {
            data.append("-1% CFR");
        }
        else
        {
            data.append(cfr + "% CFR");
        }
        
        return data.toString();
    }
}
