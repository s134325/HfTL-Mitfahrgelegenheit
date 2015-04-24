/**
 * 
 */
package de.hftl.mize.exception;


/**
 * @author tokilian
 *
 */
public class BusinessException extends BaseBusinessException {

	private static final long	serialVersionUID	= -7982401511225468802L;

	public static final String	TRIP_NOT_FOUND		= "TRIP.NOT.FOUND";
	public static final String	TRIP_UPDATE_FAILED	= "TRIP.UPDATE.FAILED";
	public static final String	TRIP_DELETE_FAILED	= "TRIP.DELETE.FAILED";
	public static final String	MYSQL_ERROR			= "MYSQL.RROR";

	public BusinessException(String errorCode) {
		super(errorCode, null);
	}

	public BusinessException(String errorCode, String message) {
		super(errorCode, message, null);
	}

	public BusinessException(String errorCode, String message,
			final Throwable rootCause) {
		super(errorCode, message, rootCause);
	}

}
