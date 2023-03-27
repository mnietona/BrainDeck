package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class ChooseDeckPlay {

    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonBackCard;
    @FXML
    private Button buttonFlipCard;
    @FXML
    private Button buttonNextCard;
    @FXML
    public ListView<String> listDecks = new ListView();
    @FXML
    public Button selectDeck;
    @FXML
    public Text displayTitle;
    @FXML
    public Text displayTextQA;
    @FXML
    public ComboBox<String> knowledgeLevel;

    private ChooseDeckPlayListener listener;

    public void setListener(ChooseDeckPlayListener listener) {
        this.listener = listener;
    }

    public void clickHome() {
        listener.clickHome();
    }

    @FXML
    protected void clickNextCard() {
        listener.clickNextCard();
    }

    @FXML
    protected void clickBackCard() {
        listener.clickBackCard();
    }

    @FXML
    protected void clickFlipCard() {
        listener.clickFlipCard();
    }

    @FXML
    protected void clickKnowledgeLevel() {
        listener.clickKnowledgeLevel();
    }



    public void updateDisplayArea(String name) {
        listener.updateDisplayArea(name);
    }

    public void showChoice() {
        listener.showChoice();
    }

    public void setChoice() {
        listener.setChoice();
    }

    public void updateKnowledgeLevel() {
        listener.updateKnowledgeLevel();
    }


    public interface ChooseDeckPlayListener {
        void clickHome();
        void updateDisplayArea(String name);
        void showChoice();
        void clickNextCard();
        void clickBackCard();
        void clickFlipCard();
        void clickKnowledgeLevel();
        void updateKnowledgeLevel();
        void setChoice();
    }
}
