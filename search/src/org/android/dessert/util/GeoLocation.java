package org.android.dessert.util;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class GeoLocation {

	private static final String TAG = GeoLocation.class.getSimpleName();

	public static Address getAddressFromLocation(final String locationAddress, final Context context) {

		Geocoder geocoder = new Geocoder(context, Locale.getDefault());

		try {

			List<Address> addressList = geocoder.getFromLocationName(locationAddress, 1);

			if (addressList != null && addressList.size() > 0) {

				Address address = addressList.get(0);

				return address;

			}

		} catch (IOException e) {

			Log.e(TAG, "Exception occurred while fetching lat & lon", e);

		}

		return null;

	}
}