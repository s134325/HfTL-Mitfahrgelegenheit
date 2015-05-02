package de.hftl.mize.response;

import de.hftl.mize.model.Status;
import de.hftl.mize.model.Trip;

/**
 * Represents a not specified response.
 * 
 * This response is mostly used when an error occurs or when a resource only
 * needs to return a resource id, e.g. of a new {@link Trip}
 * 
 * @author Tobias
 *
 */
public class BaseResponse
{

	public Status	status;
	public String	resourceId;

	public BaseResponse()
	{
	}

	public BaseResponse(Status status)
	{
		super();
		this.status = status;
	}

	public BaseResponse(Status status, String resourceId)
	{
		super();
		this.status = status;
		this.resourceId = resourceId;
	}

	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId()
	{
		return resourceId;
	}

	/**
	 * @param resourceId
	 *            the resourceId to set
	 */
	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Response [status=" + status + ", resourceId=" + resourceId
				+ "]";
	}

}
