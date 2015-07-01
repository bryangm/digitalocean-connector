package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;

import org.mule.modules.digitalocean.objects.DomainRecord;

import com.google.gson.annotations.SerializedName;

public class DomainRecordResponse implements Serializable {
	private static final long serialVersionUID = -1L;

	@SerializedName("domain_record")
	private DomainRecord domainRecord;

	/**
	 * @return the domainRecord
	 */
	public DomainRecord getDomainRecord() {
		return domainRecord;
	}

	/**
	 * @param domainRecord the domainRecord to set
	 */
	public void setDomainRecord(DomainRecord domainRecord) {
		this.domainRecord = domainRecord;
	}
}
