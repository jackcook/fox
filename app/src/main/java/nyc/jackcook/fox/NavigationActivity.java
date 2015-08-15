package nyc.jackcook.fox;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class NavigationActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection home = newSection("Home", new MainFragment());
        MaterialSection transactions = newSection("Transactions", new TransactionsFragment());

        this.addSection(home);
        this.addSection(transactions);
    }
}
