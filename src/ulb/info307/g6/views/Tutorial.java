package ulb.info307.g6.views;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Tutorial implements View {
    
    public TutorialListener listener;
    @FXML
    private Text tutorialText;
    @FXML
    private ImageView tutorialScreenshot;

    @FXML
    private void clickHome() {
        listener.clickHome();
    }

    @FXML
    private void clickNext() {
        listener.clickNext();
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (TutorialListener) listener;
    }

    public void updateText(String text) {
        System.out.println("updateText");
        tutorialText.setText(text);
    }

    public void updateImage(String path) {
        Image newImage = new Image(path);
        tutorialScreenshot.setImage(newImage);
    }

    public interface TutorialListener {
        void clickHome();
        void clickNext();
    }
}
