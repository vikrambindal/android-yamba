package vikram.app.yamba;

import vikram.app.yamba.prefs.PrefsActivity;
import vikram.app.yamba.service.UpdaterService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TweetCRUDActivity extends Activity{

	private static final String TAG = "TweetCRUDActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_crud_activity); 
    }

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
    		case R.id.itemServiceStart:
    			Log.d(TAG, "starting UpdaterService");
    			startService(new Intent(this, UpdaterService.class));
    			break;
    		case R.id.itemServiceStop:
    			Log.d(TAG, "stopping UpdaterService");
    			stopService(new Intent(this, UpdaterService.class));
    			break;
    	}
    	return true;
    }

}
