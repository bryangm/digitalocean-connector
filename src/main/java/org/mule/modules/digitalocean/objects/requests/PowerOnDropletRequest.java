package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.PowerOnType;

public class PowerOnDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private PowerOnType type;

	/**
	 * @return the type
	 */
	public PowerOnType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PowerOnType type) {
		this.type = type;
	}
}
