package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import de.hftl.mize.model.Coordinate;
import de.hftl.mize.model.Country;
import de.hftl.mize.model.Location;
import de.hftl.mize.system.DataSource;

public class LocationDAO {

	private static Logger	LOGGER	= Logger.getRootLogger();

	protected static Location getLocation(Integer id)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * from location WHERE id = ?;");

			statement.setInt(1, id);

			resultSet = statement.executeQuery();

			Location location = new Location();

			if (resultSet.next())
			{

				Coordinate geoCoordinate = new Coordinate(
						resultSet.getDouble("latitude"),
						resultSet.getDouble("longitude"));

				location.setStreet(resultSet.getString("street"));
				location.setStreetNumber(resultSet.getString("street_number"));
				location.setZipCode(resultSet.getString("zip_code"));
				location.setCity(resultSet.getString("city"));
				location.setCountry(Country.valueOf(resultSet
						.getString("country")));
				location.setGeoCoordinate(geoCoordinate);
			}

			return location;
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

	protected static Integer setLocation(Location location)
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{

			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement(
							"INSERT INTO location "
									+ "(street, streetNumber, zipCode, city, country, latitude, longitude) VALUES"
									+ "(?, ?, ?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, location.getStreet());
			statement.setString(2, location.getStreetNumber());
			statement.setString(3, location.getZipCode());
			statement.setString(4, location.getCity());
			statement.setString(5, location.getCountry().toString());
			statement.setDouble(6, location.getGeoCoordinate().getLatitude());
			statement.setDouble(7, location.getGeoCoordinate().getLatitude());

			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next())
			{
				return rs.getInt(1);
			}

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
