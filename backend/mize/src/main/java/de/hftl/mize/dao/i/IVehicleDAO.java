package de.hftl.mize.dao.i;

import java.util.ArrayList;
import java.util.UUID;

import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Vehicle;

/**
 * Interface for all vehicle related transactions with the database
 * 
 * @author Tobias
 *
 */
public interface IVehicleDAO {

	/**
	 * Get a vehicle by its external {@link UUID}
	 * 
	 * @param vehicleUUID
	 *            the external {@link UUID} of the vehicle
	 * @return {@link Vehicle}
	 * @throws BusinessException
	 */
	public Vehicle getVehicle(String vehicleUUID) throws BusinessException;

	/**
	 * Get an {@link ArrayList} of {@link Vehicle} by the external {@link UUID}
	 * of an user
	 * 
	 * @param userUUID
	 *            The external UUID of the user
	 * @return {@link ArrayList} of {@link Vehicle}
	 * @throws BusinessException
	 */
	public ArrayList<Vehicle> getVehiclesByUserId(String userUUID)
			throws BusinessException;

	/**
	 * Update a vehicle by its external {@link UUID} and the representative
	 * {@link Vehicle} JSON object
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle
	 * @param vehicle
	 *            The {@link Vehicle} object
	 * @return {@link Boolean} whether the update was successful
	 * @throws BusinessException
	 */
	public Boolean updateVehicle(UUID vehicleUUID, Vehicle vehicle)
			throws BusinessException;

	/**
	 * Insert a new vehicle
	 * 
	 * @param vehicle
	 *            The {@link Vehicle} object
	 * @return The {@link UUID} of the new vehicle when the insert was
	 *         successful, else NULL
	 * @throws BusinessException
	 */
	public UUID insertVehicle(Vehicle vehicle) throws BusinessException;

	/**
	 * Deletes a vehicle by its external {@link UUID}
	 * 
	 * @param vehicleUUID
	 *            The external {@link UUID} of the vehicle
	 * @return {@link Boolean} whether the delete was successful
	 * @throws BusinessException
	 */
	public Boolean deleteVehicle(UUID vehicleUUID) throws BusinessException;
}
