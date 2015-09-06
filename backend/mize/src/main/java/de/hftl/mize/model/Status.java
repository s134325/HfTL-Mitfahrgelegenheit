package de.hftl.mize.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a status
 * 
 * @author Tobias
 *
 */
@XmlRootElement
public class Status
{
	@XmlAttribute public String	code;
	@XmlAttribute public String	message;

	/**
	 * Constructor.
	 */
	public Status()
	{
		super();
	}

	/**
	 * Constructor.
	 * @param code
	 *            String
	 * @param message
	 *            String
	 */
	public Status(String code, String message)
	{
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	@XmlTransient
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	@XmlTransient
	public void setCode(String code)
	{
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Status [code=" + code + ", message=" + message + "]";
	}

}
