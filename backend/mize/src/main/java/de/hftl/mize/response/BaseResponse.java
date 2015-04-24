package de.hftl.mize.response;

import de.hftl.mize.model.Status;

public class BaseResponse {

	public Status status;
	public String resourceId;

	public BaseResponse() {
		// TODO Auto-generated constructor stub
	}

	public BaseResponse(Status status) {
		super();
		this.status = status;
	}

	public BaseResponse(Status status, String resourceId) {
		super();
		this.status = status;
		this.resourceId = resourceId;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId
	 *            the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Response [status=" + status + ", resourceId=" + resourceId
				+ "]";
	}

}
