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
            int nbrAleatoire = (int) (Math.random() * (answerParts.length));
            // Add answerParts[nbrAleatoire] if it is not the good answer and not already in badAnswer
            if (nbrAleatoire != goodAnswerIndex && !Arrays.asList(badAnswer).contains(answerParts[nbrAleatoire])) {
                badAnswer[i] = answerParts[nbrAleatoire];
                i++;
            }
        }
        return badAnswer;
    }

    @Override
    public String getAnswer() {
        return setGoodAnswer();
    }

    public static boolean isCardQCMType(Card card){
        String answer = card.getAnswer();
        return answer.contains("{") && answer.contains("}");
    }

    public String[] choiceAnswer(){
        String[] choiceAnswer = new String[4];
        choiceAnswer[0] = getAnswer();
        String[] badAnswer = setBadAnswer();
        System.arraycopy(badAnswer, 0, choiceAnswer, 1, 3);
        // Melange la liste
        for (int i = 0; i < choiceAnswer.length; i++) {
            int randomIndexToSwap = (int) (Math.random() * choiceAnswer.length);
            String temp = choiceAnswer[randomIndexToSwap];
            choiceAnswer[randomIndexToSwap] = choiceAnswer[i];
            choiceAnswer[i] = temp;
        }

        return choiceAnswer;
    }
}
