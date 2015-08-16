package nyc.jackcook.fox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AuthenticateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.wallet_providers));

        Spinner spinner = (Spinner) findViewById(R.id.wallet_spinner);
        spinner.setAdapter(adapter);

        spinner.getBackground().setColorFilter(getResources().getColor(R.color.disabled), PorterDuff.Mode.SRC_IN);

        TextView continueButton = (TextView) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://default-environment-u3uxmxg5ju.elasticbeanstalk.com/cbauth"));
                startActivity(intent);
            }
        });
    }
}
