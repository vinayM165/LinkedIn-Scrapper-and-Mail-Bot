package Gmail_API;

import java.io.*;
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
					//Insert you email Id from which you are sending email 
					//also insert gmail api app password that you can enable from your google account 
	                return new PasswordAuthentication("aakankshapatil36@gmail.com","ntcrvigvmblnwvei"); //Activate and paste gmail-app password
	            }
	        });
	        
	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setSubject(sub);
	           
	            	MimeBodyPart attachmentPart = new MimeBodyPart();
				// Load resource as InputStream
				InputStream is = getClass().getClassLoader().getResourceAsStream("Aakanksha Murlidhar Patil.pdf");
				if (is == null) throw new FileNotFoundException("Attachment not found in resources");
				File tempFile = File.createTempFile("attachment", ".pdf");
				try (FileOutputStream fos = new FileOutputStream(tempFile)) {
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = is.read(buffer)) != -1) {
						fos.write(buffer, 0, bytesRead);
					}
				}
				attachmentPart.attachFile(tempFile);
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
