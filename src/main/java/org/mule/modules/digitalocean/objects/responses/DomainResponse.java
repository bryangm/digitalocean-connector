package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;

import org.mule.modules.digitalocean.objects.Domain;

public class DomainResponse implements Serializable {
	private static final long serialVersionUID = -1L;

	private Domain domain;

	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
}
