package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.util.List;

public class Region implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#regions
	 */
	
	private String name;
	
	private String slug;
	
	private List<String> sizes;
	
	private List<String> features;
	
	private boolean available;

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
	 * @return the sizes
	 */
	public List<String> getSizes() {
		return sizes;
	}

	/**
	 * @param sizes the sizes to set
	 */
	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}

	/**
	 * @return the features
	 */
	public List<String> getFeatures() {
		return features;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(List<String> features) {
		this.features = features;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
