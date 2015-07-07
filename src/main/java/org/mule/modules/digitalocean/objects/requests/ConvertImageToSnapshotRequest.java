package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.ConvertType;

public class ConvertImageToSnapshotRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private ConvertType type;

	/**
	 * @return the type
	 */
	public ConvertType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ConvertType type) {
		this.type = type;
	}
}
