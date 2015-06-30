package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Kernel;
import org.mule.modules.digitalocean.objects.Links;
import org.mule.modules.digitalocean.objects.Meta;

public class KernelCollectionResponse implements Serializable {
	private static final long serialVersionUID = -1L;

	private List<Kernel> kernels;
	
	private Links links;
	
	private Meta meta;

	/**
	 * @return the kernels
	 */
	public List<Kernel> getKernels() {
		return kernels;
	}

	/**
	 * @param kernels the kernels to set
	 */
	public void setKernels(List<Kernel> kernels) {
		this.kernels = kernels;
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
