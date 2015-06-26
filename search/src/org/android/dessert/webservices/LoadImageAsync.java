package org.android.dessert.webservices;

import java.io.InputStream;
import java.net.URL;

import org.android.dessert.ImageActivity;
import org.android.dessert.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 
 * @author swati.rastogi
 *
 */
public class LoadImageAsync extends AsyncTask<Void, Void, Bitmap> {

	private Bitmap bitmap = null;
	private String mUrl = null;
	private ImageActivity mActivity;
	private ProgressBar bar = null;

	public LoadImageAsync(ImageActivity activity, String url) {
		mUrl = url;
		mActivity = activity;
		bar = (ProgressBar)mActivity.findViewById(R.id.image_loading);
	}

	protected void onPreExecute() {
		super.onPreExecute();
		if(bar != null) {
			bar.setVisibility(View.VISIBLE);
		}
	}

	protected Bitmap doInBackground(Void... params) {
		try {
			
			Log.d("LoadImageAsync", "url : " + mUrl);
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(mUrl).getContent());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	protected void onPostExecute(Bitmap image) {

		if(bar != null) {
			bar.setVisibility(View.GONE);
		}
		
		if(image != null) {
			
			((ImageView)mActivity.findViewById(R.id.photo_iv)).setImageBitmap(image);
			
		} else {
			Toast.makeText(mActivity, "Image is null after loading from list item", Toast.LENGTH_SHORT).show();
		}
	}
}
