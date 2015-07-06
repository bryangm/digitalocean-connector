package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.EnablePrivateNetworkingType;

public class EnableDropletPrivateNetworkingRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private EnablePrivateNetworkingType type;

	/**
	 * @return the type
	 */
	public EnablePrivateNetworkingType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EnablePrivateNetworkingType type) {
		this.type = type;
	}
}
