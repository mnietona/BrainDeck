package ulb.info307.g6.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.ChooseDeckPlay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ChooseDeckPlayController implements ChooseDeckPlay.ChooseDeckPlayListener {
    private final Stage stage;
    private final Listener listener;

    private DeckDaoNitriteImplementation database = new DeckDaoNitriteImplementation();
    private CardDaoNitriteImplementation databaseCard = new CardDaoNitriteImplementation();
    private List<Deck> decks;

    private int[] lastIndex = new int[2];
    private int cardIndex = 0;
    private Deck currentDeck = null;

    private ChooseDeckPlay chooseDeckPlay;

    public ChooseDeckPlayController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show() {
        chooseDeckPlay = new ChooseDeckPlay();
        chooseDeckPlay.setListener(this);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ulb/info307/g6/views/ChooseDeckPlay.fxml"));
            loader.load();
            chooseDeckPlay = loader.getController();
            chooseDeckPlay.setListener(this);
            Parent root = loader.getRoot();

            this.stage.setScene(new Scene(root, 600, 408));
            this.stage.setTitle("Study your decks");
            this.stage.show();
            showChoice();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void clickHome() {
        listener.clickHome();
        this.stage.hide();
    }

    public void showChoice() {
        setCardPackLists();
    }

    public void clickNextCard() {
        getNextRandomCard();
        updateDisplayArea("Question");
    }

    public void clickBackCard() {
        if (cardIndex > 0) {
            cardIndex--;
            updateDisplayArea("Question");
        }
    }

    public void clickFlipCard() {
        if (currentDeck != null) {
            if (chooseDeckPlay.displayTitle.getText().equals("Question")) {
                updateDisplayArea("  Answer");
            } else if (chooseDeckPlay.displayTitle.getText().equals("  Answer")) {
                updateDisplayArea("Question");
            }
        }
    }

    public void clickKnowledgeLevel() {
        if (currentDeck != null) {
            setChoice();
        }
    }

    public void updateKnowledgeLevel() {
        if (currentDeck.getCardList().size() > 0) {
            Card card = currentDeck.getCardList().get(cardIndex);
            if (chooseDeckPlay.knowledgeLevel.getValue().equals("Very bad")) {
                card.setKnowledgeLevel(0);
            } else if (chooseDeckPlay.knowledgeLevel.getValue().equals("Bad")) {
                card.setKnowledgeLevel(1);
            } else if (chooseDeckPlay.knowledgeLevel.getValue().equals("Average")) {
                card.setKnowledgeLevel(2);
            } else if (chooseDeckPlay.knowledgeLevel.getValue().equals("Good")) {
                card.setKnowledgeLevel(3);
            } else if (chooseDeckPlay.knowledgeLevel.getValue().equals("Very good")) {
                card.setKnowledgeLevel(4);
            }
            databaseCard.updateCard(card);
            database.updateDeck(currentDeck);
        }
    }

    private void setCardPackLists() {
        decks = database.getAllDecks();
        chooseDeckPlay.listDecks.getItems().clear();
        for (Deck deck : decks) {
            chooseDeckPlay.listDecks.getItems().add(deck.getName());
        }

        chooseDeckPlay.selectDeck.setOnAction(event -> {
            String selectedDeck = chooseDeckPlay.listDecks.getSelectionModel().getSelectedItem();
            for (Deck deck : decks) {
                if (deck.isDeck(selectedDeck)) {
                    currentDeck = deck;
                }
            }
            System.out.println("Selected Deck : " + selectedDeck);
            System.out.println(currentDeck.getCardList());
            cardIndex = 0;

            if (currentDeck.getCardList().size() > 0) {
                getNextRandomCard();
                updateDisplayArea("Question");
            } else {
                chooseDeckPlay.displayTextQA.setText("The deck " + currentDeck.getName() + " is empty");
            }
        });
    }

    private void getNextRandomCard() {

        Random rand = new Random();
        int random = rand.nextInt(currentDeck.getCardList().size());
        while (random == lastIndex[0] || random == lastIndex[1]) {
            random = rand.nextInt(currentDeck.getCardList().size());
        }
        cardIndex = random;
        lastIndex[1] = lastIndex[0];
        lastIndex[0] = random;

    }

    public void updateDisplayArea(String name) {
        if (currentDeck == null) {
            chooseDeckPlay.displayTitle.setText("");
            chooseDeckPlay.displayTextQA.setText("No deck selected");
        }else {
            Card card = currentDeck.getCardList().get(cardIndex);
            if (name.equals("Question")) {
                chooseDeckPlay.displayTitle.setText("Question");
                chooseDeckPlay.displayTextQA.setText(card.getQuestion());
            } else if (name.equals("  Answer")) {
                chooseDeckPlay.displayTitle.setText("  Answer");
                chooseDeckPlay.displayTextQA.setText(card.getAnswer());
            }
        }
    }

    public void setChoice() {
        chooseDeckPlay.knowledgeLevel.getItems().clear();
        ObservableList<String> options = FXCollections.observableArrayList("Very bad", "Bad", "Average", "Good", "Very good");
        for (String option : options) {
            chooseDeckPlay.knowledgeLevel.getItems().add(option);
        }
        chooseDeckPlay.knowledgeLevel.setOnAction(event -> {
            updateKnowledgeLevel();
        });
    }

    public interface Listener {
        void clickHome();
    }
}

