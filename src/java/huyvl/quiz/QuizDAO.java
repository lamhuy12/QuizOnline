/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.quiz;

import huyvl.question.QuestionDTO;
import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author HUYVU
 */
public class QuizDAO implements Serializable {

    public boolean finishQuiz(String quizId, String subjectID, String email, float total, Date createDate) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            String sql = "insert into Quiz(QuizID, SubjectID, Email, Total, CreateDate) "
                    + "values(?,?,?,?,?)";

            stm = con.prepareStatement(sql);
            stm.setString(1, quizId);
            stm.setString(2, subjectID);
            stm.setString(3, email);
            stm.setFloat(4, total);
            stm.setTimestamp(5, new Timestamp(createDate.getTime()));
            check = stm.executeUpdate() > 0;
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

    List<QuizDTO> history;

    public List<QuizDTO> getHistory() {
        return history;
    }

    public void historyQuiz(String subName, String email) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select quiz.QuizID, quiz.SubjectID, quiz.Email, quiz.Total, quiz.CreateDate\n"
                        + "from dbo.Quiz as quiz \n"
                        + "join \n"
                        + "(select subjectID, name\n"
                        + "from Subject) as sub\n"
                        + "on quiz.SubjectID = sub.SubjectID\n"
                        + "where sub.Name like ? and quiz.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + subName + "%");
                stm.setString(2, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String quizID = rs.getString("QuizID");
                    String subjectID = rs.getString("SubjectID");
                    float total = rs.getFloat("Total");
                    Timestamp createDate = rs.getTimestamp("CreateDate");
                    QuizDTO dto = new QuizDTO(quizID, subjectID, email, total, createDate);
                    
                    if (this.history == null) {
                        this.history = new ArrayList<>();
                    }
                    
                    this.history.add(dto);
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
