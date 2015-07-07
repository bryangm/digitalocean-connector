package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.EnableIpv6Type;

public class EnableDropletIpv6Request implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private EnableIpv6Type type;

	/**
	 * @return the type
	 */
	public EnableIpv6Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EnableIpv6Type type) {
		this.type = type;
	}
}
