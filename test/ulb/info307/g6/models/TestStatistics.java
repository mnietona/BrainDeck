package ulb.info307.g6.models;

import java.lang.reflect.Field;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestStatistics {
    @Test
    void updateDayStreak() throws NoSuchFieldException, IllegalAccessException {
        Statistics statistics = new Statistics();

        // Access Statistics previousStartupDate private field, without having a getter
        Field previousStartupDateField = Statistics.class.getDeclaredField("previousStartupDate");
        previousStartupDateField.setAccessible(true);

        // Test case 1: first time the app is launched (current day streak should start at 1)
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(1, statistics.getLongestDayStreak());

        // Test case 2: open app the day after it was last opened (current day streak should increase)
        previousStartupDateField.set(statistics, LocalDate.now().minusDays(1));
        statistics.updateDayStreak();
        assertEquals(2, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 3: open app on the same day (current day streak should stay the same)
        previousStartupDateField.set(statistics, LocalDate.now());
        statistics.updateDayStreak();
        assertEquals(2, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 4: open app more than one day after it was last opened
        // (current day streak should reset, longest day streak should stay the same)
        previousStartupDateField.set(statistics, LocalDate.now().minusDays(2));
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 5: open app before the day before it was last opened (should never happen, but streak should reset)
        previousStartupDateField.set(statistics, LocalDate.now().plusDays(1));
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 6: open more than one day before it was last opened (should never happen, but streak should reset)
        previousStartupDateField.set(statistics, LocalDate.now().plusDays(2));
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());
    }
}
