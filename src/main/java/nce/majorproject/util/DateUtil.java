package nce.majorproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static LocalDate stringToDate(String inputDate){

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


            //String dateString = format.format(new Date());
            Date date = format.parse(inputDate);
            return   date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch(ParseException pe) {
            pe.printStackTrace();
        }
        return LocalDate.now();
    }


}
