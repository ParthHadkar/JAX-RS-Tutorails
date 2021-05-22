package com.jersey.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link 
{
	private String link;
	private String ref;
	
	public Link() 
	{}
	
	public Link(String link, String ref) 
	{
		this.link = link;
		this.ref = ref;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
