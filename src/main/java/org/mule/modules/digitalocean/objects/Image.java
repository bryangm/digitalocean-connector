package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.util.Date;
import com.google.gson.annotations.SerializedName;

public class Image implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#images
	 */
    
    private int id;
    
	private String name;
	
	private String distribution;
	
	private String slug;
	
	@SerializedName("public")
	private boolean publicImage;
	
	private String[] regions;
	
	@SerializedName("created_at")
	private Date createdAt;
	
	private String type;
	
	@SerializedName("min_disk_size")
	private int minDiskSize;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the distribution
	 */
	public String getDistribution() {
		return distribution;
	}

	/**
	 * @param distribution the distribution to set
	 */
	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the publicImage
	 */
	public boolean isPublicImage() {
		return publicImage;
	}

	/**
	 * @param publicImage the publicImage to set
	 */
	public void setPublicImage(boolean publicImage) {
		this.publicImage = publicImage;
	}

	/**
	 * @return the regions
	 */
	public String[] getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(String[] regions) {
		this.regions = regions;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the minDiskSize
	 */
	public int getMinDiskSize() {
		return minDiskSize;
	}

	/**
	 * @param minDiskSize the minDiskSize to set
	 */
	public void setMinDiskSize(int minDiskSize) {
		this.minDiskSize = minDiskSize;
	}
}