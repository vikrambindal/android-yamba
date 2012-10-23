package vikram.app.yamba;

import vikram.app.yamba.prefs.PrefsActivity;
import vikram.app.yamba.service.UpdaterService;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TweetBaseActivity extends Activity {

	private static final String TAG = "TweetBaseActivity";
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tweet_crud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    		case R.id.menu_settings:
    			Log.d(TAG, "starting Pref Activity");
    			startActivity(new Intent(this, PrefsActivity.class));
    			break;
    		case R.id.itemToggleService:
    			if(((YambaApplication)getApplication()).isServiceRunning()){
    				Log.d(TAG, "Stopping UpdaterService");
        			stopService(new Intent(this, UpdaterService.class));
    			}else{
    				Log.d(TAG, "Starting UpdaterService");
        			startService(new Intent(this, UpdaterService.class));
    			}
    			break;
    		case R.id.itemTweetCRUD:
    			Log.d(TAG, "Starting TweetCRUD");
    			startActivity(new Intent(this, TweetCRUDActivity.class));
    			break;
    		case R.id.itemTweetList:
    			Log.d(TAG, "Starting TweetList");
    			startActivity(new Intent(this, TweetListActivity.class));
    			break;
    	}
    	return true;
    }
    
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
    	MenuItem menuItem = menu.findItem(R.id.itemToggleService);
    	if(((YambaApplication)getApplication()).isServiceRunning()){
    		menuItem.setTitle(R.string.titleServiceStop);
    		menuItem.setIcon(android.R.drawable.ic_media_pause);
    	}else{
    		menuItem.setTitle(R.string.titleServiceStart);
    		menuItem.setIcon(android.R.drawable.ic_media_play);
    	}
    	return true;
    }
}
