package nyc.jackcook.fox.util;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.view_balance_item, null);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);

        CardView card = new CardView(context);
        card.setBackgroundColor(0xFFFFFFFF);
        card.setLayoutParams(params);

        layout.addView(card);

        return layout;
    }
}
