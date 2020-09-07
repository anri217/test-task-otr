package utils;

import dao.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StartScriptExecutor {
    public static void executeSqlStartScript(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            StringBuilder query = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = br.readLine()) != null) {
                query.append(line);
                query.append(ls);
            }
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(query.toString()).executeUpdate();
            transaction.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
