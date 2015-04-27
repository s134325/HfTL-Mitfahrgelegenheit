package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import de.hftl.mize.dao.i.ILocationDAO;
import de.hftl.mize.dao.i.ITripDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Location;
import de.hftl.mize.model.Trip;
import de.hftl.mize.system.DataSource;

/**
 * Deals with all persistence aspects of a {@link Trip}
 * 
 * @author tokilian
 *
 */
public class TripDAO implements ITripDAO {

	private static Logger	LOGGER	= Logger.getRootLogger();

	/**
	 * Get a list of trips, the limit is set to 30
	 * 
	 * @return ArrayList of Trip
	 * @throws BusinessException
	 */
	public ArrayList<Trip> getTrips() throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ArrayList<Trip> alTrip = new ArrayList<Trip>();
		ILocationDAO locationDAO = new LocationDAO();

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * FROM `trip` LIMIT 30");

			resultSet = statement.executeQuery();

			while (resultSet.next())
			{

				Trip trip = new Trip();

				Location from = locationDAO.getLocation(resultSet
						.getInt("location_id_from"));
				Location to = locationDAO.getLocation(resultSet
						.getInt("location_id_to"));

				trip.setUuid(UUID.fromString(resultSet.getString("id")));
				trip.setFrom(from);
				trip.setTo(to);
				trip.setStartTime(resultSet.getString("startTime"));
				trip.setFreeSeats(resultSet.getInt("free_seats"));
				trip.setDescription(resultSet.getString("description"));
				trip.setPrice(resultSet.getDouble("price"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setUpdateTime(resultSet.getString("startTime"));
				trip.setCreateTime(resultSet.getString("startTime"));

				alTrip.add(trip);
			}

			return alTrip;
		}
		catch (SQLException e)
		{
			throw new BusinessException(BusinessException.MYSQL_ERROR);
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

	}

	/**
	 * Get a list of trips in a defined area
	 * 
	 * @return ArrayList of Trip
	 * @throws BusinessException
	 */
	// TODO: Implement a useful method, this one does not even make any sense
	public ArrayList<Trip> getTrips(Double lat, Double lon, Integer radius)
			throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ArrayList<Trip> alTrip = new ArrayList<Trip>();
		ILocationDAO locationDAO = new LocationDAO();

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT `id`, ( 6373 * acos( cos( radians( ? ) ) * cos( radians( `latitude` ) ) * cos( radians( `longitude` ) - radians( ? ) ) + sin(radians(?)) * sin(radians(`latitude`)) ) ) `distance `FROM `location` HAVING  `distance` < ? ORDER BY `distance` LIMIT 25");

			statement.setDouble(1, lat);
			statement.setDouble(2, lon);
			statement.setDouble(3, lat);
			statement.setInt(4, radius);

			resultSet = statement.executeQuery();

			while (resultSet.next())
			{

				Trip trip = new Trip();

				Location from = locationDAO.getLocation(resultSet
						.getInt("location_id_from"));
				Location to = locationDAO.getLocation(resultSet
						.getInt("location_id_to"));

				trip.setUuid(UUID.fromString(resultSet.getString("id")));
				trip.setFrom(from);
				trip.setTo(to);
				trip.setStartTime(resultSet.getString("startTime"));
				trip.setFreeSeats(resultSet.getInt("free_seats"));
				trip.setDescription(resultSet.getString("description"));
				trip.setPrice(resultSet.getDouble("price"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setUpdateTime(resultSet.getString("startTime"));
				trip.setCreateTime(resultSet.getString("startTime"));

				alTrip.add(trip);
			}

			return alTrip;
		}
		catch (SQLException e)
		{
			throw new BusinessException(BusinessException.MYSQL_ERROR);
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
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Get a single Trip by UUID
	 * 
	 * @return Trip
	 */
	public Trip getTrip(String uuid) throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ILocationDAO locationDAO = new LocationDAO();

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * from trip WHERE id = ?");

			statement.setString(1, uuid);

			resultSet = statement.executeQuery();

			Trip trip = new Trip();

			if (resultSet.next())
			{

				Location from = locationDAO.getLocation(resultSet
						.getInt("location_id_from"));
				Location to = locationDAO.getLocation(resultSet
						.getInt("location_id_to"));

				trip.setUuid(UUID.fromString(resultSet.getString("id")));
				trip.setFrom(from);
				trip.setTo(to);
				trip.setStartTime(resultSet.getString("startTime"));
				trip.setFreeSeats(resultSet.getInt("free_seats"));
				trip.setDescription(resultSet.getString("description"));
				trip.setPrice(resultSet.getDouble("price"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setUpdateTime(resultSet.getString("startTime"));
				trip.setCreateTime(resultSet.getString("startTime"));
			}
			else
			{
				throw new BusinessException(BusinessException.TRIP_NOT_FOUND);
			}

			return trip;
		}
		catch (SQLException e)
		{
			throw new BusinessException(BusinessException.MYSQL_ERROR);
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
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Update a Trip by UUID
	 * 
	 * @return a Boolean showing weather the update process was successful
	 * @throws BusinessException
	 */
	// TODO: What would be useful?
	public Boolean updateTrip(UUID tripUUID, Trip trip)
			throws BusinessException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ILocationDAO locationDAO = new LocationDAO();

		try
		{

			Integer locationFromId = locationDAO.setLocation(trip.getFrom());
			Integer locationToId = locationDAO.setLocation(trip.getTo());

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection.prepareStatement(" UPDATE trip " + " SET "
					+ " WHERE uuid = ? ");

			statement.setString(1, tripUUID.toString());
			statement.setInt(2, locationFromId);
			statement.setInt(3, locationToId);
			statement.setString(4, trip.getStartTime());
			statement.setInt(5, trip.getFreeSeats());
			statement.setString(6, trip.getDescription());
			statement.setDouble(7, trip.getPrice());
			statement.setBoolean(8, trip.getActive());
			statement.setString(9, null);

			statement.executeUpdate();

			return true;

		}
		catch (SQLException e)
		{
			throw new BusinessException(BusinessException.MYSQL_ERROR);
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
	}

	/**
	 * Insert a Trip
	 * 
	 * @return the UUID of the new Trip
	 * @throws BusinessException
	 */
	public UUID insertTrip(Trip trip) throws BusinessException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ILocationDAO locationDAO = new LocationDAO();

		try
		{
			UUID tripUUID = UUID.randomUUID();
			Integer locationFromId = locationDAO.setLocation(trip.getFrom());
			Integer locationToId = locationDAO.setLocation(trip.getTo());

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("INSERT INTO trip"
							+ "(uuid, from, to, startTime, freeSeats, description, price, active, updateTime) VALUES"
							+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setString(1, tripUUID.toString());
			statement.setInt(2, locationFromId);
			statement.setInt(3, locationToId);
			statement.setString(4, trip.getStartTime());
			statement.setInt(5, trip.getFreeSeats());
			statement.setString(6, trip.getDescription());
			statement.setDouble(7, trip.getPrice());
			statement.setBoolean(8, trip.getActive());
			statement.setString(9, null);

			statement.executeUpdate();

			return tripUUID;

		}
		catch (SQLException e)
		{
			throw new BusinessException(BusinessException.MYSQL_ERROR);
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
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}

	}

	/**
	 * Delete a Trip by UUID
	 * 
	 * @return a Boolean showing weather the delete process was successful
	 * @throws BusinessException
	 */
	public Boolean deleteTrip(UUID tripUUID) throws BusinessException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("DELETE FROM trip WHERE uuid = ?");

			statement.setString(1, tripUUID.toString());

			statement.executeUpdate();

			return true;

		}
		catch (SQLException e)
		{
			throw new BusinessException(BusinessException.MYSQL_ERROR);
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
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

}
