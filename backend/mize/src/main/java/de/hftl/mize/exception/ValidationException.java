package de.hftl.mize.exception;

/**
 * Holds all business exceptions
 * 
 * @author Tobias
 *
 */
public class ValidationException extends BaseBusinessException
{
	private static final long	serialVersionUID	= -8841134996304679160L;

	public static final String	INVALID_UUID		= "INVALID.UUID";

	public ValidationException(String errorCode)
	{
		super(errorCode);
	}

	public ValidationException(String errorCode, String message)
	{
		super(errorCode, message);
	}

	public ValidationException(String errorCode, String message,
			final Throwable rootCause)
	{
		super(errorCode, message, rootCause);
	}

}