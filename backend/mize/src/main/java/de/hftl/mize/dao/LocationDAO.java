package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import de.hftl.mize.dao.i.ILocationDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Coordinate;
import de.hftl.mize.model.Country;
import de.hftl.mize.model.Location;
import de.hftl.mize.system.DataSource;

/**
 * Deals with all persistence aspects of a {@link Location}
 * 
 * @author tokilian
 *
 */
public class LocationDAO implements ILocationDAO
{

	private static Logger	LOGGER	= Logger.getRootLogger();

	/**
	 * Get a location by ID
	 */
	public Location getLocation(Integer id) throws BusinessException
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

				return location;
			}
			else
			{

				throw new BusinessException(
						BusinessException.LOCATION_NOT_FOUND);
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
	 * Set a location with the {@link Location} object
	 */
	public Integer setLocation(Location location) throws BusinessException
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
									+ "(street, street_number, zip_code, city, country, latitude, longitude) VALUES "
									+ " (?, ?, ?, ?, ?, ?, ?); ",
							Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, location.getStreet());
			statement.setString(2, location.getStreetNumber());
			statement.setString(3, location.getZipCode());
			statement.setString(4, location.getCity());
			statement.setString(5, location.getCountry().name());
			statement.setDouble(6, location.getGeoCoordinate().getLatitude());
			statement.setDouble(7, location.getGeoCoordinate().getLongitude());

			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				throw new BusinessException(
						BusinessException.LOCATION_CREATE_FAILED);
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

	public void deleteLocation(Integer locationId) throws BusinessException
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

			statement.setInt(1, locationId);

			statement.executeUpdate();

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
}
