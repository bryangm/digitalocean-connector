package org.mule.modules.digitalocean.objects.responses;

import java.io.Serializable;

import org.mule.modules.digitalocean.objects.Image;

public class ImageResponse implements Serializable {
	private static final long serialVersionUID = -1L;
	
	private Image image;

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
}
