
package hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.*;

@Controller
public class deleteItemFromDB {
 @GetMapping("/deleteItem")
        public void deleteRecord(@RequestParam String cecha)
        {
            Connection dbConn = getConncectionToDB();
            if(dbConn == null)
            {
                System.out.println("No Connection");
            }
            else
            {
               execDelete(dbConn,cecha);
            }
        }

        public void execDelete(Connection conn, String cecha)
        {
            Statement stat = null;
            String query = "USE testowa; DELETE FROM Urzadzenie WHERE Nazwa='"+cecha+"';";
            try
            {
                stat = conn.createStatement();
                System.out.println(query);//wypisanie kontrolne zapytania
                stat.executeUpdate(query);
                System.out.println("Delete Chyba OK...");
            }
            catch(SQLException e)
            {
                System.out.println(query); //wypisanie kontrolne zapytania
                System.out.println("Delete error");
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
