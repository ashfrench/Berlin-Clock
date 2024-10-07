package berlin.clock.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Ash on 07/10/2014.
 */
public class BerlinClockKotlinTest {

    @Test
    public void testClockValid() {
        String berlinClock = getBerlinClock("00:00:00");
        assertEquals("Y OOOO OOOO OOOOOOOOOOO OOOO", berlinClock);

        String berlinClock1 = getBerlinClock("13:17:01");
        assertEquals("O RROO RRRO YYROOOOOOOO YYOO", berlinClock1);

        String berlinClock2 = getBerlinClock("23:59:59");
        assertEquals("O RRRR RRRO YYRYYRYYRYY YYYY", berlinClock2);
    }

    @Test
    public void testClockInvalidInput() {
        assertThrows(InvalidTimeFormat.class, () -> getBerlinClock("RUBBISH"));
    }

    @Test
    public void testClockInvalidFormat() {
        assertThrows(InvalidTimeFormat.class, () -> getBerlinClock("15-45-24"));
    }

    @Test
    public void testClockInvalidHours() {
        assertThrows(InvalidTimeFormat.class, () -> getBerlinClock("24:00:00"));
    }

    @Test
    public void testClockInvalidMinutes() {
        assertThrows(InvalidTimeFormat.class, () -> getBerlinClock("15:65:00"));
    }

    @Test
    public void testClockInvalidSeconds() {
        assertThrows(InvalidTimeFormat.class, () -> getBerlinClock("15:45:85"));
    }

    private String getBerlinClock(String time) {
        return new BerlinClockKotlin().getBerlinClock(time);
    }
}
