/**
 * 
 */
package de.hftl.mize.dao.i;

import java.util.ArrayList;
import java.util.UUID;

import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Trip;

/**
 * @author tokilian
 *
 */
public interface ITripDAO {

	public ArrayList<Trip> getTrips() throws BusinessException;

	public ArrayList<Trip> getTrips(Double lat, Double lon, Integer radius) throws BusinessException;
	
	public Trip getTrip(String uuid) throws BusinessException;
	
	public Boolean updateTrip(UUID uuid, Trip trip) throws BusinessException;
	
	public UUID insertTrip(Trip trip) throws BusinessException;
	
	public Boolean deleteTrip(UUID uuid) throws BusinessException;
}
