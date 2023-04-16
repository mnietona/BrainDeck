package ulb.info307.g6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardProbabilities;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.EditDeck;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class EditDeckController extends ControllerWithDeckList implements EditDeck.EditDeckListener {
    private EditDeck editDeckView;

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
        FileChooser fileChooser = new FileChooser();
        File selectedFile =  fileChooser.showOpenDialog(stage);
        try {
            String fileContent = "";
            Scanner s = new Scanner(selectedFile);
            while (s.hasNextLine()) {
                fileContent += s.nextLine();
            }
            ObjectMapper mapper = new ObjectMapper();
            Deck d = mapper.readValue(fileContent, Deck.class);
            if (d.isEmpty()) {
                throw new IllegalArgumentException("Cannot import empty deck");
            }
            int amountOfEmptyProbaCards = 0;
            for (Card c : d.getCardList()) {
                if (c.getProbability() == null) {
                    amountOfEmptyProbaCards++;
                }
            }
            if (amountOfEmptyProbaCards >= 1) {
                for (Card c : d.getCardList()) {
                    if (c.getProbability() == null) {
                        if (d.getSize() == amountOfEmptyProbaCards) {
                            c.setProbability(1.0);
                        } else {
                            c.setProbability(1.0 / (d.getSize() - amountOfEmptyProbaCards));
                        }
                    }
                }
                CardProbabilities.normalizeProbability(d);
            }
            if (!CardProbabilities.isNormalized(d)) {
                CardProbabilities.initCardProbabilities(d);
                System.out.println("Probabilities were not good so we reset them for you");
            }
            if (database.getDeckById(d.getId()) != null) {
                database.updateDeck(d);
            } else {
                database.addDeck(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDeckList();
    }

    @Override
    public void clickExport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(editDeckView.getSelectedDeck().getName()+"_export.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String deckContent = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(editDeckView.getSelectedDeck());
            File selectedFile = fileChooser.showSaveDialog(stage);
            FileWriter fileWriter = new FileWriter(selectedFile);
            fileWriter.write(deckContent);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
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
            database.deleteDeck(editDeckView.getSelectedDeck());
            database.updateDeck(editDeckView.getSelectedDeck());
            setDeckList();
            editDeckView.updateDeckTitle();
            editDeckView.activateActionButtons(false);
        }
    }

    @Override
    public void clickResetProba() {
        if (editDeckView.isDeckSelected()) {
            CardProbabilities.resetProbability(editDeckView.getSelectedDeck());
            database.updateDeck(editDeckView.getSelectedDeck());
            CardProbabilities.printProbability(editDeckView.getSelectedDeck());
        }
    }
}