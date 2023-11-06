import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class Create
{
    private static Connection connection = null;
    private static Statement stmt = null;

    public static void main(String[] args)
    {
        create();
        insert();
    }

    public static Connection create()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\Users\\Mehl_the_bescht\\Desktop\\HTL\\3.KL\\INFI\\database\\dbModulo2.db";
            connection = DriverManager.getConnection(url);
            System.out.println("sout runs");
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("ERROR with connection to database");
        }
        try
            {
                stmt = connection.createStatement();
                String sql = "create table if not exists modulo" +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                        "VALUE INT,"+
                        "VALUEMOD INT)";

                stmt.executeUpdate(sql);
                stmt.close();
                connection.close();
            }
        catch(Exception ex)
            {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                System.out.println("ERROR with creating table");
            }
        return connection;
    }
    public static void insert()
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
            for (int i = 0; i < 20; i++) {
                int rmd = random.nextInt(1, 11);
                String sql = "INSERT INTO modulo (value, valuemod) " +
                        "VALUES (" + rmd + ", " + (rmd % 2) + ");";
                state.executeUpdate(sql);

            }
            state.close();
            conn.commit();
            conn.close();              //always close - so storage gets cleared
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Insert error");
            System.exit(0);
        }
        System.out.println("data inserted in table");
    }
}