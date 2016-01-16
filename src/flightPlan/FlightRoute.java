package flightPlan;

import custom.CustomOutputStream;
import values.Values;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Alexander Bazo on 16/01/16.
 */
public class FlightRoute {
    private ArrayList<String> route;

    public FlightRoute() {
        route = new ArrayList<String>();
    }

    public void addDestination(String destination) {
        route.add(destination);
    }

    public String getOrigin() throws RouteIsEmptyException {
        String origin = route.get(0);
        if (origin == null) {
            throw new RouteIsEmptyException();
        }
        return origin;
    }

    public void printRoute(CustomOutputStream outputStream) {
        outputStream.println(Values.Strings.EN.FINISHED_ROUTE_PROMPT);
        Iterator<String> destinations = route.iterator();
        while (destinations.hasNext()) {
            outputStream.print(destinations.next());
            if (destinations.hasNext()) {
                outputStream.print(Values.Strings.EN.ROUTE_DESTINATIONS_DELIMITER);
            }

        }
    }


}
