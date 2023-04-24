package ulb.info307.g6.views;

import javafx.fxml.FXML;

public class CardsStatistics extends ViewWithCardList {
    private CardsStatisticsListener listener;
    @FXML
    private void clickBack() {
        listener.clickBack();
    }

    @FXML
    private void clickDeckStatistics() {
        listener.clickDeckStatistics();
    }

    @Override
    public void setListener(Object listener) {
        this.listener = (CardsStatistics.CardsStatisticsListener) listener;
    }

    @Override
    protected void actionOnCardSelection() {

    }

    public interface CardsStatisticsListener {
        void clickBack();
        void clickDeckStatistics();
    }
}
