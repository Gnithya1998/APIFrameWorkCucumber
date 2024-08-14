Feature: API validations for maps

@addPlace
Scenario Outline: Add place in the map api and validate the status code
Given add playload for the map api with "<name>" "<address>" "<language>"
When user perform "addPlaceAPI" with "post" http method request
Then the api call got success with status code 200
And "status" in the response body is "OK"
And "scope" in the response body is "APP"
And verify place_id maps using the "<name>" with "getPlaceAPI"

Examples:

|	name	|	address					|	language		|
|	Mojak	| rainbow street	| English-EN	|
#|	Lojak	| sunshine street |	Spanish-SP	|

@deletePlace
Scenario: Delete place in the map api and validate the status code
Given delete place 
When user perform "deletePlaceAPI" with "post" http method request
#Then the api call got success with status code 200
#And "status" in the response body is "OK"