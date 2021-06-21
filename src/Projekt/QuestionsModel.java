package Projekt;

import com.mysql.cj.protocol.Resultset;
import java.sql.*;

public class QuestionsModel {
    private static String[][] q;
    private static String[][] a;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=CET";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Resultset rs;
    public QuestionsModel(){

    }
    public static String[][]getq1() {
        Connect.main(null);
        String sviPodaci = "SELECT * FROM `sys`.`questions`";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = (Statement) conn.prepareStatement(sviPodaci, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.executeQuery(sviPodaci);
            ResultSet rs = stmt.getResultSet();
            rs.last();
            int brojRedaka = rs.getRow();
            q = new String[brojRedaka][5];
            rs.beforeFirst();
            int counter = 0;
            while(rs.next()){
                q[counter][0] = rs.getString("pitanje");
                q[counter][1] = rs.getString("opcija1");
                q[counter][2] = rs.getString("opcija2");
                q[counter][3] = rs.getString("opcija3");
                q[counter][4] = rs.getString("opcija4");
                counter++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return q;
    }

    public static String[][]geta1() {
        Connect.main(null);
        String sviPodaci = "SELECT `odgovor` FROM `sys`.`questions`";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = (Statement) conn.prepareStatement(sviPodaci, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.executeQuery(sviPodaci);
            ResultSet rs = stmt.getResultSet();
            rs.last();
            int brojRedaka = rs.getRow();
            a = new String[brojRedaka][1];
            rs.beforeFirst();
            int counter = 0;
            while(rs.next()){
                a[counter][0] = rs.getString("odgovor");
                counter++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return a;
    }




}
