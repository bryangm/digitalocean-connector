package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.ShutdownType;

public class ShutdownDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private ShutdownType type;

	/**
	 * @return the type
	 */
	public ShutdownType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ShutdownType type) {
		this.type = type;
	}
}
