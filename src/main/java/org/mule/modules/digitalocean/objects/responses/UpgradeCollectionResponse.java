package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;
import java.util.List;

import org.mule.modules.digitalocean.objects.Upgrade;

public class UpgradeCollectionResponse implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private List<Upgrade> upgrades;

	/**
	 * @return the upgrades
	 */
	public List<Upgrade> getUpgrades() {
		return upgrades;
	}

	/**
	 * @param upgrades the upgrades to set
	 */
	public void setUpgrades(List<Upgrade> upgrades) {
		this.upgrades = upgrades;
	}
}
