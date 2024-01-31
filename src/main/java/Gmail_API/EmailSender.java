package Gmail_API;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

		
	public boolean sendMail(String from, String to, String sub, String msg) {
		  Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");
	        
	        
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("email-id@gmail.com","*Gmail API Passkey*"); //Activate and paste gmail-api passkey
	            }
	        });
	        
	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setSubject(sub);
	           
	            	MimeBodyPart attachmentPart = new MimeBodyPart();
	            attachmentPart.attachFile(new File("path")); //path to attachment i.e. resume/cover letter
	            Multipart multipart = new MimeMultipart();
	            message.setContent(multipart);
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText(msg);
	            multipart.addBodyPart(messageBodyPart); // messageBodyPart is the MimeBodyPart object for the email body
	            multipart.addBodyPart(attachmentPart);


	            // Send message
	            Transport.send(message);
	            System.out.println("message sent successfully to : " + to);
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        } catch (IOException e) {
	        	 throw new RuntimeException(e);
			}
	        
	        return true;
	}
	
}
