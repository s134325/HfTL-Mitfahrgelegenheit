/**
 * 
 */
package de.hftl.mize.system;

import java.util.UUID;

/**
 * This class handles all cases of validation, especially user input
 * 
 * @author Tobias
 *
 */
public class Validation
{
	/**
	 * Check whether the provided UUID is valid
	 * 
	 * @param possibleUUID
	 *            String
	 * @return Boolean
	 */
	public static Boolean isUUID(String possibleUUID)
	{
		try
		{
			UUID.fromString(possibleUUID);
			return true;
		} catch (Exception ex)
		{
			return false;
		}
	}
}