package nyc.jackcook.fox.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import nyc.jackcook.fox.R;
import nyc.jackcook.fox.TransactionActivity;

public class TransactionsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Transaction> transactions;

    private static LayoutInflater inflater = null;

    public TransactionsAdapter(Context context) {
        this.context = context;
        this.transactions = new ArrayList<>();

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateWithTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.transactions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaction transaction = transactions.get(position);

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.card_transaction, null);

        if (position == 0) {
            int padding = (int) (context.getResources().getDisplayMetrics().density * 16 + 0.5f); // 16dp

            layout.setPadding(padding, padding, padding, padding);
        }

        try {
            TextView dateLabel = (TextView) layout.findViewById(R.id.date_label);
            String date = transaction.date.substring(0, transaction.date.length() - 2);
            SimpleDateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date parsedDate = readFormat.parse(date);
            SimpleDateFormat writeFormat = new SimpleDateFormat("MMM dd, yyyy @ hh:mm a");
            dateLabel.setText(writeFormat.format(parsedDate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView addressLabel = (TextView) layout.findViewById(R.id.address_label);
        addressLabel.setText(transaction.recipient);

        TextView details = (TextView) layout.findViewById(R.id.details_button);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transactionIntent = new Intent(context, TransactionActivity.class);
                context.startActivity(transactionIntent);
            }
        });

        TextView gainLabel = (TextView) layout.findViewById(R.id.gain_label);
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        gainLabel.setText(df.format(transaction.btcAmount) + " BTC");

        return layout;
    }
}
