package vikram.app.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class TweetCRUDActivity extends Activity {

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

    
}
