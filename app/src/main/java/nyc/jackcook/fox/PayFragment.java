package nyc.jackcook.fox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
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

public class PayFragment extends Fragment {

    private EditText addressText;
    private EditText amountText;
    private TextView continueButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);

        addressText = (EditText) view.findViewById(R.id.addressText);
        addressText.getBackground().setColorFilter(getResources().getColor(R.color.edittext_border), PorterDuff.Mode.SRC_IN);
        addressText.addTextChangedListener(new TextWatcher() {
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
                    Intent intent = new Intent(getActivity(), TransactionActivity.class);
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
            return (addressText.getText().charAt(0) == '1' || addressText.getText().charAt(0) == '3') && (addressText.getText().length() >= 26 && addressText.getText().length() <= 35) && (amountText.getText().length() > 0);
        } catch (Exception e) {
            return false;
        }
    }
}
