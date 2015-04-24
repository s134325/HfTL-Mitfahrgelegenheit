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
			{ BusinessException.TRIP_DELETE_FAILED, "Trip delte failed." },
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
