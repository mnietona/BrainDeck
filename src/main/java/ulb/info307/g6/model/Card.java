package ulb.info307.g6.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

public class Card implements Serializable {
    @Id
    private NitriteId card_id;
    private String question;
    private String answer;


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
    public Card() {

    }
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
