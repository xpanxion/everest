package com.xpanxion.wallboard.rest.dto.framework;

import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.hateoas.Link;

public class CustomLink extends Link {

	private static final long serialVersionUID = -399416265454768601L;
	
	@XmlAttribute private String method;
	
	public CustomLink(Link link) {
		super(link.getHref(), link.getRel());
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
