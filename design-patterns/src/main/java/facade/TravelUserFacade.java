package facade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TravelUserFacade {
    public List<TravelInfo> getTravelsAndHotelsAndFlights(Date from, Date to){
        TravelBooker travelBooker = new TravelBooker();
        HotelBooker hotelBooker = new HotelBooker();
        FlightBooker flightBooker = new FlightBooker();
        List<TravelInfo> travelInfos = new LinkedList<TravelInfo>();
        travelInfos.add(
                new TravelInfo(
                        hotelBooker.getHotels(from, to),
                        travelBooker.getTravels(from, to),
                        flightBooker.getFlightsFor(from, to)
                )
        );
        return travelInfos;
    }
}
