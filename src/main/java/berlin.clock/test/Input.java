package berlin.clock.test;

/**
 * Created by Ash on 07/10/2014.
 */
public class Input {

    public static void main(String [] args){
        if(args.length < 1){
            System.out.println("Please supply a time to convert to a Berlin Clock in the format - " + BerlinClock.TIME_FORMAT);
        }
        BerlinClock berlinClock = new BerlinClock();

        for(String arg : args){
            try {
                String clock = berlinClock.getBerlinClock(arg);
                System.out.println("Input - " + arg + " Clock - " + clock);
            } catch (InvalidTimeFormat invalidTimeFormat) {
                invalidTimeFormat.printStackTrace();
            }
        }
    }
}
