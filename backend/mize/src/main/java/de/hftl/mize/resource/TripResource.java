package de.hftl.mize.resource;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

import org.apache.log4j.Logger;

import com.wordnik.swagger.annotations.*;

import de.hftl.mize.dao.TripDAO;
import de.hftl.mize.model.Trip;
import de.hftl.mize.response.TripResponse;

@Path("/trips")
@Api(value = "/trips", description = "Operations about trips")
@Produces({ MediaType.APPLICATION_JSON })
public class TripResource {
	private static Logger	LOGGER	= Logger.getRootLogger();

	@GET
	@Path("/{tripUUID}")
	@ApiOperation(value = "Find trip by UUID", notes = "Returns a trip", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getTripByUUID(
			@ApiParam(value = "UUID of trip that needs to be fetched", required = true) @PathParam("tripUUID") String tripUUId)
	{
		TripResponse response = new TripResponse();

		TripDAO t = new TripDAO();

		response.setTrip(t.getTrip(tripUUId));

		return Response.status(200).entity(response).build();

	}

	@GET
	@ApiOperation(value = "Get all trips", notes = "Returns a list of all trips in a given radius from a geo coordination", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getAllTripsWithGeoCoordinates(
			@ApiParam(value = "Latitude of the Geo Coordinate", required = false) @QueryParam("latitude") Double latitude,
			@ApiParam(value = "Longitude of the Geo Coordinate", required = false) @QueryParam("longitude") Double longitude,
			@ApiParam(value = "The radius with the geo coordinates as center", required = false) @QueryParam("radius") Integer radius)
	{
		TripResponse response = new TripResponse();

		TripDAO t = new TripDAO();

		response.setTrips(t.getTrips(latitude, longitude, radius));

		return Response.status(200).entity(response).build();

	}

	@POST
	@ApiOperation(value = "Create a trip", notes = "Creates a new trip based on the JSON", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createTrip(Trip trip)
	{
		TripResponse response = new TripResponse();

		LOGGER.debug("POST trip " + trip.toString());

		return Response.status(200).entity(response).build();
	}

	@PUT
	@Path("/{tripUUID}")
	@ApiOperation(value = "Update a trip", notes = "Updates a trip", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateTrip(
			Trip trip,
			@ApiParam(value = "UUID of trip that needs to be updated", required = true) @PathParam("tripUUID") String tripUUId)
	{
		TripResponse response = new TripResponse();

		LOGGER.debug("PUT trip " + tripUUId);

		return Response.ok().entity(response).build();
	}

	@DELETE
	@Path("/{tripUUID}")
	@ApiOperation(value = "Deletes a trip", notes = "Deletes a trip", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteTrip(
			@ApiParam(value = "UUID of trip that needs to be deleted", required = true) @PathParam("tripUUID") String tripUUId)
	{
		TripResponse response = new TripResponse();

		LOGGER.debug("DELETE trip " + tripUUId);

		return Response.status(200).entity(response).build();

	}
}