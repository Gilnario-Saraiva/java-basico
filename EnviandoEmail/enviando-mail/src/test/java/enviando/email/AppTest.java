package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class AppTest {
	
	private String userName = "email@gmail.com";
	private String password = "qqqqwwwweeeerrrr";
	
	//@Test
	public void testeEmail() {
		
		try {
			
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});
			
			Address[] tuUser = InternetAddress.parse("destino@gmail.com, destino@gmail.com");
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(userName, "TESTE DE ENVIO"));
			message.setRecipients(Message.RecipientType.TO, tuUser);
			message.setSubject("Teste JavaMail");
			message.setText("Corpo do email - Enviado com JavaMail!");
			
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testeEnviaEmail() {
		
		EnviaEmail enviaEmail = new EnviaEmail("destino@gmail.com",
				"Estudando Java", "Assunto: teste de envio", "Corpo do email.");
		
		enviaEmail.enviaEmail();
	}
	
}
