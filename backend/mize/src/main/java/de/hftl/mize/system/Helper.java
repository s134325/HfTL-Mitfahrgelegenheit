package de.hftl.mize.system;

import java.sql.Timestamp;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import de.hftl.mize.exception.BaseBusinessException;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ExceptionCodeBundle;
import de.hftl.mize.exception.ExceptionResourceBundle;
import de.hftl.mize.model.Status;
import de.hftl.mize.response.BaseResponse;

/**
 * A class which provides little Helper methods
 * 
 * @author Tobias
 *
 */
public class Helper
{

	private static Logger					LOGGER	= Logger.getRootLogger();

	private static ExceptionResourceBundle	erb		= new ExceptionResourceBundle();
	private static ExceptionCodeBundle		ecb		= new ExceptionCodeBundle();

	/**
	 * Build the response in case of an error
	 * 
	 * @param bbe
	 *            {@link BaseBusinessException}
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder buildErrorResponse(BaseBusinessException bbe)
	{
		BaseResponse br = new BaseResponse(new Status(bbe.getErrorCode(),
				erb.getString(bbe.getErrorCode())));

		return Response.status((Integer) ecb.getObject(bbe.getErrorCode()))
				.entity(br);
	}

	/**
	 * Converts a String into an {@link UUID}
	 * 
	 * @param oldString
	 *            String
	 * @return {@link UUID}
	 * @throws BusinessException
	 */
	public static UUID stringToUUID(String oldString) throws BusinessException
	{
		try
		{
			return UUID.fromString(oldString);
		} catch (IllegalArgumentException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.SYSTEM_ERROR);
		}
	}

	public static Timestamp convertISO8601ToTimestamp(String jtdate)
	{
		DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();

		Timestamp t = new Timestamp(parser2.parseMillis(jtdate));

		return t;
	}

	public static String retrieveUserUUID(HttpHeaders headers)
			throws BusinessException
	{
		try
		{
			return headers.getRequestHeader("x-uuid").get(0);
		} catch (IllegalStateException | IndexOutOfBoundsException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.SYSTEM_ERROR);
		}
	}

	/**
	 * Get the id of the currently logged in user
	 * 
	 * @return Integer The internal ID
	 */
	public static Integer getIdOfCurrentUser()
	{
		// TODO Implement
		return 0;
	}

}
