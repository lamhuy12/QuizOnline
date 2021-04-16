/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.choice;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class ChoiceDTO implements Serializable{
    private String questionID;
    private String choiceID;
    private String subjectID;
    private String answer;

    public ChoiceDTO() {
    }

    public ChoiceDTO(String questionID, String choiceID, String subjectID, String answer) {
        this.questionID = questionID;
        this.choiceID = choiceID;
        this.subjectID = subjectID;
        this.answer = answer;
    }

    /**
     * @return the questionID
     */
    public String getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the choiceID
     */
    public String getChoiceID() {
        return choiceID;
    }

    /**
     * @param choiceID the choiceID to set
     */
    public void setChoiceID(String choiceID) {
        this.choiceID = choiceID;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
}
