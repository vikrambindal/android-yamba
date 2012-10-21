package vikram.app.yamba;

import java.util.List;

import vikram.app.yamba.db.YambaDAOImpl;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener {

	private static final String TAG = "YambaApplication";
	private Twitter twitter;
	private SharedPreferences sharedPreferences;
	private boolean serviceRunning;
	private YambaDAOImpl yambaDAOImpl;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		
	    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
	
        yambaDAOImpl = new YambaDAOImpl(this);
	}
	
	public synchronized Twitter getTwitter(){
		if(this.twitter == null){
			
			String uName = sharedPreferences.getString("username", "");
			String pwd = sharedPreferences.getString("password", "");
			String apiURL = sharedPreferences.getString("apiURL", "http://yamba.marakana.com/api");
			
			Log.d(TAG, "Username : "  + uName);
			Log.d(TAG, "Password : " + pwd);
			Log.d(TAG, "API: " + apiURL);
			
			if(!TextUtils.isEmpty(uName) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(apiURL)){
				this.twitter = new Twitter(uName, pwd);
				this.twitter.setAPIRootUrl(apiURL);
				
				Log.d(TAG, "Logged in to Twitter");
			}
		}
		
		return this.twitter;
	}

	public synchronized void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		Log.d(TAG, "Prefences have been changed so resetting it");
		this.twitter = null;
	}
	
	public synchronized int getStatusUpdates(){
		
		int numOfUpdates = 0;
		Twitter twitter = this.getTwitter();
		if(twitter == null){
			Log.d(TAG, "Twitter not initialized");
			return 0;
		}
		
		long latestRecordDBTimeStamp = yambaDAOImpl.getTimeStampOfLatestRecord();
		
		List<Status> statuses = twitter.getHomeTimeline();
		yambaDAOImpl.saveBulkMsgs(statuses);
		
		
		for (Status status : statuses) {
			if(latestRecordDBTimeStamp < status.createdAt.getTime()){
				numOfUpdates++;
			}
		}
		return numOfUpdates;
	}

	public boolean isServiceRunning() {
		return serviceRunning;
	}

	public void setServiceRunning(boolean serviceRunning) {
		this.serviceRunning = serviceRunning;
	}

	public YambaDAOImpl getYambaDAOImpl() {
		return yambaDAOImpl;
	}
	
}
