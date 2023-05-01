package ulb.info307.g6.models;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class TestStatistics {
    @Test
    void isDayBefore() {
        Statistics statistics = new Statistics();

        // Test case 1: date1 is one day before date2
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date1.set(2023, Calendar.MAY, 1); // May 1, 2023
        date2.set(2023, Calendar.MAY, 2); // May 2, 2023
        assertTrue(statistics.isDayBefore(date1, date2));

        // Test case 2: date1 is the same as date2
        date1.set(2023, Calendar.MAY, 2); // May 2, 2023
        date2.set(2023, Calendar.MAY, 2); // May 2, 2023
        assertFalse(statistics.isDayBefore(date1, date2));

        // Test case 3: date1 is one day after date2
        date1.set(2023, Calendar.MAY, 3); // May 3, 2023
        date2.set(2023, Calendar.MAY, 2); // May 2, 2023
        assertFalse(statistics.isDayBefore(date1, date2));

        // Test case 4: date1 is more than one day before date2
        date1.set(2023, Calendar.APRIL, 30); // April 30, 2023
        date2.set(2023, Calendar.MAY, 2);  // May 2, 2023
        assertFalse(statistics.isDayBefore(date1, date2));
    }

    @Test
    void isNotSameDay() {
        Statistics statistics = new Statistics();

        // Test case 1: date1 and date2 are the same day
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date1.set(2023, Calendar.MAY, 1); // May 1, 2023
        date2.set(2023, Calendar.MAY, 1); // May 1, 2023
        assertFalse(statistics.isNotSameDay(date1, date2));

        // Test case 2: date1 is one day before date2
        date1.set(2023, Calendar.MAY, 1); // May 1, 2023
        date2.set(2023, Calendar.MAY, 2); // May 2, 2023
        assertTrue(statistics.isNotSameDay(date1, date2));

        // Test case 3: date1 is one day after date2
        date1.set(2023, Calendar.MAY, 3); // May 3, 2023
        date2.set(2023, Calendar.MAY, 2); // May 2, 2023
        assertTrue(statistics.isNotSameDay(date1, date2));

        // Test case 4: date1 and date2 are in different months
        date1.set(2023, Calendar.APRIL, 30); // April 30, 2023
        date2.set(2023, Calendar.MAY, 2);  // May 2, 2023
        assertTrue(statistics.isNotSameDay(date1, date2));

        // Test case 5: date1 and date2 are in different years
        date1.set(2022, Calendar.MAY, 1); // May 1, 2022
        date2.set(2023, Calendar.MAY, 1); // May 1, 2023
        assertTrue(statistics.isNotSameDay(date1, date2));
    }

    @Test
    void updateDayStreak() {
        Statistics statistics = new Statistics();

        // Test case 1: first time the app is launched
        statistics.setPreviousStartupDate(null);
        statistics.updateDayStreak();
        assertEquals(0, statistics.getCurrentDayStreak());
        assertEquals(0, statistics.getLongestDayStreak());

        // Test case 2: update on the same day
        Calendar today = Calendar.getInstance();
        statistics.setPreviousStartupDate(today);
        statistics.updateDayStreak();
        assertEquals(0, statistics.getCurrentDayStreak());
        assertEquals(0, statistics.getLongestDayStreak());

        // Test case 3: update on the next day (day after yesterday)
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        statistics.setPreviousStartupDate(yesterday);
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(1, statistics.getLongestDayStreak());

        // Test case 4: open app after a gap several days after the last time it was opened
        Calendar fiveDaysBeforeToday = Calendar.getInstance();
        fiveDaysBeforeToday.add(Calendar.DATE, -5);
        statistics.setPreviousStartupDate(fiveDaysBeforeToday);
        statistics.updateDayStreak();
        assertEquals(0, statistics.getCurrentDayStreak());
        assertEquals(1, statistics.getLongestDayStreak());
    }
}
