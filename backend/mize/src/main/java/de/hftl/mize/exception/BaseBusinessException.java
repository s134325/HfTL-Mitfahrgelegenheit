/**
 * 
 */
package de.hftl.mize.exception;

/**
 * @author tokilian
 *
 */
public class BaseBusinessException extends Exception {

	private static final long serialVersionUID = -5535658615392126824L;

	private final String errorCode;

	public BaseBusinessException(String errorCode) {
		this(errorCode, null);
	}

	public BaseBusinessException(String errorCode, String message) {
		this(errorCode, message, null);
	}

	public BaseBusinessException(String errorCode, String message,
			final Throwable rootCause) {
		super(message, rootCause);
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessException [errorCode=");
		builder.append(errorCode);
		builder.append("]");
		return builder.toString();
	}

}
