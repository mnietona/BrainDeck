package ulb.info307.g6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.DeckProbabilities;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.EditDeck;
import ulb.info307.g6.views.Popup;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Controller for the EditDeck view.
 * This controller is used to edit a deck.
 * It allows the user to import a deck from a file, export a deck to a file, edit a deck, create a deck and remove a deck.
 */

public class EditDeckController extends ControllerWithDeckList implements EditDeck.EditDeckListener {
    private final EditDeck editDeckView;

    public EditDeckController(Stage stage) {
        super(stage, "/ulb/info307/g6/views/EditDeck.fxml", "Edit Deck");
        editDeckView = (EditDeck) view;
        editDeckView.activateActionButtons(false);
    }

    @Override
    public void deckSelected(){
        editDeckView.updateDeckTitle();
        editDeckView.activateActionButtons(true);
    }

    @Override
    public void clickImport() {
        try {
            File selectedFile = selectFile();
            if (selectedFile == null) {
                throw new Exception("No file selected.");
            }
            String fileContent = readFileContent(selectedFile);
            if (fileContent == null) {
                throw new Exception("Cannot read file content.");
            }
            Deck d = importDeck(fileContent);
            if (d == null) {
                throw new Exception("Cannot import deck from file content.");
            }
            int amountOfEmptyProbaCards = countEmptyProbabilityCards(d);
            setCardProbabilities(d, amountOfEmptyProbaCards);
            checkAndUpdateDeckInDatabase(d);
            setDeckList();
        } catch (Exception e) {
            new Popup(e.getMessage()).showAndWait();
        }
    }

    private File selectFile() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(stage);
    }

    private String readFileContent(File selectedFile) {
        StringBuilder fileContent = new StringBuilder();
        try {
            Scanner s = new Scanner(selectedFile);
            while (s.hasNextLine()) {
                fileContent.append(s.nextLine());
            }
            s.close();
            return fileContent.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private Deck importDeck(String fileContent) {
        ObjectMapper mapper = new ObjectMapper();
        Deck d;
        try {
            d = mapper.readValue(fileContent, Deck.class);
            return d;
        } catch (IOException e) {
            return null;
        }
    }

    private int countEmptyProbabilityCards(Deck deck) {
        int amountOfEmptyProbaCards = 0;
        Iterator<Card> cardIterator = deck.getCardIterator();
        while (cardIterator.hasNext()) {
            Card c = cardIterator.next();
            if (c.getProbability() == null) {
                amountOfEmptyProbaCards++;
            }
        }
        return amountOfEmptyProbaCards;
    }

    private void setCardProbabilities(Deck deck, int amountOfEmptyProbaCards) {
        DeckProbabilities deckProbabilities = new DeckProbabilities(deck);
        if (amountOfEmptyProbaCards >= 1) {
            Iterator<Card> cardIterator = deck.getCardIterator();
            while (cardIterator.hasNext()) {
                Card c = cardIterator.next();
                if (c.getProbability() == null) {
                    if (deck.getSize() == amountOfEmptyProbaCards) {
                        c.setProbability(1.0);
                    } else {
                        c.setProbability(1.0 / (deck.getSize() - amountOfEmptyProbaCards));
                    }
                }
            }
            deckProbabilities.normalizeProbability();
        }
        if (deckProbabilities.isNotNormalized()) {
            deckProbabilities.initDeckProbabilities();
            System.out.println("Probabilities were not good so we reset them for you");
        }
    }

    private void checkAndUpdateDeckInDatabase(Deck d) {
        if (deckDatabase.getDeckById(d.getId()) != null) {
            deckDatabase.updateDeck(d);
        } else {
            deckDatabase.addDeck(d);
        }
    }

    @Override
    public void clickExport() {
        try {
            Deck selectedDeck = editDeckView.getSelectedDeck();
            if (selectedDeck == null) {
                throw new Exception("No deck selected.");
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(selectedDeck.getName()+"_export.json");
            ObjectMapper mapper = new ObjectMapper();
            String deckContent = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(selectedDeck);
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile == null) {
                throw new Exception("No file selected for saving.");
            }
            FileWriter fileWriter = new FileWriter(selectedFile);
            fileWriter.write(deckContent);
            fileWriter.close();
        } catch (Exception e) {
            new Popup(e.getMessage()).showAndWait();
        }
    }


    @Override
    public void clickHome() {
        new WelcomeController(stage);
    }

    @Override
    public void clickEdit() {
        if (editDeckView.isDeckSelected()) {
            new EditCardController(stage, editDeckView.getSelectedDeck());
        }
    }

    @Override
    public void clickCreate() {
        new CreateDeckController(stage);
    }

    @Override
    public void clickRemove() {
        if (editDeckView.isDeckSelected()) {
            deckDatabase.deleteDeck(editDeckView.getSelectedDeck());
            deckDatabase.updateDeck(editDeckView.getSelectedDeck());
            setDeckList();
            editDeckView.updateDeckTitle();
            editDeckView.activateActionButtons(false);
        }
    }

    @Override
    public void clickResetProba() {
        if (editDeckView.isDeckSelected()) {
            DeckProbabilities deckProbabilities = new DeckProbabilities(editDeckView.getSelectedDeck());
            deckProbabilities.resetProbability();
            deckDatabase.updateDeck(editDeckView.getSelectedDeck());
            deckProbabilities.printProbability();
        }
    }
}