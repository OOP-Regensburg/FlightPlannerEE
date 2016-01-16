import acm.program.ConsoleProgram;
import custom.CustomInputStream;
import custom.CustomOutputStream;
import de.ur.mi.graphicsapp.GraphicsApp;
import dialog.*;
import flightPlan.*;
import processor.SplitterRecycler;
import processor.StringSplitter;
import processor.StringSplitterException;
import reader.AsyncFileReader;
import reader.FileReaderListener;
import values.Values;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class FlightPlanner extends ConsoleProgram implements FileReaderListener, CustomOutputStream, CustomInputStream, UserDialogListener {
    private FlighPlanBuilder flighPlanBuilder;
    private FlightPlan flightPlan;
    private FlightRoute flightRoute;
    private DialogProxy dialogProxy;

    public void run() {
        println(Values.Strings.EN.STARTUP_PROMPT);
        readInputFile();
    }

    private void readInputFile() {
        File inputFile = GraphicsApp.loadFile(Values.Files.INPUT_FILE);
        AsyncFileReader asyncFileReader = new AsyncFileReader(true);
        asyncFileReader.readFile(inputFile, this);
    }

    private void createDialogs() {
        UserDialogBuilder dialogBuilder = new UserDialogBuilder();
        dialogProxy = new DialogProxy(this);
        for (String orgin : flightPlan.getOrigins()) {
            try {
                ArrayList<String> destinations = flightPlan.getDestinationsForOrigin(orgin);
                dialogBuilder.addTitle(orgin);
                dialogBuilder.addPrompt(Values.Strings.EN.DIALOG_PROMPT_PREFFIX + orgin + Values.Strings.EN.DIALOG_PROMPT_SUFFIX);
                for (String destination : destinations) {
                    dialogBuilder.addOption(destination);
                }
                dialogProxy.addDialog(dialogBuilder.getDialog());
                dialogBuilder.clear();
            } catch (FlightPlanException e) {
                e.printStackTrace();
            } catch (DialogException e) {
                e.printStackTrace();
            }
        }
    }

    //@TODO: user should be able to select the origin
    private void startRouting() {
        flightRoute = new FlightRoute();
        try {
            flightRoute.addDestination(flightPlan.getFirstOrigin());
            dialogProxy.showDialog(flightPlan.getFirstOrigin(), this, this);
        } catch (DialogNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private void showNextDestinations(String origin) {
        try {
            if (origin.equals(flightRoute.getOrigin())) {
                showCompletedRoute();
            } else {
                flightRoute.addDestination(origin);
                try {
                    dialogProxy.showDialog(origin, this, this);
                } catch (DialogNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        } catch (RouteIsEmptyException e) {
            e.printStackTrace();
        }
    }

    private void showCompletedRoute() {
        flightRoute.printRoute(this);
    }


    @Override
    public void onStartFileReading() {
        flighPlanBuilder = new FlighPlanBuilder();
    }

    @Override
    public void onLineRead(String line) {
        StringSplitter splitter = SplitterRecycler.getInstance().getSplitter(Values.Strings.DELIMITER_STRING);
        try {
            String[] parts = splitter.split(line);
            try {
                flighPlanBuilder.addRoute(parts);
            } catch (InvalidRouteFormatException e) {
                e.printStackTrace();
            }
        } catch (StringSplitterException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onEndOfFileReached() {
        flightPlan = flighPlanBuilder.getFlightPlan();
        createDialogs();
        startRouting();
    }

    @Override
    public void onDialogOptionSelected(UserDialog dialog) {
        try {
            showNextDestinations(dialog.getSelectedOption());
        } catch (NoOptionSelectedException e) {
            e.printStackTrace();
        }
    }
}
