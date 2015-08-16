package nyc.jackcook.fox;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class NavigationActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection home = newSection(getResources().getString(R.string.menu_home), new MainFragment());
        MaterialSection transactions = newSection(getResources().getString(R.string.menu_transactions), new TransactionsFragment());
        MaterialSection charts = newSection(getResources().getString(R.string.menu_charts), new ChartsFragment());

        this.addSection(home);
        this.addSection(transactions);
        this.addSection(charts);
    }
}
