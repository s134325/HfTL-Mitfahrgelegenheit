package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import de.hftl.mize.model.Location;
import de.hftl.mize.model.Trip;
import de.hftl.mize.system.DataSource;

public class TripDAO {
	private static Logger	LOGGER	= Logger.getRootLogger();

	public ArrayList<Trip> getTrips(Double lat, Double lon, Integer radius)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		ArrayList<Trip> alTrip = new ArrayList<Trip>();
		LocationDAO locationDAO = new LocationDAO();

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

	public Trip getTrip(String uuid)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

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

				trip.setUuid(UUID.fromString(resultSet.getString("id")));
			}

			return trip;
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
}
