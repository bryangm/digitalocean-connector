package org.mule.modules.digitalocean.objects;

import java.io.Serializable;

public class Meta implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private Integer total;

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return this.total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
}
