package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.util.List;

public class Networks implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#droplets
	 */
	
	private List<Network> v4;
	
	private List<Network> v6;

	/**
	 * @return the v4
	 */
	public List<Network> getV4() {
		return v4;
	}

	/**
	 * @param v4 the v4 to set
	 */
	public void setV4(List<Network> v4) {
		this.v4 = v4;
	}

	/**
	 * @return the v6
	 */
	public List<Network> getV6() {
		return v6;
	}

	/**
	 * @param v6 the v6 to set
	 */
	public void setV6(List<Network> v6) {
		this.v6 = v6;
	}

}
