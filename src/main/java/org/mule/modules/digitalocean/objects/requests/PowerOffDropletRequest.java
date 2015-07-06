package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.PowerOffType;

public class PowerOffDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private PowerOffType type;

	/**
	 * @return the type
	 */
	public PowerOffType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PowerOffType type) {
		this.type = type;
	}
}
