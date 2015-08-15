package nyc.jackcook.fox;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.RelativeLayout;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.neokree.materialnavigationdrawer.util.MaterialDrawerLayout;

public class NavigationActivity extends MaterialNavigationDrawer {

    private MainFragment mainFragment = null;
    private TransactionsFragment transactionsFragment = null;

    @Override
    public void init(Bundle savedInstanceState) {
//        MaterialSection home = newSection("Home", new MaterialSectionListener() {
//            @Override
//            public void onClick(MaterialSection materialSection) {
//                changeToolbarColor(Color.parseColor("#ff8400"), Color.parseColor("#eb7a00"));
//                setFragment(new MainFragment(), "Testing");
//            }
//        });

//        getToolbar().setTitleTextColor();

        MaterialSection home = newSection("Home", new MainFragment());
        MaterialSection transactions = newSection("Transactions", new TransactionsFragment());

        this.addSection(home);
        this.addSection(transactions);
    }
}
