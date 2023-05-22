package ulb.info307.g6.controllers;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import ulb.info307.g6.Launcher;
import ulb.info307.g6.views.Popup;

/**
 * Entry point of the program (called by launcher).
 * Is the main controller of the app, with no view associated to it.
 * Creates the first menu / controller with a view (WelcomeController).
 */
@SpringBootApplication
public class MainController extends Application {
    private ConfigurableApplicationContext applicationContext;
    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
                ac.registerBean(Application.class, () -> MainController.this);
                ac.registerBean(Parameters.class, this::getParameters);
                ac.registerBean(HostServices.class, this::getHostServices);
        };
        this.applicationContext = new SpringApplicationBuilder()
                .sources(Launcher.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }
    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Entry point of the JavaFX app.
     *
     * @param primaryStage Window of the app, passed to all controllers.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.getIcons().add(new Image("/ulb/info307/g6/views/icons/logo.png"));
            new WelcomeController(primaryStage);
        } catch (Exception e) {
            new Popup("An error occurred while loading the application.\n" +
                    "If the problem persists, please contact the developers").showAndWait();
        }
    }
}
