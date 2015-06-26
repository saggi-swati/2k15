package org.android.dessert.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author swati.rastogi
 *
 */
public class NetworkUtil {

	/**
	 * checks the network is currently available or not.
	 * 
	 * @param context
	 * @return Whether the network is available or not
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

}
