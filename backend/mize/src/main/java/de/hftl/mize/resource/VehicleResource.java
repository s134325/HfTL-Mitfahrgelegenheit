package de.hftl.mize.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import de.hftl.mize.model.Vehicle;
import de.hftl.mize.process.VehicleHandler;
import de.hftl.mize.response.TripResponse;
import de.hftl.mize.response.VehicleResponse;

@Path("/vehicles")
@Api(value = "/vehicles", description = "Operations about vehicle")
@Produces({ MediaType.APPLICATION_JSON })
public class VehicleResource {

	@GET
	@Path("/id/{vehicleUUID}")
	@ApiOperation(value = "Find vehicle by UUID", notes = "Returns a vehicle by UUID if the user is allowed to retrieve the information", response = VehicleResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Other Error") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getVehicleById(
			@ApiParam(value = "ID of the vehicle that needs to be fetched", required = true) @PathParam("vehicleUUID") String vehicleUUID)
	{
		return VehicleHandler.getVehicle(vehicleUUID).build();
	}

	@GET
	@Path("/user/{userUUID}")
	@ApiOperation(value = "Find vehicles by UUID of an user", notes = "Returns all vehicles by user UUID if the user is allowed to retrieve the information", response = VehicleResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Other Error") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getVehiclesByUserId(
			@ApiParam(value = "ID of the user that needs to be fetched", required = true) @PathParam("userUUID") String userUUID)
	{
		return VehicleHandler.getVehiclesByUserId(userUUID).build();
	}

	@POST
	@ApiOperation(value = "Create a vehicle", notes = "Creates a new vehicle based on the JSON", response = VehicleResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createTrip(Vehicle vehicle)
	{
		return VehicleHandler.insertVehicle(vehicle).build();
	}

	@PUT
	@Path("/{vehicleUUID}")
	@ApiOperation(value = "Update a vehicle", notes = "Updates a vehicle", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateVehicle(
			Vehicle vehicle,
			@ApiParam(value = "UUID of vehicle that needs to be updated", required = true) @PathParam("vehicleUUID") String vehicleUUID)
	{
		return VehicleHandler.updateVehicle(vehicleUUID, vehicle).build();
	}

	@DELETE
	@Path("/{vehicleUUID}")
	@ApiOperation(value = "Deletes a vehicle", notes = "Deletes a vehicle by UUID", response = TripResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Trip not found") })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deleteVehicle(
			@ApiParam(value = "UUID of vehicle that needs to be deleted", required = true) @PathParam("vehicleUUID") String vehicleUUID)
	{
		return VehicleHandler.deleteVehicle(vehicleUUID).build();
	}

}
