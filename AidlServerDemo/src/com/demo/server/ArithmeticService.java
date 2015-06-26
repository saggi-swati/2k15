package com.demo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ArithmeticService extends Service {

	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	private final IRemote.Stub mBinder = new IRemote.Stub() {
		
		@Override
		public int add(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			return (a+b);
		}
	};
}
