package de.hftl.mize.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

import de.hftl.mize.model.User;
import de.hftl.mize.process.UserHandler;
import de.hftl.mize.response.BaseResponse;
import de.hftl.mize.response.UserResponse;

@Path("/user")
@Api(value = "/user", description = "User interactions")
@Produces({ MediaType.APPLICATION_JSON })
public class UserResource
{

	@GET
	@Path("/{userUUID}")
	@ApiOperation(value = "Find user by UUID", notes = "Returns a user",
			response = UserResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Other Error") })
	@Consumes({ MediaType.WILDCARD })
	public Response getUserByUUID(@Context HttpHeaders headers,
			@ApiParam(value = "UUID of a user that needs to be fetched",
					required = true) @PathParam("userUUID") String userUUID)
	{
		return UserHandler.getUserByUUID(headers, userUUID).build();
	}


	@POST
	@ApiOperation(value = "Create a user",
			notes = "Creates a new user",
			response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied")})
	@Consumes({ MediaType.WILDCARD })
	@XmlTransient
	public Response createUser(User user)
	{
		return UserHandler.insertUser(user).build();
	}
	
	@POST
	@Path("/login")
	@ApiOperation(value = "Login",
			notes = "Login",
			response = BaseResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid ID supplied")})
	@Consumes({ MediaType.WILDCARD })
	public Response loginUser(User user)
	{
		return UserHandler.loginUser(user).build();
	}
}