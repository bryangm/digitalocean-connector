package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Droplet;

public class NeighborsCollectionResponse implements Serializable {
	private static final long serialVersionUID = -1L;

	private List<List<Droplet>> neighbors;

	/**
	 * @return the neighbors
	 */
	public List<List<Droplet>> getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors the neighbors to set
	 */
	public void setNeighbors(List<List<Droplet>> neighbors) {
		this.neighbors = neighbors;
	}
	

}
