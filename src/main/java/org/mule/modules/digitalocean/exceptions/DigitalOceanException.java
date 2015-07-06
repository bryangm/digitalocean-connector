package org.mule.modules.digitalocean.exceptions;

import java.io.Serializable;

public class DigitalOceanException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String id;
	
	private String message;
	
	public DigitalOceanException(String message) {
		this.message = message;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
