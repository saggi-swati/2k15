package org.android.dessert.webservices;

import org.android.dessert.R;
import org.android.dessert.data.NearBy;
import org.android.dessert.ui.SearchActivity;
import org.android.dessert.ui.SearchApplication;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * 
 * @author swati.rastogi
 * 
 * Class to fetch the next page data based on the nextPageToken and update the UI.
 * 
 */
public class NearByAsync extends AsyncTask<Void, Void, NearBy> {

	private final static String TAG = NearByAsync.class.getSimpleName();
	
	private SearchActivity mActivity = null;
	private double mLat, mLon;
	
	
	ProgressBar bar = null;

	public NearByAsync(SearchActivity activity, double lat, double lon) {
		
		mActivity = activity;
		
		bar = (ProgressBar)mActivity.findViewById(R.id.loading);
		
		mLat = lat;
		mLon = lon;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		if(bar != null) {
			
			bar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected NearBy doInBackground(Void... params) {
		NearBy nearByResponse = null;

		try {

			
			JSONObject object = OSI.callHttpGet(SearchApplication.PLACE_SEARCH_API_URL + SearchApplication.OUTPUT_FORMAT + "?location=" + mLat + "," + mLon + "&radius="+ SearchApplication.RADIUS + "&types=food&key=" + SearchApplication.API_KEY);
			
			Log.i(TAG, "object " + object.toString());
			String nearByJsonResponse = object.toString();

			nearByResponse = JsonResponse
					.createNearBy(nearByJsonResponse);
			Log.i(TAG, " nearByResponse  =====  "
					+ nearByResponse);

		} catch (Exception exception) {

				exception.printStackTrace();
		}

		return nearByResponse;
	}

	/**
	 * On completion of the request, UI wil be updates with the data.
	 */
	@Override
	protected void onPostExecute(NearBy nearByData) {
		super.onPostExecute(nearByData);
		
		if(bar != null) {
			bar.setVisibility(View.GONE);
		}
		
		mActivity.updateView(nearByData);
			

	}

}