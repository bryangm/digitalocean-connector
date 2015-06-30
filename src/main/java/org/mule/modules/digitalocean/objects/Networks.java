package org.mule.modules.digitalocean.objects;

import java.io.Serializable;

public class Networks implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#droplets
	 */
	
	private Network[] v4;
	
	private Network[] v6;

	/**
	 * @return the v4
	 */
	public Network[] getV4() {
		return v4;
	}

	/**
	 * @param v4 the v4 to set
	 */
	public void setV4(Network[] v4) {
		this.v4 = v4;
	}

	/**
	 * @return the v6
	 */
	public Network[] getV6() {
		return v6;
	}

	/**
	 * @param v6 the v6 to set
	 */
	public void setV6(Network[] v6) {
		this.v6 = v6;
	}

}
