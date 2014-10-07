package inkglobal.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ash on 07/10/2014.
 */
public class BerlinClockTest {

    @Test
    public void testClockValid() throws InvalidTimeFormat {
        String berlinClock = BerlinClock.getBerlinClock("00:00:00");
        Assert.assertEquals("Y OOOO OOOO OOOOOOOOOOO OOOO", berlinClock);

        String berlinClock1 = BerlinClock.getBerlinClock("13:17:01");
        Assert.assertEquals("O RROO RRRO YYROOOOOOOO YYOO", berlinClock1);

        String berlinClock2 = BerlinClock.getBerlinClock("23:59:59");
        Assert.assertEquals("O RRRR RRRO YYRYYRYYRYY YYYY", berlinClock2);
    }

    @Test(expected = InvalidTimeFormat.class)
    public void testClockInvalidInput() throws InvalidTimeFormat {
        BerlinClock.getBerlinClock("RUBBISH");
    }

    @Test(expected = InvalidTimeFormat.class)
    public void testClockInvalidFormat() throws InvalidTimeFormat {
        BerlinClock.getBerlinClock("15-45-24");
    }

    @Test(expected = InvalidTimeFormat.class)
    public void testClockInvalidHours() throws InvalidTimeFormat {
        BerlinClock.getBerlinClock("24:00:00");
    }

    @Test(expected = InvalidTimeFormat.class)
    public void testClockInvalidMinutes() throws InvalidTimeFormat {
        BerlinClock.getBerlinClock("15:65:00");
    }

    @Test(expected = InvalidTimeFormat.class)
    public void testClockInvalidSeconds() throws InvalidTimeFormat {
        BerlinClock.getBerlinClock("15:45:85");
    }
}
