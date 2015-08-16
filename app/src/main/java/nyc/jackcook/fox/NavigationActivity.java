package nyc.jackcook.fox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import nyc.jackcook.fox.util.RequestManager;

public class NavigationActivity extends MaterialNavigationDrawer {

    public ArrayList<MaterialSection> sections;

    @Override
    public void init(Bundle savedInstanceState) {
        Bitmap cover_image = BitmapFactory.decodeResource(getResources(), R.drawable.cover_image);
        setDrawerHeaderImage(cover_image);

        Uri uri = getIntent().getData();

        if (uri != null) {
            String accessToken = uri.getQueryParameter("access");
            String refreshToken = uri.getQueryParameter("refresh");

            RequestManager.setAccessToken(accessToken);
            RequestManager.setRefreshToken(refreshToken);
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

        sections = new ArrayList<>();

        sections.add(home);
        sections.add(transactions);
        sections.add(charts);
        sections.add(pay);
        sections.add(sell);
    }

    @Override
    public void onBackPressed() {
    }
}
