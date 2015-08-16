package nyc.jackcook.fox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import nyc.jackcook.fox.util.MainAdapter;

public class SellFragment extends Fragment {

    private EditText amountText;
    private TextView continueButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell, container, false);

        amountText = (EditText) view.findViewById(R.id.amountText);
        amountText.getBackground().setColorFilter(getResources().getColor(R.color.edittext_border), PorterDuff.Mode.SRC_IN);
        amountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateContinueButton();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        continueButton = (TextView) view.findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canContinue()) {
                    Intent intent = new Intent(getActivity(), SellingActivity.class);
                    intent.putExtra("amount", amountText.getText().toString());
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void updateContinueButton() {
        if (canContinue()) {
            continueButton.setTextColor(getResources().getColor(R.color.main));
        } else {
            continueButton.setTextColor(getResources().getColor(R.color.disabled));
        }
    }

    private boolean canContinue() {
        try {
            return amountText.getText().length() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
