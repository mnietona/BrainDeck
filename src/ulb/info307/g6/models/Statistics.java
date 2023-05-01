package ulb.info307.g6.models;

import org.dizitart.no2.objects.Id;
import java.util.Calendar;

public class Statistics {
    @Id
    private final long id = 1;
    private int longestDayStreak;
    private int currentDayStreak;
    private Calendar previousStartupDate;  // Day stored in database

    public Statistics() {
        // Default constructor
    }

    public void updateLastDay(){
        previousStartupDate = Calendar.getInstance();
    }

    /**
     * Setter used for testing purposes.
     */
    public void setPreviousStartupDate(Calendar previousStartupDate) {
        this.previousStartupDate = previousStartupDate;
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
        Calendar today = Calendar.getInstance();
        if (previousStartupDate != null) {  // If it's not the first time the app is launched
            if (isDayBefore(previousStartupDate, today)) {
                currentDayStreak++;
                if (currentDayStreak > longestDayStreak) {
                    longestDayStreak = currentDayStreak;
                }
            } else {
                if (isNotSameDay(previousStartupDate, today)) {
                    resetCurrentStreak();
                }
            }
        }
        updateLastDay();
    }

    /**
     * @return true if date1 is exactly one day before date2.
     */
    public boolean isDayBefore(Calendar date1, Calendar date2) {
        date2.add(Calendar.DATE, -1);
        return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                date1.get(Calendar.DATE) == date2.get(Calendar.DATE);
    }

    public boolean isNotSameDay(Calendar date1, Calendar date2) {
        return date1.get(Calendar.YEAR) != date2.get(Calendar.YEAR) ||
                date1.get(Calendar.MONTH) != date2.get(Calendar.MONTH) ||
                date1.get(Calendar.DATE) != date2.get(Calendar.DATE);
    }

    public void resetCurrentStreak() {
        currentDayStreak = 0;
        updateLastDay();
    }
}
