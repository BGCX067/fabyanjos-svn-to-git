package com.cashflow.common;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class SkinBean implements Serializable {

	/**
	 * Generated by Eclipse
	 */
	private static final long serialVersionUID = -5122403205168150732L;

	private String skin;
	private Object skinChooserState;

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public Object getSkinChooserState() {
		return skinChooserState;
	}

	public void setSkinChooserState(Object skinChooserState) {
		this.skinChooserState = skinChooserState;
	}
}
