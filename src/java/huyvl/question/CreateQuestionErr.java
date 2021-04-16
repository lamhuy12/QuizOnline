/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.question;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class CreateQuestionErr implements Serializable{
    private String questionIDLengthErr;
    private String questionNameLengthErr;
    private String answerLengthErr;
    private String questionIDIsExist;

    public CreateQuestionErr() {
    }

    public CreateQuestionErr(String questionIDLengthErr, String questionNameLengthErr, String answerLengthErr, String questionIDIsExist) {
        this.questionIDLengthErr = questionIDLengthErr;
        this.questionNameLengthErr = questionNameLengthErr;
        this.answerLengthErr = answerLengthErr;
        this.questionIDIsExist = questionIDIsExist;
    }

    /**
     * @return the questionIDLengthErr
     */
    public String getQuestionIDLengthErr() {
        return questionIDLengthErr;
    }

    /**
     * @param questionIDLengthErr the questionIDLengthErr to set
     */
    public void setQuestionIDLengthErr(String questionIDLengthErr) {
        this.questionIDLengthErr = questionIDLengthErr;
    }

    /**
     * @return the questionNameLengthErr
     */
    public String getQuestionNameLengthErr() {
        return questionNameLengthErr;
    }

    /**
     * @param questionNameLengthErr the questionNameLengthErr to set
     */
    public void setQuestionNameLengthErr(String questionNameLengthErr) {
        this.questionNameLengthErr = questionNameLengthErr;
    }

    /**
     * @return the answerLengthErr
     */
    public String getAnswerLengthErr() {
        return answerLengthErr;
    }

    /**
     * @param answerLengthErr the answerLengthErr to set
     */
    public void setAnswerLengthErr(String answerLengthErr) {
        this.answerLengthErr = answerLengthErr;
    }

    /**
     * @return the questionIDIsExist
     */
    public String getQuestionIDIsExist() {
        return questionIDIsExist;
    }

    /**
     * @param questionIDIsExist the questionIDIsExist to set
     */
    public void setQuestionIDIsExist(String questionIDIsExist) {
        this.questionIDIsExist = questionIDIsExist;
    }
    
    
}
