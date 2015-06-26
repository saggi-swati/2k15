package org.android.dessert.adapter;

import java.util.ArrayList;
import java.util.Arrays;

import org.android.dessert.R;
import org.android.dessert.data.Place;
import org.android.dessert.ui.SearchActivity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * @author swati.rastogi
 * 
 * Adapter to use ImageLoader to do lazy loading of the image from url.
 *
 */
public class LocationDataAdapter extends RecyclerView.Adapter<ItemViewHolder> {
	
	private static final String TAG = LocationDataAdapter.class.getSimpleName();

	private Activity mActivity;
	private static LayoutInflater mInflater=null;
	public ImageLoader imageLoader; 

	private ArrayList<Place> mPlaceLists;

	public LocationDataAdapter(Activity activity, ArrayList<Place> placeList) {
		
		mActivity = activity;
		mInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPlaceLists = placeList;
		imageLoader = new ImageLoader(mActivity);
	}

	private class OnItemClickListener implements OnClickListener { 

		private int mPosition; 

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			
			Log.d(TAG, "--------- onCLick ---------------");
			
			SearchActivity sct = (SearchActivity)mActivity;
			
			if(mPlaceLists.get(mPosition).getmPhotoList() != null && mPlaceLists.get(mPosition).getmPhotoList().size() > 0) {
			
				sct.onItemClick(mPlaceLists.get(mPosition).getmPhotoList().get(0).getmPhotoReferences());
			} 
			else {
				Toast.makeText(mActivity, "No photo refrence available", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public int getItemCount() {
		return mPlaceLists.size();
	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position) {
		
		Place place = mPlaceLists.get(position);

		holder.placeAddress.setText(place.getmVicinity());	
		holder.placeName.setText(place.getmName());
		holder.placeTypes.setText(Arrays.toString(place.getmTypes()));

		if(place.ismIsOpen()) {

			holder.isOpen.setImageResource(R.drawable.yes);
		}
		else {
			holder.isOpen.setImageResource(R.drawable.no);
		}

		ImageView image = holder.placeIcon;

		imageLoader.DisplayImage(mPlaceLists.get(position).getmIcon(), image);


		holder.getmView().setOnClickListener(new OnItemClickListener(position));
		
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup view, int arg1) {
		
		ItemViewHolder holder = null;
		
		if(view == null) {
			
			view = (ViewGroup) mInflater.inflate(R.layout.row_layout, null);
			
			holder = new ItemViewHolder(view);
			view.setTag(holder);
		} 
		else {
			
			holder = (ItemViewHolder)view.getTag();
		}
		
		
		return null;
	}
}