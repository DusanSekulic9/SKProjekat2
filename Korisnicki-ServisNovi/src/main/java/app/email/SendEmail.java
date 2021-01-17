package app.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.constants.MailConstants;

public class SendEmail {

    public static void sendEmail(String email, String mailConst) {

        final String username = "skemailservicemds@gmail.com";
        final String password = "MDS123456";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            if(mailConst.equals(MailConstants.VERIFIKACIJA)) {
            	message.setSubject("Verifikacija e-mail adrese");
            	message.setText("Poštovani,"
            			+ "\n\n Vaš e-mail je verifikovan.");
            }else {
            	message.setSubject("Otkazan let");
            	message.setText("Poštovani,"
            			+ "\n\n Vaš let je otkazan.");
            }

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
