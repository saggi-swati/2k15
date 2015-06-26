package org.android.dessert.webservices;

import java.util.ArrayList;

import org.android.dessert.data.Location;
import org.android.dessert.data.NearBy;
import org.android.dessert.data.Photo;
import org.android.dessert.data.Place;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


/**
 * 
 * @author swati.rastogi
 * 
 * 	{
   		"html_attributions" : [
      		"Listings by \u003ca href=\"http://www.indiacom.com/\"\u003eIndiacom Yellow Pages\u003c/a\u003e",
      		"Listings by \u003ca href=\"http://www.openrice.com/\"\u003eOpenRice\u003c/a\u003e"
   		],
   		"next_page_token" : "CoQC8gAAAHESNz_epCKN_UM3RkBlga7c1trlKay0x6JHRR5Orrf0nli9TN3rsYlkEmvh-56NqTCjXyl_nYi4DqCuhqTx5P1usON4aGYC2Gz8b2DXuTGJk4stuo_Xk84C0gSuP4q2kkj_PhhXp0wzhfz4dPixTYSbFfJ5YGN1ERg-0paqDKfbv1s51Vhytx8eGfqJdchvZLqQ-ogRvBtedTcTzjdBaU2J0Z7OvmuDJv7ILdR2qu0KWC4n8pMWd-TYI0YJ0MUgtttZaUUx_d61zBj5nlvxfBpk4x6kV6YYUJ6R0qmy47WtKENjE6Xka8M8Pu-yKDR5vmbwCLlhjZFKSLsk8NOyorQSEJ-UF2JXE2VPSLXdPq7sVAgaFD7oIw3kTPaYHUYKcEZestbhFRIl",
   		"results" : [
      		{
		         "geometry" : {
		            "location" : {
		               "lat" : 12.971599,
		               "lng" : 77.59456299999999
		            }
		         },
		         "icon" : "http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png",
		         "id" : "4c545f1c9e2324c75a2cd6faf49f04224dda53c5",
		         "name" : "Daily Bread",
		         "opening_hours" : {
		            "open_now" : true,
		            "weekday_text" : []
		         },
		         "photos" : [
		          	{
		               "height" : 920,
		               "html_attributions" : [ "From a Google User" ],
		               "photo_reference" : "CnRoAAAAWOhh8FWhMvbn3Hi3eQh7bjzfH4FQnFSkFz-w8Xfvw15pt3uefdOmARYbv7FCLjPMFBbr4aD2DEo6hLM80f6VrIdJQtAaDLvq5-NK0Pmtbz18xK5mB5eF6opts69qVIiQW8OKbtbrofqi2tZkX-Za9RIQfT0O6l2UHVVy7sYk5mk9oBoUCv7HiO51CiCZTi7XU4hMMHw-N9U",
		               "width" : 1632
		            }
		         ],
		         "place_id" : "ChIJZw87IU4UrjsRKYu5CX14cWo",
		         "reference" : "CmReAAAANIRYwu6V4EnZNCDc-agPv_7pr9r2P9P4QpD5cWrAY6Rk5Wrho_PzwBazb5xGoEOPl97blqyMwF4XKRAeNtnSjwnq0OYvbln55Jk9Hkm4Moa8g34sM0oVl5K1tseUhZAfEhCGipS8n2blusEaDFCIDnuNGhQpBNc_uC1rwjP9WHuVNw5Mo_6xGQ",
		         "scope" : "GOOGLE",
		         "types" : [ "bakery", "store", "food", "establishment" ],
		         "vicinity" : "Government Food Inia Pvt. Ltd., No. 671-672, Bumanali Industrial Area Basppa Lay Out, Bengaluru"
		      },

		      .....
      	]
      	"status" : "OK"
	}
 *
 */
public class JsonResponse {

	private static String TAG = JsonResponse.class.getSimpleName();

	/**
	 * Method to convert the jsonString to {@link NearBy} object
	 * 
	 * @param nearByString
	 * @return
	 * @throws JSONException
	 */
	public static NearBy createNearBy(String nearByString) throws JSONException {

		try {


			String status = null;

			JSONObject nearByJsonObject = new JSONObject(nearByString);

			status = nearByJsonObject.optString("status");

			if(status.equals("OK")) { 

				NearBy nearBy = new NearBy();

				nearBy.setmStatus(status);
				nearBy.setmNextPageToken(nearByJsonObject.optString("next_page_token"));

				JSONArray placeArray = nearByJsonObject.optJSONArray("results");

				ArrayList<Place> places = new ArrayList<Place>();

				if(placeArray != null) {

					for(int i = 0; i < placeArray.length(); i++) {

						Place place = new Place();

						JSONObject placeJsonObject = placeArray.optJSONObject(i);

						if(placeJsonObject != null) {

							place.setmIcon(placeJsonObject.optString("icon"));
							
							Log.d(TAG, " icon url : " + place.getmIcon());
							
							place.setmId(placeJsonObject.optString("id"));
							place.setmName(placeJsonObject.optString("name"));
							place.setmPlaceID(placeJsonObject.optString("place_id"));
							place.setmReference(placeJsonObject.optString("reference"));
							place.setmScope(placeJsonObject.optString("scope"));
							place.setmVicinity(placeJsonObject.optString("vicinity"));
							
							
							JSONObject openHoursObject = placeJsonObject.optJSONObject("opening_hours");
							
							if(openHoursObject != null) {
								
								place.setmIsOpen(openHoursObject.optBoolean("open_now"));
							} 
							else {
								
								place.setmIsOpen(false);
							}


							Location location = new Location();

							JSONObject locJsonObject = placeJsonObject.optJSONObject("geometry").optJSONObject("location");

							if(locJsonObject != null ) { 

								location.setmLat(locJsonObject.optDouble("lat"));
								location.setmLon(locJsonObject.optDouble("lng"));

							} else {
								Log.d(TAG, " i " + i + "location is null");
							}

							JSONArray photoJsonArray = placeJsonObject.optJSONArray("photos");

							if(photoJsonArray != null) {

								ArrayList<Photo> photos = new ArrayList<Photo>();

								for(int j=0; j<photoJsonArray.length(); j++) {

									Photo photo = new Photo();

									JSONObject photoJsonObject = photoJsonArray.optJSONObject(j);

									if(photoJsonObject != null) {

										photo.setmHeight(photoJsonObject.optInt("height"));
										photo.setmPhotoReferences(photoJsonObject.optString("photo_reference"));
										photo.setmWidth(photoJsonObject.optInt("width"));
									}

									photos.add(photo);
								}

								place.setmPhotoList(photos);
							}
							else {
								
								Log.d(TAG, " i " + i + "photoJsonArray is null");
							}

							JSONArray typeJsonArray = placeJsonObject.optJSONArray("types");

							if(typeJsonArray != null) {

								String type[] = new String[typeJsonArray.length()];

								for(int k=0; k < typeJsonArray.length(); k++ ) {

									type[k] = typeJsonArray.optString(k);
									
									Log.d(TAG, " i : " + i + " k value : " + type[k]);
								}

								place.setmTypes(type);
								
							} 
							else {
								Log.d(TAG, "typeJsonArray is null");
							}
						} else {
							Log.d(TAG, " i : " + i + "placeArray is null");
						}

						places.add(place);
					}
				}

				nearBy.setmPlaces(places);

				return nearBy;

			}

		}
		catch(Exception e) {

			Log.d(TAG, "Error occurred while parsing json " + e.getMessage());
		}

		Log.d(TAG, "Error occurred");

		return null;
	}	

}
