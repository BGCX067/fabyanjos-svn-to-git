package com.alfa.twitter.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.alfa.twitter.util.TwitterUtil;

public class UrlCustomTag extends TagSupport {

	private static final long serialVersionUID = UrlCustomTag.class.getName().hashCode();

	private String value = null;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		String out = TwitterUtil.findUrl(this.value);
		out = TwitterUtil.findSearch(out);
		out = TwitterUtil.findUser(out);
		
		try {
			pageContext.getOut().write(out);
		} catch (IOException e) {
			System.err.println("Erro: " + e.getMessage());
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		this.release();
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();

		this.value = null;
	}
}
