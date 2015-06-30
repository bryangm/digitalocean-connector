package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Image;
import org.mule.modules.digitalocean.objects.Links;
import org.mule.modules.digitalocean.objects.Meta;

public class ImageResponses implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private List<Image> images;
	
	private Links links;
	
	private Meta meta;

	/**
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * @return the links
	 */
	public Links getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(Links links) {
		this.links = links;
	}

	/**
	 * @return the meta
	 */
	public Meta getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
