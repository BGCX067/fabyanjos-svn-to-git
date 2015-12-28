package com.alfa.tracking.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfa.tracking.dao.DaoObjeto;
import com.alfa.tracking.dao.impl.DaoObjetoImpl;
import com.alfa.tracking.model.Evento;
import com.alfa.tracking.model.Objeto;
import com.alfa.tracking.service.ConsultaService;
import com.alfa.tracking.service.MailSenderService;

@Controller
public class ConsultaController {
	
	private static final int STATUS_FINALIZADO = 1;
	private static final String TIPO_FINALIZADO = "BDE";
	private String resposta;
	
	public String getResposta() {
		return resposta;
	}

	@RequestMapping(value="cron/consulta",method=RequestMethod.GET)
	public @ResponseBody String consulta() {
		DaoObjeto dao = new DaoObjetoImpl();
		ConsultaService consulta = new ConsultaService();
		MailSenderService mail = new MailSenderService();
		
		try {
			List<Objeto> objs = dao.listOpenAndTrackable();
			
			for(Objeto obj : objs) {
				Evento evento = consulta.buscaSRO(obj.getNumero());
				if(evento != null) {
					if(!evento.getDtHora().equals(obj.getLastStatusDate())) {
						obj.setLastStatusCod(Integer.valueOf(evento.getStatus()));
						obj.setLastTipo(evento.getTipo().toUpperCase());
						obj.setLastStatusDesc(evento.getDescricao());
						obj.setLastStatusDate(evento.getDtHora());
						
						if(obj.getLastStatusCod().intValue() == STATUS_FINALIZADO
								&& obj.getLastTipo().trim().equals(TIPO_FINALIZADO))
							obj.setOpen(false);
						
						dao.update(obj);
						
						
						
						mail.send(this.emailBody(obj));
					}
				}
			}
			resposta = "Sucesso na consulta";
		} catch (Exception e) {
			resposta = "Error: " + e.getMessage();
			System.err.println("Error na consulta SRO: " + e.getMessage());
		}
		return resposta;
	}
	
	private String emailBody(Objeto obj) {
		StringBuffer msg = new StringBuffer("Novo status do Objeto: ");
		msg
			.append("<a href=\"").append(obj.getUrl()).append(obj.getNumero())
			.append("\" target=\"_blank\">")
			.append(obj.getNumero())
			.append("</a>")
			.append(",").append(obj.getDescription())
			.append("<br>")
			.append("<b>")
			.append(obj.getLastStatusDesc())
			.append("</b>");
			;
		
		return msg.toString();
	}
}
