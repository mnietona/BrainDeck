package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;

import java.util.Base64;
import java.util.Objects;

public class StudyQCM extends ViewWithDeckList {
    @FXML
    private Label knowledgeLvlSliderTitle;
    @FXML
    private WebView cardWebView; //probleme a l'initialisation
    @FXML
    private Slider knowledgeLvlSlider;
    @FXML
    private Button buttonFlipCard, buttonNextCard;
    @FXML
    private WebView webViewUpLeft, webViewUpRight, webViewDownLeft, webViewDownRight;
    private StudyQCMListener listener;

    @Override
    public void setListener(Object listener) {
        this.listener = (StudyQCMListener) listener;
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

    @FXML
    protected void clickWebViewUpLeft() {
        listener.clickWebViewUpLeft();
    }
    @FXML
    protected void clickWebViewUpRight() {
        listener.clickWebViewUpRight();
    }
    @FXML
    protected void clickWebViewDownLeft() {
        listener.clickWebViewDownLeft();
    }
    @FXML
    protected void clickWebViewDownRight() {
        listener.clickWebViewDownRight();
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

    /**
     * Set the text of the card to base64 encoded text
     * @param text The text to set
     */
    private String get_page_url(String text) {
        String page_url = Objects.requireNonNull(getClass().getResource("showCard.html")).toExternalForm();//no need to handle exception since the html file is in the same package
        page_url += "?text=" + Base64.getUrlEncoder().encodeToString(text.getBytes()); // Encode the text in base64 to avoid problems with special characters
        return page_url;
    }

    public void flipCard(boolean isQuestion, String question, String answer) {
        if (isQuestion) {
            cardWebView.getEngine().load(get_page_url(question) + "&type=question");
        } else {
            cardWebView.getEngine().load(get_page_url(answer) + "&type=answer");
        }
    }


    public String get_choices(String[] choices)
    {
        return String.join(" ", choices);
    }

    public void flipCardMCQ(boolean isQuestion, String question, String[] choices, String answer) {
        if (isQuestion) {
            cardWebView.getEngine().load(get_page_url(question) + "&type=question&radioBoxValues="+get_choices(choices));
        } else {
            cardWebView.getEngine().load(get_page_url(answer) + "&type=answer");
        }
    }

    public void showEmptyDeck() {
        cardWebView.getEngine().load("showCard.html");
    }

    @Override
    protected void actionOnDeckSelection() {
        listener.deckSelected();
    }

    public interface StudyQCMListener {
        void clickHome();
        void clickNextCard();
        void clickFlipCard();
        void deckSelected();
        void clickWebViewUpLeft();
        void clickWebViewUpRight();
        void clickWebViewDownLeft();
        void clickWebViewDownRight();
    }
}
