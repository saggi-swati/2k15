package org.android.dessert.ui;

import java.util.ArrayList;

import org.android.dessert.R;
import org.android.dessert.adapter.ItemViewHolder;
import org.android.dessert.adapter.LocationDataAdapter;
import org.android.dessert.data.NearBy;
import org.android.dessert.data.Place;
import org.android.dessert.util.GeoLocation;
import org.android.dessert.util.NetworkUtil;
import org.android.dessert.webservices.NearByAsync;
import org.android.dessert.webservices.NextPageAsync;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


/**
 * 
 * @author swati.rastogi
 * 
 * This activity shows the search bar which uses googl search api to search for nearby hotels and display the same in the listview.
 *
 */
public class SearchActivity extends Activity {

	
	private SearchView mSearchView = null;
	private Button mSearchButton = null;
	private RecyclerView mPlaceList = null;
	private RecyclerView.Adapter<ItemViewHolder> mAdapter = null;
	private ArrayList<Place> mPlaces = new ArrayList<Place>();
	
	int pastVisiblesItems, visibleItemCount, totalItemCount;

	private LinearLayoutManager mLayoutManager = null;

	private double mLat = -1;
	private double mLon = -1;

	private NearBy cacheNearBy = null;

	private static final String TAG = SearchActivity.class.getSimpleName();


	private View.OnClickListener mSearchListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			String location = String.valueOf(mSearchView.getQuery());

			Log.d(TAG, "Location entered : " + location);

			if(NetworkUtil.isNetworkAvailable(SearchActivity.this)) {

				if(location.trim() != null) {

					Address address = GeoLocation.getAddressFromLocation(location,getApplicationContext());

					if(address != null) {

						mLat = address.getLatitude();
						mLon = address.getLongitude();

						Log.d(TAG, "latitude : " + mLat + " longitude : " + mLon );

						new NearByAsync(SearchActivity.this, mLat, mLon).execute();
					}
					else {

						showToast("Cannot find latitude and longitude");
					}
				} 
				else {

					showToast("Please enter a valid location");
				}
			}
			else {
				showToast("Nework Not available");
			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_search);

		init();
	}

	

	private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

			recyclerView.setLayoutManager(mLayoutManager);

			int visibleItemCount = mLayoutManager.getChildCount();
			int totalItemCount = mLayoutManager.getItemCount();
			int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();


			if ( cacheNearBy != null  && (visibleItemCount+pastVisiblesItems) >= totalItemCount) {
				Log.d(TAG, "end of the list is reached");

				String mNextPageToken = cacheNearBy.getmNextPageToken();

				if(mNextPageToken != null) {
					new NextPageAsync(SearchActivity.this, mLat, mLon, mNextPageToken).execute();
				}
				else {
					showToast("Done loading all the results");
				}

			}
		};

		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			// TODO Auto-generated method stub
			super.onScrollStateChanged(recyclerView, newState);
		}
	};

	/*@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {


		Log.d(TAG, "firstVisibleItem : " + firstVisibleItem + " visibleItemCount : " + visibleItemCount + " totalItemCount : " + totalItemCount);

		if(cacheNearBy != null && (firstVisibleItem + visibleItemCount) == totalItemCount) {

			Log.d(TAG, "end of the list is reached");

			String mNextPageToken = cacheNearBy.getmNextPageToken();

			if(mNextPageToken != null) {
				new NextPageAsync(SearchActivity.this, mLat, mLon, mNextPageToken).execute();
			}
			else {
				showToast("Done loading all the results");
			}
		}
	}*/



	/**
	 * Method to initialize the variables.
	 */
	private void init() {

		mSearchView = (SearchView) findViewById(R.id.search_view);
		mSearchButton = (Button) findViewById(R.id.search_button);

		mSearchButton.setOnClickListener(mSearchListener);

		mPlaceList = (RecyclerView)findViewById(R.id.search_lv);

		mLayoutManager = new LinearLayoutManager(SearchActivity.this);

		mPlaceList.setLayoutManager(mLayoutManager);

		mPlaceList.addOnScrollListener(onScrollListener);

	}

	private void showToast(String message) {

		Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();

	}

	public void updateView(NearBy nearBy) {

		if (nearBy != null) {

			cacheNearBy = nearBy;
			mPlaces = nearBy.getmPlaces();
			mAdapter = new LocationDataAdapter(this, mPlaces);

			mPlaceList.setAdapter(mAdapter);

		}

	}

	public void onItemClick(String photoReference) {

		if(photoReference != null ) {

			Intent intent = new Intent(this, ImageActivity.class);
			intent.putExtra("Reference", photoReference);

			startActivity(intent);
		} 
		else {
			showToast("No photo reference");
		}

	}

	public void updateDataSet(NearBy nearBy) {

		int previousSize = mPlaces.size();

		ArrayList<Place> mLoadItems = nearBy.getmPlaces();

		mPlaces.addAll(mLoadItems);

		mAdapter.notifyDataSetChanged();

		mPlaceList.scrollTo(0, previousSize);

		cacheNearBy.setmNextPageToken(nearBy.getmNextPageToken());

	}
}
