package de.hftl.mize.process;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import de.hftl.mize.dao.VehicleDAO;
import de.hftl.mize.dao.i.IVehicleDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Status;
import de.hftl.mize.model.Vehicle;
import de.hftl.mize.response.BaseResponse;
import de.hftl.mize.response.VehicleResponse;
import de.hftl.mize.system.Helper;
import de.hftl.mize.system.StatusList;
import de.hftl.mize.system.Validation;

public class VehicleHandler
{
	/**
	 * Builds the response and returns a {@link VehicleResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle as String
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder getVehicle(String vehicleUUID)
	{
		try
		{
			Validation.isUUID(vehicleUUID);

			VehicleResponse response = new VehicleResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Vehicle vehicle = vehicleDAO.getVehicle(vehicleUUID);

			response.setStatus(new Status(StatusList.SUCCESS,
					"The vehicle was retrieved successfully"));

			response.setVehicle(vehicle);

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
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
			Validation.isUUID(userUUID);

			VehicleResponse response = new VehicleResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			ArrayList<Vehicle> vehicles = vehicleDAO
					.getVehiclesByUserId(userUUID);

			response.setStatus(new Status(StatusList.SUCCESS,
					"The vehicles of the the user were retrieved successfully"));

			response.setVehicles(vehicles);

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
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
			Validation.isUUID(vehicle.getUserId());

			BaseResponse response = new BaseResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Integer userId = Helper.getIdOfCurrentUser();

			// TODO: Check if the logged in user is allowed to add a vehicle to
			// the given user

			UUID vehicleUUID = vehicleDAO.insertVehicle(vehicle);

			response.setStatus(new Status(StatusList.SUCCESS,
					"The vehicle was inserted successfully"));

			response.setResourceId(vehicleUUID.toString());

			return Response.status(201).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	/**
	 * Builds the response and returns a {@link BaseResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle as String
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
			Validation.isUUID(vehicleUUID);

			BaseResponse response = new BaseResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Boolean isUpdated = vehicleDAO.updateVehicle(vehicleUUID, vehicle);

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.VEHICLE_UPDATE_FAILED);
			}

			response.setStatus(new Status(StatusList.SUCCESS,
					"The vehicle was updated successfully"));
			response.setResourceId(vehicleUUID);

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	/**
	 * Builds the response and returns a {@link BaseResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle as String
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder deleteVehicle(String vehicleUUID)
	{
		try
		{
			Validation.isUUID(vehicleUUID);

			BaseResponse response = new BaseResponse();

			IVehicleDAO vehicleDAO = new VehicleDAO();

			Boolean isUpdated = vehicleDAO.deleteVehicle(vehicleUUID);

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.VEHICLE_DELETE_FAILED);
			}

			response.setStatus(new Status(StatusList.SUCCESS,
					"The vehicle was deleted successfully"));

			response.setResourceId(vehicleUUID);

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}
}
