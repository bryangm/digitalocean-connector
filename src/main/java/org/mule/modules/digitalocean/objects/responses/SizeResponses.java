package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Links;
import org.mule.modules.digitalocean.objects.Meta;
import org.mule.modules.digitalocean.objects.Size;

public class SizeResponses implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private List<Size> sizes;
	
	private Links links;
	
	private Meta meta;

	/**
	 * @return the sizes
	 */
	public List<Size> getSizes() {
		return sizes;
	}

	/**
	 * @param sizes the sizes to set
	 */
	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
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
