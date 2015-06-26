package org.android.dessert;

import org.android.dessert.util.NetworkUtil;
import org.android.dessert.webservices.LoadImageAsync;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class ImageActivity extends Activity {

	private static final String TAG = ImageActivity.class.getSimpleName();

	private ImageView mImageView;

	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF startPoint = new PointF();
	PointF midPoint = new PointF();
	float oldDist = 1f;
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;	

	private String mPhotoReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.zoom);

		mImageView = (ImageView)findViewById(R.id.photo_iv);

		mImageView.setOnTouchListener(listener);

		Intent i = getIntent();

		if(i != null) {
			mPhotoReference = i.getStringExtra("Reference");

			Log.d(TAG, "photo reference is : " + mPhotoReference);


			if(NetworkUtil.isNetworkAvailable(this)) {

				if(mPhotoReference != null) {

					String url = SearchApplication.PHOTO_SEARCH_API_URL + "?maxwidth=400&photoreference="+mPhotoReference+"&key="+SearchApplication.API_KEY; 

					Log.d(TAG, "URL : "  + url);

					new LoadImageAsync(this, url).execute();
				}
				else {
					Toast.makeText(this, "Photo reference is invalid", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT).show();
			}
		}
		else {
			Toast.makeText(this, "intent is null ", Toast.LENGTH_SHORT).show();
		}

	}

	private View.OnTouchListener listener = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			ImageView view = (ImageView) v;
			System.out.println("matrix=" + savedMatrix.toString());
			
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			
			case MotionEvent.ACTION_DOWN:
				
				savedMatrix.set(matrix);
				startPoint.set(event.getX(), event.getY());
				mode = DRAG;
				break;
				
			case MotionEvent.ACTION_POINTER_DOWN:
				
				oldDist = spacing(event);
				
				if (oldDist > 10f) {
				
					savedMatrix.set(matrix);
					midPoint(midPoint, event);
					mode = ZOOM;
				}
				break;
				
			case MotionEvent.ACTION_UP:
				
			case MotionEvent.ACTION_POINTER_UP:
				
				mode = NONE;
				break;
				
			case MotionEvent.ACTION_MOVE:
				
				if (mode == DRAG) {
					
					matrix.set(savedMatrix);
					matrix.postTranslate(event.getX() - startPoint.x,event.getY() - startPoint.y);
					
				} else if (mode == ZOOM) {
					
					float newDist = spacing(event);
					
					if (newDist > 10f) {
						
						matrix.set(savedMatrix);
						float scale = newDist / oldDist;
						matrix.postScale(scale, scale, midPoint.x, midPoint.y);
					}
				}
				break;
			}
			view.setImageMatrix(matrix);
			return true;
		}

		@SuppressLint("FloatMath")
		private float spacing(MotionEvent event) {
			
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}

		private void midPoint(PointF point, MotionEvent event) {
			float x = event.getX(0) + event.getX(1);
			float y = event.getY(0) + event.getY(1);
			point.set(x / 2, y / 2);
		}
	};

}
