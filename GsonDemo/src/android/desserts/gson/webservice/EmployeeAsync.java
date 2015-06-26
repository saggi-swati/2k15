package android.desserts.gson.webservice;

import java.io.Reader;

import android.desserts.gsondemo.data.Employees;
import android.os.AsyncTask;
import android.util.EventLogTags.Description;

import com.google.gson.GsonBuilder;

/**
 * 
 * @author swati.rastogi
 * 
 * {@link Description} async task to perform the call to fetch the data from url in a background thread.
 *
 */
public class EmployeeAsync extends AsyncTask<Void, Void, Employees> {
	
	String mURL;
	private IPostData mPostData = null;
	
	public EmployeeAsync(String url, IPostData postData) {

		mURL = url;
		mPostData = postData; 
	}
	
	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
	}
	

	@Override
	protected Employees doInBackground(Void... params) {
		
		Employees employees = null;
		Reader reader = OSI.getData(mURL);
		
		employees = new GsonBuilder().create().fromJson(reader, Employees.class);
		
		return employees;
	}

	@Override
	protected void onPostExecute(Employees result) {
		
		mPostData.postData(result);
	}

	public interface IPostData {
		
		public void postData(Employees employees);
	}
}
