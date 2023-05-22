package ulb.info307.g6.views;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Help implements View {
    
    public HelpListener listener;
    @FXML
    private Text howText;
    @FXML
    private ImageView howImage;
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
        this.listener = (Help.HelpListener) listener;
    }

    public void updateText(String text) { //TODO: static?
        howText.setText(text);
    }

    public void updateImage(String path) {
        howImage.setImage(new Image(path));
    }

    public interface HelpListener {
        void clickHome();
        void clickNext();
    }
}
