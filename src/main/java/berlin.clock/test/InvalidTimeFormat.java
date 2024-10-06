package berlin.clock.test;

/**
 * Created by Ash on 07/10/2014.
 */
public class InvalidTimeFormat extends RuntimeException{

    public InvalidTimeFormat(String string){
        super(string);
    }
}
