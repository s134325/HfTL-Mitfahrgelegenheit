package de.hftl.mize.model;

public class Vehicle
{
	public String	make;
	public String	model;
	public Integer	seats;
	public String	vehicleId;
	public String	userId;

	/**
	 * Constructor.
	 */
	public Vehicle()
	{
	}

	/**
	 * Constructor.
	 * 
	 * @param make
	 *            String
	 * @param model
	 *            String
	 * @param seats
	 *            Integer
	 * @param userId
	 *            String
	 */
	public Vehicle(String make, String model, Integer seats, String vehicleId,
			String userId)
	{
		super();
		this.make = make;
		this.model = model;
		this.seats = seats;
		this.vehicleId = vehicleId;
		this.userId = userId;
	}

	/**
	 * @return the make
	 */
	public String getMake()
	{
		return make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make)
	{
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model)
	{
		this.model = model;
	}

	/**
	 * @return the seats
	 */
	public Integer getSeats()
	{
		return seats;
	}

	/**
	 * @param seats
	 *            the seats to set
	 */
	public void setSeats(Integer seats)
	{
		this.seats = seats;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the vehicleId
	 */
	public String getVehicleId()
	{
		return vehicleId;
	}

	/**
	 * @param vehicleId
	 *            the vehicleId to set
	 */
	public void setVehicleId(String vehicleId)
	{
		this.vehicleId = vehicleId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Vehicle [make=" + make + ", model=" + model + ", seats="
				+ seats + ", userId=" + userId + "]";
	}

}
