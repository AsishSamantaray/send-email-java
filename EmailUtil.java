

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
//	public static void sendEmail(String fromEmail, String password, String toEmail, String subject, String body){
//
//
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//		props.put("mail.smtp.port", "587"); //TLS Port
//		props.put("mail.smtp.auth", "true"); //enable authentication
//		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//
//                //create Authenticator object to pass in Session.getInstance argument
//		Authenticator auth = new Authenticator() {
//			//override the getPasswordAuthentication method
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(fromEmail, password);
//			}
//		};
//		Session session = Session.getInstance(props, auth);
//
//		try
//	    {
//	      MimeMessage msg = new MimeMessage(session);
//	      //set message headers
//	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//	      msg.addHeader("format", "flowed");
//	      msg.addHeader("Content-Transfer-Encoding", "8bit");
//
//	      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
//
//	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
//
//	      msg.setSubject(subject, "UTF-8");
//
//	      msg.setText(body, "UTF-8");
//
//	      msg.setSentDate(new Date());
//
//	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//	      System.out.println("Message is ready");
//    	  Transport.send(msg);
//
//	      System.out.println("EMail Sent Successfully!!");
//	    }
//	    catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	}

	public static void sendEmail(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}


	public static void sendAttachmentEmail(String from, String password, String toEmail, String subject, String body){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(from, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(toEmail));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText(body);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "D:\\J2EE\\Projects\\jdbcdemo\\employee_details\\data.csv";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName("data.csv");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}

}
