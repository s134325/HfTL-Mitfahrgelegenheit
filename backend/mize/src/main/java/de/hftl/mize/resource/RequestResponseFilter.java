package de.hftl.mize.resource;

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.google.common.base.Charsets;

@Provider
public class RequestResponseFilter implements ContainerResponseFilter,
		ContainerRequestFilter
{

	private static Logger	LOGGER	= Logger.getRootLogger();

	/**
	 * Request
	 */
	public void filter(ContainerRequestContext requestContext)
			throws IOException
	{
		DateTime dt = DateTime.now();
		UUID uuid = UUID.randomUUID();

		requestContext.setProperty("time", dt);
		requestContext.setProperty("uuid", uuid);

		String build = "\n>>>>>>>>> Request >>>>>>>>>>>>>>>>>>>>>>";
		build += "\nUUID:\t\t" + uuid;
		build += "\nMethod:\t\t" + requestContext.getMethod();
		build += "\nURI:\t\t" + requestContext.getUriInfo().getAbsolutePath();
		build += "\nDate:\t\t" + dt;
		build += "\nDate:\t\t" + IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);;
		build += "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";

		LOGGER.debug(build);
	}

	/**
	 * Response
	 */
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException
	{
		DateTime dtResponse = DateTime.now();
		DateTime dtRequest = (DateTime) requestContext.getProperty("time");
		UUID uuid = (UUID) requestContext.getProperty("uuid");

		responseContext.getHeaders().add("X-Powered-By", "Kapa Eta");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

		String build = "\n<<<<<<<<< Response <<<<<<<<<<<<<<<<<<<<";
		build += "\nUUID:\t\t" + uuid;
		build += "\nDate:\t\t" + DateTime.now();
		build += "\nStatus:\t\t" + responseContext.getStatus();
		build += "\nDuration:\t"
				+ (dtResponse.getMillis() - dtRequest.getMillis()) + "ms";
		build += "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

		LOGGER.debug(build);
	}
}