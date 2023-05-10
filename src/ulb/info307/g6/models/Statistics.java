package ulb.info307.g6.models;

import java.time.LocalDate;
import org.dizitart.no2.objects.Id;

public class Statistics {
    @Id
    private final long id = 1;
    private int currentDayStreak, longestDayStreak;
    private LocalDate previousStartupDate;  // Day stored in database

    public Statistics() {
        currentDayStreak = 1;
        longestDayStreak = 1;
        previousStartupDate = LocalDate.now();
    }

    /**
     * Setter used for testing purposes.
     */
    public void setPreviousStartupDate(LocalDate date) {
        previousStartupDate = date;
    }

    public int getCurrentDayStreak() {
        return currentDayStreak;
    }

    public int getLongestDayStreak() {
        return longestDayStreak;
    }

    /**
     * Increments day streak only if today is exactly the day after the last day
     * the app was opened (date stored in database). If it is more than one day, it resets the day streak.
     * Also updates the longest streak when needed.
     */
    public void updateDayStreak() {
        if (isYesterday(previousStartupDate)) {
            currentDayStreak++;
            if (currentDayStreak > longestDayStreak) longestDayStreak = currentDayStreak;
        } else {  // Opened on the same day, or more than a day after
            if (!isToday(previousStartupDate)) {
                currentDayStreak = 0;
            }
        }
        previousStartupDate = LocalDate.now();
    }

    public static boolean isYesterday(LocalDate date) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return date.isEqual(yesterday);
    }

    public static boolean isToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isEqual(today);
    }
}
