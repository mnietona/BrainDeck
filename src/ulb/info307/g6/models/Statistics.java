package ulb.info307.g6.models;

import org.dizitart.no2.objects.Id;
import java.util.Calendar;

public class Statistics {
    @Id
    private final long id = 1;
    private int longestDayStreak = 0;
    private int currentDayStreak = 0;
    private Calendar previousStartupDate;  // Day stored in database

    public Statistics() {
        // Constructeur par défaut
    }

    public void updateLastDay(){
        previousStartupDate = Calendar.getInstance();
    }

    public int getCurrentDayStreak() {
        return currentDayStreak;
    }

    public int getLongestDayStreak() {
        return longestDayStreak;
    }

    public Calendar getStringLastDay() {
        return previousStartupDate;
    }

    public void update() {
        Calendar today = Calendar.getInstance();
        System.out.println("currentDayStreak: " + currentDayStreak);
        if (isDayBefore(previousStartupDate, today)) {
            currentDayStreak++;
            System.out.println("currentDayStreak: " + currentDayStreak);
            if (currentDayStreak > longestDayStreak) longestDayStreak = currentDayStreak;
        } else if ((isNotSameDay(previousStartupDate, today))) {
            resetCurrentStreak();
        }
        updateLastDay();
    }

    public boolean isDayBefore(Calendar date1, Calendar date2) {
        // Subtract one day from date2
        date2.add(Calendar.DATE, -1);
        // Compare the two dates
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
