package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import de.hftl.mize.dao.i.IVehicleDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Vehicle;
import de.hftl.mize.system.DataSource;

/**
 * 
 * Deals with all persistence aspects of a {@link Vehicle}
 * 
 * @author Tobias
 *
 */
public class VehicleDAO implements IVehicleDAO {

	private static Logger	LOGGER	= Logger.getRootLogger();

	/**
	 * Get a vehicle by its external {@link UUID}
	 */
	public Vehicle getVehicle(String vehicleUUID) throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * from `vehicle` WHERE id = ?;");

			statement.setString(1, vehicleUUID);

			resultSet = statement.executeQuery();

			Vehicle vehicle = new Vehicle();

			if (resultSet.next())
			{
				// TODO: Implement
			}
			else
			{
				throw new BusinessException(BusinessException.VEHICLE_NOT_FOUND);
			}

			return vehicle;
		}
		catch (SQLException e)
		{
			LOGGER.fatal(e);
		}
		finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			}
			catch (SQLException e)
			{
				LOGGER.error(e);
			}
		}

		return null;
	}

	/**
	 * Get an {@link ArrayList} of {@link Vehicle} by the external {@link UUID}
	 * of an user
	 */
	public ArrayList<Vehicle> getVehiclesByUserId(String userId)
			throws BusinessException
	{
		// TODO Implement
		return null;
	}

	/**
	 * Update a vehicle by its external {@link UUID} and the representative
	 * {@link Vehicle} JSON object
	 */
	public Boolean updateVehicle(UUID vehicleUUID, Vehicle vehicle)
			throws BusinessException
	{
		// TODO Implement
		return null;
	}

	/**
	 * Insert a new vehicle
	 */
	public UUID insertVehicle(Vehicle vehicle) throws BusinessException
	{
		// TODO Implement
		return null;
	}

	/**
	 * Deletes a vehicle by its external {@link UUID}
	 */
	public Boolean deleteVehicle(UUID vehicleUUID) throws BusinessException
	{
		// TODO Implement
		return null;
	}

}
