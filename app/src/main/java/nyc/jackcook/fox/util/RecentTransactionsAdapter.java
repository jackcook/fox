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

import nyc.jackcook.fox.ChartsFragment;
import nyc.jackcook.fox.R;
import nyc.jackcook.fox.TransactionActivity;

public class RecentTransactionsAdapter extends BaseAdapter {

    private Context context;

    private static LayoutInflater inflater = null;

    public RecentTransactionsAdapter(Context context) {
        this.context = context;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 2;
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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.view_transaction, null);

        TextView details = (TextView) layout.findViewById(R.id.details_button);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transactionIntent = new Intent(context, TransactionActivity.class);
                context.startActivity(transactionIntent);
            }
        });

        return layout;
    }
}
