package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Droplet implements Serializable {
	private static final long serialVersionUID = -1L;
	
	/*
	 *	This is the account object used by DigitalOcean
	 *	See documentation at https://developers.digitalocean.com/documentation/v2/#droplets
	 */
	
	private Integer id;
	
	private String name;
	
	private Integer memory;
	
	private Integer vcpus;
	
	private Integer disk;
	
	private Boolean locked;
	
	@SerializedName("created_at")
	private Date createdAt;
	
	private String status;
	
	@SerializedName("backup_ids")
	private List<Integer> backupIds;
	
	@SerializedName("snapshot_ids")
	private List<Integer> snapshotIds;
	
	private List<String> features;
	
	private Region region;
	
	private Size size;
	
	@SerializedName("size_slug")
	private String sizeSlug;
	
	private Networks networks;
	
	private Kernel kernel;
	
	@SerializedName("next_backup_window")
	private BackupWindow nextBackupWindow;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the memory
	 */
	public Integer getMemory() {
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	/**
	 * @return the vcpus
	 */
	public Integer getVcpus() {
		return vcpus;
	}

	/**
	 * @param vcpus the vcpus to set
	 */
	public void setVcpus(Integer vcpus) {
		this.vcpus = vcpus;
	}

	/**
	 * @return the disk
	 */
	public Integer getDisk() {
		return disk;
	}

	/**
	 * @param disk the disk to set
	 */
	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	/**
	 * @return the locked
	 */
	public Boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the backups
	 */
	public List<Integer> getBackupIds() {
		return backupIds;
	}

	/**
	 * @param backups the backups to set
	 */
	public void setBackupIds(List<Integer> backups) {
		this.backupIds = backups;
	}

	/**
	 * @return the snapshots
	 */
	public List<Integer> getSnapshotIds() {
		return snapshotIds;
	}

	/**
	 * @param snapshots the snapshots to set
	 */
	public void setSnapshotIds(List<Integer> snapshots) {
		this.snapshotIds = snapshots;
	}

	/**
	 * @return the features
	 */
	public List<String> getFeatures() {
		return features;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(List<String> features) {
		this.features = features;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Size size) {
		this.size = size;
	}

	/**
	 * @return the sizeSlug
	 */
	public String getSizeSlug() {
		return sizeSlug;
	}

	/**
	 * @param sizeSlug the sizeSlug to set
	 */
	public void setSizeSlug(String sizeSlug) {
		this.sizeSlug = sizeSlug;
	}

	/**
	 * @return the networks
	 */
	public Networks getNetworks() {
		return networks;
	}

	/**
	 * @param networks the networks to set
	 */
	public void setNetworks(Networks networks) {
		this.networks = networks;
	}

	/**
	 * @return the kernel
	 */
	public Kernel getKernel() {
		return kernel;
	}

	/**
	 * @param kernel the kernel to set
	 */
	public void setKernel(Kernel kernel) {
		this.kernel = kernel;
	}

	/**
	 * @return the nextBackupWindow
	 */
	public BackupWindow getNextBackupWindow() {
		return nextBackupWindow;
	}

	/**
	 * @param nextBackupWindow the nextBackupWindow to set
	 */
	public void setNextBackupWindow(BackupWindow nextBackupWindow) {
		this.nextBackupWindow = nextBackupWindow;
	}

}
