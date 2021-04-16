/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.question;

import huyvl.choice.ChoiceDTO;
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
public class QuestionDAO implements Serializable {

    List<QuestionDTO> load20Question;

    public List<QuestionDTO> getLoad20Question() {
        return load20Question;
    }

    public void loadQuestion() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select top(20) QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID \n"
                        + "from Question ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    boolean status = rs.getBoolean("Status");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.load20Question == null) {
                        this.load20Question = new ArrayList<>();
                    }
                    
                    this.load20Question.add(dto);
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

    List<QuestionDTO> listSearchByNameSubIDStatus;

    public List<QuestionDTO> getListSearchByNameSubIDStatus() {
        return listSearchByNameSubIDStatus;
    }

    public void searchByNameSubIDStatus(String searchValueName, String subID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where QuestionName like ? and SubjectID =? and Status=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValueName + "%");
                stm.setString(2, subID);
                stm.setBoolean(3, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    //  String choice = rs.getString("ChoiceID");
                    //   String answer = rs.getString("Anwer");
                    //         QuestionAnswerDTO dto = new QuestionAnswerDTO(subjectID, questionID, questionName, answer, correctAnswer, status, dateCreate);
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchByNameSubIDStatus == null) {
                        this.listSearchByNameSubIDStatus = new ArrayList<>();
                    }
