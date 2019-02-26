package facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightBooker {
    List<Flight> getFlightsFor(Date from, Date to){
        List<Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight("kermanshah"));
        return  flights;
    }
}
