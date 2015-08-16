package nyc.jackcook.fox.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.util.MaterialDrawerLayout;
import nyc.jackcook.fox.MainFragment;
import nyc.jackcook.fox.NavigationActivity;
import nyc.jackcook.fox.PayFragment;
import nyc.jackcook.fox.R;
import nyc.jackcook.fox.SellFragment;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private NavigationActivity nav;

    private static LayoutInflater inflater = null;

    public MainAdapter(Context context, NavigationActivity nav) {
        this.context = context;
        this.nav = nav;

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

            final TextView btcValue = (TextView) layout.findViewById(R.id.btc_value);
            final TextView usdValue = (TextView) layout.findViewById(R.id.usd_value);

            RequestManager.getBalance(new Runnable() {
                @Override
                public void run() {
                    btcValue.setText(RequestManager.btcBalance + " BTC");
                    usdValue.setText("$" + RequestManager.usdBalance + " USD");
                }
            });

            ImageButton switchButton = (ImageButton) layout.findViewById(R.id.switch_button);
            switchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence tmp = btcValue.getText();
                    btcValue.setText(usdValue.getText());
                    usdValue.setText(tmp);
                }
            });

            TextView details = (TextView) layout.findViewById(R.id.details_button);
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("FOX", "onClick");
                }
            });
        } else if (position == 1) {
            layout = (LinearLayout) inflater.inflate(R.layout.view_buttons_item, null);

            RelativeLayout pay_button = (RelativeLayout) layout.findViewById(R.id.pay_button);
            pay_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nav.setSection(nav.sections.get(3));
                    nav.setFragment(new PayFragment(), context.getResources().getString(R.string.menu_pay));
                }
            });

            RelativeLayout sell_button = (RelativeLayout) layout.findViewById(R.id.sell_button);
            sell_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nav.setSection(nav.sections.get(4));
                    nav.setFragment(new SellFragment(), context.getResources().getString(R.string.menu_sell));
                }
            });
        } else if (position == 2) {
            layout = (LinearLayout) inflater.inflate(R.layout.view_transactions_item, null);

            RecentTransactionsAdapter recentTransactionsAdapter = new RecentTransactionsAdapter(context);

            ListView recentTransactions = (ListView) layout.findViewById(R.id.recent_transactions_list);
            recentTransactions.setAdapter(recentTransactionsAdapter);
        }

        return layout;
    }
}
