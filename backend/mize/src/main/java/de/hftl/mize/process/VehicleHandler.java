package de.hftl.mize.process;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import de.hftl.mize.dao.VehicleDAO;
import de.hftl.mize.dao.i.IVehicleDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Vehicle;
import de.hftl.mize.response.BaseResponse;
import de.hftl.mize.response.VehicleResponse;
import de.hftl.mize.system.Helper;

public class VehicleHandler {
	/**
	 * Builds the response and returns a {@link VehicleResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder getVehicle(String vehicleUUID)
	{
		try
		{
			VehicleResponse response = new VehicleResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Vehicle vehicle = vehicleDAO.getVehicle(vehicleUUID);

			response.setVehicle(vehicle);

			return Response.status(200).entity(response);
		}
		catch (BusinessException be)
		{
			return Helper.buildErrorResponse(be);
		}

	}

	/**
	 * Builds the response and returns a {@link VehicleResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param userUUID
	 *            The external {@link UUID} of the user
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder getVehiclesByUserId(String userUUID)
	{
		try
		{
			VehicleResponse response = new VehicleResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			ArrayList<Vehicle> vehicles = vehicleDAO
					.getVehiclesByUserId(userUUID);

			response.setVehicles(vehicles);

			return Response.status(200).entity(response);
		}
		catch (BusinessException be)
		{
			return Helper.buildErrorResponse(be);
		}
	}

	/**
	 * Builds the response and returns a {@link BaseResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicle
	 *            The {@link Vehicle} object
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder insertVehicle(Vehicle vehicle)
	{
		try
		{
			BaseResponse response = new BaseResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			UUID vehicles = vehicleDAO.insertVehicle(vehicle);

			response.setResourceId(vehicles.toString());

			return Response.status(201).entity(response);
		}
		catch (BusinessException be)
		{
			return Helper.buildErrorResponse(be);
		}
	}

	/**
	 * Builds the response and returns a {@link BaseResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle
	 * @param vehicle
	 *            The {@link Vehicle} object
	 * 
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder updateVehicle(String vehicleUUID,
			Vehicle vehicle)
	{
		try
		{
			BaseResponse response = new BaseResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Boolean isUpdated = vehicleDAO.updateVehicle(
					UUID.fromString(vehicleUUID), vehicle);

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.VEHICLE_UPDATE_FAILED);
			}

			response.setResourceId(vehicleUUID);

			return Response.status(200).entity(response);
		}
		catch (BusinessException be)
		{
			return Helper.buildErrorResponse(be);
		}
	}

	/**
	 * Builds the response and returns a {@link BaseResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder deleteVehicle(String vehicleUUID)
	{
		try
		{
			BaseResponse response = new BaseResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Boolean isUpdated = vehicleDAO.deleteVehicle(UUID
					.fromString(vehicleUUID));

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.VEHICLE_DELETE_FAILED);
			}

			response.setResourceId(vehicleUUID);

			return Response.status(200).entity(response);
		}
		catch (BusinessException be)
		{
			return Helper.buildErrorResponse(be);
		}
	}
}
