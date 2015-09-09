package de.hftl.mize.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a trip
 * 
 * @author tokilian
 *
 */
@XmlRootElement(name = "trip")
public class Trip
{

	public String			uuid;
	public Location			from;
	public Location			to;
	public String			startTime;
	public Integer			freeSeats;
	public String			description;
	public Double			price;
	public Boolean			active;
	public ArrayList<User>	participants;
	public String			createTime;
	public String			updateTime;

	/**
	 * Constructor.
	 */
	public Trip()
	{
	}

	/**
	 * Constructor.
	 * 
	 * @param from
	 *            {@link Location}
	 * @param to
	 *            {@link Location}
	 * @param startTime
	 *            String
	 * @param freeSeats
	 *            Integer
	 * @param description
	 *            String
	 * @param price
	 *            Double
	 * @param active
	 *            Boolean
	 */
	public Trip(Location from, Location to, String startTime,
			Integer freeSeats, String description, Double price, Boolean active)
	{
		super();
		this.from = from;
		this.to = to;
		this.startTime = startTime;
		this.freeSeats = freeSeats;
		this.description = description;
		this.price = price;
		this.active = active;
	}

	/**
	 * Constructor.
	 * 
	 * @param uuid
	 *            String
	 * @param from
	 *            {@link Location}
	 * @param to
	 *            {@link Location}
	 * @param startTime
	 *            String
	 * @param freeSeats
	 *            Integer
	 * @param description
	 *            String
	 * @param price
	 *            Double
	 * @param active
	 *            Boolean
	 * @param createTime
	 *            String
	 * @param updateTime
	 *            String
	 */
	public Trip(String uuid, Location from, Location to, String startTime,
			Integer freeSeats, String description, Double price,
			Boolean active, String createTime, String updateTime)
	{
		super();
		this.uuid = uuid;
		this.from = from;
		this.to = to;
		this.startTime = startTime;
		this.freeSeats = freeSeats;
		this.description = description;
		this.price = price;
		this.active = active;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/**
	 * @return the uuid
	 */

	public String getUuid()
	{
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	/**
	 * @return the from
	 */

	public Location getFrom()
	{
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */

	public void setFrom(Location from)
	{
		this.from = from;
	}

	/**
	 * @return the to
	 */

	public Location getTo()
	{
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */

	public void setTo(Location to)
	{
		this.to = to;
	}

	/**
	 * @return the startTime
	 */

	public String getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return the freeSeats
	 */

	public Integer getFreeSeats()
	{
		return freeSeats;
	}

	/**
	 * @param freeSeats
	 *            the freeSeats to set
	 */

	public void setFreeSeats(Integer freeSeats)
	{
		this.freeSeats = freeSeats;
	}

	/**
	 * @return the description
	 */

	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */

	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the price
	 */

	public Double getPrice()
	{
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */

	public void setPrice(Double price)
	{
		this.price = price;
	}

	/**
	 * @return the active
	 */

	public Boolean getActive()
	{
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */

	public void setActive(Boolean active)
	{
		this.active = active;
	}

	public ArrayList<User> getParticipants()
	{
		return participants;
	}

	public void setParticipants(ArrayList<User> participants)
	{
		this.participants = participants;
	}

	public void setParticipant(User participant)
	{
		this.participants.add(participant);
	}

	/**
	 * @return the createTime
	 */

	public String getCreateTime()
	{
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */

	public String getUpdateTime()
	{
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */

	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Trip [uuid=" + uuid + ", from=" + from + ", to=" + to
				+ ", startTime=" + startTime + ", freeSeats=" + freeSeats
				+ ", description=" + description + ", price=" + price
				+ ", active=" + active + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}