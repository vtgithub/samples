package facade;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
import java.util.List;

public class TravelInfo {
    private List<Hotel> hotels;
    private List<Travel> travels;
    private List<Flight> flights;

    public TravelInfo(List<Hotel> hotels, List<Travel> travels, List<Flight> flights) {
        this.hotels = hotels;
        this.travels = travels;
        this.flights = flights;
//        Collections.sort();
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
