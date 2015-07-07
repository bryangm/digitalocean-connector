package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.PowerCycleType;

public class PowerCycleDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private PowerCycleType type;

	/**
	 * @return the type
	 */
	public PowerCycleType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PowerCycleType type) {
		this.type = type;
	}
}
