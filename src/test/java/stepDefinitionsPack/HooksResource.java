package stepDefinitionsPack;

import java.io.IOException;

import io.cucumber.java.Before;

public class HooksResource {

	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException {
		
		AddPlaceValidation add = new AddPlaceValidation();
		//String placeID = ;
		String name = "Mira";
		String address = "seoul, south korea";
		String language = "Korean-KR";
		
		if(AddPlaceValidation.place_id==null) {
			
			add.add_playload_for_the_map_api_with(name, address, language);
			add.user_perform_the_post_http_method_request("addPlaceAPI", "post");
			add.verify_place_id_maps_using_the_with(name, "getPlaceAPI");
		}
	}
}
