package nyc.jackcook.fox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import nyc.jackcook.fox.util.RequestManager;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        RequestManager.context = getApplicationContext();

        String accessToken = RequestManager.accessToken();

        if (accessToken == null) {
            Intent intent = new Intent(LoadingActivity.this, AuthenticateActivity.class);
            startActivity(intent);
        } else {
            RequestManager.refreshTokens(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoadingActivity.this, NavigationActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
