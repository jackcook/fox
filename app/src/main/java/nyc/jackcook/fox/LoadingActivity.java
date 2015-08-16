package nyc.jackcook.fox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String accessToken = PreferenceManager.getDefaultSharedPreferences(LoadingActivity.this).getString("access", null);

        if (accessToken == null) {
            Intent intent = new Intent(LoadingActivity.this, AuthenticateActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoadingActivity.this, NavigationActivity.class);
            startActivity(intent);
        }
    }
}
