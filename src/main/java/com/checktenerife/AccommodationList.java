package com.checktenerife;

import java.util.ArrayList;
import java.util.List;

public class AccommodationList {

	private static List<Accommodation> list;

	public AccommodationList() {
		list = new ArrayList<Accommodation>();
	}

	public List<Accommodation> getAccommodation() {
		return list;
	}

	public void setAccommodation(Accommodation ac) {
		list.add(ac);
	}

}
