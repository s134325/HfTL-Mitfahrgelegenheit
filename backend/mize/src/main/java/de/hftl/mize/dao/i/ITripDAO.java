/**
 * 
 */
package de.hftl.mize.dao.i;

import java.util.ArrayList;
import java.util.UUID;

import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Trip;

/**
 * @author tokilian
 *
 */
public interface ITripDAO
{

	/**
	 * Get all trips
	 * 
	 * @return {@link ArrayList} of {@link Trip}
	 * @throws BusinessException
	 */
	public ArrayList<Trip> getTrips() throws BusinessException;

	/**
	 * Get all trips within a given radius around geocoordinates
	 * 
	 * @param lat
	 *            {@link Double}
	 * @param lon
	 *            {@link Double}
	 * @param radius
	 *            {@link Integer}
	 * @return {@link ArrayList} of {@link Trip}
	 * @throws BusinessException
	 */
	public ArrayList<Trip> getTrips(Double lat, Double lon, Integer radius)
			throws BusinessException;

	/**
	 * Get a trip by its external {@link UUID}
	 * 
	 * @param uuid
	 *            The external UUID of the trip
	 * @return {@link Trip}
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Trip getTrip(String uuid) throws BusinessException,
			ValidationException;

	/**
	 * Update a trip by its external {@link UUID} and the representative
	 * {@link Trip} JSON object
	 * 
	 * @param tripUUId
	 *            The external UUID of the trip
	 * @param trip
	 *            The {@link Trip} object
	 * @return {@link Boolean} whether the update was successful
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean updateTrip(String tripUUId, Trip trip)
			throws BusinessException, ValidationException;

	/**
	 * Insert a new trip
	 * 
	 * @param trip
	 *            The {@link Trip} object
	 * @return The {@link UUID} of the new trip when the insert was successful,
	 *         else NULL
	 * @throws BusinessException
	 */
	public UUID insertTrip(String userUUID, Trip trip) throws BusinessException;

	/**
	 * Deletes a trip by its external {@link UUID}
	 * 
	 * @param tripUUID
	 *            The external UUID of the trip
	 * @return {@link Boolean} whether the delete was successful
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean deleteTrip(String tripUUID) throws BusinessException,
			ValidationException;

	public Boolean bookTrip(String userUUID, String tripUUID)
			throws BusinessException;

	public Boolean unbookTrip(String userUUID, String tripUUID)
			throws BusinessException;
}
