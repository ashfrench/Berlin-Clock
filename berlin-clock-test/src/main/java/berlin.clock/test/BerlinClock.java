package berlin.clock.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ash on 07/10/2014.
 */
public class BerlinClock {

    public static final String TIME_FORMAT = "HH:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);

    static{
        dateFormat.setLenient(false);
    }

    public static final String LAMP_OFF = "O";
    public static final String LAMP_YELLOW = "Y";
    public static final String LAMP_RED = "R";

    public static String getBerlinClock(String timeString) throws InvalidTimeFormat {
        try {
            Date date = dateFormat.parse(timeString);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int hours = cal.get(Calendar.HOUR_OF_DAY);
            int minutes = cal.get(Calendar.MINUTE);
            int seconds = cal.get(Calendar.SECOND);

            return getBerlinClock(hours, minutes, seconds);

        } catch (ParseException e) {
            throw new InvalidTimeFormat("Invalid time format: " + timeString + " Must but in the format - " + TIME_FORMAT);
        }
    }

    private static String getBerlinClock(int hours, int minutes, int seconds) throws InvalidTimeFormat {
        if(!isValid(hours, minutes, seconds)){
            throw new InvalidTimeFormat("Invalid time format");
        }

        int firstRowReds = hours / 5;
        int secondRowReds = hours % 5;

        int firstRowMinutes = minutes /5;
        int secondRowMinutes = minutes % 5;

        String secondLamp = (seconds % 2 == 0) ? LAMP_YELLOW : LAMP_OFF;

        StringBuilder sb = new StringBuilder();
        sb.append(secondLamp);
        sb.append(" ");

        for(int x = 1; x <= 4; x ++){
            if(firstRowReds > 0){
                sb.append(LAMP_RED);
            }else{
                sb.append(LAMP_OFF);
            }
            firstRowReds --;
        }

        sb.append(" ");

        for(int x = 1; x <= 4; x ++){
            if(secondRowReds > 0){
                sb.append(LAMP_RED);
            }else{
                sb.append(LAMP_OFF);
            }
            secondRowReds --;
        }

        sb.append(" ");

        for(int x = 1; x <= 11; x ++){
            if(firstRowMinutes > 0){
                if(x % 3 == 0){
                    sb.append(LAMP_RED);
                }else{
                    sb.append(LAMP_YELLOW);
                }
            }else{
                sb.append(LAMP_OFF);
            }

            firstRowMinutes --;
        }

        sb.append(" ");

        for(int x = 1; x <= 4; x ++){
            if(secondRowMinutes > 0){
                sb.append(LAMP_YELLOW);
            }else{
                sb.append(LAMP_OFF);
            }
            secondRowMinutes --;
        }

        return sb.toString();
    }

    private static boolean isValid(int hours, int minutes, int seconds){
         return hours <= 23 && hours >= 0 && minutes <= 59 && minutes >= 0 && seconds >= 0 && seconds <= 59;
    }
}
