package prj5;

import java.awt.Color;
import cs2.Button;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;

/**
 * This class represents a Gui Window and will 
 * display the COVID visualization to the end user.
 * 
 * @author Mohammad Mian, Rahul Menon, Jae Trimboli
 * @version Apr 29, 2022
 */
public class GUIWindow {

    private Window window;
    private CovidReader reader;
    private State currentState;
    private DLList<State> stateList;
    private boolean cfrSort;

    /**
     * Initializes the window and its buttons
     * 
     * @param reader
     *            A CovidReader object that reads inputed data.
     */
    public GUIWindow(CovidReader r) {

        reader = r;
        stateList = reader.getStates();
        currentState = stateList.get(0);

        // Creates top buttons
        Button sortByAlpha = new Button("Sort by Alpha");
        Button sortByCFR = new Button("Sort By CFR");
        Button quit = new Button("Quit");

        window = new Window();

        // Adds top buttons to the window
        window.addButton(sortByAlpha, WindowSide.NORTH);
        window.addButton(quit, WindowSide.NORTH);
        window.addButton(sortByCFR, WindowSide.NORTH);

        // Creates bottom buttons
        Button DCButton = new Button("Represent DC");
        Button GAButton = new Button("Represent GA");
        Button MDButton = new Button("Represent MD");
        Button NCButton = new Button("Represent NC");
        Button TNButton = new Button("Represent TN");
        Button VAButton = new Button("Represent VA");

        // Adds bottom buttons to window
        window.addButton(DCButton, WindowSide.SOUTH);
        window.addButton(GAButton, WindowSide.SOUTH);
        window.addButton(MDButton, WindowSide.SOUTH);
        window.addButton(NCButton, WindowSide.SOUTH);
        window.addButton(TNButton, WindowSide.SOUTH);
        window.addButton(VAButton, WindowSide.SOUTH);
        
        // Defines button click behavior
        DCButton.onClick(this, "clickedDC");
        GAButton.onClick(this, "clickedGA");
        MDButton.onClick(this, "clickedMD");
        NCButton.onClick(this, "clickedNC");
        TNButton.onClick(this, "clickedTN");
        VAButton.onClick(this, "clickedVA");
        
        sortByAlpha.onClick(this, "clickedSortByAlpha");
        sortByCFR.onClick(this, "clickedSortByCFR");
        quit.onClick(this, "clickedQuit");
    }

    /**
     * Defines what happens when DC button is clicked.
     * 
     * @param B Button that will be clicked
     */
    public void clickedDC(Button B) {
        window.removeAllShapes();
        currentState = stateList.get(0);
        displayInfo();
    }

    /**
     * Defines what happens when GA button is clicked.
     * 
     * @param B Button that will be clicked
     */
    public void clickedGA(Button B) {
        window.removeAllShapes();
        currentState = stateList.get(1);
        displayInfo();
    }

    /**
     * Defines what happens when MD button is clicked.
     * 
     * @param B Button that will be clicked
     */
    public void clickedMD(Button B) {
        window.removeAllShapes();
        currentState = stateList.get(2);
        displayInfo();
    }

    /**
     * Defines what happens when NC button is clicked.
     * 
     * @param B Button that will be clicked
     */
    public void clickedNC(Button B) {
        window.removeAllShapes();
        currentState = stateList.get(3);
        displayInfo();
    }

    /**
     * Defines what happens when TN button is clicked.
     * 
     * @param B Button that will be clicked
     */
    public void clickedTN(Button B) {
        window.removeAllShapes();
        currentState = stateList.get(4);
        displayInfo();
    }

    /**
     * Defines what happens when VA button is clicked.
     * 
     * @param B Button that will be clicked
     */
    public void clickedVA(Button B) {
        window.removeAllShapes();
        currentState = stateList.get(5);
        displayInfo();
    }

    /**
     * Displays the CFR sorted data of the current state
     * 
     * @param button
     *            The button that was clicked
     */
    public void clickedSortByCFR(Button button) {
        window.removeAllShapes();
        cfrSort = true;
        displayInfo();
    }


    /**
     * Displays the Alpha sorted data of the current state
     * 
     * @param button
     *            The button that was clicked
     */
    public void clickedSortByAlpha(Button button) {
        window.removeAllShapes();
        cfrSort = false;
        displayInfo();
    }


    /**
     * Quits the program
     * 
     * @param button
     *            The quit button
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }

    /**
     * Helper method that displays all the information for a specific state.
     */
    private void displayInfo() {

        int windowX = (window.getGraphPanelWidth() - 210) / 4;

        // Displays the title on window
        TextShape text = new TextShape(window.getGraphPanelWidth() / 2, 40, currentState
            .getName() + " Case Fatality Ratios by Race");
        
        // Displays our names on window
        TextShape name = new TextShape(window.getGraphPanelWidth() / 2, 
            10, "mohammadm21 jaetrim rahulmenon");

        text.move(-1 * text.getWidth() / 2, 0);
        name.move(-1 * name.getWidth() / 2, 0);

        window.addShape(text);
        window.addShape(name);

        // Displays the data bars for each race
        for (int i = 0; i < 5; i++) {

            Race r = null;

            // Checks to see if data should be ordered by Alpha or CFR
            if (cfrSort == true) {

                r = currentState.sortCFR().get(i);

            }
            else {

                r = currentState.sortAlpha().get(i);

            }

            double cfr = r.getCFR();
            String cfrString;

            if (Double.compare(cfr, -1) == 0)
            {
                cfrString = "N/A";
            }
            else
            {
                cfrString = cfr + "%";
            }

            TextShape race = new TextShape(windowX * i + 80, 240, r.getName());

            TextShape cfrText = new TextShape(windowX * i + 80, 260, (cfrString));

            // Adds data bars to window
            if (Double.compare(cfr, -1) != 0) {
                int height = (int)(15 * cfr);
                Shape shape = new Shape(windowX * i + 100, 230 - height, 10,
                    height, Color.BLUE);
                window.addShape(shape);
            }

            // Adds text to window
            window.addShape(race);
            window.addShape(cfrText);

        }
    }
}
