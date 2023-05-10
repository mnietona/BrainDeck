package ulb.info307.g6.models;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestStatistics {
    @Test
    void updateDayStreak() {
        Statistics statistics = new Statistics();

        // Test case 1: first time the app is launched (current day streak should start at 1)
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(1, statistics.getLongestDayStreak());

        // Test case 2: open app the day after it was last opened (current day streak should increase)
        statistics.setPreviousStartupDate(LocalDate.now().minusDays(1));
        statistics.updateDayStreak();
        assertEquals(2, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 3: open app on the same day (current day streak should stay the same)
        statistics.setPreviousStartupDate(LocalDate.now());
        statistics.updateDayStreak();
        assertEquals(2, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 4: open app more than one day after it was last opened
        // (current day streak should reset, longest day streak should stay the same)
        statistics.setPreviousStartupDate(LocalDate.now().minusDays(2));
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 5: open app before the day before it was last opened (should never happen, but streak should reset)
        statistics.setPreviousStartupDate(LocalDate.now().plusDays(1));
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());

        // Test case 6: open more than one day before it was last opened (should never happen, but streak should reset)
        statistics.setPreviousStartupDate(LocalDate.now().plusDays(2));
        statistics.updateDayStreak();
        assertEquals(1, statistics.getCurrentDayStreak());
        assertEquals(2, statistics.getLongestDayStreak());
    }
}
