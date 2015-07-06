package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.TransferType;

import com.google.gson.annotations.SerializedName;

public class TransferImageRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private TransferType type;
	@SerializedName("region")
	private String regionSlug;

	/**
	 * @return the type
	 */
	public TransferType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TransferType type) {
		this.type = type;
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
}
