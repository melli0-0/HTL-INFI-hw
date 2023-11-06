import java.sql.*;
import java.util.Random;

import static java.lang.StrictMath.random;


public class Insert {

    public static void main(String args[])
    {
        Connection conn = null;
        Statement state = null;
        Random random = new Random();
        try {
            Class.forName("org.sqlite.JDBC");

        String url = "jdbc:sqlite:C:\\Users\\Mehl_the_bescht\\Desktop\\HTL\\3.KL\\INFI\\database\\dbModulo2.db";
        conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false);
        System.out.println("Opened database successfully");

        state = conn.createStatement();
    }
        catch (Exception e)
        {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.out.println("Insert error");
        System.exit(0);
        }
            try {
                for (int i = 0; i < 20; i++)
                {
                    int rmdNum = random.nextInt(1, 11);
                    String sql = "UPDATE modulo set value =" + rmdNum + " where id =" + i + ";";
                    state.executeUpdate(sql);
                    String sqlMod = "UPDATE modulo set valuemod ="+rmdNum%2+" where id = "+i+";";
                    state.executeUpdate(sqlMod);
                }
                conn.commit(); //changes were permanently and are visible
                state.close();
                conn.close();
            } catch ( Exception e )
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Update error");
                System.exit(0);
            }
            System.out.println("Insert new data - done successfully");
        }
    }
