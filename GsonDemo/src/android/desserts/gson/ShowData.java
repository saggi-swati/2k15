package android.desserts.gson;

import android.app.Activity;
import android.desserts.gson.webservice.EmployeeAsync;
import android.desserts.gson.webservice.EmployeeAsync.IPostData;
import android.desserts.gsondemo.data.Employee;
import android.desserts.gsondemo.data.Employees;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author swati.rastogi
 * 
 * Activity to trigger webservice call in oncreate
 *
 */
public class ShowData extends Activity implements IPostData{

	private static final String TAG = ShowData.class.getSimpleName();
	
	/**
	 * Url for dummy json data
	 */
	private static final String URL = "http://beta.json-generator.com/api/json/get/BK4z5cG";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new EmployeeAsync(URL, this).execute();
	}

	/**
	 * Api exposed by the async task to get the employees object
	 */
	@Override
	public void postData(Employees employees) {
		
		Log.d(TAG, "Employees : companyName : " + employees.getCompany());
		Log.d(TAG, "Employees : location : " + employees.getLocation());
		
		Log.d(TAG, "Employees : employee list size : " + employees.getEmployees().size());
		
		int len = employees.getEmployees().size();
		for(int i =0; i < len; i++) {
			Employee employee = employees.getEmployees().get(i);
			
			Log.d(TAG, "Employee : name : " + employee.getName());
			Log.d(TAG, "Employee : gender : " + employee.getGender());
			Log.d(TAG, "Employee : age : " + employee.getAge());
		}
	}
	
	
}
