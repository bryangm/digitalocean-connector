package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.RebuildType;

import com.google.gson.annotations.SerializedName;

public class RebuildDropletRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private RebuildType type;
	@SerializedName("image")
	private String imageIdOrSlug;

	/**
	 * @return the type
	 */
	public RebuildType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(RebuildType type) {
		this.type = type;
	}

	/**
	 * @return the imageIdOrSlug
	 */
	public String getImageIdOrSlug() {
		return imageIdOrSlug;
	}

	/**
	 * @param imageIdOrSlug the imageIdOrSlug to set
	 */
	public void setImageIdOrSlug(String imageIdOrSlug) {
		this.imageIdOrSlug = imageIdOrSlug;
	}
}
