package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CardsStatistics extends ViewWithCardList {
    private CardsStatisticsListener listener;

    @FXML
    Text firstText, secondText, thirdText;

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (CardsStatistics.CardsStatisticsListener) listener;
    }

    @Override
    protected void actionOnCardSelection() {
        listener.showCardStatistics();
    }

    public void setCardKnowledgeLevel(int knowledgeLevel) {
        String text = "Knowledge level: ";
        text += knowledgeLevel;
        firstText.setText(text);
    }

    public void setCardTimeSpent(long timeSpent) {
        String text = "Time spent: ";
        text += timeSpent;
        secondText.setText(text);
    }

    public void setCardProbability(Double probability) {
        String text = "Probability: ";
        text += probability;
        thirdText.setText(text);
    }

    public interface CardsStatisticsListener {
        void clickBack();
        void showCardStatistics();
    }
}
