package org.mule.modules.digitalocean.common;

import java.io.Serializable;

public enum DomainRecordType implements Serializable {
	A, 
	AAAA, 
	CNAME, 
	MX, 
	TXT, 
	SRV, 
	NS;
}
