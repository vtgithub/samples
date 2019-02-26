package facade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HotelBooker {
    List<Hotel> getHotels(Date from, Date to){
        List<Hotel> hotels = new LinkedList<Hotel>();
        hotels.add(new Hotel("5 setareye beheshte kermanshah"));
        return hotels;
    }
}
