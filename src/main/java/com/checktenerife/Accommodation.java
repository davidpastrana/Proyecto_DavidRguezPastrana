/**
 * 
 */

package com.checktenerife;

import java.io.Serializable;

public class Accommodation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String type;
	private String category;
	private String place;
	private String address;
	private Integer postalCode;
	private Float xCoordinate;
	private Float yCoordinate;
	private Float altitude;
	private String municipality;
	private Integer availability;
	private Integer totalPlaces;
	private String phone;
	private String fax;
	private String email;
	private String link;

	public Accommodation() {
	}

	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	public Float getAltitude() {
		return altitude;
	}

	public Integer getAvailability() {
		return availability;
	}

	public String getCategory() {
		return category;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getLink() {
		return link;
	}

	public String getMunicipality() {
		return municipality;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getPlace() {
		return place;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public Integer getTotalPlaces() {
		return totalPlaces;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	public Float getxCoordinate() {
		return xCoordinate;
	}

	public Float getyCoordinate() {
		return yCoordinate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 
	 * @param municipality
	 */
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public void setTotalPlaces(Integer totalPlaces) {
		this.totalPlaces = totalPlaces;
	}

	public void setxCoordinate(Float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(Float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	void setType(String type) {
		this.type = type;
	}
}
