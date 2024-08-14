package resources;

import java.util.ArrayList;
import java.util.List;

import apiData.Location;
import apiData.Place;

public class TestDataBuilder {

	public Place addPlacedata(String name, String address, String language) {
		
		Place place = new Place();
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91)7397341648");
		place.setAddress(address);
		place.setWebsite("http://rahulshettyacademy.com");
		place.setLanguage(language);

		List<String> values = new ArrayList<>();
		values.add("doggoodies");
		values.add("shop");

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		place.setTypes(values);
		place.setLocation(location);
		return place;
	}
	
	public String getPlaceId(String placeId) {
		
		return "{\n    \"place_id\":\"" + placeId + "\"\n}";
	}
}
