package nyc.jackcook.fox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class NavigationActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection section = newSection("Home", new MainFragment());
        this.addSection(section);
    }
}
