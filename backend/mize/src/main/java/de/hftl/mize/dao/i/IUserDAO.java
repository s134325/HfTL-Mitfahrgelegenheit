package de.hftl.mize.dao.i;

import de.hftl.mize.exception.BusinessException;
import de.hftl.mize.model.User;

/**
 * Interface for all user related transactions with the database
 * 
 * @author Tobias
 *
 */
public interface IUserDAO
{
	public User getUser(String userUUID) throws BusinessException;

	public User insertUser(User user) throws BusinessException;

	public User loginUser(User user) throws BusinessException;

	public Boolean isLoggedIn(String userUUID) throws BusinessException;

}