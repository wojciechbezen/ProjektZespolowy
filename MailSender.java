package pl.edu.pwr.s241223;

import org.springframework.stereotype.Controller;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

@Controller
public class MailSender
{
    private  String ServerSMTP = "smtp.gmail.com";
    private  String Login = "testydlaprojektu@gmail.com";
    private  String Password = "testy123";
    private  String SenderAddress = "testydlaprojektu@gmail.com";
    //private  String ReceiverAddress = "241864@student.pwr.edu.pl, 241226@student.pwr.edu.pl, 241223@student.pwr.edu.pl, 241920@student.pwr.edu.pl, 241926@student.pwr.edu.pl"; // w jednym stringu po przecinku można kilka adresów, w tym wypadku wysyłamy na swój własny adres
    private  String ReceiverAddress = "testydlaprojektu@gmail.com"; // dla testów mail do samego siebie
    //private  String Copies = ""; // tutaj też można po przecinku kilka jak coś, sprawdzałem, działa
    //private  String HiddenCopies = ""; // tutaj również, sprawdzałem, działa
    private  String Topic = "Test maila z apki - temat";
    private  String MessageContent = "Testuję po raz kolejny";

    @RequestMapping(
            value = "/sendMail",
            method = RequestMethod.GET)
    @ResponseBody
    public void SendMail()
    {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", ServerSMTP);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // 587 to port
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try
        {
            msg.setFrom(new InternetAddress(SenderAddress));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(ReceiverAddress, false));
            //msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(Copies, false));
            //msg.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(HiddenCopies, false));
            msg.setSubject(Topic);
            msg.setText(MessageContent);
            msg.setSentDate(new Date());
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect(ServerSMTP, Login, Password);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
