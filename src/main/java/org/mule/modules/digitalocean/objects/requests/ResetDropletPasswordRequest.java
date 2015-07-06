package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.PasswordResetType;

public class ResetDropletPasswordRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private PasswordResetType type;

	/**
	 * @return the type
	 */
	public PasswordResetType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PasswordResetType type) {
		this.type = type;
	}
}
