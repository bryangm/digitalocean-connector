package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;

import org.mule.modules.digitalocean.objects.Droplet;

public class DropletResponse implements Serializable {
	private static final long serialVersionUID = -1L;

	private Droplet droplet;

	/**
	 * @return the droplet
	 */
	public Droplet getDroplet() {
		return droplet;
	}

	/**
	 * @param droplet the droplet to set
	 */
	public void setDroplet(Droplet droplet) {
		this.droplet = droplet;
	}
}
