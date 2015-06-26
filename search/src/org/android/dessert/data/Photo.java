package org.android.dessert.data;

/**
 * 
 * @author swati.rastogi
 * 
 * {
       "height" : 231,
       "html_attributions" : [],
       "photo_reference" : "CnRnAAAAaqdwSw7ASlxYrLEHdV0AVNYo3JqonqDLViFgYAoR6OiVR3J7gpIBXh-T9EfJHa_UPOXnldNg-SXInupOnDTA-QvZVh-0d2obuAbEU0XGyYmt-Muu-ZSt-LufeIiXY0WIbuiejh_3H1exNXnW8wMa7xIQTiQqiAkYeAeRLesJPbr8BRoUQ_8964uQZ9G-Kirtt3kq88d9sTA",
       "width" : 286
    }
 *
 */
public class Photo {
	
	private int mHeight;
	private int mWidth;
	private String mPhotoReferences;

	public int getmHeight() {
		return mHeight;
	}

	public void setmHeight(int mHeight) {
		this.mHeight = mHeight;
	}

	public int getmWidth() {
		return mWidth;
	}

	public void setmWidth(int mWidth) {
		this.mWidth = mWidth;
	}

	public String getmPhotoReferences() {
		return mPhotoReferences;
	}

	public void setmPhotoReferences(String mPhotoReferences) {
		this.mPhotoReferences = mPhotoReferences;
	}

}
