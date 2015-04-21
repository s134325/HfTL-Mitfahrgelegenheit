package de.hftl.mize.model;

import java.util.UUID;

public class Trip
{
	public UUID		uuid;
	public Location	from;
	public Location	to;
	public String	startTime;
	public Integer	freeSeats;
	public String	description;
	public Double	price;
	public Boolean	active;
	public String	createTime;
	public String	updateTime;

	public Trip() {
	}

	public Trip(UUID uuid, Location from, Location to, String startTime,
			Integer freeSeats, String description, Double price,
			Boolean active, String createTime, String updateTime) {
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

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Location getFrom() {
		return from;
	}

	public void setFrom(Location from) {
		this.from = from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(Integer freeSeats) {
		this.freeSeats = freeSeats;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trip [uuid=" + uuid + ", from=" + from.toString() + ", to="
				+ to.toString() + ", startTime=" + startTime + ", freeSeats="
				+ freeSeats + ", description=" + description + ", price="
				+ price + ", active=" + active + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}