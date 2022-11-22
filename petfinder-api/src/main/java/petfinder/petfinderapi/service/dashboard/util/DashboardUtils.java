package petfinder.petfinderapi.service.dashboard.util;

import java.util.ArrayList;
import java.util.List;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public class DashboardUtils {
    
    // adding single value hole dates
    public static List<String> addDateHole(String actualDate, List<DateHole> list) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getStringDate().equals(actualDate)) {
                return 
                    List.of(
                        actualDate,
                        String.valueOf(list.remove(i).getValue())
                    );
            }
        }

        return List.of(actualDate, "0");
    }

    // adding double value void dates
    public static List<String> addDateHole(String actualDate, List<DateHole> list1, List<DateHole> list2) {
        List<String> values = new ArrayList<String>(List.of(actualDate, "0", "0"));
        
        for(int i = 0; i < list1.size(); i++) {
            if(list1.get(i).getStringDate().equals(actualDate)) {
                values.set(1, String.valueOf(list1.remove(i).getValue()));
            }
        }
        
        for(int i = 0; i < list2.size(); i++) {
            if(list2.get(i).getStringDate().equals(actualDate)) {
                values.set(2, String.valueOf(list2.remove(i).getValue()));
            }
        }
        
        return values;
    }
}
