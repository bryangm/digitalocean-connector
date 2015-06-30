package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Key;
import org.mule.modules.digitalocean.objects.Links;
import org.mule.modules.digitalocean.objects.Meta;

public class KeyResponses implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private List<Key> keys;
	
	private Links links;
	
	private Meta meta;

	/**
	 * @return the keys
	 */
	public List<Key> getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(List<Key> keys) {
		this.keys = keys;
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
