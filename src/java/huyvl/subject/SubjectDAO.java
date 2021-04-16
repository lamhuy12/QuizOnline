package huyvl.subject;

import huyvl.ultites.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HUYVU
 */
public class SubjectDAO implements Serializable {

    public List<SubjectDTO> getListSubject() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<SubjectDTO> list = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select subjectID, Name from Subject";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String subID = rs.getString("subjectID");
                    String name = rs.getString("Name");
                    SubjectDTO dto = new SubjectDTO(subID, name);

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }
    
    
}
