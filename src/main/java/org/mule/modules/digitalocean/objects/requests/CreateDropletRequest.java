package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;
import java.util.List;

import org.mule.api.annotations.display.FriendlyName;

import com.google.gson.annotations.SerializedName;

public class CreateDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String name;
	
	@SerializedName("region")
	private String regionSlug;
	
	@SerializedName("size")
	private String sizeSlug;
	
	@SerializedName("image")
	private Integer imageId;
	
	@FriendlyName("SSH Keys")
	@SerializedName("ssh_keys")
	private List<Integer> sshKeys;
	
	@SerializedName("backups")
	private Boolean enableBackups;
	
	@FriendlyName("Enable IPv6")
	@SerializedName("ipv6")
	private Boolean enableIpv6;
	
	@FriendlyName("Enable Private Networking")
	@SerializedName("private_networking")
	private Boolean enablePrivateNetworking;
	
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
	 * @return the regionSlug
	 */
	public String getRegionSlug() {
		return regionSlug;
	}

	/**
	 * @param regionSlug the regionSlug to set
	 */
	public void setRegionSlug(String regionSlug) {
		this.regionSlug = regionSlug;
	}

	/**
	 * @return the sizeSlug
	 */
	public String getSizeSlug() {
		return sizeSlug;
	}

	/**
	 * @param sizeSlug the sizeSlug to set
	 */
	public void setSizeSlug(String sizeSlug) {
		this.sizeSlug = sizeSlug;
	}

	/**
	 * @return the imageId
	 */
	public Integer getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
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
	 * @return the enableBackups
	 */
	public Boolean isEnableBackups() {
		return enableBackups;
	}

	/**
	 * @param enableBackups the enableBackups to set
	 */
	public void setEnableBackups(Boolean enableBackups) {
		this.enableBackups = enableBackups;
	}

	/**
	 * @return the enableIpv6
	 */
	public Boolean isEnableIpv6() {
		return enableIpv6;
	}

	/**
	 * @param enableIpv6 the enableIpv6 to set
	 */
	public void setEnableIpv6(Boolean enableIpv6) {
		this.enableIpv6 = enableIpv6;
	}

	/**
	 * @return the enablePrivateNetworking
	 */
	public Boolean isEnablePrivateNetworking() {
		return enablePrivateNetworking;
	}

	/**
	 * @param enablePrivateNetworking the enablePrivateNetworking to set
	 */
	public void setEnablePrivateNetworking(Boolean enablePrivateNetworking) {
		this.enablePrivateNetworking = enablePrivateNetworking;
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
