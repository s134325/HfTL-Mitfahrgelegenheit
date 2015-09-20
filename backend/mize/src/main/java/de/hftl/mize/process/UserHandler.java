package de.hftl.mize.process;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import de.hftl.mize.dao.UserDAO;
import de.hftl.mize.dao.i.IUserDAO;
import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.exception.ValidationException;
import de.hftl.mize.model.Status;
import de.hftl.mize.model.User;
import de.hftl.mize.response.UserResponse;
import de.hftl.mize.system.Helper;
import de.hftl.mize.system.Validation;

public class UserHandler
{
	public static ResponseBuilder getUserByUUID(HttpHeaders headers,
			String userUUID)
	{
		try
		{
			Validation.isUUID(userUUID);

			UserResponse response = new UserResponse();

			IUserDAO userDAO = new UserDAO();

			User user = userDAO.getUser(userUUID);

			response.setUuid(user.getUuid());

			return Response.status(200).entity(response);

		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	public static ResponseBuilder insertUser(User user)
	{

		try
		{
			Validation.isValidUserObject(user);

			UserResponse response = new UserResponse();

			IUserDAO userDAO = new UserDAO();

			User returnUser = userDAO.insertUser(user);

			if (returnUser.getUuid() == null)
			{
				throw new BusinessException(
						BusinessException.USER_INSERT_FAILED);
			}

			response.setUuid(returnUser.getUuid());

			return Response.status(200).entity(response);
		} catch (BusinessException | ValidationException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

	public static ResponseBuilder loginUser(User user)
	{
		try
		{
			if (user.getUsername() == null || user.getPassword() == null)
			{

				throw new BusinessException(BusinessException.USER_LOGIN_FAILED);
			}

			UserResponse response = new UserResponse();

			IUserDAO userDAO = new UserDAO();

			User returnUser = userDAO.loginUser(user);

			if ("".equals(returnUser.getUuid()) || returnUser.getUuid() == null)
			{
				throw new BusinessException(BusinessException.USER_LOGIN_FAILED);
			}

			response.setUuid(returnUser.getUuid());
			response.setStatus(new Status("SUCCESS", "Login was successfull"));

			return Response.status(200).entity(response);
			
		} catch (BusinessException e)
		{
			return Helper.buildErrorResponse(e);
		}
	}

}
