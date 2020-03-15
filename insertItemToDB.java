
package hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.*;

@Controller
public class insertItemToDB 
{
    @GetMapping("/insertItem")
        public void insertRecord(@RequestParam String nazwa,@RequestParam String kod, @RequestParam String stan)
        {
            Connection dbConn = getConncectionToDB();
            if(dbConn == null)
            {
                System.out.println("No Connection");
            }
            else
            {
               execInsert(dbConn,nazwa,kod,stan);
            }
        }

        public void execInsert(Connection conn, String nazwa, String kod, String stan)
        {
            Statement stat = null;
            String query = "USE testowa; INSERT INTO URZADZENIE(Nazwa, Kod, Stan)"
                             + "VALUES('"+nazwa+"','"+kod+"','"+stan+"');";
            try
            {
                stat = conn.createStatement();
                System.out.println(query);//wypisanie kontrolne zapytania
                stat.executeUpdate(query);
                System.out.println("Insert Chyba OK...");
            }
            catch(SQLException e)
            {
                System.out.println(query); //wypisanie kontrolne zapytania
                System.out.println("Insert error");
                e.printStackTrace();
            }
        }

        public Connection getConncectionToDB()
        {
            try
            {
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://WOJTEK\\MSSQLSERVER01","testowyadmin1","12345678");
                System.out.println("Mamy polaczenie");
                return conn;
            }
            catch(Exception e)
            {
                System.out.println("XYZ");
                e.printStackTrace();
                return null;
            }
        }
}
