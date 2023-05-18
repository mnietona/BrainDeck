package ulb.info307.g6.models.database;

import org.dizitart.no2.objects.ObjectRepository;
import ulb.info307.g6.models.Statistics;
public class StatisticsDao {
    final ObjectRepository<Statistics> con = DatabaseConnection.getConnection().getRepository(Statistics.class);

    public void insert(Statistics statistics) {
        con.insert(statistics);
    }

    public Statistics getStatistics() {
        return con.find().toList().get(0);
    }

    public int getCurrentDayStreak() {
        return con.find().toList().get(0).getCurrentDayStreak();
    }

    public int getLongestDayStreak() {
        return con.find().toList().get(0).getLongestDayStreak();
    }

    public void update(Statistics statistics) {
        con.update(statistics);
    }
}
