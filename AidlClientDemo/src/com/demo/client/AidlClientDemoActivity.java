package com.demo.client;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.server.IRemote;


public class AidlClientDemoActivity extends Activity implements OnClickListener{
	
	private final String TAG = AidlClientDemoActivity.class.getSimpleName();
	
	EditText mFirst,mSecond;
    Button mAdd,mSubtract,mClear;
    TextView mResultText;
    protected IRemote mService;
    ServiceConnection mServiceConnection;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mFirst = (EditText) findViewById(R.id.firstValue);
        mSecond = (EditText) findViewById(R.id.secondValue);
            mResultText = (TextView) findViewById(R.id.resultText);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(this);
 
        initConnection();
    }
 
    public void initConnection() {
    	mServiceConnection = new ServiceConnection() {

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mService = IRemote.Stub.asInterface((IBinder) service);
				Log.d(TAG, "Service Connectted");
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				
				mService = null;
				Log.d(TAG, "Service Disconnected");
			}
    		
    	};
    	
    	if(mService==null) {
    		Intent it = new Intent();
            it.setAction("com.demo.server.CALCULATOR");
            //binding to remote service
            bindService(it, mServiceConnection, Service.BIND_AUTO_CREATE);
    	}
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	if(mServiceConnection !=null)
    		unbindService(mServiceConnection);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add:
			int a = Integer.parseInt(mFirst.getText().toString());
            int b = Integer.parseInt(mSecond.getText().toString());
 
            try{
                mResultText.setText("Result -> Add ->"+mService.add(a,b));
                Log.d("IRemote", "Binding - Add operation");
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			break;

		default:
			break;
		}
	}
    
    
}