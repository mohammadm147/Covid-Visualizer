/**
 * 
 */
package prj5;

/**
 * This class represents a state.
 * 
 * @author Mohammad Mian, Jae Trimboli, Rahul Menon
 * @version Apr 20, 2022
 */
public class State {
    
    private String name;
    private DLList<Race> races;
    
    /**
     * Initializes a new state.
     * 
     * @param n Name of state.
     * @param r List of state's race data.
     */
    public State(String n, DLList<Race> r)
    {
        name = n;
        races = r;
    }
    
    /**
     * Gets the name of the state.
     * 
     * @return Name of state.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the races of the current state.
     * 
     * @return A doubly linked list containing all the races.
     */
    public DLList<Race> getRaces()
    {
        return races;
    }
    
    /**
     * Helper method that creates a clone of the 
     * race list that can be used during sorting.
     * 
     * @return A list containing the same elements as the race list.
     */
    private DLList<Race> cloneRace()
    {
        DLList<Race> clone = new DLList<Race>();
        
        for (int i = 0; i < races.size(); i++)
        {
            clone.add(i, races.get(i));
        }
        
        return clone;
    }
    /**
     * Sorts the races into alphabetical order 
     * and returns a list containing the new order.
     * 
     * @return List containing sorted races
     */
    public DLList<Race> sortAlpha()
    {
        DLList<Race> clone = this.cloneRace();
        
        for (int i = 0; i < clone.size(); i++)
        {
            int index = i;
            
            // Loop finds smallest value in list.
            for (int j = i + 1; j < clone.size(); j++)
            {
                if (clone.get(index).getName().compareTo(clone.get(j).getName()) > 0)
                {
                    index = j;
                }
            }
            
            // Moves the smallest value in list
            clone.add(i, clone.get(index));
            clone.add(index, clone.get(i + 1));
            
            clone.remove(i + 1);
            clone.remove(index + 1);
        }
        
        return clone;
    }

    /**
     * Sorts the races by highest CFR 
     * and returns a list containing the new order.
     * 
     * @return List containing sorted races
     */
    public DLList<Race> sortCFR()
    {
        DLList<Race> clone = this.cloneRace();
        
        for (int i = 0; i < races.size() - 1; i++)
        {
            for (int j = 0; j < races.size() - 1; j++)
            {
                // Moves race if race's CFR is smaller then next race
                if (clone.get(j).getCFR() < clone.get(j + 1).getCFR())
                {
                    Race first = clone.get(j);
                    clone.remove(j);
                    Race second = clone.get(j);
                    clone.remove(j);
                    
                    clone.add(j, second);
                    clone.add(j + 1, first);
                }
                // If races have same CFR then they are sorted alphabetically
                else if ((clone.get(j).getCFR() == clone.get(j + 1).getCFR()) 
                    && (clone.get(j).getName().compareTo
                    (clone.get(j + 1).getName()) > 0))
                {
                    Race first = clone.get(j);
                    clone.remove(j);
                    Race second = clone.get(j);
                    clone.remove(j);
                    
                    clone.add(j, second);
                    clone.add(j + 1, first);
                }
            }
        }
        return clone;
    }
    
    /**
     * Converts the states information into a string format.
     * 
     * @return A string containing the states information.
     */
    public String toString()
    {
        StringBuilder data = new StringBuilder(name + "\n");
        
        DLList<Race> sortedAlpha = sortAlpha();
        
        // Adds data in Alphabetical order
        for (int i = 0; i < sortedAlpha.size(); i++)
        {
            data.append(sortedAlpha.get(i).toString() + "\n");
        }
        
        data.append("=====" + "\n");
        
        DLList<Race> sortedCFR = sortCFR();
        
        // Adds data sorted by largest CFR
        for (int j = 0; j < sortedCFR.size(); j++)
        {
            data.append(sortedCFR.get(j).toString() + "\n");
        }
        
        data.append("=====" + "\n");

        return data.toString();
    }
}
