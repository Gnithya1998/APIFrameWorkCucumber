package resources;

public enum APIResources {

	addPlaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"), deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	private APIResources(String resouce) {

		this.resource = resouce;
	}
	
	public String getAPIResource() {
		
		return resource;
	}

}
