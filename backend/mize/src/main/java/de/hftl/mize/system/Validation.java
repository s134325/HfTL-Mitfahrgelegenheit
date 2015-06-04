/**
 * 
 */
package de.hftl.mize.system;

import java.util.UUID;

import org.apache.log4j.Logger;

import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Trip;

/**
 * This class handles all cases of validation, especially user input
 * 
 * @author Tobias
 *
 */
public class Validation
{
	private static Logger	LOGGER	= Logger.getRootLogger();

	/**
	 * Check whether the provided UUID is valid
	 * 
	 * @param possibleUUID
	 *            String
	 * @return Boolean
	 * @throws ValidationException
	 */
	public static void isUUID(String possibleUUID) throws ValidationException
	{
		try
		{
			LOGGER.debug("Validate UUID: " + possibleUUID);
			UUID.fromString(possibleUUID);
		} catch (Exception ex)
		{
			LOGGER.error("UUID validation failed");
			throw new ValidationException(ValidationException.INVALID_UUID);
		}
	}

	/**
	 * Check whether the provided ISO8601 date and time is valid
	 * 
	 * @param date
	 *            String
	 * @throws ValidationException
	 */
	public static void isISO8601(String date) throws ValidationException
	{
		LOGGER.debug("Validate ISO8601: " + date);

		String regEx = "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$";

		if (date.matches(regEx) == false)
		{
			LOGGER.error("ISO8601 validation failed");
			throw new ValidationException(
					ValidationException.INVALID_ISO8601_TIME);
		}
	}

	/**
	 * Check whether the provided {@link Trip} is valid
	 * 
	 * @param trip
	 *            {@link Trip}
	 * @throws ValidationException
	 */
	public static void isValidTripObject(Trip trip) throws ValidationException
	{
		LOGGER.debug("Validate trip: " + trip.toString());

		if (trip.getActive() == null || trip.getFrom() == null
				|| trip.getTo() == null || trip.getPrice() == null
				|| trip.getFreeSeats() == null || trip.getStartTime() == null)
		{
			LOGGER.error("Trip validation failed.");
			throw new ValidationException(ValidationException.INVALID_TRIP);
		}

		if (trip.getPrice() < 0 || trip.getFreeSeats() < 0)
		{
			LOGGER.error("Trip validation failed.");
			throw new ValidationException(ValidationException.INVALID_TRIP);
		}
	}
}