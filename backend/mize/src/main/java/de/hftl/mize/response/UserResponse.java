/**
 * 
 */
package de.hftl.mize.response;

import de.hftl.mize.model.Status;

/**
 * @author Tobias
 *
 */
public class UserResponse
{
	public String	uuid;
	public Status	status;

	/**
	 * 
	 */
	public UserResponse()
	{
	}

	/**
	 * @param uuid
	 * @param status
	 */
	public UserResponse(String uuid, Status status)
	{
		this.uuid = uuid;
		this.status = status;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid()
	{
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
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
}
