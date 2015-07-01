package org.mule.modules.digitalocean.objects;

import java.io.Serializable;

public class Meta implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private int total;

	/**
	 * @return the total
	 */
	public int getTotal() {
		return this.total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
