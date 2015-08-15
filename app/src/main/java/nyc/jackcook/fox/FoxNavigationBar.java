package nyc.jackcook.fox;

import android.app.Fragment;
import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class FoxNavigationBar extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection section = newSection("Test", new Fragment());
        this.addSection(section);
    }
}
