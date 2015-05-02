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
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Vehicle;
import de.hftl.mize.system.DataSource;
import de.hftl.mize.system.Helper;
import de.hftl.mize.system.Validation;

/**
 * 
 * Deals with all persistence aspects of a {@link Vehicle}
 * 
 * @author Tobias
 *
 */
public class VehicleDAO implements IVehicleDAO
{

	private static Logger	LOGGER	= Logger.getRootLogger();

	/**
	 * Get a vehicle by its external uuid as String
	 * 
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Vehicle getVehicle(String vehicleUUID) throws BusinessException,
			ValidationException
	{
		if (!Validation.isUUID(vehicleUUID))
		{
			throw new ValidationException(ValidationException.INVALID_UUID);
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * FROM `vehicle` WHERE uuid = ?;");

			statement.setString(1, vehicleUUID);

			resultSet = statement.executeQuery();

			Vehicle vehicle = new Vehicle();

			if (resultSet.next())
			{
				vehicle.setMake(resultSet.getString("v.make"));
				vehicle.setModel(resultSet.getString("v.model"));
				vehicle.setSeats(resultSet.getInt("v.seats"));
				vehicle.setUserId(Helper.stringToUUID(resultSet
						.getString("u.uuid")));
			}
			else
			{
				throw new BusinessException(BusinessException.VEHICLE_NOT_FOUND);
			}

			return vehicle;
		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Get an {@link ArrayList} of {@link Vehicle} by the external {@link UUID}
	 * of an user
	 * 
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public ArrayList<Vehicle> getVehiclesByUserId(String userUUID)
			throws BusinessException, ValidationException
	{
		if (!Validation.isUUID(userUUID))
		{
			throw new ValidationException(ValidationException.INVALID_UUID);
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT v.*, u.uuid, u.id FROM `vehicle` v, `user` u WHERE u.uuid = ? AND u.id = v.user_id;");

			statement.setString(1, userUUID);

			resultSet = statement.executeQuery();

			ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

			if (resultSet.next())
			{
				Vehicle vehicle = new Vehicle();

				vehicle.setMake(resultSet.getString("v.make"));
				vehicle.setModel(resultSet.getString("v.model"));
				vehicle.setSeats(resultSet.getInt("v.seats"));
				vehicle.setUserId(Helper.stringToUUID(resultSet
						.getString("u.uuid")));

				vehicles.add(vehicle);
			}
			else
			{
				throw new BusinessException(BusinessException.VEHICLE_NOT_FOUND);
			}

			return vehicles;

		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Update a vehicle by its external {@link UUID} and the representative
	 * {@link Vehicle} JSON object
	 * 
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean updateVehicle(String vehicleUUID, Vehicle vehicle)
			throws BusinessException, ValidationException
	{
		if (!Validation.isUUID(vehicleUUID))
		{
			throw new ValidationException(ValidationException.INVALID_UUID);
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection.prepareStatement("UPDATE `vehicle` "
					+ " SET make = ?, model = ?, seats = ? "
					+ " WHERE uuid = ?;");

			statement.setString(1, vehicle.getMake());
			statement.setString(2, vehicle.getModel());
			statement.setInt(3, vehicle.getSeats());
			statement.setString(4, vehicleUUID);

			if (statement.executeUpdate() > 0)
			{
				return true;
			}
			else
			{
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}

		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Insert a new vehicle
	 */
	public UUID insertVehicle(Vehicle vehicle, Integer userId)
			throws BusinessException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			UUID vehicleUUID = UUID.randomUUID();

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection.prepareStatement("INSERT INTO `vehicle` "
					+ " (uuid, make, model, seats, user_id) VALUES "
					+ " (?, ?, ?, ?, ?);");

			statement.setString(1, vehicleUUID.toString());
			statement.setString(2, vehicle.getMake());
			statement.setString(3, vehicle.getModel());
			statement.setInt(4, vehicle.getSeats());
			statement.setInt(5, userId);

			statement.executeUpdate();

			return vehicleUUID;

		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Deletes a vehicle by its external {@link UUID}
	 * 
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean deleteVehicle(String vehicleUUID) throws BusinessException,
			ValidationException
	{
		if (!Validation.isUUID(vehicleUUID))
		{
			throw new ValidationException(ValidationException.INVALID_UUID);
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("DELETE FROM `vehicle` WHERE uuid = ?;");

			statement.setString(1, vehicleUUID);

			statement.executeUpdate();

			return true;

		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e)
			{
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

}
