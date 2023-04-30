package ulb.info307.g6.models.database;

import org.dizitart.no2.objects.ObjectRepository;
import ulb.info307.g6.models.Statistics;

public class StatisticsDao {
    final ObjectRepository<Statistics> con = DatabaseConnection.getConnection().getRepository(Statistics.class);

    /**
     * Adds new statistics database
     */
    public void insert(Statistics statistics) {
        try {
            con.insert(statistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the statistics database (used to create a Statistics object)
     */
    public Statistics getStatistics() {
        try {
            return con.find().toList().get(0);
        } catch (Exception e) {
            System.out.println("No statistics found in database.");
        }
        return null;
    }

    /**
     * Updates the statistics object already database
     */
    public void update(Statistics statistics) {
        try {
            con.update(statistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
