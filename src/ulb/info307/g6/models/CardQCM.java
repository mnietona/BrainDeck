package ulb.info307.g6.models;

import java.util.Arrays;

public class CardQCM extends Card {

    private final String[] answerParts;
    private int goodAnswerIndex;  // New variable to store index of good answer

    public CardQCM(String questionInput, String answerInput) {
        super(questionInput, answerInput);
        String ANSWER_SEPARATOR = ",";  // Arbitrary choice of separator, could be anything
        answerParts = answerInput.split(ANSWER_SEPARATOR);
        goodAnswerIndex = -1;  // Initialize to invalid value
        for(int i = 0; i < answerParts.length; i++) {
            answerParts[i] = convertLatex(answerParts[i]);
        }
    }
    private String convertLatex(String input) {
        return input.replaceAll("\\$\\$(.*?)\\$\\$", "\\\\($1\\\\)");
    }

    private String setGoodAnswer() {
        for(int i = 0; i < answerParts.length; i++){
            if(answerParts[i].contains("{") && answerParts[i].contains("}")){
                String goodAnswer = answerParts[i];
                goodAnswer = goodAnswer.replace("{", "").replace("}", "");
                goodAnswerIndex = i;  // Store index of good answer
                return goodAnswer;
            }
        }
        return null;
    }

    private String[] setBadAnswer() {
        String[] badAnswer = new String[3];
        int i = 0;
        while (i < 3) {
            int randomNumber = (int) (Math.random() * (answerParts.length));
            // Add answerParts[randomNumber] if it is not the good answer and not already in badAnswer
            if (randomNumber != goodAnswerIndex && !Arrays.asList(badAnswer).contains(answerParts[randomNumber])) {
                badAnswer[i] = answerParts[randomNumber];
                i++;
            }
        }
        return badAnswer;
    }

    @Override
    public String getAnswer() {
        return setGoodAnswer();
    }



    public String[] choiceAnswer(){
        String[] choiceAnswer = new String[4];
        choiceAnswer[0] = getAnswer();
        String[] badAnswer = setBadAnswer();
        System.arraycopy(badAnswer, 0, choiceAnswer, 1, 3);
        // Shuffle array
        for (int i = 0; i < choiceAnswer.length; i++) {
            int randomIndexToSwap = (int) (Math.random() * choiceAnswer.length);
            String temp = choiceAnswer[randomIndexToSwap];
            choiceAnswer[randomIndexToSwap] = choiceAnswer[i];
            choiceAnswer[i] = temp;
        }

        return choiceAnswer;
    }

    @Override
    public boolean isValid() {
        boolean isValid = false;
        for (String answerPart : answerParts) {
            if (answerPart.contains("{") && answerPart.contains("}")) {
                isValid = true;
                break;
            }
        }
        return super.isValid() && answerParts.length >= 4 && isValid;
    }
}
