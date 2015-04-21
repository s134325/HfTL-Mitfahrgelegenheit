package de.hftl.mize.response;

import de.hftl.mize.model.Status;

public class Response
{

	public Status	status;

	public Response() {
		// TODO Auto-generated constructor stub
	}

	public Response(Status status) {
		super();
		this.status = status;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Response [status=" + status + "]";
	}

}
