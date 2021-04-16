/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.subject;

import java.io.Serializable;

/**
 *
 * @author HUYVU
 */
public class SubjectDTO implements Serializable{
    private String subjectID;
    private String name;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String name) {
        this.subjectID = subjectID;
        this.name = name;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
