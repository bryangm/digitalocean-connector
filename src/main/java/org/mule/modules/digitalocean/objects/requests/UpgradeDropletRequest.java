package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.UpgradeType;

public class UpgradeDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private UpgradeType type;

	/**
	 * @return the type
	 */
	public UpgradeType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(UpgradeType type) {
		this.type = type;
	}
}
