package de.hftl.mize.model;

public class Location
{

	public String		street;
	public String		streetNumber;
	public String		zipCode;
	public String		city;
	public Country		country;
	public Coordinate	geoCoordinate;

	public Location() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber
	 *            the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the geoCoordinate
	 */
	public Coordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	/**
	 * @param geoCoordinate
	 *            the geoCoordinate to set
	 */
	public void setGeoCoordinate(Coordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [street=" + street + ", streetNumber=" + streetNumber
				+ ", zipCode=" + zipCode + ", city=" + city + ", country="
				+ country + ", geoCoordinate=" + geoCoordinate + "]";
	}

}
