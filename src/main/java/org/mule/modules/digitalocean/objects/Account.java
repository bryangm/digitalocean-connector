package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Account implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#account
	 */
	@SerializedName("droplet_limit")
	private Integer dropletLimit;
	
	private String email;
	
	private String uuid;
	
	@SerializedName("email_verified")
	private boolean emailVerified;
	
	public Account(String json) {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the dropletLimit
	 */
	public Integer getDropletLimit() {
		return dropletLimit;
	}
	/**
	 * @param dropletLimit the dropletLimit to set
	 */
	public void setDropletLimit(Integer dropletLimit) {
		this.dropletLimit = dropletLimit;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the emailVerified
	 */
	public boolean isEmailVerified() {
		return emailVerified;
	}
	/**
	 * @param emailVerified the emailVerified to set
	 */
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
}
