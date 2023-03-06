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
}