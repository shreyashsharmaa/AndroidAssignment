package com.example.shreyash.quizzler;

/**
 * Created by shreyash on 04/10/18.
 */

public class Results {
    private String category;

    private String correct_answer;

    private String difficulty;

    private String question;

    private String type;

    private String[] incorrect_answers;

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getCorrect_answer ()
    {
        return correct_answer;
    }

    public void setCorrect_answer (String correct_answer)
    {
        this.correct_answer = correct_answer;
    }

    public String getDifficulty ()
    {
        return difficulty;
    }

    public void setDifficulty (String difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String[] getIncorrect_answers ()
    {
        return incorrect_answers;
    }

    public void setIncorrect_answers (String[] incorrect_answers)
    {
        this.incorrect_answers = incorrect_answers;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [category = "+category+", correct_answer = "+correct_answer+", difficulty = "+difficulty+", question = "+question+", type = "+type+", incorrect_answers = "+incorrect_answers+"]";
    }
}
