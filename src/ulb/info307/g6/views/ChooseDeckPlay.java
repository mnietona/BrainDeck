package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import ulb.info307.g6.models.Deck;

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
    public ListView<Deck> listDecks = new ListView();
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






    public interface ChooseDeckPlayListener {
        void clickHome();
        void clickNextCard();
        void clickBackCard();
        void clickFlipCard();
        void clickKnowledgeLevel();
    }
}
