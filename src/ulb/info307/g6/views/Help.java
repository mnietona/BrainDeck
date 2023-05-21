package ulb.info307.g6.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Help implements View {
    public HelpListener listener;
    @FXML
    private Text howText;
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
        howText.setText("Next");
    }

    public void updateImage(String path) {
        System.out.println(path);
    }

    public interface HelpListener {
        void clickHome();
        void clickNext();
    }
}
