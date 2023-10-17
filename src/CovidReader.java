package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import bsh.ParseException;

/**
 * This is the Covid Reader Class
 * 
 * @author Rahul Menon
 * @version 2022.04.21
 * 
 */
public class CovidReader {
    private DLList<State> states;
    private DLList<State> random;

    /**
     * Main constructor for covid Reader
     * 
     * @param fileA
     *            by Race
     * @param fileB
     *            random Number
     * @throws FileNotFoundException
     *             Exception thrown if file not found
     * @throws ParseException
     *             Exception thrown if there is a parsing error
     */

    public CovidReader(String fileA, String fileB)
        throws FileNotFoundException {
        states = this.readFile(fileA);
        random = this.readFile(fileB);

    }


    public CovidReader(String fileA) throws FileNotFoundException {
        states = readFile(fileA);
    }


    public DLList<State> getStates() {
        return states;
    }


    /**
     * Reads File from DLList
     * 
     * @param fileName
     *            file that it will read
     * @return file
     * @throws FileNotFoundException
     *             Exception thrown if file not found
     * @throws ParseException
     *             Exception thrown if there is a parsing error
     * 
     * 
     */

    private DLList<State> readFile(String fileName)
        throws FileNotFoundException {
        DLList<State> statesList = new DLList<State>();
        Scanner sc = new Scanner(new File(fileName));
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] inputs = sc.nextLine().split(",");
            // if (inputs.length != 11) {
            // sc.close();
            // throw new ParseException("Not Formatted Correctly");
            // }
            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i].equals("NA")) {
                    inputs[i] = "-1";
                }
            }

            String stateName = inputs[0];
            int whiteCases = Integer.parseInt(inputs[1]);
            int blackCases = Integer.parseInt(inputs[2]);
            int latinCases = Integer.parseInt(inputs[3]);
            int asianCases = Integer.parseInt(inputs[4]);
            int otherCases = Integer.parseInt(inputs[5]);
            int whiteDeaths = Integer.parseInt(inputs[6]);
            int blackDeaths = Integer.parseInt(inputs[7]);
            int latinDeaths = Integer.parseInt(inputs[8]);
            int asianDeaths = Integer.parseInt(inputs[9]);
            int otherDeaths = Integer.parseInt(inputs[10]);

            Race white = new Race("white", whiteCases, whiteDeaths);
            Race black = new Race("black", blackCases, blackDeaths);
            Race latinx = new Race("latinx", latinCases, latinDeaths);
            Race asian = new Race("asian", asianCases, asianDeaths);
            Race other = new Race("other", otherCases, otherDeaths);

            DLList<Race> list = new DLList<Race>();
            list.add(white);
            list.add(black);
            list.add(latinx);
            list.add(asian);
            list.add(other);

            State state = new State(stateName, list);
            statesList.add(state);

        }
        sc.close();
        return statesList;
    }

}
