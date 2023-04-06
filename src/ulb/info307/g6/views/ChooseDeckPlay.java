package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ulb.info307.g6.models.Deck;

public class ChooseDeckPlay {
    @FXML
    public ListView<Deck> deckList = new ListView();
    @FXML
    private Button buttonHome;
    @FXML
    public Pane cardBackground;
    @FXML
    public Text displayTextQA;
    @FXML
    private Button buttonFlipCard;
    @FXML
    private Button buttonNextCard;
    @FXML
    private Slider KnowledgeLvlSlider;

    @FXML
    public ComboBox<String> knowledgeLevel;  // TODO: te be removed, changed to slider

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
    protected void clickFlipCard() {
        listener.clickFlipCard();
    }

    public void deactivateSlider() {
        KnowledgeLvlSlider.setDisable(true);
        KnowledgeLvlSlider.setOpacity(0.5);
    }
    public void activateSlider() {
        KnowledgeLvlSlider.setDisable(false);
        KnowledgeLvlSlider.setOpacity(1);
    }

    public interface ChooseDeckPlayListener {
        void clickHome();
        void clickNextCard();
        void clickFlipCard();
    }
}
