package nyc.jackcook.fox;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nyc.jackcook.fox.util.RequestManager;

public class TransactionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8400")));
    }

    @Override
     public void onResume() {
        super.onResume();

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            String str = new String(msg.getRecords()[0].getPayload());
            String[] components = str.split(",");

            String address = components[0];
            String amt = components[1];
            final double amount = Double.parseDouble(components[1]);

            final ProgressDialog dialog = ProgressDialog.show(TransactionActivity.this, null, "Processing...");

            RequestManager.makeTransaction(address, amt, new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();

                    TextView receivedText = (TextView) findViewById(R.id.received_text);
                    receivedText.setText(getResources().getString(R.string.you_sent) + " " + amount + " BTC");

                    TextView receivedTextUSD = (TextView) findViewById(R.id.received_text_usd);
                    receivedTextUSD.setText("$" + round(amount * 260.25, 2) + " USD");

                    TextView timeText = (TextView) findViewById(R.id.time_text);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd @ HH:mm:ss");
                    Date date = new Date();
                    timeText.setText(dateFormat.format(date));

                    TextView confirmationsText = (TextView) findViewById(R.id.confirmations_text);
                    confirmationsText.setText("0");

                    TextView feeText = (TextView) findViewById(R.id.fee_text);

                    TextView statusText = (TextView) findViewById(R.id.status_text);
                    statusText.setText("Pending");
                }
            });
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
