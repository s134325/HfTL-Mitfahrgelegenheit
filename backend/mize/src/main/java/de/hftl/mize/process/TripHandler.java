package de.hftl.mize.process;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;
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
	public static ResponseBuilder getTrip(HttpHeaders headers, String tripUUID)
	{
		TripResponse response = new TripResponse();
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);
			Validation.isUUID(tripUUID);

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
	public static ResponseBuilder getTrips(HttpHeaders headers,
			Double latitude, Double longitude, Integer radius)
	{

		TripResponse response = new TripResponse();
		ArrayList<Trip> trips = null;
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);

			if (latitude == null || longitude == null || radius == null)
			{
				trips = tripDAO.getTrips();
			} else
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

	public static ResponseBuilder getTrips(HttpHeaders headers)
	{

		TripResponse response = new TripResponse();
		ArrayList<Trip> trips = null;
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);

			trips = tripDAO.getTrips();

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
	public static ResponseBuilder insertTrip(HttpHeaders headers, Trip trip)
	{

		BaseResponse response = new BaseResponse();
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);
			Validation.isValidTripObject(trip);
			Validation.isISO8601(trip.getStartTime());

			UUID uuid = tripDAO.insertTrip(Helper.retrieveUserUUID(headers),
					trip);

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
	public static ResponseBuilder updateTrip(HttpHeaders headers,
			String tripUUID, Trip trip)
	{

		BaseResponse response = new BaseResponse();
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isUUID(tripUUID);
			Validation.isLoggedIn(headers);
			Validation.isValidTripObject(trip);

			Boolean isUpdated = tripDAO.updateTrip(tripUUID, trip);

			if (!isUpdated)
			{
				throw new BusinessException(
						BusinessException.TRIP_UPDATE_FAILED);
			}

			response.setResourceId(tripUUID);
			response.setStatus(new Status("UPDATED",
					"The trip was updated successfully"));

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
	 * @param tripUUID
	 *            The external {@link UUID} of the trip
	 * @return {@link ResponseBuilder}
	 */
	public static ResponseBuilder deleteTrip(HttpHeaders headers,
			String tripUUID)
	{

		BaseResponse response = new BaseResponse();
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);
			Validation.isUUID(tripUUID);

			Boolean isDeleted = tripDAO.deleteTrip(tripUUID);

			if (!isDeleted)
			{
				throw new BusinessException(
						BusinessException.TRIP_DELETE_FAILED);
			}

			response.setResourceId(tripUUID);
			response.setStatus(new Status("DELETE",
					"The trip was delete successfully"));

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	public static ResponseBuilder bookTrip(HttpHeaders headers,
			String userUUID, String tripUUID)
	{
		BaseResponse response = new BaseResponse();
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);
			Validation.isUUID(userUUID);
			Validation.isUUID(tripUUID);

			Boolean isBooked = tripDAO.bookTrip(userUUID, tripUUID);

			if (!isBooked)
			{
				throw new BusinessException(BusinessException.TRIP_BOOK_FAILED);
			}

			response.setResourceId("null");
			response.setStatus(new Status("BOOK", "The trip was booked"));

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	public static ResponseBuilder unbookTrip(HttpHeaders headers,
			String userUUID, String tripUUID)
	{
		BaseResponse response = new BaseResponse();
		ITripDAO tripDAO = new TripDAO();

		try
		{
			Validation.isLoggedIn(headers);
			Validation.isUUID(userUUID);
			Validation.isUUID(tripUUID);

			Boolean isUnBooked = tripDAO.unbookTrip(userUUID, tripUUID);

			if (!isUnBooked)
			{
				throw new BusinessException(
						BusinessException.TRIP_UNBOOK_FAILED);
			}

			response.setResourceId("null");
			response.setStatus(new Status("UNBOOK", "The trip was unbooked"));

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}
}
