package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import de.hftl.mize.dao.i.ILocationDAO;
import de.hftl.mize.dao.i.ITripDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Location;
import de.hftl.mize.model.Trip;
import de.hftl.mize.model.User;
import de.hftl.mize.system.DataSource;
import de.hftl.mize.system.Helper;

/**
 * Deals with all persistence aspects of a {@link Trip}
 * 
 * @author tokilian
 *
 */
public class TripDAO implements ITripDAO
{

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
					.prepareStatement("SELECT * FROM `trip` LIMIT 30;");

			resultSet = statement.executeQuery();

			while (resultSet.next())
			{

				Trip trip = new Trip();

				Location from = locationDAO.getLocation(resultSet
						.getInt("location_id_from"));
				Location to = locationDAO.getLocation(resultSet
						.getInt("location_id_to"));

				trip.setUuid(resultSet.getString("uuid"));
				trip.setFrom(from);
				trip.setTo(to);
				trip.setStartTime(resultSet.getString("start_time"));
				trip.setFreeSeats(resultSet.getInt("free_seats"));
				trip.setDescription(resultSet.getString("description"));
				trip.setPrice(resultSet.getDouble("price"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setParticipants(getParticipants(resultSet.getInt("id")));
				trip.setUpdateTime(resultSet.getString("update_time"));
				trip.setCreateTime(resultSet.getString("create_time"));

				alTrip.add(trip);
			}

			return alTrip;
		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} catch (BusinessException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(e.getErrorCode());
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}

	}

	private ArrayList<User> getParticipants(Integer tripId)
			throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ArrayList<User> alUser = new ArrayList<>();

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT ut.*, u.* FROM user_trip ut, user u WHERE ut.trip_id = ? AND u.id = ut.user_id;");

			statement.setInt(1, tripId);

			resultSet = statement.executeQuery();

			while (resultSet.next())
			{

				User user = new User();

				user.setUuid(resultSet.getString("u.uuid"));
				user.setFirstName(resultSet.getString("u.first_name"));
				user.setLastName(resultSet.getString("u.last_name"));
				user.setMail(resultSet.getString("u.mail"));
				user.setRole(resultSet.getString("ut.type"));

				alUser.add(user);
			}

			return alUser;
		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
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
					.prepareStatement("SELECT `id`, ( 6373 * acos( cos( radians( ? ) ) * cos( radians( `latitude` ) ) * cos( radians( `longitude` ) - radians( ? ) ) + sin(radians(?)) * sin(radians(`latitude`)) ) ) AS `distance` FROM `location` HAVING  `distance` < ? ORDER BY `distance` LIMIT 25");

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

