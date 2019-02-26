package facade;

import java.util.Date;
import java.util.List;

//create an interface for subsystem and make it easier to use
public class Executor {
    public static void main(String[] args) {
        TravelUserFacade travelUserFacade = new TravelUserFacade();
        List<TravelInfo> travelInfos = travelUserFacade.getTravelsAndHotelsAndFlights(new Date(), new Date());

        for (TravelInfo travelInfo : travelInfos) {
            System.out.println(travelInfo.getFlights().get(0).toString());
            System.out.println(travelInfo.getHotels().get(0).toString());
            System.out.println(travelInfo.getTravels().get(0).toString());
        }
    }
}
