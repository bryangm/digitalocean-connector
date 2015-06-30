package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Droplet;
import org.mule.modules.digitalocean.objects.Links;
import org.mule.modules.digitalocean.objects.Meta;

public class DropletResponses implements Serializable {
	private static final long serialVersionUID = -1L;

	private List<Droplet> domains;
	
	private Links links;
	
	private Meta meta;

	/**
	 * @return the domains
	 */
	public List<Droplet> getDomains() {
		return domains;
	}

	/**
	 * @param domains the domains to set
	 */
	public void setDomains(List<Droplet> domains) {
		this.domains = domains;
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
