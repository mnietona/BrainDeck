package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * View controller of the Study menu, implements View interface and is the controller for the Study.fxml file.
 * Contains :
 * - a ListView to select a deck
 * - a home button, a button to flip the card, and a button to go to the next card
 * - a slider to set the knowledge level of the card (and chage it's probability of occurence)
 */
public class Study extends ViewWithDeckList {
    @FXML
    private Pane cardBackground;
    @FXML
    private Text cardText;
    @FXML
    private Label knowledgeLvlSliderTitle;
    @FXML
    private Slider knowledgeLvlSlider;
    @FXML
    private Button buttonFlipCard, buttonNextCard;
    private static final String
            ANSWER_BACKGROUND = "-fx-background-color: #FFA07A; -fx-background-radius: 10px",
            QUESTION_BACKGROUND = "-fx-background-color: #ADD8E6; -fx-background-radius: 10px",
            CLEAR_BACKGROUND = "-fx-background-color: transparent; ";
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

    public void activateSlider(boolean activate) {
        knowledgeLvlSlider.setDisable(!activate);
        knowledgeLvlSliderTitle.setDisable(!activate);
    }

    public void activateActionButtons(boolean activate) {
        buttonFlipCard.setDisable(!activate);
        buttonNextCard.setDisable(!activate);
    }

    public void setSliderLabels() {
        knowledgeLvlSlider.setValue(2);  // Set the default value to 2 (Middle of the slider)
        knowledgeLvlSlider.setLabelFormatter(new StringConverter<>() {
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

    public int getSelectedKnowledgeLvl() {
        return (int) knowledgeLvlSlider.getValue();
    }

    public void setSliderLvl(Double lvl) {
        knowledgeLvlSlider.setValue(lvl);
    }

    public void flipCard(boolean isQuestion, String question, String answer) {
        if (isQuestion) {
            cardBackground.setStyle(QUESTION_BACKGROUND);
            cardText.setText(question);
        } else {
            cardBackground.setStyle(ANSWER_BACKGROUND);
            cardText.setText(answer);
        }
    }

    public void showEmptyDeck(String text) {
        cardBackground.setStyle(CLEAR_BACKGROUND);
        cardText.setText(text);
    }

    @Override
    protected void actionOnDeckSelection() {
        listener.deckSelected();
    }

    public interface StudyListener {
        void clickHome();
        void clickNextCard();
        void clickFlipCard();
        void deckSelected();
    }
}
