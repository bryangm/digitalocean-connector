package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.api.annotations.display.FriendlyName;

import com.google.gson.annotations.SerializedName;

public class CreateDomainRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String name;
	
	@SerializedName("ip_address")
	@FriendlyName("IP Address")
	private String ipAddress;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
