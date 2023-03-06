package ulb.info307.g6;

import javafx.application.Application;
import javafx.stage.Stage;
// for button
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox; // to align horizontally (VBox for vertical)
import javafx.geometry.Pos;
// for rectangle
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Main class of the app.
 */
public class Main extends Application {
    /**
     * Launches an empty javaFX window.
     */
    @Override
    public void start(Stage window) {
        window.setTitle("Projet 2023-groupe-6");

        // Create three buttons
        Button buttonEdit = new Button("Edit");
        Button buttonAdd = new Button("Add");
        Button buttonRemove = new Button("Remove");

        // Set the position of the buttons to the right side
        HBox buttonBox = new HBox(10, buttonEdit, buttonAdd, buttonRemove);
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        // Create a layout pane with two sections ( to divide the window in two )
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setLeft(new BorderPane());
        root.setRight(buttonBox);

        // Create a rectangle and position it at the center of the right side of the window ( rectangle represents the card )
        Rectangle rectangle = new Rectangle(320, 200, Color.GRAY);
        StackPane stackPane = new StackPane(rectangle);
        stackPane.setAlignment(Pos.CENTER_RIGHT);
        root.setCenter(stackPane);

        // Create a scene and set the root layout pane ( you can choose the size of your window )
        Scene scene = new Scene(root, 800, 600);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

