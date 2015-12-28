package br.com.estudo.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	private Properties props = null;
	private MimeMessage message = null;
	private Session session = null;
	private String login = null;
	private String password = null;

	public SendMail(String login, String password) {
		this.login = login;
		this.password = password;
		this.loadProperties();
	}

	private void loadProperties() {
		InputStream is = getClass().getResourceAsStream("/br/com/estudo/mail/properties/gmail.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSession(Authenticator authenticator) {
		session = Session.getInstance(props, authenticator);

	}

	private void addDestinations(String from, String[] to, String[] cc,
			String[] bcc) {
		try {
			message = new MimeMessage(session);
			InternetAddress addrfrom = new InternetAddress(from);
			message.setFrom(addrfrom);

			// Emvia uma cópia para o solicitante
			message.addRecipient(RecipientType.BCC, addrfrom);

			if (to != null) {
				for (int i = 0; i < to.length; i++) {
					Address address = new InternetAddress(to[i]);
					message.addRecipient(RecipientType.TO, address);
				}
			}

			if (cc != null) {
				for (int i = 0; i < to.length; i++) {
					Address address = new InternetAddress(to[i]);
					message.addRecipient(RecipientType.CC, address);
				}
			}

			if (bcc != null) {
				for (int i = 0; i < to.length; i++) {
					Address address = new InternetAddress(to[i]);
					message.addRecipient(RecipientType.BCC, address);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createBodyMessage(String subject, String body, File attach) {
		try {
			message.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html");
			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachFilePart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(attach);
			attachFilePart.setDataHandler(new DataHandler(fds));
			attachFilePart.setFileName(fds.getName());

			multipart.addBodyPart(attachFilePart);
			message.setContent(multipart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(String from, String[] to, String[] cc, String[] bcc,
			String msg, File attach, String subject) {
		this.createSession(authenticator(login, password));
		this.addDestinations(from, to, cc, bcc);
		this.createBodyMessage(subject, msg, attach);
		try {
			Transport transport = session.getTransport();
			transport.connect();

			if (transport.isConnected()) {
				Transport.send(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Authenticator authenticator(String login, String password) {
		Authenticator authenticator = new MailAuthenticator(login, password);
		return authenticator;
	}

	public static void main(String[] args) {
		SendMail sendMail = new SendMail("fabyanjos", "ocenas16");
		sendMail.send("fabyanjos@gmail.com", new String[] {
				"fabyanjos@gmail.com", "fabyanjos@hotmail.com" }, null, null,
				"Teste JavaMail With Attach", new File("SendMail.java"),
				"JavaMail");
	}
}
