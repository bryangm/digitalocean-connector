package org.mule.modules.digitalocean.objects;

import java.io.Serializable;

public class Links implements Serializable {
	private static final long serialVersionUID = -1L;

	private Pages pages;

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
