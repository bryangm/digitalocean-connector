package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import com.google.gson.annotations.SerializedName;

public class Size implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#sizes
	 */
	
    private String slug;
    
	private int memory;
	
	private int vcpus;
	
	private int disk;
	
	private BigDecimal transfer;
	
	@SerializedName("price_monthly")
	private BigDecimal priceMonthly;
	
	@SerializedName("price_hourly")
	private BigDecimal priceHourly;
	
	private String[] regions;
	
	private boolean available;

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the memory
	 */
	public int getMemory() {
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	public void setMemory(int memory) {
		this.memory = memory;
	}

	/**
	 * @return the vcpus
	 */
	public int getVcpus() {
		return vcpus;
	}

	/**
	 * @param vcpus the vcpus to set
	 */
	public void setVcpus(int vcpus) {
		this.vcpus = vcpus;
	}

	/**
	 * @return the disk
	 */
	public int getDisk() {
		return disk;
	}

	/**
	 * @param disk the disk to set
	 */
	public void setDisk(int disk) {
		this.disk = disk;
	}

	/**
	 * @return the transfer
	 */
	public BigDecimal getTransfer() {
		return transfer;
	}

	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(BigDecimal transfer) {
		this.transfer = transfer;
	}

	/**
	 * @return the priceMonthly
	 */
	public BigDecimal getPriceMonthly() {
		return priceMonthly;
	}

	/**
	 * @param priceMonthly the priceMonthly to set
	 */
	public void setPriceMonthly(BigDecimal priceMonthly) {
		this.priceMonthly = priceMonthly;
	}

	/**
	 * @return the priceHourly
	 */
	public BigDecimal getPriceHourly() {
		return priceHourly;
	}

	/**
	 * @param priceHourly the priceHourly to set
	 */
	public void setPriceHourly(BigDecimal priceHourly) {
		this.priceHourly = priceHourly;
	}

	/**
	 * @return the regions
	 */
	public String[] getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(String[] regions) {
		this.regions = regions;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
