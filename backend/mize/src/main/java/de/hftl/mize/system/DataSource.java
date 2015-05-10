package de.hftl.mize.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * Database connection pooling
 * 
 * @author Tobias
 *
 */
public class DataSource
{

	private static final int	CONN_POOL_SIZE	= 30;

	private BasicDataSource		bds				= new BasicDataSource();

	private static final String	properties		= "db.properties";

	private static Logger		LOGGER			= Logger.getRootLogger();

	private DataSource()
	{
		Properties props = new Properties();
		InputStream input = null;

		try
		{
			LOGGER.trace("Read DB properties.");

			input = DataSource.class.getClassLoader().getResourceAsStream(
					properties);

			// load a properties file
			props.load(input);

			bds.setDriverClassName(props.getProperty("DB_DRIVER_CLASS"));
			bds.setUrl(props.getProperty("DB_URL"));
			bds.setUsername(props.getProperty("DB_USERNAME"));
			bds.setPassword(props.getProperty("DB_PASSWORD"));
			bds.setInitialSize(CONN_POOL_SIZE);

			LOGGER.trace("Done.");

		} catch (IOException ex)
		{
			LOGGER.error(ex.getMessage());
		} finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				} catch (IOException e)
				{
					LOGGER.error(e.getMessage());
				}
			}
		}
	}

	private static class DataSourceHolder  
	{
		private static final DataSource	INSTANCE	= new DataSource();
	}

	public static DataSource getInstance()
	{
		return DataSourceHolder.INSTANCE;
	}

	public BasicDataSource getBds()
	{
		return bds;
	}

	public void setBds(BasicDataSource bds)
	{
		this.bds = bds;
	}

}
