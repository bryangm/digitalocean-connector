package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.DomainRecord;
import org.mule.modules.digitalocean.objects.Links;
import org.mule.modules.digitalocean.objects.Meta;

import com.google.gson.annotations.SerializedName;

public class DomainRecordCollectionResponse implements Serializable {
	private static final long serialVersionUID = -1L;

	@SerializedName("domain_records")
	private List<DomainRecord> domainsRecords;
	
	private Links links;
	
	private Meta meta;

	/**
	 * @return the domainsRecords
	 */
	public List<DomainRecord> getDomainsRecords() {
		return domainsRecords;
	}

	/**
	 * @param domainsRecords the domainsRecords to set
	 */
	public void setDomainsRecords(List<DomainRecord> domainsRecords) {
		this.domainsRecords = domainsRecords;
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
