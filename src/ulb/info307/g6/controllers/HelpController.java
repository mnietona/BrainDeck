package ulb.info307.g6.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import ulb.info307.g6.views.Help;

/**
 * Controller for the help view.
 * This controller is used to display the help view.
 */

public class HelpController extends Controller implements Help.HelpListener {
    public final Help helpView;
    private int step = 0;

    public HelpController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/Help.fxml", "Help");
        helpView = (Help) view;
    }

    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }
    private String getText(int step){
        String filePath = "resources/ulb/info307/g6/howToText.txt"; // Replace with the actual file path

        try {
            Path file = Paths.get(filePath);
            List<String> lines = Files.readAllLines(file);
            String line = lines.get(step);
            System.out.println("line: " + line);
            return line;
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return null;
        }

    }

    private String getImage(int step){
        String folderPath= "resources/ulb/info307/g6/howToImages";
        File folder = new File(folderPath);
        try {

        if (folder.isDirectory()) {
            File[] imageFiles = folder.listFiles((dir, name) ->
                    name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".PNG") || name.endsWith(".png"));

            if (imageFiles != null && imageFiles.length >= 2) {
                return imageFiles[step].getAbsolutePath();
            }
        }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());

        }
        return null;
    }
    private void displayHelp(int step){

        String path=getImage(step);
        String text=getText(step);

        helpView.updateText(text); //on file le texte lié a la setp
        helpView.updateImage(path); //on file le path de l'image lié a la step
        System.out.println(step);
    }

    @Override
    public void clickNext() {
        displayHelp(step++);
    }
}
