/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.question;

import huyvl.choice.ChoiceDTO;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author HUYVU
 */
public class QuestionAnswerDTO implements Serializable {

    private String subjectID;
    private String questionID;
    private String questionName;
    private String answer;
    private String correctAnswer;
    private boolean status;
    private Timestamp dateCreate;
    private List<ChoiceDTO> listChoice;

    public QuestionAnswerDTO() {
    }

    public QuestionAnswerDTO(String subjectID, String questionID, String questionName, String answer, String correctAnswer, boolean status, Timestamp dateCreate) {
        this.subjectID = subjectID;
        this.questionID = questionID;
        this.questionName = questionName;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.status = status;
        this.dateCreate = dateCreate;
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
     * @return the questionName
     */
    public String getQuestionName() {
        return questionName;
    }

    /**
     * @param questionName the questionName to set
     */
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
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

    /**
     * @return the correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param correctAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the dateCreate
     */
    public Timestamp getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }
    
    

}
