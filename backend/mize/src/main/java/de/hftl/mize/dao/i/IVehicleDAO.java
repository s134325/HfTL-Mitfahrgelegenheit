package de.hftl.mize.dao.i;

import java.util.ArrayList;
import java.util.UUID;

import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Vehicle;

/**
 * Interface for all vehicle related transactions with the database
 * 
 * @author Tobias
 *
 */
public interface IVehicleDAO
{

	/**
	 * Get a vehicle by its external {@link UUID}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle as String
	 * @return {@link Vehicle}
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Vehicle getVehicle(String vehicleUUID) throws BusinessException,
			ValidationException;

	/**
	 * get the number of vehicle stored in the database for a single user.
	 * 
	 * @param userUUID
	 *            The external UUID of the user
	 * @return
	 * @throws BusinessException
	 */
	public Integer getVehicleCountByUserId(String userUUID)
			throws BusinessException;

	/**
	 * Get an {@link ArrayList} of {@link Vehicle} by the external {@link UUID}
	 * of an user
	 * 
	 * @param userUUID
	 *            The external UUID of the user
	 * @return {@link ArrayList} of {@link Vehicle}
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public ArrayList<Vehicle> getVehiclesByUserId(String userUUID)
			throws BusinessException, ValidationException;

	/**
	 * Update a vehicle by its external {@link UUID} and the representative
	 * {@link Vehicle} JSON object
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle as String
	 * @param vehicle
	 *            The {@link Vehicle} object
	 * @return {@link Boolean} whether the update was successful
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean updateVehicle(String vehicleUUID, Vehicle vehicle)
			throws BusinessException, ValidationException;

	/**
	 * Insert a new vehicle
	 * 
	 * @param vehicle
	 *            The {@link Vehicle} object
	 * @param userId
	 *            Integer The internal id of the current user
	 * @return The {@link UUID} of the new vehicle when the insert was
	 *         successful, else NULL
	 * @throws BusinessException
	 * @throws ValidationException 
	 */
	public UUID insertVehicle(Vehicle vehicle) throws BusinessException, ValidationException;

	/**
	 * Deletes a vehicle by its external {@link UUID}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle as String
	 * @return {@link Boolean} whether the delete was successful
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean deleteVehicle(String vehicleUUID) throws BusinessException,
			ValidationException;
}
