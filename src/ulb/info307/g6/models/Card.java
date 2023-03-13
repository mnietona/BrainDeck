package ulb.info307.g6.models;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;
import java.util.Random;

public class Card implements Serializable {

    @Id
    private long id;
    private String question;
    private String answer;
    private int knowledgeLevel; // 5 levels: 0, 1, 2, 3, 4

    public long getId() {
        return id;
    }

    public void setId(long id) {this.id = id; }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }
    public void setKnowledgeLevel(int knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }
    public Card() {

    }
    public Card(String question, String answer) {
        this.id = new Random().nextLong();
        this.question = question;
        this.answer = answer;
        this.knowledgeLevel = 0; // 0 is the lowest level (doesn't know the card)
    }
}
