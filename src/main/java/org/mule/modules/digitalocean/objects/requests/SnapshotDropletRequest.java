package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.SnapshotType;

public class SnapshotDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private SnapshotType type;

	/**
	 * @return the type
	 */
	public SnapshotType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SnapshotType type) {
		this.type = type;
	}
}
