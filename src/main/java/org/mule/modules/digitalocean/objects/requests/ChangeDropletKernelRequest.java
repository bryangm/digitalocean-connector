package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

import org.mule.modules.digitalocean.common.ChangeKernelType;

import com.google.gson.annotations.SerializedName;

public class ChangeDropletKernelRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private ChangeKernelType type;
	@SerializedName("kernel")
	private Integer kernelId;

	/**
	 * @return the type
	 */
	public ChangeKernelType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ChangeKernelType type) {
		this.type = type;
	}

	/**
	 * @return the kernel
	 */
	public Integer getKernelId() {
		return kernelId;
	}

	/**
	 * @param kernel the kernel to set
	 */
	public void setKernel(Integer kernelId) {
		this.kernelId = kernelId;
	}
}
