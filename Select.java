import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select
{
    public static void main(String args[])
    {
    Connection conn = null;
    Statement stmt = null;
    System.out.print(args[0]);  //ueber args even / odd oder ungerade Ergebnisse ausgegeben werden sollen
    try
            {
            Class.forName("org.sqlite.JDBC");
            String url ="jdbc:sqlite:C:\\Users\\Mehl_the_bescht\\Desktop\\HTL\\3.KL\\INFI\\database\\dbModulo2.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            System.out.println(" Opened database successfully");

            stmt = conn.createStatement();
            if(args[0].equals("even")) {
                ResultSet rseven = stmt.executeQuery("SELECT count(id) as mod FROM modulo where valuemod = 0;");

                while (rseven.next()) {
                    int evenOut = rseven.getInt("mod");

                    System.out.println("Even numbers = " + evenOut);
                }
                rseven.close();
            }
            else if(args[0].equals("odd")) {
                ResultSet rsodd = stmt.executeQuery("SELECT count(id) as mod FROM modulo where valuemod = 1;");
                while (rsodd.next()) {
                    int oddOut = rsodd.getInt("mod");

                    System.out.println("Odd numbers = " + oddOut);
                }
                rsodd.close();
            }
            else
            {
                System.out.println("check args value - value has to be even or odd");
            }
            //stmt.executeQuery("DROP TABLE if exists modulo;");
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Select error");
            System.exit(1);
        }
        System.out.println("Selection done successfully");
}
}