package prj5;

import java.io.FileNotFoundException;

/**
 * This is the project runner class
 * which takes the input files
 * 
 * @author Rahul Menon, Mohammad Mian, Jae Trimboli
 * @version 2022.04.20
 * 
 */
public class Input {

    /**
     * Main method
     * 
     * @param args
     *            args is used as the input file name if the length is
     *            2
     * 
     * @throws FileNotFoundException
     *             if there is an issue with the file
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        CovidReader covidReader;

        if (args.length == 1) {
            covidReader = new CovidReader(args[0]);
            GUIWindow gui = new GUIWindow(covidReader);
        }
        else {
            covidReader = new CovidReader(
                "Cases_and_Deaths_by_race_CRDT_Sep2020.csv");
            GUIWindow gui = new GUIWindow(covidReader);
        }

    }

}
