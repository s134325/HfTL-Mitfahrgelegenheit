package de.hftl.mize.dao.i;

import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Location;

/**
 * Interface for all location related transactions with the database
 * 
 * @author Tobias
 *
 */
public interface ILocationDAO
{

	/**
	 * Get a location by ID
	 * 
	 * @param id
	 *            Integer The id of the location
	 * @return {@link Location}
	 * @throws BusinessException
	 */
	public Location getLocation(Integer id) throws BusinessException;

	/**
	 * Set a location with the {@link Location} object
	 * 
	 * @param location
	 *            {@link Location} The object of the location
	 * @return {@link Location}
	 * @throws BusinessException
	 */
	public Integer setLocation(Location location) throws BusinessException;
}