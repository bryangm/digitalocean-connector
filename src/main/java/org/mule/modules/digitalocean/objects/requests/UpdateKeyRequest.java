package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

public class UpdateKeyRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
