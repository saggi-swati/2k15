package org.android.dessert.webservices;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.util.Log;

/**
 * 
 * @author swati.rastogi
 * 
 */
public class OSI {

	public static final String TAG = OSI.class.getSimpleName();

	
	/**
	 * Method to perform a service reuest for the specified url.
	 * 
	 * 
	 * @param urlPath : service url.
	 * @return jsonObject - reposnse jsonobject
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 */
	public static JSONObject callHttpGet(String urlPath) throws IOException,ParseException, JSONException {

		JSONObject jsonResponseObj = null;
		String jsonResponse = null;
		
		HttpClient httpClient = new DefaultHttpClient(httpParams());

		Log.i(TAG, "------ SERVICE URL -------" + urlPath);
		
		HttpGet httpGet = new HttpGet(urlPath);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		Log.i(TAG, "------ SERVICE URL DONE-------" + urlPath);
		
		HttpEntity entity = httpResponse.getEntity();

		if (entity != null) {
			
			jsonResponse = EntityUtils.toString(entity, "UTF-8");
			jsonResponseObj =  new JSONObject(jsonResponse);
		}
		
		return jsonResponseObj;
	}

	
	
	/**
	 * Set the timeout of 30sec to httpparams
	 * @return
	 */
	private static HttpParams httpParams() {

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
		return httpParams;
	}
}
