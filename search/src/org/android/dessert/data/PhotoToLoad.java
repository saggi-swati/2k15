package org.android.dessert.data;

import android.widget.ImageView;

/**
 * 
 * @author swati.rastogi
 * 
 * Class to store the url and corresponding imageview.
 *
 */
public class PhotoToLoad {
	public String url;
	public ImageView imageView;

	public PhotoToLoad(String u, ImageView i) {
		url = u;
		imageView = i;
	}
}