//
                    this.listSearchByNameSubIDStatus.add(dto);
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

    List<QuestionDTO> listSearchByName;

    public List<QuestionDTO> getListSearchByName() {
        return listSearchByName;
    }

    public void searchByName(String searchValueName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where QuestionName like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValueName + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    boolean status = rs.getBoolean("Status");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchByName == null) {
                        this.listSearchByName = new ArrayList<>();
                    }

                    this.listSearchByName.add(dto);
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

    List<QuestionDTO> listSearchByNameSubID;

    public List<QuestionDTO> getListSearchByNameSubID() {
        return listSearchByNameSubID;
    }

    public void searchByNameSubID(String searchValueName, String subID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where QuestionName like ? and SubjectID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValueName + "%");
                stm.setString(2, subID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    boolean status = rs.getBoolean("Status");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchByNameSubID == null) {
                        this.listSearchByNameSubID = new ArrayList<>();
                    }

                    this.listSearchByNameSubID.add(dto);
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

    List<QuestionDTO> listSearchByNameStatus;

    public List<QuestionDTO> getListSearchByNameStatus() {
        return listSearchByNameStatus;
    }

    public void searchByNameStatus(String searchValueName, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where QuestionName like ? and Status =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValueName + "%");
                stm.setBoolean(2, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchByNameStatus == null) {
                        this.listSearchByNameStatus = new ArrayList<>();
                    }

                    this.listSearchByNameStatus.add(dto);
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

    List<QuestionDTO> listSearchBySubject;

    public List<QuestionDTO> getListSearchBySubject() {
        return listSearchBySubject;
    }

    public void searchBySubject(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where SubjectID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    boolean status = rs.getBoolean("Status");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchBySubject == null) {
                        this.listSearchBySubject = new ArrayList<>();
                    }

                    this.listSearchBySubject.add(dto);
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

    List<QuestionDTO> listSearchBySubIDStatus;

    public List<QuestionDTO> getListSearchBySubIDStatus() {
        return listSearchBySubIDStatus;
    }

    public void searchBySubIDStatus(String subjectID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where SubjectID =? and Status =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchBySubIDStatus == null) {
                        this.listSearchBySubIDStatus = new ArrayList<>();
                    }

                    this.listSearchBySubIDStatus.add(dto);
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

    List<QuestionDTO> listSearchByStatus;

    public List<QuestionDTO> getListSearchByStatus() {
        return listSearchByStatus;
    }

    public void searchByStatus(boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where Status =?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);

                    if (this.listSearchByStatus == null) {
                        this.listSearchByStatus = new ArrayList<>();
                    }

                    this.listSearchByStatus.add(dto);
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

    public boolean UpdateQuestion(String questionID, String questionName, String correctAnswer, boolean status, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
//                (QuestionName, CorrectAnswer, Status, SubjectID)
                String sql = "update Question "
                        + "set QuestionName =?, CorrectAnswer =?, Status =?, SubjectID =? "
                        + "where QuestionID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionName);
                stm.setString(2, correctAnswer);
                stm.setBoolean(3, status);
                stm.setString(4, subjectID);
                stm.setString(5, questionID);
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

    public boolean DeleteQuestion(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Question "
                        + "set Status = 0 "
                        + "where QuestionID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
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

    List<QuestionAnswerDTO> listQuestionAnswer;

    public List<QuestionAnswerDTO> getListQuestionAnswer() {
        return listQuestionAnswer;
    }

    public void searchByQuestionID(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select aws.QuestionID, ques.QuestionName, ques.CorrectAnswer, ques.Status, ques.DateCreate, ques.SubjectID, aws.ChoiceID, aws.Answer\n"
                        + "from Question as ques \n"
                        + "join \n"
                        + "( select * from Choice ) as aws\n"
                        + "on ques.QuestionID = aws.QuestionID\n"
                        + "where ques.QuestionID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionName = rs.getString("QuestionName");
                    String correctAnswer = rs.getString("CorrectAnswer");
                    boolean status = rs.getBoolean("Status");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    String subjectID = rs.getString("SubjectID");
                    String choice = rs.getString("ChoiceID");
                    String answer = rs.getString("Answer");
//                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);
                    QuestionAnswerDTO dto = new QuestionAnswerDTO(subjectID, questionID, questionName, answer, correctAnswer, status, dateCreate);
                    if (this.listQuestionAnswer == null) {
                        this.listQuestionAnswer = new ArrayList<>();
                    }
//
                    this.listQuestionAnswer.add(dto);
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

//    public void takeQuiz(String subjectID) throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "select ques.QuestionID, ques.QuestionName, aws.ChoiceID, aws.Answer\n"
//                        + "from Question as ques \n"
//                        + "join\n"
//                        + "(select * from Choice ) as aws\n"
//                        + "on ques.QuestionID = aws.QuestionID and Status =1 \n"
//                        + "where ques.SubjectID = ? order by rand() ";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, subjectID);
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    String questionID = rs.getString("QuestionID");
//                    String questionName = rs.getString("QuestionName");
//                    String choice = rs.getString("ChoiceID");
//                    String answer = rs.getString("Answer");
////                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctAnswer, dateCreate, subjectID);
//                    QuestionAnswerDTO dto = new QuestionAnswerDTO(subjectID, questionID, questionName, answer, correctAnswer, status, dateCreate);
//                    if (this.listQuestionAnswer == null) {
//                        this.listQuestionAnswer = new ArrayList<>();
//                    }
////
//                    this.listQuestionAnswer.add(dto);
//                }
//            }
//
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//    }
    public boolean createQuestion(String questionID, String questionName, String correctAnswer, boolean status, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Question(QuestionID, QuestionName, CorrectAnswer, Status, DateCreate, SubjectID) "
                        + "values(?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.setString(2, questionName);
                stm.setString(3, correctAnswer);
                stm.setBoolean(4, status);
                stm.setTimestamp(5, new Timestamp(new Date().getTime()));
                stm.setString(6, subjectID);
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

    List<QuestionDTO> detailQuestion;

    public List<QuestionDTO> getDetailQuestion() {
        return detailQuestion;
    }

    public void getQuestion(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select QuestionName, CorrectAnswer, Status, DateCreate, SubjectID "
                        + "from Question "
                        + "where QuestionID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionName = rs.getString("QuestionName");
                    String correctiAnswer = rs.getString("CorrectAnswer");
                    boolean status = rs.getBoolean("Status");
                    String subjectID = rs.getString("SubjectID");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctiAnswer, dateCreate, subjectID);

                    if (this.detailQuestion == null) {
                        this.detailQuestion = new ArrayList<>();
                    }

                    this.detailQuestion.add(dto);
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

    List<QuestionDTO> quizQuestion;

    public List<QuestionDTO> getQuizQuestion() {
        return quizQuestion;
    }

    public void getQuestionBySubjectID(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select top(10) QuestionID, QuestionName, CorrectAnswer, DateCreate, SubjectID, Status "
                        + "from Question "
                        + "where SubjectID =? and Status =1 order by NEWID()";
                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String questionName = rs.getString("QuestionName");
                    String correctiAnswer = rs.getString("CorrectAnswer");
                    boolean status = rs.getBoolean("Status");
                    Timestamp dateCreate = rs.getTimestamp("DateCreate");
                    QuestionDTO dto = new QuestionDTO(questionID, questionName, status, correctiAnswer, dateCreate, subjectID);

                    if (this.quizQuestion == null) {
                        this.quizQuestion = new ArrayList<>();
                    }

                    this.quizQuestion.add(dto);
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

    public String CorrectAnswer(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select CorrectAnswer\n"
                        + "from Question\n"
                        + "where QuestionID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String fullname = rs.getString("CorrectAnswer");
                    return fullname;
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
        return null;
    }
}
