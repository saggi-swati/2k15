package org.android.dessert.data;

import java.util.ArrayList;


/**
 * 
 * @author swati.rastogi
 * 
 *  {
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
 *
 */
public class Place {
	
	private Location mLocation = null;
	private String mIcon = null;
	private String mId = null;
	private String mName = null;
	private ArrayList<Photo> mPhotoList = null;
	
	private String mPlaceID = null;
	private String mReference = null;
	private String mScope = null;
	private String[] mTypes;
	private String mVicinity;
	
	private boolean mIsOpen;

	public boolean ismIsOpen() {
		return mIsOpen;
	}

	public void setmIsOpen(boolean mIsOpen) {
		this.mIsOpen = mIsOpen;
	}

	public Location getmLocation() {
		return mLocation;
	}

	public void setmLocation(Location mLocation) {
		this.mLocation = mLocation;
	}

	public String getmIcon() {
		return mIcon;
	}

	public void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public ArrayList<Photo> getmPhotoList() {
		return mPhotoList;
	}

	public void setmPhotoList(ArrayList<Photo> mPhotoList) {
		this.mPhotoList = mPhotoList;
	}

	public String getmPlaceID() {
		return mPlaceID;
	}

	public void setmPlaceID(String mPlaceID) {
		this.mPlaceID = mPlaceID;
	}

	public String getmReference() {
		return mReference;
	}

	public void setmReference(String mReference) {
		this.mReference = mReference;
	}

	public String getmScope() {
		return mScope;
	}

	public void setmScope(String mScope) {
		this.mScope = mScope;
	}

	public String[] getmTypes() {
		return mTypes;
	}

	public void setmTypes(String[] mTypes) {
		this.mTypes = mTypes;
	}

	public String getmVicinity() {
		return mVicinity;
	}

	public void setmVicinity(String mVicinity) {
		this.mVicinity = mVicinity;
	}

}
