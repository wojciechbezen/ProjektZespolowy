
package hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.*;

@Controller
public class insertUserToDB 
{
    @GetMapping("/insertUser")
        public void insertRecord(@RequestParam String imie,@RequestParam String nazwisko, 
                                 @RequestParam String nick, @RequestParam String haslo, 
                                 @RequestParam String funkcja, @RequestParam String RFID)
        //public void insertRecord()
        {
            Connection dbConn = getConncectionToDB();
            if(dbConn == null)
            {
                System.out.println("No Connection");
            }
            else
            {
               //execInsert(dbConn,"Rekord","DodanyOsobnymPlikiem","Insertem","Czyli","Dziala","Hehehe");
               execInsert(dbConn,imie,nazwisko,nick,haslo,funkcja,RFID);
            }
        }

        public void execInsert(Connection conn, String imie, String nazwisko, String nick, String haslo, String funkcja, String RFID)
        {
            Statement stat = null;
            String query = "USE testowa; INSERT INTO OSOBA(Nazwisko, Imie, Nick, Haslo, Funkcja, RFID) "
                             + "VALUES('"+imie+"','"+nazwisko+"','"+nick+"','"+haslo+"','"+funkcja+"','"+RFID+"');";
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
