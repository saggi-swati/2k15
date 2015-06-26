package org.android.dessert.adapter;

import org.android.dessert.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

	private View mView = null;
	
	protected ImageView placeIcon;
	protected ImageView isOpen;
	protected TextView placeName;
	protected TextView placeAddress;
	protected TextView placeTypes;
	
	public ItemViewHolder(View convertView) {
		super(convertView);
		
		placeAddress = (TextView)convertView.findViewById(R.id.place_address_tv);
		placeIcon = (ImageView)convertView.findViewById(R.id.place_icon_iv);
		placeName = (TextView)convertView.findViewById(R.id.place_name_tv);
		placeTypes = (TextView)convertView.findViewById(R.id.place_type_tv);
		isOpen = (ImageView)convertView.findViewById(R.id.open_now_iv);
		
	}

	public View getmView() {
		return mView;
	}

	public void setmView(View mView) {
		this.mView = mView;
	}
	
	
	
}
