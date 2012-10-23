package vikram.app.yamba;

import android.os.Bundle;

public class TweetCRUDActivity extends TweetBaseActivity{

	private static final String TAG = "TweetCRUDActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_crud_activity); 
    }
}
