package de.hftl.mize.model;

public class Status
{
	public String	code;
	public String	message;

	public Status() {
		super();
	}

	public Status(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Status [code=" + code + ", message=" + message + "]";
	}

}
