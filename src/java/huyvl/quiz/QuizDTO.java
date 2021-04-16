/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.quiz;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author HUYVU
 */
public class QuizDTO implements Serializable{
    private String quizID;
    private String subjectID;
    private String email;
    private float total;
    private Timestamp createDate;

    public QuizDTO() {
    }

    public QuizDTO(String quizID, String subjectID, String email, float total, Timestamp createDate) {
        this.quizID = quizID;
        this.subjectID = subjectID;
        this.email = email;
        this.total = total;
        this.createDate = createDate;
    }
    
    

    public String getQuizID() {
        return quizID;
    }

    /**
     * @param quizID the quizID to set
     */
    public void setQuizID(String quizID) {
        this.quizID = quizID;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    
    
}
