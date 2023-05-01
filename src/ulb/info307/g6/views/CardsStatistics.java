package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CardsStatistics extends ViewWithCardList {
    private CardsStatisticsListener listener;
    @FXML
    Text cardStat1, cardStat2, cardStat3, cardStat4;

    @Override
    public void setListener(Object listener) {
        this.listener = (CardsStatistics.CardsStatisticsListener) listener;
    }

    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @Override
    protected void actionOnCardSelection() {
        listener.showCardStatistics();
    }

    public void setCardKnowledgeLevel(int knowledgeLevel) {
        String text = "Knowledge level: ";
        text += knowledgeLevel;
        cardStat1.setText(text);
    }

    public void setCardTimeSpent(long timeSpent) {
        String text = "Time spent: ";
        text += timeSpent;
        cardStat2.setText(text);
    }

    public void setCardProbability(Double probability) {
        String text = "Probability: ";
        text += probability;
        cardStat3.setText(text);
    }

    public void setCardNumberOfAppearances(int numberOfAppearances) {
        String text = "Number of appearances: ";
        text += numberOfAppearances;
        cardStat4.setText(text);
    }

    public interface CardsStatisticsListener {
        void clickBack();
        void showCardStatistics();
    }
}
