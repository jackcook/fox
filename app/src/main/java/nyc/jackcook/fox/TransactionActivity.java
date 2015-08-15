package nyc.jackcook.fox;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class TransactionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8400")));
    }
}
