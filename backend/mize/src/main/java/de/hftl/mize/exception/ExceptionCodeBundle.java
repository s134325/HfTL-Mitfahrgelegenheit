package de.hftl.mize.exception;

import java.util.ListResourceBundle;

public class ExceptionCodeBundle extends ListResourceBundle {

	private static final Object[][]	CONTENTS	= {
			// BusinessException
			{ BusinessException.TRIP_NOT_FOUND, new Integer(404) },
			{ BusinessException.TRIP_UPDATE_FAILED, new Integer(500) },
			{ BusinessException.TRIP_DELETE_FAILED, new Integer(500) },

			{ BusinessException.VEHICLE_NOT_FOUND, new Integer(404) },
			{ BusinessException.VEHICLE_UPDATE_FAILED, new Integer(500) },
			{ BusinessException.VEHICLE_DELETE_FAILED, new Integer(500) },

			{ BusinessException.USER_NOT_FOUND, new Integer(404) },

			{ BusinessException.MYSQL_ERROR, new Integer(500) } };

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
