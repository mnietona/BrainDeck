package ulb.info307.g6.models;

import org.dizitart.no2.objects.Id;

import java.io.Serializable;
import java.util.Random;

public class Card implements Serializable {

    @Id
    private long id;
    private String res;
    private String answer;
    private Double knowledgeLevel; // 5 levels: 0, 1, 2, 3, 4 from bad to good


    public Card() {
    }

    public Card(String question, String answer) {
        this.id = new Random().nextLong();
        this.res = question;
        this.answer = answer;
        this.knowledgeLevel = 0d; // 0 is the lowest level (doesn't know the card)
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {this.id = id; }

    public String getQuestion() {
        return res;
    }

    public void setQuestion(String question) {
        this.res = question;
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

    public Double getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(Double knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }

    public boolean isValid() {
        return res != null && answer != null && !res.isEmpty() && !answer.isEmpty();
    }

    public int getNumberOfFlips() {
        return 1;
    }



    public String toString() {
        String s = res;
        int length = s.length();
        s = s.substring(0, Math.min(length, 20));
        if (s.length() < length) s += "...";
        return s;
    }
}
