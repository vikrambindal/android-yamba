package vikram.app.yamba.service;

import java.util.List;

import vikram.app.yamba.YambaApplication;
import winterwell.jtwitter.Twitter.Status;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {

	private static final String TAG = "UpdaterService";
	static final int DELAY = 60000;
	private Updater updater;
	private YambaApplication yambaApplication;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.updater = new Updater();
		yambaApplication = (YambaApplication)getApplication();
		Log.d(TAG, "onCreate()");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		this.updater.start();
		yambaApplication.setServiceRunning(true);
		Log.d(TAG, "Service Started");
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		yambaApplication.setServiceRunning(false);
		this.updater.interrupt();
		this.updater = null;
		Log.d(TAG, "Service Stopped");
	}
	
	class Updater extends Thread{
		
		List<Status> postedMsgs;
		
		@Override
		public void run() {
			while(yambaApplication.isServiceRunning()){
				Log.d(TAG, "Service Running");
				try{
					Log.d(TAG, "Service Ran");
					int newRecords = yambaApplication.getStatusUpdates();
					Log.d(TAG, "Found " + newRecords + " records");
					Thread.sleep(DELAY);
				}catch(InterruptedException e){
					yambaApplication.setServiceRunning(false);
				}				
			}
		}
	}
}