package Projekt;
import java.sql.*;


public class Connect
{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL="jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";

    public static String success="greska, niste spojeni na bazu, user -> root, password -> root";
    static final String USER = "root" ;
    static final String PASS = "root";

    public static void main(String[] args)
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Provjera konekcije na bazu...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            success="Uspjesno ste spojeni na bazu";
            System.out.println("Uspjesno spajanje na bazu...");


        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
    }
}