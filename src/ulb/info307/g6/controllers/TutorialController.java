package ulb.info307.g6.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.stage.Stage;
import ulb.info307.g6.views.Tutorial;

public class TutorialController extends Controller implements Tutorial.TutorialListener {
    private final Tutorial tutorialView;
    private int index = 0;
    private final List<String> tutorialTexts;

    public TutorialController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Tutorial.fxml", "Tutorial");
        tutorialView = (Tutorial) view;
        tutorialTexts = getTutorialTexts();
        nextPage();
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void clickNext() {
        index++;
        nextPage();
    }

    private void nextPage() {
        tutorialView.updateText(tutorialTexts.get(index));
        String TUTORIAL_PATH = "/ulb/info307/g6/views/tutorial/";
        tutorialView.updateImage(TUTORIAL_PATH + index + ".png");
    }

    private List<String> getTutorialTexts() {
        try {
            Path file = Paths.get("resources/ulb/info307/g6/views/tutorial/strings.txt");
            return Files.readAllLines(file);
        } catch (IOException e) {
            tutorialView.updateText("An error occurred while reading the tutorial file");
            System.out.println("An error occurred while reading the tutorial file: " + e.getMessage());
            return null;
        }
    }
}
