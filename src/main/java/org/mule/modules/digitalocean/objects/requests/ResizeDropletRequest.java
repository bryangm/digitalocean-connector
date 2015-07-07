package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.ResizeType;

import com.google.gson.annotations.SerializedName;

public class ResizeDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private ResizeType type;
	@SerializedName("name")
	private String sizeSlug;
	@SerializedName("disk")
	private Boolean upgradeDisk;

	/**
	 * @return the type
	 */
	public ResizeType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResizeType type) {
		this.type = type;
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
	 * @return the upgradeDisk
	 */
	public Boolean getUpgradeDisk() {
		return upgradeDisk;
	}

	/**
	 * @param upgradeDisk the upgradeDisk to set
	 */
	public void setUpgradeDisk(Boolean upgradeDisk) {
		this.upgradeDisk = upgradeDisk;
	}
}
