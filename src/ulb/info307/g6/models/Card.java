package ulb.info307.g6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dizitart.no2.objects.Id;
import java.io.Serializable;
import java.util.Random;

public class Card implements Serializable {
    @Id
    private final long id;
    private String question;
    private String answer;
    private Double probability;  // 5 levels: 0, 1, 2, 3, 4 from bad to good

    public Card() {
        this.id = new Random().nextLong();
        this.probability = null;
    }

    public Card(String question, String answer) {
        this.id = new Random().nextLong();
        this.question = question;
        this.answer = answer;
        this.probability = 1.0;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getNthFlippedAnswer(int i) {
        return getAnswer();
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @JsonIgnore
    public boolean isValid() {
        return question != null && answer != null && !question.isEmpty() && !answer.isEmpty();
    }

    @JsonIgnore
    public int getNumberOfFlips() {
        return 1;
    }

    @Override
    public String toString() {
        String s = question;
        int length = s.length();
        s = s.substring(0, Math.min(length, 20));
        if (s.length() < length) s += "...";
        return s;
    }
}
