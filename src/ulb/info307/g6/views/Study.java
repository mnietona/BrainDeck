package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import ulb.info307.g6.models.Deck;

public class Study implements View {
    @FXML
    public ListView<Deck> deckList = new ListView();
    @FXML
    private Button buttonHome;
    @FXML
    public Pane cardBackground;
    @FXML
    public Text displayTextQA;
    @FXML
    private Label knowledgeLvlSliderTitle;
    @FXML
    private Slider knowledgeLvlSlider;
    @FXML
    private Button buttonFlipCard, buttonNextCard;
    private static final String
            BACKGROUND_ANSWER = "-fx-background-color: #FFA07A; -fx-background-radius: 10px",
            BACKGROUND_QUESTION = "-fx-background-color: #ADD8E6; -fx-background-radius: 10px";
    private StudyListener listener;

    @Override
    public void setListener(Object listener) {
        this.listener = (StudyListener) listener;
    }

    @FXML
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
        knowledgeLvlSlider.setDisable(true);
        knowledgeLvlSliderTitle.setDisable(true);
    }

    public void activateSlider() {
        knowledgeLvlSlider.setDisable(false);
        knowledgeLvlSliderTitle.setDisable(false);
    }

    public void activateActionButtons() {
        buttonFlipCard.setDisable(false);
        buttonNextCard.setDisable(false);
    }

    public void setSliderLabels() {
        knowledgeLvlSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n == 0) {
                    return "Very bad";
                } else if (n == 1) {
                    return "Bad";
                } else if (n == 2) {
                    return "Average";
                } else if (n == 3) {
                    return "Good";
                } else if (n == 4) {
                    return "Very good";
                }
                return "";
            }
            @Override
            public Double fromString(String s) {
                return switch (s) {
                    case "Very bad" -> 0d;
                    case "Bad" -> 1d;
                    case "Average" -> 2d;
                    case "Good" -> 3d;
                    case "Very good" -> 4d;
                    default -> null;
                };
            }
        });
    }

    public Double getSelectedKnowledgeLvl() {
        return knowledgeLvlSlider.getValue();
    }

    public void setSliderLvl(Double lvl) {
        knowledgeLvlSlider.setValue(lvl);
    }

    public void setCardText(String text) {
        displayTextQA.setText(text);
    }

    public void flipCard(boolean isQuestion, String question, String answer) {
        if (isQuestion) {
            cardBackground.setStyle(BACKGROUND_QUESTION);
            setCardText(question);
        } else {
            cardBackground.setStyle(BACKGROUND_ANSWER);
            setCardText(answer);
        }
    }

    public interface StudyListener {
        void clickHome();
        void clickNextCard();
        void clickFlipCard();
    }
}
