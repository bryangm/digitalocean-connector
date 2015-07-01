package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Domain implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#domains
	 */
	  
	 private String name;
	 
	 @SerializedName("ttl")
	 private Integer timeToLive;
	 
	 @SerializedName("zone_file")
	 private String zoneFile;

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
	 * @return the timeToLive
	 */
	public Integer getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the zoneFile
	 */
	public String getZoneFile() {
		return zoneFile;
	}

	/**
	 * @param zoneFile the zoneFile to set
	 */
	public void setZoneFile(String zoneFile) {
		this.zoneFile = zoneFile;
	}
}
