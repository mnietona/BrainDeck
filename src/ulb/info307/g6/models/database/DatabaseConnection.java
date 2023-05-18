package ulb.info307.g6.models.database;

import org.dizitart.no2.Nitrite;
import ulb.info307.g6.views.Popup;

public class DatabaseConnection {
    private static Nitrite con = null;

    static {
        String db_path = "db.db";
        try {
            Class.forName("org.dizitart.no2.Nitrite");
            con = Nitrite.builder()
                    .compressed()
                    .filePath(db_path)
                    .openOrCreate();
        } catch (ClassNotFoundException e) {
            new Popup("Database driver not found").showAndWait();
            System.exit(1);
        }
    }
    public static Nitrite getConnection() {
        return con;
    }
}
