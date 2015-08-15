package nyc.jackcook.fox.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import nyc.jackcook.fox.R;

public class TransactionsAdapter extends BaseAdapter {

    private Context context;

    private static LayoutInflater inflater = null;

    public TransactionsAdapter(Context context) {
        this.context = context;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 30;
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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.card_transaction, null);

        if (position == 0) {
            int padding = (int) (context.getResources().getDisplayMetrics().density * 16 + 0.5f); // 16dp

            layout.setPadding(padding, padding, padding, padding);
        }

        return layout;
    }
}
