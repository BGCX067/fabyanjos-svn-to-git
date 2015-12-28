package com.alfa.jsf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "homeBean")
@RequestScoped
public class HomeBean {

	public void load() {
		System.out.println("CHAMOU ACTION");
	}
}
