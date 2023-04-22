package ulb.info307.g6.models;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: add a way to display the LaTeX formula in the question and answer text areas
public class CardLatex extends Card {
    private final String latexQuestion;
    private final String latexAnswer;

    public CardLatex(String questionInput, String answerInput) {
        super(questionInput, answerInput);
        this.latexQuestion = questionInput;
        this.latexAnswer = answerInput;
    }


    @Override
    public String getQuestion() {
        return latexQuestion;
    }

    @Override
    public String getAnswer() {
        return latexAnswer;
    }

    private List<Object> parseLaTeX(String input) {
        // This method parses a string containing LaTeX formulas and returns a list of objects.

        List<Object> output = new ArrayList<>();
        Pattern latexPattern = Pattern.compile("\\\\[(]?[a-zA-Z]*[^a-zA-Z]*[)]?");
        Matcher matcher = latexPattern.matcher(input);

        int lastIndex = 0;
        while (matcher.find()) {
            if (lastIndex < matcher.start()) {
                output.add(input.substring(lastIndex, matcher.start()));
            }

            TeXFormula formula = new TeXFormula(matcher.group());
            TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
            output.add(icon);

            lastIndex = matcher.end();
        }

        if (lastIndex < input.length()) {
            output.add(input.substring(lastIndex));
        }

        return output;
    }

    public List<Object> getParsedContent(boolean isQuestion) {
        if (isQuestion) {
            return parseLaTeX(latexQuestion);
        } else {
            return parseLaTeX(latexAnswer);
        }
    }
}

