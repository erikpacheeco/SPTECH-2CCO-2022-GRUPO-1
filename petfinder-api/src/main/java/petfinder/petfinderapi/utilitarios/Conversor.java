package petfinder.petfinderapi.utilitarios;

import java.time.ZoneId;
import java.util.Date;

public class Conversor {
    
    public static String dateToDayMonthString(Date date) {
        return date.getDate() + "/" + (date.getMonth() + 1);
    }

    public static String dateToYearMonthString(Date date) {
        Integer year = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        return year + "/" + (date.getMonth() + 1);
    }
}
