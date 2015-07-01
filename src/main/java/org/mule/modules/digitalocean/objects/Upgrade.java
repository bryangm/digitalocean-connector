package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Upgrade implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#droplets
	 */
	
	@SerializedName("droplet_id")
	private Integer dropletId;
	
	@SerializedName("date_of_migration")
	private Date dateOfMigration;
	
	private String url;

	/**
	 * @return the dropletId
	 */
	public Integer getDropletId() {
		return dropletId;
	}

	/**
	 * @param dropletId the dropletId to set
	 */
	public void setDropletId(Integer dropletId) {
		this.dropletId = dropletId;
	}

	/**
	 * @return the dateOfMigration
	 */
	public Date getDateOfMigration() {
		return dateOfMigration;
	}

	/**
	 * @param dateOfMigration the dateOfMigration to set
	 */
	public void setDateOfMigration(Date dateOfMigration) {
		this.dateOfMigration = dateOfMigration;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
