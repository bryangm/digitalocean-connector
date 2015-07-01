package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class CreateKeyRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String name;
	
	@SerializedName("public_key")
	private String publicKey;

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
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
