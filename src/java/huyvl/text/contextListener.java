package huyvl.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HUYVU
 */
public class contextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileReader fr = null;
        BufferedReader br = null;
        Map<String, String> file = new HashMap<String, String>();
        ServletContext context = sce.getServletContext();
        String fileName = "/WEB-INF/pageIndex.txt";
        String realPath = context.getRealPath("/");
//        File f = new File(contextPath + "/WEB-INF/pageIndex.txt");      
        try {
//            File f = new File(realPath);
            fr = new FileReader(realPath + fileName);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.length() > 0) {
                    String[] splitStr = line.split("=");
                    file.put(splitStr[0], splitStr[1]);
                }
            }
            
            context.setAttribute("FILE", file);
            fr.close();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce
    ) {
        System.out.println("detroys");
    }

}
