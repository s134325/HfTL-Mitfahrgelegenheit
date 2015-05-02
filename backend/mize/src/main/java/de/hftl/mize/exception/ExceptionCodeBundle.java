package de.hftl.mize.exception;

import java.util.ListResourceBundle;

public class ExceptionCodeBundle extends ListResourceBundle
{

	private static final Object[][]	CONTENTS	= {
			// ####### BusinessExceptions

			// Trip Exceptions
			{ BusinessException.TRIP_NOT_FOUND, new Integer(404) },
			{ BusinessException.TRIP_CREATE_FAILED, new Integer(500) },
			{ BusinessException.TRIP_UPDATE_FAILED, new Integer(500) },
			{ BusinessException.TRIP_DELETE_FAILED, new Integer(500) },

			// Vehicle Exceptions
			{ BusinessException.VEHICLE_NOT_FOUND, new Integer(404) },
			{ BusinessException.VEHICLE_CREATE_FAILED, new Integer(500) },
			{ BusinessException.VEHICLE_UPDATE_FAILED, new Integer(500) },
			{ BusinessException.VEHICLE_DELETE_FAILED, new Integer(500) },

			// User Exceptions
			{ BusinessException.USER_NOT_FOUND, new Integer(404) },

			// System Exceptions
			{ BusinessException.MYSQL_ERROR, new Integer(500) },
			{ BusinessException.SYSTEM_ERROR, new Integer(500) },

			// ####### ValidationExceptions
			{ ValidationException.INVALID_UUID, new Integer(400) }

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
