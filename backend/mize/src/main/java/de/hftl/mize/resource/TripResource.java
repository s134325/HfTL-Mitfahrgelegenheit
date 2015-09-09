package de.hftl.mize.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlTransient;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import de.hftl.mize.model.Trip;
import de.hftl.mize.process.TripHandler;
import de.hftl.mize.response.BaseResponse;
import de.hftl.mize.response.TripResponse;

@Path("/trips")
@Api(value = "/trips", description = "Operations about trips")
@Produces({ MediaType.APPLICATION_JSON })
public class TripResource
{

	@GET
	@Path("/{tripUUID}")
	@ApiOperation(value = "Find trip by UUID", notes = "Returns a trip", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Other Error") })
	@Consumes({ MediaType.WILDCARD })
	public Response getTripByUUID(
			@Context HttpHeaders headers,
			@ApiParam(value = "UUID of trip that needs to be fetched", required = true) @PathParam("tripUUID") String tripUUId)
	{
		return TripHandler.getTrip(headers, tripUUId).build();
	}

	@GET
	@ApiOperation(value = "Get all trips", notes = "Returns a list of all trips in a given radius from a geo coordination", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.WILDCARD })
	public Response getAllTrips(@Context HttpHeaders headers)
	{
		return TripHandler.getTrips(headers).build();
	}

	@GET
	@ApiOperation(value = "Get all trips", notes = "Returns a list of all trips in a given radius from a geo coordination", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.WILDCARD })
	public Response getAllTripsWithGeoCoordinates(
			@Context HttpHeaders headers,
			@ApiParam(value = "Latitude of the Geo Coordinate", required = false) @QueryParam("latitude") Double latitude,
			@ApiParam(value = "Longitude of the Geo Coordinate", required = false) @QueryParam("longitude") Double longitude,
			@ApiParam(value = "The radius with the geo coordinates as center", required = false) @QueryParam("radius") Integer radius)
	{
		return TripHandler.getTrips(headers, latitude, longitude, radius)
				.build();
	}

	@POST
	@ApiOperation(value = "Create a trip", notes = "Creates a new trip based on the JSON", response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	@XmlTransient
	public Response createTrip(@Context HttpHeaders headers, Trip trip)
	{
		return TripHandler.insertTrip(headers, trip).build();
	}

	@PUT
	@Path("/{tripUUID}")
	@ApiOperation(value = "Update a trip", notes = "Updates a trip", response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateTrip(
			@Context HttpHeaders headers,
			Trip trip,
			@ApiParam(value = "UUID of trip that needs to be updated", required = true) @PathParam("tripUUID") String tripUUId)
	{
		return TripHandler.updateTrip(headers, tripUUId, trip).build();
	}

	@DELETE
	@Path("/{tripUUID}")
	@ApiOperation(value = "Deletes a trip", notes = "Deletes a trip", response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.WILDCARD })
	public Response deleteTrip(
			@Context HttpHeaders headers,
			@ApiParam(value = "UUID of trip that needs to be deleted", required = true) @PathParam("tripUUID") String tripUUId)
	{
		return TripHandler.deleteTrip(headers, tripUUId).build();
	}

	@POST
	@Path("/{userUUId}/{tripUUID}")
	@ApiOperation(value = "Book a trip", notes = "Books a trip for the logged user", response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response bookTrip(
			@Context HttpHeaders headers,
			@ApiParam(value = "UUID of the user", required = true) @PathParam("userUUId") String userUUID,
			@ApiParam(value = "UUID of the trip", required = true) @PathParam("tripUUID") String tripUUId)
	{
		return TripHandler.bookTrip(headers, userUUID, tripUUId).build();

	}

	@DELETE
	@Path("/{userUUId}/{tripUUID}")
	@ApiOperation(value = "Unbook a trip", notes = "Unbooks a trip for the logged user", response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	@XmlTransient
	public Response unbookTrip(
			@Context HttpHeaders headers,
			@ApiParam(value = "UUID of the user", required = true) @PathParam("userUUId") String userUUID,
			@ApiParam(value = "UUID of the trip", required = true) @PathParam("tripUUID") String tripUUId)
	{
		return TripHandler.unbookTrip(headers, userUUID, tripUUId).build();

	}
}