package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.util.Date;
import com.google.gson.annotations.SerializedName;

public class Action implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#actions
	 */
	
	private int id;
	
	private String status;
	
	private String type;
	
	@SerializedName("started_at")
	private Date startedAt;
	
	@SerializedName("completed_at")
	private Date completedAt;
	
	@SerializedName("resource_id")
	private int resourceId;
	
	@SerializedName("resource_type")
	private String resourceType;
	
	private Region region;
	
	@SerializedName("region_slug")
	private String regionSlug;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the startedAt
	 */
	public Date getStartedAt() {
		return startedAt;
	}

	/**
	 * @param startedAt the startedAt to set
	 */
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	/**
	 * @return the completedAt
	 */
	public Date getCompletedAt() {
		return completedAt;
	}

	/**
	 * @param completedAt the completedAt to set
	 */
	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	/**
	 * @return the resourceId
	 */
	public int getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
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
