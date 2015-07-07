package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.RebootType;

public class RebootDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private RebootType type;
	
	/**
	 * @return the type
	 */
	public RebootType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(RebootType type) {
		this.type = type;
	}
}
