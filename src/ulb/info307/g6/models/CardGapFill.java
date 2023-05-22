package ulb.info307.g6.models;

/**
 * Model class for a card.
 * The CardGapFill class is a model representing a card used in an application.
 * It extends the Card class and introduces specific features for gaped questions.
 * It divides the question and answer into parts using separators and provides methods for validating the card,
 * reconstructing the question with hidden answers, and retrieving flipped versions of the question with a specified number of revealed answer parts.
 */
public class CardGapFill extends Card {
    public final static String QUESTION_SEPARATOR = "_";  // Arbitrary choice of separator, could be anything
    private final String[] questionParts;
    private final String[] answerParts;

    public CardGapFill(String questionInput, String answerInput) {
        super(questionInput + " ", answerInput);
        questionInput = questionInput + " ";  // Add a space at the end to avoid a bug when the last part of the question is a gap
        questionParts = questionInput.split(QUESTION_SEPARATOR);
        String ANSWER_SEPARATOR = ", ";  // Arbitrary choice of separator, could be anything
        answerParts = answerInput.split(ANSWER_SEPARATOR);
    }

    public static boolean isCardGapFilType(Card card) {
        String question = card.getQuestion();
        return question.contains(QUESTION_SEPARATOR);
    }

    @Override
    public boolean isValid() {
        return super.isValid() && questionParts.length == answerParts.length + 1;  // Number of question parts must be equal to number of answer parts + 1
    }

    @Override
    public String getQuestion() {
        StringBuilder question = new StringBuilder();
        for (int i = 0; i < questionParts.length; i++) {  // Reconstruction of the question as a String with the answers hidden
            question.append(questionParts[i]);
            if (i < answerParts.length) {
                question.append(QUESTION_SEPARATOR.repeat(answerParts[i].length()));  // Replace the answer part by the same number of letters as the answer part
            }
        }
        return question.toString();
    }

    @Override
    public int getMaxNumberOfFlips() {
        return answerParts.length;
    }

    @Override
    public String getNthFlippedAnswer(int numberOfFlips) {
        try {
            assert (numberOfFlips <= getMaxNumberOfFlips());
        } catch (AssertionError e) {
            return "INCORRECT NUMBER OF FLIPS";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < questionParts.length; i++) {
            res.append(questionParts[i]);
            if (i < numberOfFlips) {
                res.append(answerParts[i]);
            }
            else if (i < questionParts.length - 1) {
                res.append(QUESTION_SEPARATOR.repeat(answerParts[i].length()));  // Replace the answer part by the same number of letters as the answer part
            }
        }
        return res.toString();
    }
}
