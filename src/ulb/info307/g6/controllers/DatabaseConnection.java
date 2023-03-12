package ulb.info307.g6.controllers;

import org.dizitart.no2.Nitrite;

public class DatabaseConnection {
    private static Nitrite con = null;

    static {
        //TODO: move the db into the resources of the jar
        String db_path = "db.db";
        try {
            Class.forName("org.dizitart.no2.Nitrite");
            con = Nitrite.builder()
                    .compressed()
                    .filePath(db_path)
                    .openOrCreate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Nitrite getConnection() {
        return con;
    }
}
