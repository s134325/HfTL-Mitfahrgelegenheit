/**
 * 
 */
package de.hftl.mize.exception;

import java.util.ListResourceBundle;

/**
 * @author tokilian
 *
 */
public class ExceptionResourceBundle extends ListResourceBundle
{

	private static final Object[][]	CONTENTS	= {
			// ####### BusinessExceptions

			// Trip Exceptions
			{ BusinessException.TRIP_NOT_FOUND, "Trip does not exist." },
			{ BusinessException.TRIP_CREATE_FAILED, "Trip create failed." },
			{ BusinessException.TRIP_UPDATE_FAILED, "Trip update failed." },
			{ BusinessException.TRIP_DELETE_FAILED, "Trip delete failed." },

			// Vehicle Exceptions
			{ BusinessException.VEHICLE_NOT_FOUND, "Vehicle does not exist." },
			{ BusinessException.VEHICLE_CREATE_FAILED, "Vehicle create failed." },
			{ BusinessException.VEHICLE_UPDATE_FAILED, "Vehicle update failed." },
			{ BusinessException.VEHICLE_DELETE_FAILED, "Vehicle delete failed." },
			{ BusinessException.VEHICLE_LITIM_EXCEEDED,
			"Vehicle limit exceeded." },

			// User Exceptions
			{ BusinessException.USER_NOT_FOUND, "User does not exist." },

			// System Exceptions
			{ BusinessException.MYSQL_ERROR, "Internal Server Error." },
			{ BusinessException.SYSTEM_ERROR, "Internal Server Error." },

			// ####### ValidationExceptions
			{ ValidationException.INVALID_UUID,
			"The UUID you provided is invalid." },
			{ ValidationException.INVALID_ISO8601_TIME,
			"The time you provided is invalid." },
			{ ValidationException.INVALID_TRIP,
			"The trip you provided is invalid." }

												};

	@Override
	protected Object[][] getContents()
	{
		return copyArray(CONTENTS);
	}

	private static Object[][] copyArray(final Object[][] origin)
	{
		final Object[][] copy = new Object[origin.length][origin[0].length];
		for (int i = 0; i < origin.length; ++i)
		{
			System.arraycopy(origin[i], 0, copy[i], 0, origin[i].length);
		}
		return copy;
	}

}
