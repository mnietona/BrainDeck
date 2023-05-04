package ulb.info307.g6.models;

import java.util.Arrays;

public class CardQCM extends Card{

    private  final String[] answerParts;

    private String [] badAnswer;

    private String goodAnswer;


    public CardQCM(String questionInput, String answerInput) {
        super(questionInput, answerInput);
        String ANSWER_SEPARATOR = ",";  // Arbitrary choice of separator, could be anything$
        answerParts = answerInput.split(ANSWER_SEPARATOR);


    }

    private String setGoodAnswer() {

        for(int i = 0; i < answerParts.length; i++){
            if(answerParts[i].contains("{") && answerParts[i].contains("}")){

                goodAnswer = answerParts[i];

                goodAnswer= goodAnswer.replace("{", "").replace("}", "");
                // removes the good answer from the array
                answerParts[i] = null;
                return goodAnswer;
            }
        }

        return null;
    }

    public String getGoodAnswer(){
        return goodAnswer;
    }
    public String[] getBadAnswer(){
        return badAnswer;
    }
    private void setBadAnswer(){

        int i = 0;
        while(badAnswer.length < 3){

            int nbrAleatoire = (int) (Math.random() * (answerParts.length ));
            if(answerParts[nbrAleatoire] != null && !Arrays.asList(badAnswer).contains(answerParts[nbrAleatoire])){
                badAnswer[i] = answerParts[nbrAleatoire];
                i++;

            }
        }
    }


}
