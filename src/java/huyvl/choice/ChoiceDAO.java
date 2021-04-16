/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.choice;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class ChoiceDAO implements Serializable {

    List<ChoiceDTO> listAnswer;

    public List<ChoiceDTO> getListAnswer() {
        return listAnswer;
    }

    public void getAnswer(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select Answer, ChoiceID, SubjectID \n"
                        + "from Choice\n"
                        + "where QuestionID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String answer = rs.getString("Answer");
                    String choiceID = rs.getString("ChoiceID");
                    String subjectID = rs.getString("SubjectID");
                    ChoiceDTO dto = new ChoiceDTO(questionID, choiceID, subjectID, answer);
                    if (this.listAnswer == null) {
                        this.listAnswer = new ArrayList<>();
                    }
                    this.listAnswer.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean createChoice(String questionID, String choiceID, String answer, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Choice(QuestionID, ChoiceID, SubjectID, Answer) "
                        + "values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.setString(2, choiceID);
                stm.setString(3, subjectID);
                stm.setString(4, answer);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean UpdateChoice(String questionID, String choiceId, String answer, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Choice "
                        + "set SubjectID =?, Answer =? "
                        + "where QuestionID =? and ChoiceID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                stm.setString(2, answer);
                stm.setString(3, questionID);
                stm.setString(4, choiceId);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    List<ChoiceDTO> quizAnswer;

    public List<ChoiceDTO> getQuizAnswer() {
        return quizAnswer;
    }

    public void searchQuizAnswer(String questionID, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, ChoiceID, Answer\n"
                        + "from Choice \n"
                        + "where QuestionID = ? and SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.setString(2, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String answer = rs.getString("Answer");
                    String choiceID = rs.getString("ChoiceID");
                    ChoiceDTO dto = new ChoiceDTO(questionID, choiceID, subjectID, answer);
                    if (this.quizAnswer == null) {
                        this.quizAnswer = new ArrayList<>();
                    }
                    this.quizAnswer.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    List<ChoiceDTO> quizQuestionID;

    public List<ChoiceDTO> getQuizQuestionID() {
        return quizQuestionID;
    }
    
    public void searchQuizQuestionID(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, ChoiceID, Answer\n"
                        + "                        from Choice \n"
                        + "                        where ChoiceID = 'A' and SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String answer = rs.getString("Answer");
                    String choiceID = rs.getString("ChoiceID");
                    ChoiceDTO dto = new ChoiceDTO(questionID, choiceID, subjectID, answer);
                    if (this.quizQuestionID == null) {
                        this.quizQuestionID = new ArrayList<>();
                    }
                    this.quizQuestionID.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