				trip.setUuid(resultSet.getString("uuid"));
				trip.setFrom(from);
				trip.setTo(to);
				trip.setStartTime(resultSet.getString("start_time"));
				trip.setFreeSeats(resultSet.getInt("free_seats"));
				trip.setDescription(resultSet.getString("description"));
				trip.setPrice(resultSet.getDouble("price"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setUpdateTime(resultSet.getString("update_time"));
				trip.setCreateTime(resultSet.getString("create_time"));

				alTrip.add(trip);
			}

			return alTrip;
		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Get a single Trip by UUID
	 * 
	 * @return Trip
	 * @throws BusinessException
	 * @throws ValidationException
	 * 
	 */
	public Trip getTrip(String tripUUID) throws BusinessException,
			ValidationException
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
					.prepareStatement("SELECT * from trip WHERE uuid = ?;");

			statement.setString(1, tripUUID);

			resultSet = statement.executeQuery();

			Trip trip = new Trip();

			if (resultSet.next())
			{

				Location from = locationDAO.getLocation(resultSet
						.getInt("location_id_from"));
				Location to = locationDAO.getLocation(resultSet
						.getInt("location_id_to"));

				trip.setUuid(resultSet.getString("uuid"));
				trip.setFrom(from);
				trip.setTo(to);
				trip.setParticipants(getParticipants(resultSet.getInt("id")));
				trip.setStartTime(resultSet.getString("start_time"));
				trip.setFreeSeats(resultSet.getInt("free_seats"));
				trip.setDescription(resultSet.getString("description"));
				trip.setPrice(resultSet.getDouble("price"));
				trip.setActive(resultSet.getBoolean("active"));
				trip.setUpdateTime(resultSet.getString("update_time"));
				trip.setCreateTime(resultSet.getString("create_time"));
			} else
			{
				LOGGER.error("Trip with UUID: " + tripUUID + " not found");
				throw new BusinessException(BusinessException.TRIP_NOT_FOUND);
			}

			return trip;
		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Update a Trip by UUID
	 * 
	 * @return a Boolean showing weather the update process was successful
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	// TODO: Not quite happy with this solution, location is not updated just
	// inserted again
	public Boolean updateTrip(String tripUUID, Trip trip)
			throws BusinessException, ValidationException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ILocationDAO locationDAO = new LocationDAO();

		try
		{

			Date today = new Date();

			Integer locationFromId = locationDAO.setLocation(trip.getFrom());
			Integer locationToId = locationDAO.setLocation(trip.getTo());

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("UPDATE trip "
							+ " SET from = ?, to = ?, start_time = ?, free_seats = ?, "
							+ " description = ?, price = ?, active = ?, update_time = ? "
							+ " WHERE uuid = ?;");

			statement.setInt(1, locationFromId);
			statement.setInt(2, locationToId);
			statement.setString(3, trip.getStartTime());
			statement.setInt(4, trip.getFreeSeats());
			statement.setString(5, trip.getDescription());
			statement.setDouble(6, trip.getPrice());
			statement.setBoolean(7, trip.getActive());
			statement.setTimestamp(8, new Timestamp(today.getTime()));
			statement.setString(9, tripUUID);

			if (statement.executeUpdate() > 0)
			{
				return true;
			} else
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
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Insert a Trip
	 * 
	 * @return the UUID of the new Trip
	 * @throws BusinessException
	 */
	public UUID insertTrip(String userUUID, Trip trip) throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ILocationDAO locationDAO = new LocationDAO();

		Integer locationFromId = 0;
		Integer locationToId = 0;
		UUID tripUUID = UUID.randomUUID();

		try
		{

			locationFromId = locationDAO.setLocation(trip.getFrom());
			locationToId = locationDAO.setLocation(trip.getTo());

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("INSERT INTO trip"
							+ " (uuid, location_id_from, location_id_to, "
							+ " start_time, free_seats, description, price, active) VALUES "
							+ " (?, ?, ?, ?, ?, ?, ?, ?);");

			statement.setString(1, tripUUID.toString());
			statement.setInt(2, locationFromId);
			statement.setInt(3, locationToId);
			statement.setTimestamp(4,
					Helper.convertISO8601ToTimestamp(trip.getStartTime()));
			statement.setInt(5, trip.getFreeSeats());
			statement.setString(6, trip.getDescription());
			statement.setDouble(7, trip.getPrice());
			statement.setBoolean(8, trip.getActive());

			statement.executeUpdate();
			statement.close();
			
			statement = connection
					.prepareStatement("INSERT INTO user_trip "
							+ " (user_id, trip_id, type)"
							+ " VALUES ((SELECT id FROM user u WHERE u.uuid = ?), (SELECT id FROM trip t WHERE t.uuid = ?), 'OWNER')");

			statement.setString(1, userUUID);
			statement.setString(2, tripUUID.toString());

			statement.executeUpdate();

			return tripUUID;

		} catch (SQLException e)
		{
			locationDAO.deleteLocation(locationFromId);
			locationDAO.deleteLocation(locationToId);
			
			try
			{
				deleteTrip(tripUUID.toString());
			} catch (ValidationException e1)
			{
				LOGGER.fatal(e1);
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}

			LOGGER.fatal(e);
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	/**
	 * Delete a Trip by UUID
	 * 
	 * @return a Boolean showing weather the delete process was successful
	 * @throws BusinessException
	 * @throws ValidationException
	 */
	public Boolean deleteTrip(String tripUUID) throws BusinessException,
			ValidationException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("DELETE FROM trip WHERE uuid = ?;");

			statement.setString(1, tripUUID.toString());

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
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	@Override
	public Boolean bookTrip(String userUUID, String tripUUID)
			throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("INSERT INTO user_trip "
							+ " (user_id, trip_id, type) VALUES "
							+ " ((SELECT id FROM user u WHERE u.uuid = ?), (SELECT id FROM trip t WHERE t.uuid = ?), 'PASSENGER');");

			statement.setString(1, userUUID);
			statement.setString(2, tripUUID);

			if (statement.executeUpdate() > 0)
			{
				return true;
			}

			return false;

		} catch (SQLException e)
		{
			LOGGER.fatal(e);
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				LOGGER.fatal(e.getMessage());
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

	@Override
	public Boolean unbookTrip(String userUUID, String tripUUID)
			throws BusinessException
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("DELETE FROM user_trip WHERE user_id = (SELECT id FROM user u WHERE u.uuid = ?) AND trip_id = (SELECT id FROM trip t WHERE t.uuid = ?);");

			statement.setString(1, userUUID.toString());
			statement.setString(2, tripUUID.toString());

			if (statement.executeUpdate() > 0)
			{

				return true;
			}

			return false;

		} catch (SQLException e)
		{
			LOGGER.fatal(e.getMessage());
			throw new BusinessException(BusinessException.MYSQL_ERROR);
		} finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e)
			{
				throw new BusinessException(BusinessException.MYSQL_ERROR);
			}
		}
	}

}
