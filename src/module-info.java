 module ulb.info307.g6 {
     exports ulb.info307.g6;
     exports ulb.info307.g6.controllers;
     exports ulb.info307.g6.models;
     requires javafx.controls;
     requires javafx.fxml;
     requires org.controlsfx.controls;
     requires org.kordamp.bootstrapfx.core;
     requires nitrite;
     opens ulb.info307.g6 to javafx.fxml;
<<<<<<< HEAD
     opens ulb.info307.g6.controllers to javafx.graphics;
    exports ulb.info307.g6;
    opens ulb.info307.g6.views to javafx.graphics, javafx.fxml;
    exports ulb.info307.g6.views;
=======
     opens ulb.info307.g6.models to nitrite;
>>>>>>> origin/db
}