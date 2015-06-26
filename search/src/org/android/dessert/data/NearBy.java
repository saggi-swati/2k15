package org.android.dessert.data;

import java.util.ArrayList;

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
		      }
      	]
      	"status" : "OK"
	}
 *
 */
public class NearBy {

	private String mNextPageToken = null;
	private String mStatus = null;
	private ArrayList<Place> mPlaces = null;

	public String getmNextPageToken() {
		return mNextPageToken;
	}

	public void setmNextPageToken(String mNextPageToken) {
		this.mNextPageToken = mNextPageToken;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public ArrayList<Place> getmPlaces() {
		return mPlaces;
	}

	public void setmPlaces(ArrayList<Place> mPlaces) {
		this.mPlaces = mPlaces;
	}

}
