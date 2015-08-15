package nyc.jackcook.fox.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import nyc.jackcook.fox.R;

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
        Log.d("FOX", "getView");
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.view_transaction, null);

        return layout;
    }
}
