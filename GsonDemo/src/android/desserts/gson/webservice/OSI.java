package android.desserts.gson.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * 
 * @author swati.rastogi
 * 
 */
public class OSI {

	public static final String TAG = OSI.class.getSimpleName();

	/**
	 * 
	 * @param SERVER_URL
	 * @return reader object
	 */
	public static Reader getData(String SERVER_URL) {

		Reader reader=null;

		try {
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpPost httpPost = new HttpPost(SERVER_URL);
			
			HttpResponse response = httpClient.execute(httpPost);
			
			StatusLine statusLine = response.getStatusLine();
			
			if (statusLine.getStatusCode() == 200) {
				
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				reader = new InputStreamReader(content);
			} 
			else {
				
				Log.e(TAG + " error: ", "Server responded with status code: "+ statusLine.getStatusCode());
			}
			
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
			
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return reader;
	}
}
