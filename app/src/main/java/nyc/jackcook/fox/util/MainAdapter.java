package nyc.jackcook.fox.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import nyc.jackcook.fox.R;

public class MainAdapter extends BaseAdapter {

    private Context context;

    private static LayoutInflater inflater = null;

    public MainAdapter(Context context) {
        this.context = context;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
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
        LinearLayout layout = null;

        if (position == 0) {
            layout = (LinearLayout) inflater.inflate(R.layout.view_balance_item, null);

            TextView details = (TextView) layout.findViewById(R.id.details_button);
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("FOX", "onClick");
                }
            });
        } else if (position == 1) {
            layout = (LinearLayout) inflater.inflate(R.layout.view_buttons_item, null);
        } else if (position == 2) {
            layout = (LinearLayout) inflater.inflate(R.layout.view_transactions_item, null);

            RecentTransactionsAdapter recentTransactionsAdapter = new RecentTransactionsAdapter(context);

            ListView recentTransactions = (ListView) layout.findViewById(R.id.recent_transactions_list);
            recentTransactions.setAdapter(recentTransactionsAdapter);
        }

        return layout;
    }
}
