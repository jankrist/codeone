package cz.jankrist.codeone.view.fragment.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.jankrist.codeone.R;

/**
 * Created by jankristdev@gmail.com on 07.03.2017.
 */

public class HomeFragment extends NavigationTabFragment {

    @Override
    protected int getTitle() {
        return R.string.title_tab_home;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        return rootView;
    }
}
