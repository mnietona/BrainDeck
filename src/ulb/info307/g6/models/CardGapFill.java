package ulb.info307.g6.models;


public class CardGapFill extends Card {
    public final static String QUESTION_SEPARATOR = "_"; // Arbitrary choice of separator, could be anything
    private final String[] questionParts;
    private final String[] answerParts;


    public CardGapFill(String questionInput, String answerInput) {
        super(questionInput + " ", answerInput);
        questionInput = questionInput + " "; // Add a space at the end to avoid a bug when the last part of the question is a gap
        questionParts = questionInput.split(QUESTION_SEPARATOR);
        String ANSWER_SEPARATOR = ", "; // Arbitrary choice of separator, could be anything
        answerParts = answerInput.split(ANSWER_SEPARATOR);
    }

    public static boolean isCardGapFilType(Card card) {
        return card.getQuestion().contains(QUESTION_SEPARATOR);
    }

    @Override
    public boolean isValid() {
        return super.isValid() && questionParts.length == answerParts.length + 1; // Number of question parts must be equal to number of answer parts + 1
    }

    @Override
    public String getQuestion() {
        StringBuilder question = new StringBuilder();
        for (int i = 0; i < questionParts.length; i++) { // Reconstruction of the question as a String with the answers hidden
            question.append(questionParts[i]);
            if (i < answerParts.length) {
                question.append(QUESTION_SEPARATOR.repeat(answerParts[i].length())); // Replace the answer part by the same number of letters as the answer part
            }
        }
        return question.toString();
    }

    @Override
    public int getNumberOfFlips() {
        return answerParts.length;
    }

    @Override
    public String getNthFlippedAnswer(int n) {
        if (n > getNumberOfFlips()) {
            return "INCORRECT LENGTH NUMBER OF ANSWERS ASKED";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < questionParts.length; i++) {
            res.append(questionParts[i]);
            if (i < n) {
                res.append(answerParts[i]);
            }
            else if (i < questionParts.length - 1) {
                res.append(QUESTION_SEPARATOR.repeat(answerParts[i].length())); // Replace the answer part by the same number of letters as the answer part
            }
        }
        return res.toString();
    }
}

