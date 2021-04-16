/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.question;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author HUYVU
 */
public class QuestionDTO implements Serializable {

    private String questionID;
    private String questionName;
    private boolean status;
    private String correctAnswer;
    private Timestamp dateCreate;
    private String subjectID;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionID, String questionName, boolean status, String correctAnswer, Timestamp dateCreate, String subjectID) {
        this.questionID = questionID;
        this.questionName = questionName;
        this.status = status;
        this.correctAnswer = correctAnswer;
        this.dateCreate = dateCreate;
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



    
}
