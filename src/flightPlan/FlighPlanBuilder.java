package flightPlan;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class FlighPlanBuilder {
    private HashMap<String, ArrayList<String>> routes;

    public FlighPlanBuilder() {
        routes = new HashMap<String, ArrayList<String>>();
    }

    public void addRoute(String[] route) throws InvalidRouteFormatException {
        if (route.length != 2) {
            throw new InvalidRouteFormatException();
        }
        addRoute(route[0], route[1]);
    }

    public void addRoute(String from, String to) {
        ArrayList<String> route = routes.get(from);
        if (route == null) {
            routes.put(from, new ArrayList<String>());
            route = routes.get(from);
        }
        route.add(to);
    }

    public void clear() {
        routes.clear();
    }

    public FlightPlan getFlightPlan() {
        FlightPlan flightPlan = new FlightPlan();
        flightPlan.setFlightPlan(routes);
        return flightPlan;
    }
}
