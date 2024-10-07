package berlin.clock.test

import java.text.ParseException
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BerlinClockKotlin : BerlinClockInterface {
    private val LAMP_OFF: String = "O"
    private val LAMP_YELLOW: String = "Y"
    private val LAMP_RED: String = "R"

    override fun getBerlinClock(timeString: String): String {
        try {
            val localTime = LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME)

            val hours = localTime.hour
            val minutes = localTime.minute
            val seconds = localTime.second

            return getBerlinClock(hours, minutes, seconds)
        } catch (e: DateTimeParseException) {
            throw InvalidTimeFormat("Invalid time format: $timeString Must but in the format - ${DateTimeFormatter.ISO_LOCAL_TIME}")
        }
    }

    private fun getBerlinClock(hours: Int, minutes: Int, seconds: Int): String {
        if (!isValid(hours, minutes, seconds)) {
            throw InvalidTimeFormat("Invalid time format")
        }

        var firstRowReds = hours / 5
        var secondRowReds = hours % 5

        var firstRowMinutes = minutes / 5
        var secondRowMinutes = minutes % 5

        val secondLamp = if ((seconds % 2 == 0)) LAMP_YELLOW else LAMP_OFF

        val sb = StringBuilder()
        sb.append(secondLamp)
        sb.append(" ")

        for (x in 1..4) {
            if (firstRowReds > 0) {
                sb.append(LAMP_RED)
            } else {
                sb.append(LAMP_OFF)
            }
            firstRowReds--
        }

        sb.append(" ")

        for (x in 1..4) {
            if (secondRowReds > 0) {
                sb.append(LAMP_RED)
            } else {
                sb.append(LAMP_OFF)
            }
            secondRowReds--
        }

        sb.append(" ")

        for (x in 1..11) {
            if (firstRowMinutes > 0) {
                if (x % 3 == 0) {
                    sb.append(LAMP_RED)
                } else {
                    sb.append(LAMP_YELLOW)
                }
            } else {
                sb.append(LAMP_OFF)
            }

            firstRowMinutes--
        }

        sb.append(" ")

        for (x in 1..4) {
            if (secondRowMinutes > 0) {
                sb.append(LAMP_YELLOW)
            } else {
                sb.append(LAMP_OFF)
            }
            secondRowMinutes--
        }

        return sb.toString()
    }

    private fun isValid(hours: Int, minutes: Int, seconds: Int): Boolean {
        return (hours in 0..23) && (minutes in 0..59) && (seconds in 0..59)
    }
}