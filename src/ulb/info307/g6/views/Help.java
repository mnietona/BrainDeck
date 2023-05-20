package ulb.info307.g6.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Help implements View {
    public HelpListener listener;

    @FXML
    private void clickHome() {
        listener.clickHome();
    }
    @FXML
    private void clickNext() {
        listener.clickHome();
    }
    @Override
    public void setListener(Object listener) {
        this.listener = (Help.HelpListener) listener;
    }



    public interface HelpListener {
        void clickHome();
        void clickNext();
    }
}
