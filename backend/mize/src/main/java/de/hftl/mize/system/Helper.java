package de.hftl.mize.system;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import de.hftl.mize.exception.BaseBusinessException;
import de.hftl.mize.exception.ExceptionCodeBundle;
import de.hftl.mize.exception.ExceptionResourceBundle;
import de.hftl.mize.model.Status;
import de.hftl.mize.response.BaseResponse;

public class Helper {

	private static Logger					LOGGER	= Logger.getRootLogger();
	private static ExceptionResourceBundle	erb		= new ExceptionResourceBundle();
	private static ExceptionCodeBundle		ecb		= new ExceptionCodeBundle();

	public static ResponseBuilder buildErrorResponse(BaseBusinessException bbe)
	{
		BaseResponse br = new BaseResponse(new Status(bbe.getErrorCode(),
				erb.getString(bbe.getErrorCode())));

		LOGGER.error(bbe.getClass().getTypeName() + br.toString());

		return Response.status((Integer) ecb.getObject(bbe.getErrorCode()))
				.entity(br);
	}

}
