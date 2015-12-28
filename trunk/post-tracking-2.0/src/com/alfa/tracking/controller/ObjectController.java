package com.alfa.tracking.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfa.tracking.dao.DaoObjeto;
import com.alfa.tracking.dao.impl.DaoObjetoImpl;
import com.alfa.tracking.exception.ConsultaException;
import com.alfa.tracking.model.Objeto;
import com.alfa.tracking.service.ConsultaService;
import com.alfa.tracking.spring.helper.ListObjectTree;
import com.alfa.tracking.spring.helper.ObjectItemTree;
import com.alfa.tracking.spring.helper.Response;
import com.alfa.tracking.spring.helper.Tree;

@Controller
public class ObjectController {

	@RequestMapping(value = "/object/list", method = { RequestMethod.GET })
	public String getObjects(@RequestParam("node") String node, ModelMap model) {

		List<Objeto> objetos = getObjects();
		model.put("objetos", objetos);
		return "list";
	}

	@RequestMapping(value = "/getObjects", method = RequestMethod.GET)
	public @ResponseBody
	List<Objeto> getObjects() {
		DaoObjeto dao = new DaoObjetoImpl();
		List<Objeto> objetos = dao.listOpen();
		Collections.sort(objetos, new Comparator<Objeto>() {
			@Override
			public int compare(Objeto o1, Objeto o2) {
				return o2.getId().compareTo(o1.getId());
			}
		});
		return objetos;
	}

	@RequestMapping(value = "/postObject", method = { RequestMethod.POST })
	public @ResponseBody
	Response saveObject(@RequestParam("numero") String numero,
			@RequestParam("description") String description, ModelMap model) {
		Response response = new Response();
		if (description == null || description.trim().trim().equals("")) {
			response.setSucess(false);
			com.alfa.tracking.spring.helper.Error erro = new com.alfa.tracking.spring.helper.Error();
			erro.setField("Descricaoo");
			erro.setMessage("obrigatorio");
			response.getErrors().add(erro);
		}
		
		try {
			if (response.isSucess()) {
				DaoObjeto dao = new DaoObjetoImpl();
				Objeto objeto = new Objeto();
				if(numero!=null && numero.trim().isEmpty()){
					objeto.setNumero(numero);
				}
				objeto.setDescription(description);
				dao.save(objeto);
			}
		} catch (Exception e) {
			response.setSucess(false);
		}
		return response;

	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Response delete(@RequestParam("id") String id, ModelMap model) {
		Response response = new Response();
		try {
			DaoObjeto dao = new DaoObjetoImpl();
			dao.delete(Long.parseLong(id));
		} catch (Exception e) {
			response.setSucess(false);
		}
		return response;
	}

	@RequestMapping(value = "/getTree", method = {RequestMethod.GET })
	public @ResponseBody Tree getTreeList(@RequestParam("node") String node){
		if(node.equals("root")){
			ListObjectTree list = new ListObjectTree(getObjects());
			return list; 
		}else{
			long id = Long.parseLong(node);
			DaoObjeto dao = new DaoObjetoImpl();
			Objeto objeto = dao.findById(id);
			
			ObjectItemTree list = new ObjectItemTree(objeto);
			return list;
		}
	}
	
	@RequestMapping(value = "/getHistory", method = {RequestMethod.GET })
	public @ResponseBody String getHistory(@RequestParam("id") String nroObjeto){
		ConsultaService consultaService = new ConsultaService();
		
		try {
			return consultaService.getHtmlFor(nroObjeto.split("-")[1]);
		} catch (ConsultaException e) {
			return e.getMessage();
		}
	}
	
}
