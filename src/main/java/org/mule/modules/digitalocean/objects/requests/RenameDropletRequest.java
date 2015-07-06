package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.RenameType;

public class RenameDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private RenameType type;
	private String name;

	/**
	 * @return the type
	 */
	public RenameType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(RenameType type) {
		this.type = type;
	}

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
