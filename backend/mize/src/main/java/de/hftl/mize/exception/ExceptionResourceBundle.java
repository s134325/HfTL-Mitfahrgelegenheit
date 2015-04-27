/**
 * 
 */
package de.hftl.mize.exception;

import java.util.ListResourceBundle;

/**
 * @author tokilian
 *
 */
public class ExceptionResourceBundle extends ListResourceBundle {

	private static final Object[][]	CONTENTS	= {
			// BusinessException
			{ BusinessException.TRIP_NOT_FOUND, "Trip does not exist." },
			{ BusinessException.TRIP_UPDATE_FAILED, "Trip update failed." },
			{ BusinessException.TRIP_DELETE_FAILED, "Trip delete failed." },

			{ BusinessException.VEHICLE_NOT_FOUND, "Vehicle does not exist." },
			{ BusinessException.VEHICLE_UPDATE_FAILED, "Vehicle update failed." },
			{ BusinessException.VEHICLE_DELETE_FAILED, "Vehicle delete failed." },

			{ BusinessException.USER_NOT_FOUND, "User does not exist." },

			{ BusinessException.MYSQL_ERROR, "Internal Server Error." } };

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
