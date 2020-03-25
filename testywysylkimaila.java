package hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Controller
public class testywysylkimaila 
{

    private  String serwer_smtp = "smtp.gmail.com";
    private  String login = "testydlaprojektu@gmail.com";
    private  String haslo = "testy123";
    private  String email_nadawcy = "testydlaprojektu@gmail.com";
    private  String email_odbiorcy = "testydlaprojektu@gmail.com, wojtekbe7@gmail.com, 241864@student.pwr.edu.pl, beata-kos1@o2.pl";
    private  String ukryte_kopie = "saximprow@gmail.com";
    private  String temat = "Test maila z apki - temat";
    private  String tresc = "Próbny e-mail z apki... Działa...? ";

    @GetMapping("/testymailowe")
        public void wyslij_maila()
        {
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", serwer_smtp);
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop, null);
            Message msg = new MimeMessage(session);

                try 
                {

                    msg.setFrom(new InternetAddress(email_nadawcy));
                    msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email_odbiorcy, false));
                    msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(ukryte_kopie, false));
                    msg.setSubject(temat);
                    msg.setText(tresc);
                    msg.setSentDate(new Date());
                    SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
                    t.connect(serwer_smtp, login, haslo);
                    t.sendMessage(msg, msg.getAllRecipients());
                    t.close();
                } 
               catch (MessagingException e) 
               {
                    e.printStackTrace();
               }
        }
}
