package org.mule.modules.digitalocean.objects;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Pages implements Serializable {
	private static final long serialVersionUID = -1L;

	private String first;
	
	@SerializedName("prev")
	private String previous;
	
	private String next;
	
	private String last;
	
	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	/**
	 * @return the previous
	 */
	public String getPrevious() {
		return previous;
	}
	/**
	 * @param previos the previous to set
	 */
	public void setPrevious(String previos) {
		this.previous = previos;
	}
	/**
	 * @return the next
	 */
	public String getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(String next) {
		this.next = next;
	}
	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}
	/**
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	
}
