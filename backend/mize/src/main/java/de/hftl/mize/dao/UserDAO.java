package de.hftl.mize.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import de.hftl.mize.dao.i.IUserDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.Gender;
import de.hftl.mize.model.User;
import de.hftl.mize.system.DataSource;

public class UserDAO implements IUserDAO
{

	private static Logger	LOGGER	= Logger.getRootLogger();

	@Override
	public User getUser(String userUUID) throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		User user = new User();

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * FROM `user` WHERE uuid = ?;");

			statement.setString(1, userUUID);

			resultSet = statement.executeQuery();

			if (resultSet.next())
			{
				user.setUuid(resultSet.getString("uuid"));
				user.setUsername(resultSet.getString("register_number"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setMail(resultSet.getString("mail"));
				user.setPhoneNumber(resultSet.getString("phone"));
				user.setGender(Gender.valueOf(resultSet.getString("gender")));
			}
			else
			{
				throw new BusinessException(BusinessException.USER_NOT_FOUND);
			}

			return user;
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

	@Override
	public User insertUser(User user) throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		User returnUser = new User();

		try
		{
			UUID userUUID = UUID.randomUUID();
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("INSERT INTO user "
							+ " (uuid, register_number, password, first_name, last_name, mail, phone, gender) VALUES "
							+ " (?, ?, ?, ?, ?, ?, ?, ?);");

			statement.setString(1, userUUID.toString());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getMail());
			statement.setString(7, user.getPhoneNumber());
			statement.setString(8, user.getGender().toString());

			statement.executeUpdate();

			returnUser.setUuid(userUUID.toString());

			return returnUser;

		} catch (SQLException e)
		{
			LOGGER.fatal(e);
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

	@Override
	public User loginUser(User user) throws BusinessException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		User returnUser = new User();

		try
		{
			BasicDataSource bds = DataSource.getInstance().getBds();

			connection = bds.getConnection();

			statement = connection
					.prepareStatement("SELECT * FROM `user` WHERE register_number = ? AND password = ?;");

			statement.setString(1, returnUser.getUsername());
			statement.setString(2, returnUser.getPassword());

			resultSet = statement.executeQuery();

			if (resultSet.next())
			{
				returnUser.setUuid(resultSet.getString("uuid"));
				returnUser.setFirstName(resultSet.getString("first_name"));
				returnUser.setLastName(resultSet.getString("last_name"));
				returnUser.setMail(resultSet.getString("mail"));
				returnUser.setPhoneNumber(resultSet.getString("phone"));
				returnUser.setGender(Gender.valueOf(resultSet
						.getString("gender")));
			}
			else
			{
				throw new BusinessException(BusinessException.USER_NOT_FOUND);
			}

			return returnUser;
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
