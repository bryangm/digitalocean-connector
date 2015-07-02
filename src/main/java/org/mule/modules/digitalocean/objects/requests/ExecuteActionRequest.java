package org.mule.modules.digitalocean.objects.requests;

import java.io.Serializable;

public class ExecuteActionRequest implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private String type;
	
	private Integer image;
	
	private Boolean disk;
	
	private String size;
	
	private String name;
	
	private Integer kernel;
	
	private String region;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the image
	 */
	public Integer getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Integer image) {
		this.image = image;
	}

	/**
	 * @return the disk
	 */
	public Boolean isDisk() {
		return disk;
	}

	/**
	 * @param disk the disk to set
	 */
	public void setDisk(Boolean disk) {
		this.disk = disk;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

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
	 * @return the kernel
	 */
	public Integer getKernel() {
		return kernel;
	}

	/**
	 * @param kernel the kernel to set
	 */
	public void setKernel(Integer kernel) {
		this.kernel = kernel;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
}
