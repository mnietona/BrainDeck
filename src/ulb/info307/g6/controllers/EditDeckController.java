package ulb.info307.g6.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ulb.info307.g6.models.Card;
import ulb.info307.g6.models.CardProbabilities;
import ulb.info307.g6.models.Deck;
import ulb.info307.g6.views.EditDeck;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        File selectedFile = selectFile();
        String fileContent = readFileContent(selectedFile);
        Deck d = importDeck(fileContent);
        checkEmptyDeck(d);
        int amountOfEmptyProbaCards = countEmptyProbabilityCards(d);
        setCardProbabilities(d, amountOfEmptyProbaCards);
        checkAndUpdateDeckInDatabase(d);
        setDeckList();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    private Deck importDeck(String fileContent) {
        ObjectMapper mapper = new ObjectMapper();
        Deck d = null;
        try {
            d = mapper.readValue(fileContent, Deck.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    private void checkEmptyDeck(Deck d) {
        if (d.isEmpty()) {
            throw new IllegalArgumentException("Cannot import empty deck");
        }
    }

    private int countEmptyProbabilityCards(Deck d) {
        int amountOfEmptyProbaCards = 0;
        for (Card c : d.getCardList()) {
            if (c.getProbability() == null) {
                amountOfEmptyProbaCards++;
            }
        }
        return amountOfEmptyProbaCards;
    }

    private void setCardProbabilities(Deck d, int amountOfEmptyProbaCards) {
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
        if (CardProbabilities.isNotNormalized(d)) {
            CardProbabilities.initCardProbabilities(d);
            System.out.println("Probabilities were not good so we reset them for you");
        }
    }

    private void checkAndUpdateDeckInDatabase(Deck d) {
        if (database.getDeckById(d.getId()) != null) {
            database.updateDeck(d);
        } else {
            database.addDeck(d);
        }
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