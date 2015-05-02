package de.hftl.mize.response;

import java.util.ArrayList;

import de.hftl.mize.model.Status;
import de.hftl.mize.model.Trip;

/**
 * Represents a response containing of a status and a list of {@link Trip}s,
 * sized >= 1
 * 
 * @author Tobias
 *
 */
public class TripResponse
{
	public ArrayList<Trip>	trips	= new ArrayList<Trip>();
	public Status			status;

	/**
	 * Constructor.
	 */
	public TripResponse()
	{
		super();
	}

	/**
	 * 
	 * Constructor.
	 * 
	 * @param trips
	 *            {@link ArrayList} of {@link Trip}
	 * @param status
	 *            {@link Status}
	 */
	public TripResponse(ArrayList<Trip> trips, Status status)
	{
		super();
		this.trips = trips;
		this.status = status;
	}

	/**
	 * @return the trips
	 */
	public ArrayList<Trip> getTrips()
	{
		return trips;
	}

	/**
	 * @param trip
	 *            the trip to set
	 */
	public void setTrip(Trip trip)
	{
		this.trips.add(trip);
	}

	/**
	 * @param trips
	 *            the trips to set
	 */
	public void setTrips(ArrayList<Trip> trips)
	{
		this.trips = trips;
	}

	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * @param trip
	 *            the trip to add
	 */
	public void addTrips(Trip trip)
	{
		this.trips.add(trip);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TripResponse [trips=" + trips + ", status=" + status + "]";
	}

}
