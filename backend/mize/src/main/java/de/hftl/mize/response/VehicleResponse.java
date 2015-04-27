package de.hftl.mize.response;

import java.util.ArrayList;

import de.hftl.mize.model.Status;
import de.hftl.mize.model.Vehicle;

public class VehicleResponse {

	public ArrayList<Vehicle>	vehicles	= new ArrayList<Vehicle>();
	public Status				status;

	public VehicleResponse() {
	}

	/**
	 * @param vehicles
	 * @param status
	 */
	public VehicleResponse(ArrayList<Vehicle> vehicles, Status status) {
		this.vehicles = vehicles;
		this.status = status;
	}

	/**
	 * @return the vehicles
	 */
	public ArrayList<Vehicle> getVehicles()
	{
		return vehicles;
	}

	/**
	 * @param vehicles
	 *            the vehicles to set
	 */
	public void setVehicles(ArrayList<Vehicle> vehicles)
	{
		this.vehicles = vehicles;
	}

	/**
	 * @param vehicle
	 *            the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle)
	{
		this.vehicles.add(vehicle);
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
}
