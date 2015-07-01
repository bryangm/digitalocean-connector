package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CreateDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String name;
	
	private String region;
	
	private String size;
	
	private Integer image;
	
	@SerializedName("ssh_keys")
	private List<Integer> sshKeys;
	
	private Boolean backups;
	
	private Boolean ipv6;
	
	@SerializedName("private_networking")
	private Boolean privateNetworking;
	
	@SerializedName("user_data")
	private String userData;

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
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the image
	 */
	public Integer getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Integer image) {
		this.image = image;
	}

	/**
	 * @return the sshKeys
	 */
	public List<Integer> getSshKeys() {
		return sshKeys;
	}

	/**
	 * @param sshKeys the sshKeys to set
	 */
	public void setSshKeys(List<Integer> sshKeys) {
		this.sshKeys = sshKeys;
	}

	/**
	 * @return the backups
	 */
	public Boolean isBackups() {
		return backups;
	}

	/**
	 * @param backups the backups to set
	 */
	public void setBackups(Boolean backups) {
		this.backups = backups;
	}

	/**
	 * @return the ipv6
	 */
	public Boolean isIpv6() {
		return ipv6;
	}

	/**
	 * @param ipv6 the ipv6 to set
	 */
	public void setIpv6(Boolean ipv6) {
		this.ipv6 = ipv6;
	}

	/**
	 * @return the privateNetworking
	 */
	public Boolean isPrivateNetworking() {
		return privateNetworking;
	}

	/**
	 * @param privateNetworking the privateNetworking to set
	 */
	public void setPrivateNetworking(Boolean privateNetworking) {
		this.privateNetworking = privateNetworking;
	}

	/**
	 * @return the userData
	 */
	public String getUserData() {
		return userData;
	}

	/**
	 * @param userData the userData to set
	 */
	public void setUserData(String userData) {
		this.userData = userData;
	}
	
}
