package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;

import org.mule.modules.digitalocean.objects.Action;

public class ActionResponse implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private Action action;

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}
}
