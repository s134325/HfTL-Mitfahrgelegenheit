package de.hftl.mize.model;

public class Coordinate
{
	public Double	latitude;
	public Double	longitude;

	/**
	 * 
	 */
	public Coordinate()
	{
	}

	/**
	 * Constructor.
	 * 
	 * @param latitude
	 *            Double
	 * @param longitude
	 *            Double
	 */
	public Coordinate(Double latitude, Double longitude)
	{
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Coordinate [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}

}
