package nyc.jackcook.fox;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class NavigationActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        Uri uri = getIntent().getData();

        if (uri != null) {
            String accessToken = uri.getQueryParameter("access");
            String refreshToken = uri.getQueryParameter("refresh");

            PreferenceManager.getDefaultSharedPreferences(NavigationActivity.this).edit().putString("access", accessToken).commit();
            PreferenceManager.getDefaultSharedPreferences(NavigationActivity.this).edit().putString("refresh", refreshToken).commit();
        }


        MaterialSection home = newSection(getResources().getString(R.string.menu_home), new MainFragment());
        MaterialSection transactions = newSection(getResources().getString(R.string.menu_transactions), new TransactionsFragment());
        MaterialSection charts = newSection(getResources().getString(R.string.menu_charts), new ChartsFragment());
        MaterialSection pay = newSection(getResources().getString(R.string.menu_pay), new PayFragment());
        MaterialSection sell = newSection(getResources().getString(R.string.menu_sell), new SellFragment());

        this.addSection(home);
        this.addSection(transactions);
        this.addSection(charts);
        this.addDivisor();
        this.addSection(pay);
        this.addSection(sell);
    }
}
