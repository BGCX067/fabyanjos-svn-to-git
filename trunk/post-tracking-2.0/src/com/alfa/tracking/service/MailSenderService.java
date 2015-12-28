package com.alfa.tracking.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.appengine.api.mail.MailService.Message;

public class MailSenderService {
	
	public void send(String msg) {
		try {
			MailService service = MailServiceFactory.getMailService();
	
			Message message = new Message();
			message.setSender("fabyanjos@gmail.com");
			message.setTo("fabyanjos@gmail.com", "aleqi200@gmail.com");
			
			String subject = "Consulta Correios: ";
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			subject += format.format(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"), new Locale("pt_BR")).getTime());
			message.setSubject(subject);			
			
			message.setHtmlBody(msg);
	
			service.send(message);
			System.out.println("Email Enviado com sucesso!!");
		} catch (Exception e) {
			System.err.println("Falha ao enviar email: " + e.getMessage());
		}
	}
}
