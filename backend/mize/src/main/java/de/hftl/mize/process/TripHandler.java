package de.hftl.mize.process;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import de.hftl.mize.dao.TripDAO;
import de.hftl.mize.dao.i.ITripDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Status;
import de.hftl.mize.model.Trip;
import de.hftl.mize.response.BaseResponse;
import de.hftl.mize.response.TripResponse;
import de.hftl.mize.response.VehicleResponse;
import de.hftl.mize.system.Helper;
import de.hftl.mize.system.Validation;

public class TripHandler
{

	/**
	 * Builds the response and returns a {@link VehicleResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param tripUUID
	 *            The external {@link UUID} of the trip
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder getTrip(String tripUUID)
	{
		try
		{
			Validation.isUUID(tripUUID);

			TripResponse response = new TripResponse();

			ITripDAO tripDAO = new TripDAO();

			Trip trip = tripDAO.getTrip(tripUUID);

			response.setTrip(trip);

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
	 * @param latitude
	 *            {@link Double} The latitude of the geocoordinates
	 * @param longitude
	 *            {@link Double} The longitude of the geocoordinates
	 * @param radius
	 *            {@link Integer} The radius
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder getTrips(Double latitude, Double longitude,
			Integer radius)
	{

		TripResponse response = new TripResponse();
		ArrayList<Trip> trips = null;

		try
		{
			ITripDAO tripDAO = new TripDAO();

			if (latitude == null || longitude == null || radius == null)
			{
				trips = tripDAO.getTrips();
			}
			else
			{
				trips = tripDAO.getTrips(latitude, longitude, radius);
			}

			response.setTrips(trips);

			return Response.status(200).entity(response);
		} catch (BusinessException be)
		{
			return Helper.buildErrorResponse(be);
		}
	}

	/**
	 * Builds the response and returns a {@link VehicleResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param trip
	 *            The {@link Trip} object
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder insertTrip(Trip trip)
	{

		BaseResponse response = new BaseResponse();

		try
		{
			Validation.isISO8601(trip.getStartTime());

			ITripDAO tripDAO = new TripDAO();

			UUID uuid = tripDAO.insertTrip(trip);

			response.setResourceId(uuid.toString());
			response.setStatus(new Status("CREATED",
					"The trip was created successfully"));

			return Response.status(201).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	/**
	 * Builds the response and returns a {@link VehicleResponse} within a
	 * {@link ResponseBuilder}
	 * 
	 * @param tripUUID
	 *            The external {@link UUID} of the trip
	 * @param trip
	 *            The {@link Trip} object
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder updateTrip(String tripUUID, Trip trip)
	{

		BaseResponse response = new BaseResponse();

		try
		{

			Validation.isUUID(tripUUID);

			ITripDAO tripDAO = new TripDAO();

			Boolean isUpdated = tripDAO.updateTrip(tripUUID, trip);

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.TRIP_UPDATE_FAILED);
			}

			response.setResourceId(tripUUID);

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
	 * @param tripUUId
	 *            The external {@link UUID} of the trip
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder deleteTrip(String tripUUId)
	{

		BaseResponse response = new BaseResponse();

		try
		{

			Validation.isUUID(tripUUId);

			ITripDAO tripDAO = new TripDAO();

			Boolean isUpdated = tripDAO.deleteTrip(tripUUId);

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.TRIP_DELETE_FAILED);
			}

			response.setResourceId(tripUUId);

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}
}
