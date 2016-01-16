package flightPlan;

import values.Values;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class FlightPlan {
    private HashMap<String, ArrayList<String>> flightPlan;

    public FlightPlan() {

    }

    public void setFlightPlan(HashMap<String, ArrayList<String>> flightPlan) {
        this.flightPlan = flightPlan;
    }

    public boolean isValidOrigin(String origin) {
        if (flightPlan.keySet().contains(origin)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidRoute(String from, String to) throws InvalidOriginException, NoDestinationsAvailableException {
        if (!isValidOrigin(from)) {
            throw new InvalidOriginException();
        }
        ArrayList<String> destinations = flightPlan.get(from);
        if (destinations.size() < 1) {
            throw new NoDestinationsAvailableException();
        }
        return destinations.contains(to);
    }

    public ArrayList<String> getDestinationsForOrigin(String origin) throws InvalidOriginException, NoDestinationsAvailableException {
        if (!isValidOrigin(origin)) {
            throw new InvalidOriginException();
        }
        ArrayList<String> destinations = flightPlan.get(origin);
        if (destinations.size() < 1) {
            throw new NoDestinationsAvailableException();
        }
        return destinations;
    }

    public String getFirstOrigin() {
        return getOrigins().iterator().next();
    }

    public Set<String> getOrigins() {
        return flightPlan.keySet();
    }

    public void print(PrintStream out) {
        for (String origin : flightPlan.keySet()) {
            out.print(getFormatedOriginStringForOutput(origin) + "\t");
            Iterator destinations = flightPlan.get(origin).iterator();
            while (destinations.hasNext()) {
                out.print(destinations.next());
                if (destinations.hasNext()) {
                    out.print(Values.Strings.LIST_DELIMITER_FOR_OUTPUT);
                }
            }
            out.print(System.lineSeparator());
        }
    }

    private String getFormatedOriginStringForOutput(String origin) {
        int maxCharacters = getMaxNumbersOfCharsInOrigin();
        if (origin.length() < maxCharacters) {
            for (int i = origin.length(); i < maxCharacters; i++) {
                origin += Values.Strings.SINGLE_WHITESPACE;
            }
        }
        return origin;
    }

    private int getMaxNumbersOfCharsInOrigin() {
        int maxChars = -1;
        for (String origin : flightPlan.keySet()) {
            if (maxChars < origin.length()) {
                maxChars = origin.length();
            }
        }
        return maxChars;
    }

}
