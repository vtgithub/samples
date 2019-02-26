package facade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TravelBooker {

    List<Travel> getTravels(Date from, Date to){
      List<Travel> travels = new LinkedList<Travel>();
      travels.add(new Travel("6 days travel"));
      return travels;
    }
}
