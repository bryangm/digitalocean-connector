package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.DisableBackupsType;

public class DisableDropletBackupsRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private DisableBackupsType type;

	/**
	 * @return the type
	 */
	public DisableBackupsType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DisableBackupsType type) {
		this.type = type;
	}
}
